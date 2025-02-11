(ns bowling-game-spec
  (:require [speclj.core :refer [describe it should=]]
            [bowling-game :refer [score]]))

(describe "bowling game"
          (it "should be a gutter game"
              (should= 0 (score []))))