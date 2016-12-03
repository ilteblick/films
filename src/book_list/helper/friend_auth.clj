(ns book-list.helper.friend-auth
  (:use book-list.constants.roles
        ring.util.json-response)
  (:require [cemerick.friend.workflows :as workflows]
            [cemerick.friend.credentials :as creds]
            [book_list.repositories.user])
  (:import [book_list.repositories.user UserRepository]))

(defn register [{:keys [username password email]}]
  (try
    (.Insert (UserRepository.) {:username username :password password :email email})
    (workflows/make-auth {:identity username :roles (get roles :user) :username username})
    (catch Exception e
      (.println System/out e)
      (json-response (str "Username address already in use")))))

(defn reg-workflow []
  (fn [{:keys [uri request-method params]}]
    (when (and (= uri "/register")
               (= request-method :post))
      (register params))))

(defn credential-fn []
  (fn [auth-map]
    (let [user (get (.FindUserByUsername (UserRepository.) (:username auth-map)) 0)]
      (if (not (= user nil))
        (if (creds/bcrypt-verify (:password auth-map) (:password user))
          {:identity (:id user) :roles (get roles :user) :user user})))))