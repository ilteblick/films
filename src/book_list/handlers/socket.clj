(ns book-list.handlers.socket
  (:require [org.httpkit.server :as kit]
                [cheshire.core :as json]
            [book-list.service.films :refer [like-film dislike-film get-film create-film get-all-films delete-film]])
  )



(def ^:private ws-conns (ref #{}))

(def printer (agent nil))

(defn print [old new]
  (println new)
  )


(defn send-all [data]
  (doall (for [c @ws-conns]
           (kit/send! c (json/generate-string data))
           ))
  )

(defn like [id]
  (like-film id)
  (let [film (get-film id)]
    (send-all {:req "like" :data film})
    )
  )


(defn dislike [id]
  (dislike-film id)
  (let [film (get-film id)]
    (send-all {:req "dislike" :data film})
    )
  )

(defn create-film-handler [data]
  (create-film data)
  (let [films (get-all-films)]
    (send-all {:req "films" :data films}))
  )

(defn delete-film-handler [id]
  (delete-film id)
  (let [films (get-all-films)]
    (send-all {:req "films" :data films}))
  )


(defn socket-handler [request]
  (kit/with-channel request channel
                    (dosync (do (alter ws-conns conj channel) (println "chanel open") (println (count @ws-conns))))

                    (kit/on-close channel (fn [status]
                                            (println "chanel closed " status)
                                            (dosync (alter ws-conns disj channel))
                                            (send-all {:req "users" :data (count @ws-conns)})
                                            )
                                              )
                    (kit/on-receive channel (fn [data]
                                              (send-off printer print data)
                                              (let [socket-request (json/parse-string data)]
                                                (println socket-request)
                                                (case (socket-request "req")
                                                  "lol" (doall (for [c @ws-conns]
                                                               (kit/send! c "Sam lol")
                                                               ))
                                                  "like/film" (like (socket-request "id"))
                                                  "dislike/film" (dislike (socket-request "id"))
                                                  "delete/film" (delete-film-handler (socket-request "id"))
                                                  "create/film" (create-film-handler (socket-request "data"))
                                                  )))
                                              )
                    )
  )