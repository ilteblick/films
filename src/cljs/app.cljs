(ns book-list.cljs.app
  (:require [reagent.core :as reagent]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]
            [ajax.core :refer [GET POST]]
            [containers.header.header :refer [header]]
            [containers.home-page.home-page :refer [home-page]]
            [containers.film-page.film :refer [film-page]]
            [containers.create-film-form.create-film-form :refer [create-film-form]]
            [bootstrap.socket :refer [socket handle-like-response film-response]]
            )

  (:import goog.History)
  )



(defn skelet [& children]
      [:div
       [header]
       [:div children]]
      )

(defn current-page []
  [:div [(session/get :current-page)]])


(secretary/defroute "/" []
                    (session/put! :current-page #'home-page))

(secretary/defroute "/film/create" []
                    (session/put! :current-page #'create-film-form))

(secretary/defroute "/film/:id" [id]
                    (session/put! :current-page #(film-page id)))


(defn mount-root []
  (reagent/render [skelet [current-page]]  (.getElementById js/document "app")))

(defn parseJSON [x]
  (.parse (.-JSON js/window) x))

(defn init-socket! []
  (reset! socket (js/WebSocket. "ws://localhost:3000/socket"))
  (doall
    (map #(aset @socket (first %) (second %))
         [["onopen" (fn [] (js/console.log "OPEN")  )]
          ["onclose" (fn [] (js/console.log "CLOSE"))]
          ["onerror" (fn [e] (js/console.log (str "ERROR:" e)))]
          ["onmessage" (fn [data]
                         (let [socket-request (parseJSON (.-data data))]
                                    (case (.-req socket-request)
                                      "like" (handle-like-response (.-data socket-request))
                                      "dislike" (handle-like-response (.-data socket-request))
                                      "users" (js/console.log (.-data socket-request))
                                      "films" (film-response (.-data socket-request))
                                      )
                                    )
                         )]]))
  )

(defn init! []
  (init-socket!)
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))