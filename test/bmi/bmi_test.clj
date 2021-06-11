(ns bmi.bmi-test
  (:use midje.sweet)
  (:use [bmi.bmi])
  (:require [clojure.test :refer :all]))

(facts "about traffic lights"
       (facts "about invalid values"
              (fact "given a BMI when it is less than zero should throw an exception"
                    (imc -1) => (throws IllegalArgumentException)
                    )
              (fact "given a BMI when it is greater than 60 should throw an exception"
                    (imc 61) => (throws IllegalArgumentException)
                    )
              )
       (facts "about severely-underweight"
              (fact "given a BMI, when it is in the range 0-17 should return severely-underweight"
                    (let [range-to-test (range 0 17)]
                               (map imc range-to-test ) => (repeat (count range-to-test) "severely-underweight")))
              (fact "given a BMI, when it is in the range 17-18 should return underweight"
                    (let [range-to-test (range 17 18)]
                      (map imc range-to-test ) => (repeat (count range-to-test) "underweight")))
              (fact "given a BMI, when it is in the range 18-25 should return normal"
                    (let [range-to-test (range 18 25)]
                      (map imc range-to-test ) => (repeat (count range-to-test) "normal")))
              (fact "given a BMI, when it is in the range 25-30 should return overweight"
                    (let [range-to-test (range 25 30)]
                      (map imc range-to-test ) => (repeat (count range-to-test) "overweight")))
              (fact "given a BMI, when it is in the range 30-35 should return severely-overweight"
                    (let [range-to-test (range 30 35)]
                      (map imc range-to-test ) => (repeat (count range-to-test) "severely-overweight")))
              (fact "given a BMI, when it is in the range 30-35 should return very-severely-overweight"
                    (let [range-to-test (range 35 60)]
                      (map imc range-to-test ) => (repeat (count range-to-test) "very-severely-overweight")))
              )
       )