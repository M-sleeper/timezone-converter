(ns timezone-converter.app
  (:require [reagent.dom :as rd]
            [timezone-converter.state :as state]
            [timezone-converter.view :as view]))

(defn app []
  [:div
   [:h2 "Timezone converter"]
   [view/form]
   @state/current-result])

(defn ^:dev/after-load init []
  (rd/render [app] (.getElementById js/document "app")))
