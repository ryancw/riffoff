(ns riffoff.controllers.shouts
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [riffoff.views.shouts :as view]
            [riffoff.models.shout :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [shout]
  (when-not (str/blank? shout)
    (model/create shout))
  (ring/redirect "/"))

(defn face-off []
  (view/face-off (first (model/random)) (first (model/random))))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [shout] (create shout))
  (GET "/faceoff" [] (face-off)))
