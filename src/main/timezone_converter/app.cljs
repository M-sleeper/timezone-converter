(ns timezone-converter.app
  (:require [reagent.dom :as rd]
            [timezone-converter.state :as state]
            [timezone-converter.view :as view]))

(defn app []
  [:div
   [view/time-input]
   [view/timezone-input :timezone-from]
   [view/timezone-input :timezone-to]
   @state/current-result])

(defn ^:dev/after-load init []
  (rd/render [app] (.getElementById js/document "app")))
