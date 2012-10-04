(ns ec2-tools.hook
  (:use [robert.hooke :as h]))

(defn log-hook
  "A log decorator"
  [f & args]
  (println (format "\nfn   = %s\nargs = args\n" f args))
  (apply f args))
