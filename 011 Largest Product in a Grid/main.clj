(ns main
  (:require [clojure.string :as str]))

(def grid (->> (str/split (slurp "grid.txt") #"\n")
               (mapv (fn [line] (mapv #(parse-long %) (str/split line #" "))))))

(defn find-max-product [matrix x-op y-op]
  (->> (for [x (range (count matrix)) y (range (count matrix))] [x y])
       (map (fn [[x y]] (map (fn [i] (nth (nth matrix (y-op y i) []) (x-op x i) 0)) (range 4))))
       (map (fn [nums] (reduce * nums)))
       (apply max)))

(println (apply max [(find-max-product grid + +)
                     (find-max-product grid - +)
                     (find-max-product grid (fn [x i] x) +)
                     (find-max-product grid + (fn [y i] y))]))

