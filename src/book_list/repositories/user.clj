(ns book-list.repositories.user
  (:use korma.core
        book-list.repositories.protocol)
  (:require [cemerick.friend.credentials :as creds]))

(defentity user)

(defprotocol UserRepositoryProtocol
  (FindUserByUsername [this username]))

(defrecord UserRepository []
  UserRepositoryProtocol
  (FindUserByUsername [this username] (select user (where {:username username})))

  RepositoryProtocol
  (Insert [this entity]
    (insert user (values {:username (:username entity)
                          :email (:email entity)
                          :password (creds/hash-bcrypt (:password entity))}))))