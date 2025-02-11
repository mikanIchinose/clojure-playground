(ns main
  (:require [org.httpkit.server :as server]))

(defn handler [_]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "Hello World"})

(defn -main []
  (println "Server starting on port 3000...")
  (server/run-server handler {:port 3000}))
