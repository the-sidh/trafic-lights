(ns traffic-lights.core)

(def millis-multiplier 1000)

(def lights {:green-light  {:next-light      :yellow-light
                           :seconds-to-wait 10
                           }
             :yellow-light {:next-light      :red-light
                           :seconds-to-wait 1
                           }
             :red-light    {:next-light      :green-light
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
  ([light total times]
   (if (< times total)
     (run (change light) total (inc times))))
  ([light total]
   (run light total 0))
  )

(defn -main [& args]
  (run :green-light 30)
  )