(ns book-list.reagent.init
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [myproject.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

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
   (include-css (if (env :dev) "/css/bootstrap.css" "/css/bootstrap.min.css"))
   (include-css (if (env :dev) "https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"))
   ;(include-js "https://code.jquery.com/jquery-2.1.1.min.js")
   (include-js "/js/jquery.js")
   ])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")
     (include-js "/js/bootstrap.js")
     (include-js "/js/jquery.js")
     ]))
