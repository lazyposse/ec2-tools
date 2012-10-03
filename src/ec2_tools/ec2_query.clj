(ns ec2-tools.ec2-query
  (:require [clj-http.client            :as c]
            [ec2-tools.ec2-authenticate :as a]))

(def ^{:private true
       :doc "The ec2 host"}
  ec2-host "ec2.amazonaws.com")

(def ^{:private true
       :doc "The main access to the ec2 web services."}
  url (str "https://" ec2-host))

(defn- log-dec
  "A log decorator"
  [f] (fn [& args] (println (str "\nfn   = " f
                                "\nargs = " args
                                "\n"))
        (apply f args)))

(defn amazon-query
  "Querying amazon's account"
  [method path & [opts]]
  ((log-dec c/request)
   (merge {:method     method
           :url        (str url \? (a/compute-url-parameters path))
           :accept     :xml
           :as         :xml}
          opts)))
