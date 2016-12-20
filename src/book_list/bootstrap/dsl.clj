(ns book-list.bootstrap.dsl
  (:use book-list.repositories.protocol
        [book-list.repositories.films]
        )
  (:require [book-list.repositories.films :as film])
  (:import [book_list.repositories.films FilmsRepository])
  )

(def filmdao (film/->FilmsRepository))

(def cur-ns ((meta #'filmdao) :ns))

(defn run [string]
  (try
    (binding [*ns* cur-ns]
      (load-string string)
      )
    (catch Exception exception
      (str "unknown command"))))

(defn show [id]
    (let [film (.FindOne (filmdao) id)]
      (if film
        (str (apply str film))
        (str "no such film: " id)))
)

(defn show-all []
  (println "loooooooooooooooooooooooooooooooooooooooooool")
  (let [films (.FindAll (filmdao) )]
    (println (str ("FIlms:" films)))
    )
  )

(defn delete [id]
  (let [result (.Delete (filmdao) id)]
    (if result
      (str ("deleted" result))
      (str "error")
      )
    )
  )


