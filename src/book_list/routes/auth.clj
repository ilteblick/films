(ns book-list.routes.auth
  (:require [cemerick.friend :as friend]
            [book-list.views.layout :as layout]
            [book-list.views.contents :as contents]
            [book-list.reagent.init :refer [loading-page]])
  (:use [compojure.core]))

(defroutes auth-routes
           (GET "/register" [] (-> (layout/application "Регистрация" (contents/registration))))
           (GET "/authorization" [] (loading-page))
           (GET "/password-recovery" [] (layout/application "Восстановление пароля" (contents/password-recovery)))
           (friend/logout (ANY "/logout" request (ring.util.response/redirect "/"))))