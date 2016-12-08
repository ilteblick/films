(ns book-list.routes.films
  (:require
            [book-list.reagent.init :refer [loading-page]])
  (:use [compojure.core]
        book-list.service.films
        ring.util.json-response))

(defroutes films-routes
           (GET "/films" req (json-response (get-all-films)))
           (GET "/json/film/:id" [id] (json-response (get-film id)))
           (POST "/film/create" [data] (json-response (create-film data)))
           )