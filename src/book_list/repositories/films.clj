(ns book-list.repositories.films
  (:use korma.core
        book-list.repositories.protocol)
  (:require [cemerick.friend.credentials :as creds]))

(defentity film)

(defprotocol FilmsRepositoryProtocol
  (LikeFilm [this id])
  (DislikeFilm [this id])
  )

(defrecord FilmsRepository [] RepositoryProtocol
  (Insert [this entity]
    (insert film (values {:name (entity "name")
                          :year (entity "year")
                          :producer (entity "producer")
                          :rate 0
                          :avatar "lol"
                          })))
  (FindAll [this] (select film))
  (FindOne [this id] (get (select film (where {:id id})) 0 ))
  FilmsRepositoryProtocol
  (LikeFilm [this id] (exec-raw ["UPDATE films.film set film.rate = film.rate+1 WHERE film.id=?" [id]]))
  (DislikeFilm [this id] (exec-raw ["UPDATE films.film set film.rate = film.rate-1 WHERE film.id=?" [id]]))
  )