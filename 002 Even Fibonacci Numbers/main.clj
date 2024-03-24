(ns main)

(defn count-even-fib [limit]
  (loop [f1 1 f2 1 sum 0]
    (if (> f2 limit) sum
      (recur f2 (+ f1 f2) (if (zero? (mod f2 2)) (+ sum f2) sum)))))

(println (count-even-fib 4000000))

