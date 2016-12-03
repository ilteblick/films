(ns containers.header.header
  (:require [reagent.core :as reagent]
            [containers.header.reducer :refer [creds-reducer]]
            [ajax.core :refer [GET POST]])
  )

(defn header []

    (reagent/create-class
      {
       :component-did-mount
       #(GET "/creds" {:handler (fn [response]
                                  (if (= {} response)
                                    (reset! creds-reducer "DAVAI LOGINSA")
                                    (reset! creds-reducer "ZALOGINEN"))
                                  )})
       :reagent-render
       (fn []
         [:div [:h1 @creds-reducer]]
         )
       })

  )
