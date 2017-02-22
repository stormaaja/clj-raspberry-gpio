(ns clj-raspberry-gpio.gpio)

(def low 0)
(def high 1)

(defn setup
  "Setup pin"
  [pin mode])

(defn input
  "Read pin value"
  [pin])

(defn output
  "Set pin state"
  [pin state])

(defn cleanup
  "Cleanup pin"
  [pin])

(defn cleanup-all
  "Cleanup all pins"
  [])