(ns main
  (:require [clojure.math :as math]))

(defn find-triplet [target]
  (->> (for [a (range 400) b (range 400)] [a b (math/sqrt (+ (* a a) (* b b)))])
       (filter (fn [[a b c]] (and (< a b) (= (math/floor c) c) (= (+ a b (int c)) target))))
       first))

(let [[a b c] (find-triplet 1000)]
  (println (* a b (int c))))

