(defn find-multiples [limit]
  (->> (range limit)
       (filter #(or (zero? (mod % 3)) (zero? (mod % 5))))
       (reduce +)))

(println (find-multiples 1000))

