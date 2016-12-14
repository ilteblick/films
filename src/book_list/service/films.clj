(ns book-list.service.films
  (:use [book-list.repositories.films])
  (:require   [bouncer.core :as b]
              [bouncer.validators :as v])
  (:import [book_list.repositories.films FilmsRepository])
  )

(defn get-all-films []
  (.FindAll (FilmsRepository.))
  )

(defn get-film [id]
  (.FindOne (FilmsRepository.) id)
  )

(defn like-film [id]
  (.LikeFilm (FilmsRepository.) id)
  )

(defn dislike-film [id]
  (.DislikeFilm (FilmsRepository.) id)
  )

(defn delete-film [id]
  (.Delete (FilmsRepository.) id)
  )

(defn validate-film-create [params]
  (first
    (b/validate
      params
      :name v/required
      :producer v/required
      :year v/required
      )))

(defn create-film [data]
  (let [film {:name (data "name") :producer (data "producer") :year (data "year")}]
    (let [errors (validate-film-create film)]
      (if errors
        (println "IDI NAHYI" (str errors))
        (.Insert (FilmsRepository.) data)
      )
      )
    )
  )