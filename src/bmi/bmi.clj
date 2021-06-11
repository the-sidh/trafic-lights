(ns bmi.bmi)

(def categories
  [{:status "severely-underweight" :range (range 0 17)}
   {:status "underweight" :range (range 17 18)}
   {:status "normal" :range (range 18 25)}
   {:status "overweight" :range (range 25 30)}
   {:status "severely-overweight" :range (range 30 35)}
   {:status "very-severely-overweight" :range (range 35 60)}
   ]
  )


(defn- is-valid [value] (if (or (< value 0) (>= value 60))
                          (throw (IllegalArgumentException. "IMC must be in the range 0..60"))
                          ))

(defn imc [value]
  (is-valid value)
  (loop [category 0]
    (if (contains? (set (get (get categories category) :range)) value)
      (val (first (get categories category)))
      (recur (inc category)))
    )
  )

(defn -main [& args]
  (imc args)
  )