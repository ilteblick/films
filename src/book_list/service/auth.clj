(ns book-list.service.auth
  (:require [cemerick.friend.workflows :as workflows]
            [cemerick.friend.credentials :as creds]
            [book-list.service.protocols.auth]
            [book-list.models.users]
            [book-list.repositories.user]
            [cemerick.friend.workflows]
            [book-list.service.protocols.base])
  (:use
        book-list.constants.roles

        )
  (:import
    [book_list.models.users UserModel]
    [book_list.repositories.user UserRepository]
    (book_list.service.protocols.auth AuthServiceProtocol)
    (book_list.service.protocols.base BaseServiceProtocol))
  )


(def register-lock (atom #{}))

(defrecord AuthService []

  AuthServiceProtocol
  (Registration [this {:keys [username password email]}]
    (try
      (if (= (get @register-lock {:username username}) nil)
        ((fn []
           (swap! register-lock conj {:username username})
           (let [user (UserModel. nil username password "" email)]
             (.Insert (UserRepository.) user)
             (workflows/make-auth {:identity username :roles (get roles :user) :username username})
             (swap! register-lock disj {:username username}))
           (println @register-lock)
           ))
        (throw "User already is used"))
      (catch Exception e
        (swap! register-lock disj {:username username})
        (throw "User already is used"))))
  (Authorization [this]
    (fn [auth-map]
      (let [user (.FindUserByUsername (UserRepository.) (:username auth-map))]
        (if (not (= user nil))
          (if (creds/bcrypt-verify (:password auth-map) (:password user))
            {:identity (:id user) :roles (get roles :user) :user user})))))

  BaseServiceProtocol
  (GetItems [this]
    (.FindAll (UserRepository.))))