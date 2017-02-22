(ns clj-raspberry-gpio.gpio)

(def low 0)
(def high 1)

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