(ns book-list.repositories.user
  (:use korma.core
        book-list.repositories.protocol)
  (:require [cemerick.friend.credentials :as creds]))

(defentity users)

(defprotocol UserRepositoryProtocol
  (FindUserByUsername [this username]))

(defrecord UserRepository []
  UserRepositoryProtocol
  (FindUserByUsername [this username] (select users (where {:username username})))

  RepositoryProtocol
  (Insert [this entity]
    (insert users (values {:username (:username entity)
                          :email (:email entity)
                          :password (creds/hash-bcrypt (:password entity))}))))