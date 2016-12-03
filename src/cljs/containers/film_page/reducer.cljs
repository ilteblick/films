(ns containers.film-page.reducer
  (:require [reagent.core :as reagent]
            )
  )

(def current-film-reducer (reagent.ratom/atom {}))
(def comments-reducer (reagent.ratom/atom []))
