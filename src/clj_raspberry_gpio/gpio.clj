(ns clj-raspberry-gpio.gpio
  (:require [clojure.string :as str]))

(def low 0)
(def high 1)

(def in "in")
(def out "out")

(def -base-path "/sys/class/gpio")
(def -setup-path (format "%s/export" base-path))
(def -cleanup-path (format "%s/unexport" base-path))

(defn -pin-path
  [pin]
  (format
    "%s/gpio%d"
    base-path
    pin))

(defn -pin-setup-path
  [pin]
  (format
    "%s/direction"
    (-pin-path pin)))

(defn -pin-state-path
  [pin]
  (format
    "%s/value"
    (-pin-path pin)))

(defn setup
  "Setup pin"
  [pin mode]
  (spit -setup-path pin)
  (spit (-pin-setup-path pin) mode))

(defn input
  "Read pin value"
  [pin]
  (slurp (-pin-path pin)))

(defn output
  "Set pin state"
  [pin state]
  (spit (-pin-state-path pin) state))

(defn cleanup
  "Cleanup pin"
  [pin]
  (spit -cleanup-path pin))

(defn cleanup-all
  "Cleanup all pins"
  [])