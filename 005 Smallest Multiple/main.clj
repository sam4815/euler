(defn find-smallest-multiple [target]
  (let [nums (range 1 target)]
    (loop [n target]
      (if (every? #(zero? (mod n %)) nums) n (recur (+ n target))))))

(println (find-smallest-multiple 20))

