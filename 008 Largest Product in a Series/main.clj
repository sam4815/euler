(def nums (->> (slurp "number.txt")
               (mapv #(parse-long (str %)))))

(defn find-max-adjacent-product [nums]
  (loop [i 0 maximum 0]
    (if (>= i (- (count nums) 13)) maximum
      (let [product (reduce * (subvec nums i (+ i 13)))]
        (recur (inc i) (if (> product maximum) product maximum))))))

(println (find-max-adjacent-product nums))

