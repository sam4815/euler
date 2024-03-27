(ns main
  (:require [clojure.string :as str]))

(def sudokus
  (->> (str/split (slurp "p096_sudoku.txt") #"\n")
       (partition 10)
       (map (fn [sudoku] (mapv (fn [line] (mapv #(parse-long (str %)) line)) (drop 1 sudoku))))))

(defn is-valid [nums]
  (let [non-zero (filter #(not= % 0) nums)]
    (= (count non-zero) (count (set non-zero)))))

(defn is-complete [nums]
  (= (count (set (filter #(not= % 0) nums))) 9))

(defn check-board [rows check-fn]
  (let [columns (map (fn [col] (map #(nth % col) rows)) (range 9))
        squares (apply concat (map (fn [parts] (map (fn [i] (flatten (map #(nth % i) parts))) (range 3)))
                                   (partition 3 (map #(partition 3 %) rows))))]
    (and (every? check-fn rows) (every? check-fn columns) (every? check-fn squares))))

(defn count-options [x y rows]
  (let [row (nth rows y) col (map #(nth % x) rows)]
    (count (set (concat row col)))))

(defn find-zero [rows]
  (let [cells (apply concat (map (fn [x] (map (fn [y] [x y (count-options x y rows)]) (range 9))) (range 9)))]
    (take 2 (last (sort-by last (filter (fn [[x y]] (zero? (nth (nth rows y) x))) cells))))))

(defn solve-sudoku [sudoku]
  (loop [queue [sudoku]]
    (let [solution (first queue)]
      (if (check-board solution is-complete) solution
        (let [[x y] (find-zero solution)]
          (recur (->> (range 1 10)
                      (map (fn [num] (assoc solution y (assoc (nth solution y) x num))))
                      (filter #(check-board % is-valid))
                      (#(concat % (rest queue))))))))))

(def solved (map solve-sudoku sudokus))
(println (reduce + (map #(parse-long (str/join (take 3 (first %)))) solved)))

