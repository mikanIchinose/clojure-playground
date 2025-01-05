(ns ring-todo.core
  (:require [ring.adapter.jetty :as server]
            [ring.middleware.json :refer [wrap-json-response]]
            [compojure.core :refer [defroutes context GET]]
            [compojure.route :as route]
            [ring.util.response :as response]))

(defn home [req]
  (response/response "home"))

(def todo-list
  [{:title "朝ごはんを作る"}
   {:title "燃えるゴミを出す"}
   {:title "卵を買って帰る"}
   {:title "お風呂を洗う"}])

(defn todos [req]
  todo-list)

; (def routes
;   {"/" home
;    "/todos" todos})
;
; (defn match-route [uri]
;   (get routes uri))
;
; (defn handler [req]
;   (let [uri (:uri req)
;         maybe-fn (match-route uri)]
;     (if maybe-fn
;       (maybe-fn req)
;       (not-found))))

(defroutes handler
  (GET "/" req home)
  (GET "/todos" req todos)
  (route/not-found "Not Found"))

(def app
  (wrap-json-response handler))

(defonce server (atom nil))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'app {:port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))

