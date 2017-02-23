# clj-raspberry-gpio

Simple Clojure library for using Raspberry Pi GPIO.

## Install

### Leiningen/Boot

    [stormaaja/clj-rpio "0.1.0"]

## Usage

Require

  [clj-raspberry-gpio.gpio :as gpio]

Setup pin 4 as an input

    (gpio/setup 4 gpio/in)

Read value of pin 4

    (gpio/input 4)

Setup pin 17 as an output

    (gpio/setup 17 gpio/out)

Set state of pin 17

    (gpio/output 17 gpio/high)

    (gpio/output 17 gpio/low)

Cleanup

    (gpio/cleanup 4)

    (gpio/cleanup 17)

    (gpio/cleanup-all)

## Notes

You should run your application as an user with sufficient privileges (or with sudo).

Setup and input functions has side effects.

Currently pin numbers are in BCM.

This library is tested only with Raspberry Pi B and
[Raspbian Jessie](https://www.raspberrypi.org/downloads/raspbian/)

## License

Copyright © 2017 Matti Ahinko

Distributed under the MIT License.
