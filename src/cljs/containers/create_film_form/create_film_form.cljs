(ns containers.create-film-form.create-film-form
  (:require [reagent.core :as reagent]
            [ajax.core :refer [GET POST]])
  )

(defn create-film-form []

  (reagent/create-class
    {
     :reagent-render
     (fn []
       [:div [:h1 "Form"]]
       )
     })

  )
