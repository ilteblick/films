(ns book-list.views.layout
  (:use [hiccup.page :only (html5 include-css include-js)]))

(defn application [title & content]
  (html5 {:lang "en"}
         [:head
          [:title title]
          (include-css "css/bootstrap.css")
          (include-css "css/site.css")
          (include-js "js/jquery.js")
          (include-js "js/bootstrap.js")

          [:body content]]))