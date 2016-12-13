(ns containers.film-page.film
  (:require [reagent.core :as reagent]
            [containers.film-page.reducer :refer [current-film-reducer comments-reducer]]
            [ajax.core :refer [GET POST json-response-format]]
            [bootstrap.socket :refer [like-film dislike-film create-film]]
  )
  )

(defn get-elem-value [id]
  (.-value (.getElementById js/document id))
  )

(defn send []
  (fn []
    (let [name (get-elem-value "name") ]
      (let [producer (get-elem-value "producer") ]
        (let [year (get-elem-value "year") ]
          (create-film 1)
          )
        )
      ))
  )

(defn lol []
    [:div
     [:div [:h1 "SUKA2122sasdasd3s1"]]

     [:div [:input {:id "name" :name "name"}]]

     [:div [:input {:id "producer" :name "producer"}]]
     [:div [:input {:id "year" :name "year"}]]
     [:div [:input {:type "button" :value "lol" :on-click #(let [name (get-elem-value "name") ]
                                                            (let [producer (get-elem-value "producer") ]
                                                              (let [year (get-elem-value "year") ]
                                                                (create-film {:name name :producer producer :year year})
                                                                )
                                                              )
                                                            )}]]
     ]
  )

(defn create-film-form []
  (reagent/create-class
    {

     :reagent-render
     (fn []
       [lol]
     )
     } )

  )

(defn comment-component [comment]
  (reagent/create-class
    {
     :reagent-render
     (fn []
       ^{:key (:id comment)} [:div
        [:h1 "Info: " (:info comment)]
        [:h1 "user: " (:User_id comment)]
        ])
     })
  )


(defn film-page [id]
  (reagent/create-class
    {
     :component-will-mount
     (fn []
       (GET (str "/json/film/" id) {
                                   :handler (fn [response] (reset! current-film-reducer response))
                                   :response-format (json-response-format {:keywords? true})
                                   })
       (GET (str "/json/comments/" id) {
                                    :handler (fn [response] (reset! comments-reducer response))
                                    :response-format (json-response-format {:keywords? true})
                                    })
       )
     :reagent-render
     (fn []
       [:div
        [:h1 (:name @current-film-reducer)]
        [:h1 (:producer @current-film-reducer)]
        [:h1 (:year @current-film-reducer)]
        [:h1 (:rate @current-film-reducer)]
        [:input {:type "button" :value "+" :on-click (like-film id)}]
        [:input {:type "button" :value "-" :on-click (dislike-film id)}]
        (for [comment @comments-reducer]
          [comment-component comment]
          )
        ]
       )
     })
  )