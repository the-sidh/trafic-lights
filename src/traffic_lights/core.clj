(ns traffic-lights.core)

(def green-light "GREEN")
(def yellow-light "YELLOW")
(def red-light "RED")
(def millis-multiplier 1000)

(def lights {green-light  {:next-light      yellow-light
                           :seconds-to-wait 10
                           }
             yellow-light {:next-light      red-light
                           :seconds-to-wait 1
                           }
             red-light    {:next-light      green-light
                           :seconds-to-wait 5
                           }
             }
  )

(defn next-light [current-light]
  (get-in lights [current-light :next-light])
  )

(defn seconds-for [light]
  (get-in lights [light :seconds-to-wait])
  )

(defn sleep [millis]
  (Thread/sleep millis)
  )

(defn wait-for [seconds]
  (sleep (* seconds millis-multiplier))
  )

(defn show [light]
  (println light)
  )

(defn change [current-light]
  (let [new-light (next-light current-light)]
    (show new-light)
    (wait-for (seconds-for new-light))
    new-light)
  )

(defn run
  ([light times limit]
   (if (< times limit)
     (run (change light) (inc times) limit)))
  ([light limit]
   (run light 0 limit))
  )

(defn -main [& args]
  (run green-light 30)
  )