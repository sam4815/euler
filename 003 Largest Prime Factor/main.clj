(defn is-prime [number]
  (loop [n 2]
    (if (> n (/ number 2)) true
      (if (zero? (mod number n)) false (recur (inc n))))))

(defn find-largest-prime-multiple [number]
  (loop [n 10000]
    (if (and (zero? (mod number n)) (is-prime n)) n
      (recur (dec n)))))

(println (find-largest-prime-multiple 600851475143))

