(ns clj-raspberry-gpio.gpio
  (require [environ.core :refer [env]]
              [clojure.string :as str]))

(def low 0)
(def high 1)

(def in "in")
(def out "out")

(def ^:private default-base-path "/sys/class/gpio")

(defn- detect-base-path
  []
  (if (:gpio-path env)
    (:gpio-path env)
    default-base-path))

(def ^:private base-path (detect-base-path))
(def ^:private setup-path (format "%s/export" base-path))
(def ^:private cleanup-path (format "%s/unexport" base-path))

(defn- pin-path
  [pin]
  (format
    "%s/gpio%d"
    base-path
    pin))

(defn- pin-setup-path
  [pin]
  (format
    "%s/direction"
    (pin-path pin)))

(defn- pin-state-path
  [pin]
  (format
    "%s/value"
    (pin-path pin)))

(defn setup
  "Setup pin"
  [pin mode]
  (spit setup-path pin)
  (spit (pin-setup-path pin) mode))

(defn input
  "Read pin value"
  [pin]
  (slurp (pin-path pin)))

(defn output
  "Set pin state"
  [pin state]
  (spit (pin-state-path pin) state))

(defn cleanup
  "Cleanup pin"
  [pin]
  (spit cleanup-path pin))

(defn- match-pin?
  [pin-dir]
  (not=
    (re-matches #"gpio(\d{1,2})" pin-dir)
    nil))

(defn- get-name
  [file]
  (.getName file))

(defn- list-pin-dirs
  []
  (filter
    match-pin?
    (map
      get-name
      (file-seq (clojure.java.io/file base-path)))))

(defn- parse-pin-number
  [pin-dir]
  (str/replace pin-dir #"gpio" ""))

(defn- list-pins
  []
  (map
    parse-pin-number
    (list-pin-dirs)))

(defn cleanup-all
  "Cleanup all pins"
  []
  (dorun (map cleanup (list-pins))))