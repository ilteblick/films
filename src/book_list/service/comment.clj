(ns book-list.service.comment

  (:use [book-list.repositories.comment])
  (:import [book_list.repositories.comment CommentRepository])
  )

(defn get-comments-by-film-id [id]
  (.getCommentByFilmId (CommentRepository.) id)
  )

