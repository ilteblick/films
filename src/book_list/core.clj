(ns book-list.core
  (:require [compojure.core :refer [defroutes GET ANY]]
            [ring.middleware.json :as middleware]
            [ring.adapter.jetty :as jetty]
            [cemerick.friend :as friend]
            [clojure.set :refer [rename-keys]]
            (cemerick.friend [workflows :as workflows])
            [book-list.helper [friend-auth :refer [credential-fn reg-workflow]]]
            [ring.middleware.reload :as reload]
            [org.httpkit.server :as kit]
            [book-list.service.auth]
            [ring.util.response :as response])
  (:use compojure.core

        compojure.handler
        ring.middleware.params
        ring.util.json-response
        carica.core
        book-list.bootstrap.db
        book-list.constants.roles
        book-list.routes.user
        book-list.routes.default
        book-list.routes.films
        book-list.routes.auth
        book-list.routes.comment

        )
  (:import (java.io BufferedWriter FileWriter)
           (book_list.service.auth AuthService))
  )




(defn simple-logging-middleware [app]
  (fn [req]
    (app req)
    ))

(defn app-routes []
  (routes
      comment-routes
      films-routes
      auth-routes
      default-routes

    ))


(def app
  (-> (app-routes)
      (friend/authenticate {:login-uri             "/authorization"
                            :redirect-on-auth?     false
                            :login-failure-handler (fn [request]
                                                     (if (not (= (get-in request [:headers "x-requested-with"]) nil))
                                                       (response/status (json-response {:error   "Login or password is incorrect"
                                                                                        :success "true"}) 401)))
                            :credential-fn         (.Authorization (AuthService.))
                            :workflows             [(workflows/interactive-form)
                                                    (reg-workflow)]
                            })
      site
      (middleware/wrap-json-body {:keywords? true})
      middleware/wrap-json-response
      simple-logging-middleware
      ))


(defn -main [& args]
  (kit/run-server (reload/wrap-reload app) {:port 3000}))