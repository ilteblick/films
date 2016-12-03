(ns book-list.routes.user
  (:require [book-list.views.layout :as layout]
            [book-list.views.contents :as contents]
            [compojure.route :as route]
            [cemerick.friend :as friend])
  (:use [compojure.core]
        [ring.util.json-response]
        [book-list.constants.roles]))

(defroutes user-all
           (GET "/all" request (json-response [])))

(defroutes user-routes
           (context "/user" request
             (friend/wrap-authorize user-all (get roles :user)))
           (GET "/req" request
             (let [name (or (get-in request [:params :name])
                            (get-in request [:body :name])
                            "John Doe")]
               {:status 200
                :body   {:name name
                         :desc (str "The name you sent to me was " name)}}))
           (GET "/settings" [] (layout/application "Настройки" (contents/settings)))
           (GET "/profile" [] (layout/application "Профиль" (contents/profile))))