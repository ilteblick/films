(ns containers.create-film-form.create-film-form
  (:require [reagent.core :as reagent]
            [ajax.core :refer [GET POST]]
            [bootstrap.socket :refer [create-film]])
  )

(defn get-elem-value [id]
  (.-value (.getElementById js/document id))
  )

(defn create-film-form []

  [:div
   [:h1 "Name"]
   [:div [:input {:id "name" :name "name"}]]
   [:h1 "Producer"]
   [:div [:input {:id "producer" :name "producer"}]]
   [:h1 "year"]
   [:div [:input {:id "year" :name "year"}]]
   [:div [:input {:type "button" :value "Create" :on-click #(let [name (get-elem-value "name") ]
                                                          (let [producer (get-elem-value "producer") ]
                                                            (let [year (get-elem-value "year") ]
                                                              (create-film {:name name :producer producer :year year})
                                                              )
                                                            )
                                                          )}]]
   ]
  )
