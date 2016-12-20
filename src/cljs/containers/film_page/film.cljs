(ns containers.film-page.film
  (:require [reagent.core :as reagent]
            [containers.film-page.reducer :refer [current-film-reducer comments-reducer]]
            [containers.header.reducer :refer [creds-reducer]]
            [ajax.core :refer [GET POST json-response-format]]
            [bootstrap.socket :refer [like-film dislike-film create-film]]
  )
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
        (if (= @creds-reducer "ZALOGINEN")
            [:div
             [:input {:type "button" :value "+" :on-click (like-film id)}]
             [:input {:type "button" :value "-" :on-click (dislike-film id)}]
             ]
          )

        (for [comment @comments-reducer]
          [comment-component comment]
          )
        ]
       )
     })
  )