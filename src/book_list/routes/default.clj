(ns book-list.routes.default
  (:require [book-list.views.layout :as layout]
            [book-list.views.contents :as contents]
            [compojure.route :as route]
            [hiccup.page :refer [include-js include-css html5]]
            [config.core :refer [env]])
  (:use compojure.core
    ring.util.json-response
        book-list.handlers.socket))


(def mount-target
  [:div#app
   [:h3 "ClojureScript has not been compiled!"]
   [:p "please run "
    [:b "lein figwheel"]
    " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
  ])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))

(defroutes default-routes
           (GET "/" [] (loading-page))
           (GET "/creds" req (json-response (-> req :session)))
           (GET "/socket" [] socket-handler)
           (route/resources "/")
           (ANY "*" [] (route/not-found (layout/application "Страница не найдена" (contents/not-found))))
           (GET "*" [] (loading-page)))