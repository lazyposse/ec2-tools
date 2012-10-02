(ns ec2-tools.ec2-api
  "The main entry to query your amazon account"
  (:require [ec2-tools.ec2-query :as q]))

(defn ls-region
  [region]
  (q/amazon-query :get (str "Action=DescribeRegions&RegionName.1=" region)))

(defn ls-regions
  []
  (q/amazon-query :get "Action=DescribeRegions"))

(comment
  (def regions (ls-regions))
  (-> regions :body)
  (def region-us-east-1 (ls-region "us-east-1")))
