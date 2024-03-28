(defn is-palindrome [number]
  (= (seq (str number)) (reverse (str number))))

(defn find-largest-palindrome-product [limit]
  (->> (for [i (range limit) j (range limit)] (* i j))
       sort
       (filter is-palindrome)
       last))

(println (find-largest-palindrome-product 1000))

