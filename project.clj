(defproject book_list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.5.0"]
                 [ring-server "0.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [ring-json-response "0.2.0"]
                 [ring/ring-defaults "0.2.1"]
                 [reagent "0.6.0"]
                 [reagent-forms "0.5.25"]
                 [reagent-utils "0.2.0"]
                 [com.domkm/silk "0.1.2"]
                 [kibu/pushy "0.3.6"]
                 [binaryage/devtools "0.8.3"]
                 [environ "1.1.0"]
                 [re-frame "0.8.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.5.1"]
                 [yogthos/config "0.8"]
                 [http-kit "2.2.0"]
                 [com.pupeno/free-form "0.4.1"]
                 [org.clojure/clojurescript "1.9.293"
                  :scope "provided"]
                 [secretary "1.2.3"]
                 [venantius/accountant "0.1.7"
                  :exclusions [org.clojure/tools.reader]]
                 [sonian/carica "1.1.0" :exclusions [[cheshire]]]
                 [korma "0.3.1"]
                 [mysql/mysql-connector-java "5.1.30"]
                 [fogus/ring-edn "0.2.0"]
                 [cljs-ajax "0.5.8"]
                 [bouncer "1.0.0"]
                 [com.cemerick/friend "0.2.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.0.2"]
            [lein-cljsbuild "1.1.1"]
            [lein-asset-minifier "0.2.7"
             :exclusions [org.clojure/clojure]]
            ]
  :ring {:handler book-list.core/app
         :uberwar-name "book_list.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "book_list.jar"

  :main book-list.core

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["resources" "target/cljsbuild"]

  :minify-assets
  {:assets
   {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild
  {:builds {:min
            {:source-paths ["src/cljs" "env/prod/cljs"]
             :compiler
                           {:output-to "target/cljsbuild/public/js/app.js"
                            :output-dir "target/uberjar"
                            :optimizations :advanced
                            :pretty-print  false}}
            :app
            {:source-paths ["src/cljs" "env/dev/cljs"]
             :compiler
                           {:main "myproject.dev"
                            :asset-path "/js/out"
                            :output-to "target/cljsbuild/public/js/app.js"
                            :output-dir "target/cljsbuild/public/js/out"
                            :source-map true
                            :optimizations :none
                            :pretty-print  true}}
            }
   }

  :figwheel
  {:http-server-root "public"
   :server-port 4000
   :nrepl-port 7002
   :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"
                      ]
   :css-dirs ["resources/public/css"]
   :ring-handler book-list.core}

  :profiles {:dev {:repl-options {:init-ns book_list.repl}

                   :dependencies [[ring/ring-mock "0.3.0"]
                                  [ring/ring-devel "1.5.0"]
                                  [prone "1.1.2"]
                                  [figwheel-sidecar "0.5.7"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [com.cemerick/piggieback "0.2.2-SNAPSHOT"]
                                  [pjstadig/humane-test-output "0.8.1"]
                                  ]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.5.7"]
                             ]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :env {:dev true}}

             :uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true}}
  )
