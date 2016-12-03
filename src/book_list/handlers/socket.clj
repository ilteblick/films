(ns book-list.handlers.socket
  (:require [org.httpkit.server :as kit]
                [cheshire.core :as json]
            [book-list.service.films :refer [like-film dislike-film get-film]])
  )



(def ^:private ws-conns (ref #{}))

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

(defn socket-handler [request]
  (kit/with-channel request channel
                    (dosync (alter ws-conns conj channel))
                    (kit/on-close channel (fn [status]
                                            (println "chanel closed " status)
                                            (alter ws-conns disj channel))
                                              )
                    (kit/on-receive channel (fn [data]
                                              (let [socket-request (json/parse-string data)]
                                                (case (socket-request "req")
                                                  "HYI" (doall (for [c @ws-conns]
                                                               (kit/send! c "Sam HYI")
                                                               ))
                                                  "like/film" (like (socket-request "id"))
                                                  "dislike/film" (dislike (socket-request "id"))
                                                  )))
                                              )
                    )
  )