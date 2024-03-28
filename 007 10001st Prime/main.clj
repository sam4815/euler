(defn is-prime [number]
  (loop [n 2]
    (if (> n (/ number 2)) true
      (if (zero? (mod number n)) false (recur (inc n))))))

(defn nth-prime [target]
  (loop [i 3 n 1]
    (if (= n target) (- i 2)
      (recur (+ i 2) (if (is-prime i) (inc n) n)))))

(println (nth-prime 10001))

