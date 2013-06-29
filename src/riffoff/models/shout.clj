(ns riffoff.models.shout
  (:require [clojure.java.jdbc :as sql]))

(defn all []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from shouts order by id desc"]
      (into [] results))))

(defn random []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from shouts order by RANDOM() limit 1"]
      (into [] results))))

(defn create [shout]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/insert-values :shouts [:body] [shout])))

(defn vote [shout, direction]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/update-values
      :shouts
      ["id=?" (:id shout)]
      (if (> direction 0) {:upvotes (inc :upvotes)}))))
