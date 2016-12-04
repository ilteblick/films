(ns containers.home-page.home-page
  (:require [reagent.core :as reagent]
            [containers.home-page.reducer :refer [films-reducer ]]
              [ajax.core :refer [GET POST json-response-format]]
            [secretary.core :as secretary :include-macros true]
            [bootstrap.socket :refer [socket]]
            )
  )


(defn film-component [film]
    (reagent/create-class
      {
       :reagent-render
       (fn []
         ^{:key (:id film)} [:div
          [:h1 "Name: " (:name film)]
          [:a {:href (str "/film/" (:id film))} "about"]
          ])
       }

      )

  )

(defn home-page []
    (reagent/create-class
      {
       :component-will-mount
       #(GET "/films" {:handler (fn [response]
                                  (
                                    (reset! films-reducer response)))
                       :response-format (json-response-format {:keywords? true})
                       })


       :reagent-render
       (fn []

         [:div
          [:h1 "HOME PAGE"]
          (for [film @films-reducer]
            [film-component film]
            )

          ]
         )
       })
  )

