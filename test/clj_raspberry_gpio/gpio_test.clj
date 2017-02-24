(ns clj-raspberry-gpio.gpio-test
  (:require [clojure.test :refer :all]
            [environ.core :refer [env]]
            [clojure.java.io :as io]
            [clj-raspberry-gpio.gpio :as gpio]))

(defn gpio-test-fixture [f]
  (io/make-parents (format "%s/export" (:gpio-path env)))
  (f)
  ()) ; todo: clean

(use-fixtures :once gpio-test-fixture)

(defn file-exists?
  [path]
  (.exists (io/file path)))

(defn create-pin-path
  [pin]
  (format "%s/gpio%d" (:gpio-path env) pin))


(deftest setup-in-test
  (testing "Test setting pin IN"
    (let [pin 4
          file (format "%s/direction" (create-pin-path pin))]
      (io/make-parents file)
      (gpio/setup pin gpio/in)
      (is (= (slurp (format "%s/export" (:gpio-path env))) "4"))
      (is (file-exists? file))
      (is (= (slurp file) "in"))
      (io/delete-file file))))

(deftest setup-out-test
  (testing "Test setting pin OUT"
    (let [pin 4
          file (format "%s/direction" (create-pin-path pin))]
      (io/make-parents file)
      (gpio/setup pin gpio/out)
      (is (= (slurp (format "%s/export" (:gpio-path env))) "4"))
      (is (file-exists? file))
      (is (= (slurp file) "out"))
      (io/delete-file file))))


(deftest setup-output
  (testing "Test setting pin state to high and low"
    (let [pin 7
          file (format "%s/value" (create-pin-path pin))]
      (io/make-parents file)
      (gpio/output pin gpio/high)
      (is (= (slurp file) "1"))
      (gpio/output pin gpio/low)
      (is (= (slurp file) "0"))
      (io/delete-file file))))

(deftest cleanup-test
  (testing "Test cleaning pin out"
    (let [pin 4
          file (format "%s/unexport" (:gpio-path env))]
      (gpio/cleanup pin)
      (is (= (slurp file) "4")))))