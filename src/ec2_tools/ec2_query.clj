(ns ec2-tools.ec2-query
  "A namespace to permit the construction of amazon query"
  (:require [clj-http.client            :as c]
            [ec2-tools.ec2-authenticate :as a]))

(defn amazon-query
  "Querying amazon's account"
  [method params & [opts]]
  (c/request
   (merge {:method     method
           :url        (a/compute-url params)
           :accept     :xml
           :as         :xml}
          opts)))

(rh/add-hook #'amazon-query #'h/log-hook)
