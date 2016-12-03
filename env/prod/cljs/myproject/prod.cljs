(ns myproject.prod
  (:require [book-list.cljs.app :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
