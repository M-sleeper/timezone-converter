(ns timezone-converter.timezone
  (:require [tick.core :as t]
            [tick.timezone]
            [tick.locale-en-us]
            ["@js-joda/core" :refer [ZoneId]]))

(def timezones (js->clj (.getAvailableZoneIds ZoneId)))

(defn validate-timezone [value]
  (contains? (set timezones) value))

(defn convert-timezone
  [{:keys [timezone-from timezone-to input-time]}]
  (t/format
    (t/formatter "dd/MM/YYYY HH:mm")
    (->
      ;; Create date-time from the string
      (t/date-time input-time)

      ;; Add the input timezone to the date-time
      (t/in timezone-from)

      ;; Convert to instant
      t/instant

      ;; Add the output timezone to the instant
      (t/in timezone-to))))
