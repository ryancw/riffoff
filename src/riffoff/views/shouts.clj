(ns riffoff.views.shouts
  (:use [hiccup.core :only (h)]
        [hiccup.form :only (form-to label text-area submit-button)])
  (:require [riffoff.views.layout :as layout]))

(defn shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "shout" "What do you want to SHOUT?")
            (text-area "shout")
            (submit-button "SHOUT!"))])

(defn display-shouts [shouts]
  [:div {:class "shouts sixteen columns alpha omega"}
   (map
    (fn [shout] [:h2 {:class "shout"} (h (:body shout))])
    shouts)])

(defn index [shouts]
  (layout/common "SHOUTER"
                 (shout-form)
                 [:div {:class "clear"}]
                 (display-shouts shouts)))

(defn face-off [shout1 shout2]
  (layout/common "SHOUTER"
                 [:div {:class "clear"}]
                 [:h2 {:class "shout"} (str (h (:body shout1)) (:upvotes shout1) (:downvotes shout1))]
                 [:h2 {:class "shout"} (str (h (:body shout2)) (:upvotes shout1) (:downvotes shout1))]))
