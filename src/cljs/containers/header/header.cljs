(ns containers.header.header
  (:require [reagent.core :as reagent]
            [containers.header.reducer :refer [creds-reducer]]
            [containers.auth-form.auth-form :refer [auth-form]]
            [ajax.core :refer [GET POST]]

            )
  )

(defn header []

    (reagent/create-class
      {
       :component-did-mount
       #(GET "/creds" {:handler (fn [response]
                                  (if (= {} response)
                                    (reset! creds-reducer false)
                                    (reset! creds-reducer "ZALOGINEN"))
                                  )})
       :reagent-render
       (fn []
         (if (= @creds-reducer false)
           [:div [auth-form]]
           [:div [:h1 @creds-reducer]]
           )


         )
       })

  )
