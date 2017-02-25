(defproject clj-raspberry-gpio "0.1.1"
  :description "Simple Clojure library for using Raspberry Pi GPIO."
  :url "https://github.com/stormaaja/clj-raspberry-gpio"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                [environ "1.1.0"]]
  :plugins [[lein-environ "1.1.0"]]
  :profiles {:test {:env {:gpio-path "/tmp/gpio"}}})
