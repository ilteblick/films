(ns containers.auth-form.auth-form
  (:require [reagent.core :as reagent]
            [containers.header.reducer :refer [creds-reducer]]
            [ajax.core :refer [GET POST json-response-format]])
  )

(defn get-elem-value [id]
  (.-value (.getElementById js/document id))
  )

(defn auth-form []
  [:div
   [:h1 "userame"]
   [:div [:input {:id "username" :name "username"}]]
   [:h1 "password"]
   [:div [:input {:id "password" :name "password"}]]
   [:div [:input {:type "button" :value "Login" :on-click #(let [username (get-elem-value "username") ]
                                                             (let [password (get-elem-value "password") ]
                                                               (POST "/authorization" {:headers {:X-Requested-With "XMLHttpRequest"}
                                                                                       :body (doto
                                                                                               (js/FormData.)
                                                                                               (.append "username" username)
                                                                                               (.append "password" password))
                                                                                       :handler (fn []
                                                                                                  (GET "/creds" {:handler (fn [response]
                                                                                                                            (js/console.log (:authentications (:cemerick.friend/identity response)) (str response))
                                                                                                                            (if (= {} response)
                                                                                                                              (reset! creds-reducer false)
                                                                                                                              (reset! creds-reducer "ZALOGINEN"))
                                                                                                                            )
                                                                                                                 :response-format (json-response-format {:keywords? true})
                                                                                                                 })
                                                                                                  )

                                                                                       })
                                                               )
                                                             )}]]
   ]
  )
