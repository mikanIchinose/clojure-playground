(ns gossiping-bus-drivers
  (:require
   [clojure.set :as set]))

;; spec
;; バスの運転手がn人いる
;; 各運転手は別々の循環ルートを担当している
;; 各運転手が持っているゴシップを全運転手に知らせるまでに必要なステップ数を求める
;; 運転手がゴシップを知らせるのは、同じ停留所に停車した時だけ
;;
;; example
;; Bob's route: [p,q,r]
;; Jim's route: [s,t,u,p]
;; t = 0: Bob = p, Jim = s
;; t = 1: Bob = q, Jim = t
;; t = 2: Bob = r, Jim = u
;; t = 3: Bob = p, Jim = p
;; ゴシップを共有するのに必要な最小時間は3

(defn make-driver [name route rumors]
  (assoc {} :name name :route (cycle route) :rumors rumors))

(defn move-driver [driver]
  (-> driver
      (update :route rest)))

(defn move-drivers [world]
  (map move-driver world))

(defn get-stops [world]
  (loop [world world
         stops {}]
    (if (empty? world)
      stops
      (let [driver (first world)
            stop (first (:route driver))
            stops (update stops stop conj driver)]
        (recur (rest world) stops)))))

(defn merge-rumors [drivers]
  (let [rumors (map :rumors drivers)
        all-rumors (apply set/union rumors)]
    (map #(assoc % :rumors all-rumors) drivers)))

(defn spread-rumors [world]
  (let [stop-with-drivers (get-stops world)
        drivers-by-stop (vals stop-with-drivers)]
    (flatten (map merge-rumors drivers-by-stop))))

(defn drive [world]
  (-> world
      move-drivers
      spread-rumors))

(defn drive-till-all-rumors-spread [world]
  (loop [world (drive world)
         time 1]
    (cond
      (> time 480) :never
      (apply = (map :rumors world)) time
      :else (recur (drive world) (inc time)))))