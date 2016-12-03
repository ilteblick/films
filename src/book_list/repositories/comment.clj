(ns book-list.repositories.comment
  (:use korma.core
        book-list.repositories.protocol)
  (:require [cemerick.friend.credentials :as creds]))

(defentity comment)

(defprotocol CommentRepositoryProtocol
  (getCommentByFilmId [this id]))

(defrecord CommentRepository [] CommentRepositoryProtocol
  (getCommentByFilmId [this id] (select comment (where {:film_id id})))
  )
