(ns timezone-converter.state
  (:require [reagent.core :as r]
            [timezone-converter.timezone :as timezone]))

(defonce state (r/atom {}))

(defn get-value-from-event [event]
  (-> event .-target .-value))

(defn get-on-change-fn [state-key]
  #(swap! state assoc state-key (get-value-from-event %)))

(defn get-current-validation-error
  [{:keys [timezone-from timezone-to input-time]}]
  (cond
    (nil? input-time)
    "Please select the input time"

    (not (timezone/validate-timezone timezone-from))
    "Please select an input timezone from the list"

    (not (timezone/validate-timezone timezone-to))
    "Please select an output timezone from the list"))

(def current-result
  (r/track
   (fn []
     (let [current-value @state]
       (or (get-current-validation-error current-value)
           (str "Converted time: " (timezone/convert-timezone current-value)))))))
