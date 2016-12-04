(ns ^:figwheel-no-load myproject.dev
  (:require [book-list.cljs.app :as core]
            [figwheel.client :as figwheel :include-macros true]))

(enable-console-print!)

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:4000/figwheel-ws"
  :jsload-callback core/mount-root)

(core/init!)
