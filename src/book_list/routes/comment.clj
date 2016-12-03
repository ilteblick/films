(ns book-list.routes.comment
  (:require
    [book-list.reagent.init :refer [loading-page]])
  (:use [compojure.core]
        book-list.service.comment
        ring.util.json-response))

(defroutes comment-routes
           (GET "/json/comments/:id" [id] (json-response (get-comments-by-film-id id)))
           )
