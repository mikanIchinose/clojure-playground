(defproject hello-world "0.1.0-SNAPSHOT"
  :description "Simple Hello World HTTP server"
  :dependencies [[org.clojure/clojure "1.11.1"]
                [http-kit "2.7.0"]
                ]
  :profiles {:dev {:dependencies [[speclj "3.4.3"]]}}
  :plugins [[speclj "3.4.3"]]
  :test-paths ["spec"]
  :main main)
