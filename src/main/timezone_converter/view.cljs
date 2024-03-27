(ns timezone-converter.view
  (:require [timezone-converter.state :as state]
            [timezone-converter.timezone :as timezone]))

(defn time-input []
  [:input {:on-change (state/get-on-change-fn :input-time)
           :type :datetime-local}])

(defn timezone-input [state-key]
  [:div {:style {:display "inline-block"}}
   [:input {:type :text
            :list :timezones
            :on-change (state/get-on-change-fn state-key)}]
   (into [:datalist {:id :timezones}]
         (map (partial vector :option) timezone/timezones))])

(def bottom-space-style {:style {:margin-bottom ".5em"}})

(defn form []
  [:div
   [:div bottom-space-style
    [:label "Input time: "]
    [time-input]]
   [:div bottom-space-style
    [:label "Input timezone: "]
    [timezone-input :timezone-from]]
   [:div bottom-space-style
    [:label "Output timezone: "]
    [timezone-input :timezone-to]]])
