(defn sieve [limit]
  (loop [primes (range 2 limit) n 2]
    (if (> n (Math/sqrt limit)) primes
      (recur (vec (filter #(or (= % n) (not= 0 (mod % n))) primes)) (inc n)))))

(println (reduce + (sieve 2000000)))

