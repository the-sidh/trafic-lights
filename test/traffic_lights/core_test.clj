(ns traffic-lights.core-test
  (:use midje.sweet)
  (:use [traffic-lights.core]))

(facts "about `traffic lights`"
       (facts "about `next lights`"
              (fact "given a green light, when asking for the next light should get yellow"
                    (next-light green-light) => yellow-light)
              (fact "given a yellow light, when asking for the next light should get red"
                    (next-light yellow-light) => red-light)
              (fact "given a red light, when asking for the next light should get green"
                    (next-light red-light) => green-light)
              )
       (facts "about `seconds to wait`"
              (fact "given a green light, when asked for its time to wait in seconds should get 10"
                    (seconds-for green-light) => 10)
              (fact "given a yellow light, when asked for its time to wait in seconds should get 1"
                    (seconds-for yellow-light) => 1)
              (fact "given a red light, when asked for its time to wait in seconds should get 5"
                    (seconds-for red-light) => 5)
              )
       (facts "about `wait`"
              (fact "given a time in seconds when wait is invoked should call Thread/sleep with the value in milliseconds"
                    (wait-for 10) => irrelevant
                    (provided (sleep 10000) => irrelevant :times 1)
                    )
              )
       (facts "about change"
              (fact "given a light when changing the light should change the light to the next and wait for the correct time"
                    (change green-light) => yellow-light
                    (provided (show yellow-light) => irrelevant :times 1)
                    (provided (next-light green-light) => yellow-light :times 1)
                    (provided (seconds-for yellow-light) => 1 :times 1)
                    (provided (wait-for 1) => irrelevant :times 1)
                    )
              )
       (facts "about traffic-lights"
              (fact "given a number when running should execute that number of times"
                    (run green-light 3) => irrelevant
                    (provided (change green-light) => green-light :times 3)
                    )
              )
       )
