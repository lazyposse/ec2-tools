(ns ec2-tools.hook
  "A namespace to develop and setup hook"
  (:require [robert.hooke :as h]))

;; ############### HOOK DEF

(defn log-hook
  "A log decorator"
  [f & args]
  (println (format "\nfn   = %s\nargs = %s\n" f args))
  (apply f args))

;; ############### HOOK SETUP

(defn load-hook
  "Setup the needed hook on the ec2-tools desired functions"
  [& fns]
  (map #(h/add-hook % #'log-hook) fns))

(defn unload-hook
  "Remove the hooks on the ec2-tools functions"
  [& fns]
  (map #(h/remove-hook % #'log-hook) fns))
