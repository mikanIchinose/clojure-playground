(ns gossiping-bus-drivers-spec
  (:require [speclj.core :refer [describe it should=]]
            [gossiping-bus-drivers :refer [make-driver, get-stops, merge-rumors, drive, drive-till-all-rumors-spread]]))

(describe "ゴシップ好きなバス運転手"
          (it "バス運転手が1人の場合"
              (let [driver (make-driver "d1" [:s1] #{:r1})
                    world [driver]
                    new-world (drive world)]
                (should= 1 (count new-world))
                (should= :s1 (-> new-world first :route first))))

          (it "運転手は1人、停留所は2つ"
              (let [driver (make-driver "d1" [:s1 :s2] #{:r1})
                    world [driver]
                    new-world (drive world)]
                (should= 1 (count new-world))
                (should= :s2 (-> new-world first :route first))))

          (it "運転手は2人、停留所は複数"
              (let [driver1 (make-driver "d1" [:s1 :s2] #{:r1})
                    driver2 (make-driver "d2" [:s1 :s3 :s2] #{:r2})
                    world [driver1 driver2]
                    new-1 (drive world)
                    new-2 (drive new-1)]
                (should= 2 (count new-1))
                (should= :s2 (-> new-1 first :route first))
                (should= :s3 (-> new-1 second :route first))
                (should= 2 (count new-2))
                (should= :s1 (-> new-2 first :route first))
                (should= :s2 (-> new-2 second :route first))))
          (it "停留所を取得"
              (let [drivers #{{:name "d1" :route [:s1]}
                              {:name "d2" :route [:s1]}
                              {:name "d3" :route [:s2]}}]
                (should= {:s1 [{:name "d1" :route [:s1]}
                               {:name "d2" :route [:s1]}]
                          :s2 [{:name "d3" :route [:s2]}]}
                         (get-stops drivers))))
          (it "噂話をマージ"
              (should= [{:name "d1" :rumors #{:r1 :r2}}
                        {:name "d2" :rumors #{:r2 :r1}}]
                       (merge-rumors [{:name "d1" :rumors #{:r1}}
                                      {:name "d2" :rumors #{:r2}}])))

          (it "同じ停留所に止まったら噂話を共有"
              (let [d1 (make-driver "d1" [:s1 :s2] #{:r1})
                    d2 (make-driver "d2" [:s1 :s2] #{:r2})
                    world [d1 d2]
                    new-world (drive world)]
                (should= 2 (count new-world))
                (should= #{:r1 :r2} (-> new-world first :rumors))
                (should= #{:r2 :r1} (-> new-world second :rumors))))
          (it "受け入れテスト1"
              (let [world [(make-driver "d1" [3 1 2 3] #{1})
                           (make-driver "d2" [3 2 3 1] #{2})
                           (make-driver "d3" [4 2 3 4 5] #{3})]]
                (should= 6 (drive-till-all-rumors-spread world))))
          (it "受け入れテスト2"
              (let [world [(make-driver "d1" [2 1 2] #{1})
                           (make-driver "d2" [5 2 8] #{2})]]
                (should= :never (drive-till-all-rumors-spread world)))))
