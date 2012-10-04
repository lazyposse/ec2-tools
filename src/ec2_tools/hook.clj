(ns ec2-tools.hook
  "A namespace to develop and setup hook"
  (:require [robert.hooke        :as h]
            [ec2-tools.ec2-query :as q]))

;; ############### HOOK DEF

(defn log-hook
  "A log decorator"
  [f & args]
  (println (format "\nfn   = %s\nargs = args\n" f args))
  (apply f args))

;; ############### HOOK SETUP

(defn load-hook
  "Setup the needed hook on the ec2-tools desired functions"
  []
  (h/add-hook #'q/amazon-query #'log-hook))

(defn unload-hook
  "Remove the hooks on the ec2-tools functions"
  []
  (h/remove-hook #'q/amazon-query #'log-hook ))
