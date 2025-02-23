(ns bowling-game-spec
  (:require [speclj.core :refer [describe it should=]]
            [bowling-game :refer [score]]))

(describe "bowling game"
          (it "should be a gutter game"
              (should= 0 (score (repeat 20 0))))
          (it "is 20"
              (should= 20 (score (repeat 20 1))))
          (it "is one spare"
              (should= 24 (score (concat [5 5 7] (repeat 17 0)))))
          (it "is one strike"
              (should= 20 (score (concat [10 2 3] (repeat 16 0)))))
          (it "is a perfect game"
              (should= 300 (score (repeat 12 10)))))