(ns containers.home-page.reducer
  (:require [reagent.core :as reagent]
            )
  )


(def films-reducer (reagent.ratom/atom []))