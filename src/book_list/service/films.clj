(ns book-list.service.films
  (:use [book-list.repositories.films])
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