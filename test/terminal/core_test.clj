(ns terminal.core-test
  (:require [clojure.test :refer :all]
            [terminal.core :refer :all]))

(def test_carts  ["ABCDABAA", "CCCCCCC", "ABCD"])

(deftest check_carts
  (is (instance? clojure.lang.LazySeq (reduce scan [] (nth test_carts 0))) "Testing if the scan loads the items into a lazy seq")
  (is (= (get-total (nth test_carts 0) prices) 32.40) "Test case 1")
  (is (= (get-total (nth test_carts 1) prices) 7.25) "Test case 2")
  (is (= (get-total (nth test_carts 2) prices) 15.40)) "Test case 3")