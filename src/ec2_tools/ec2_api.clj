(ns ec2-tools.ec2-api
  "The main entry to query your amazon account"
  (:require [ec2-tools.ec2-query :as q]
            [clojure.xml         :as x]
            [clojure.zip         :as z]))

(defn ls-region
  "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-query-DescribeRegions.html"
  [region]
  (q/amazon-query :get (str "Action=DescribeRegions&RegionName.1=" region)))

(defn ls-regions
  "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-query-DescribeRegions.html"
  []
  (q/amazon-query :get "Action=DescribeRegions"))

(defn ls-instances
  "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-query-DescribeInstances.html"
  []
  (q/amazon-query :get "Action=DescribeInstances"))

(comment
  (def regions (ls-regions))
  (def regions-parsed (-> regions :body (.getBytes) (java.io.ByteArrayInputStream.) (x/parse)))
  (for [x (xml-seq regions-parsed)]
    (:tag x))

  (def region-us-east-1 (ls-region "us-east-1"))
  (def instances (ls-instances)))
