(ns bootstrap.socket
  (:require [containers.film-page.reducer :refer [current-film-reducer]])
  )


(def socket (atom nil))

(defn like-film [id]
  #(.send @socket (.stringify js/JSON (clj->js {:req "like/film" :id id})))
  )

(defn dislike-film [id]
  #(.send @socket (.stringify js/JSON (clj->js {:req "dislike/film" :id id})))
  )


(defn handle-like-response [film]
  (if (= (:id @current-film-reducer) (.-id film))
    (swap! current-film-reducer assoc :rate (.-rate film))
    )
  )