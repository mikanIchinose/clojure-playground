(ns prime-factor-spec
  (:require [speclj.core :refer [describe it should=]]
            [prime-factor :refer [prime-factors-of]]))

(describe "prime factors"
          (it "factors 1 into empty list"
              (should= [] (prime-factors-of 1)))
          (it "factors 2 into [2]"
              (should= [2] (prime-factors-of 2)))
          (it "factors 3 into [3]"
              (should= [3] (prime-factors-of 3)))
          (it "factors 4 into [2 2]"
              (should= [2 2] (prime-factors-of 4)))
          (it "factors 5 into [5]"
              (should= [5] (prime-factors-of 5)))
          (it "factors 6 into [2 3]"
              (should= [2 3] (prime-factors-of 6)))
          (it "factors 7 into [7]"
              (should= [7] (prime-factors-of 7)))
          (it "factors 8 into [2 2 2]"
              (should= [2 2 2] (prime-factors-of 8)))
          (it "factors 9 into [3 3]"
              (should= [3 3] (prime-factors-of 9)))
          (it "factors 10 into [2 5]"
              (should= [2 5] (prime-factors-of 10)))
          (it "factors 11 into [11]"
              (should= [11] (prime-factors-of 11)))
          (it "factors 12 into [2 2 3]"
              (should= [2 2 3] (prime-factors-of 12))))
