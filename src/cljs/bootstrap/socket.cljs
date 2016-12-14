(ns bootstrap.socket
  (:require [containers.film-page.reducer :refer [current-film-reducer]]
            [containers.home-page.reducer :refer [films-reducer]]
            )
  )


(def socket (atom nil))

(defn like-film [id]
  (js/console.log (.stringify js/JSON (clj->js {:req "like/film" :id id})))
  #(.send @socket (.stringify js/JSON (clj->js {:req "like/film" :id id})))
  )

(defn dislike-film [id]
  #(.send @socket (.stringify js/JSON (clj->js {:req "dislike/film" :id id})))
  )

(defn delete-film [id]
  #(.send @socket (.stringify js/JSON (clj->js {:req "delete/film" :id id})))
  )


(defn handle-like-response [film]
  (if (= (:id @current-film-reducer) (.-id film))
    (swap! current-film-reducer assoc :rate (.-rate film))
    )
  )

(defn jsx->clj
  [x]
  (into []
        (for
          [k (.keys js/Object x)]
          (let [item (get-in x k)]
            (into {}
                  (for [key (.keys js/Object item)]
                    [(keyword key) (aget item key)]
                    )
                  )
        ))))

(defn create-film [data]
  (.send @socket (.stringify js/JSON (clj->js {:req "create/film" :data data})))
  )

(defn handle-create-film-response [films]
  (reset! films-reducer (jsx->clj films))
  )