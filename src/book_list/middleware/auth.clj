(ns book-list.middleware.auth
  (:require [cemerick.friend :as friend]
            [book_list.services.auth]
            [ring.util.response :as response])
  (:use ring.util.json-response)
  (:import (book_list.services.auth AuthService)))

(defn unauthorized [app]
  (fn [request]
    (if (= (friend/current-authentication) nil)
      (app)
      (response/redirect "/"))))

(defn authorized [app]
  (fn [request]
    (if (= (friend/current-authentication) nil)
      (response/redirect "/authorization")
      (app))))

(defn registration-middleware []
  (fn [{:keys [uri request-method params]}]
    (when (and (= uri "/register")
               (= request-method :post))
      (.Registration (AuthService.) params))))