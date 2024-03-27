(ns timezone-converter.view
  (:require [timezone-converter.state :as state]
            [timezone-converter.timezone :as timezone]))

(defn time-input []
  [:input {:on-change (state/get-on-change-fn :input-time)
           :type :datetime-local}])

(defn timezone-input [state-key]
  [:div
   [:input {:type :text
            :list :timezones
            :on-change (state/get-on-change-fn state-key)}]
   (into [:datalist {:id :timezones}]
         (map (partial vector :option) timezone/timezones))])
