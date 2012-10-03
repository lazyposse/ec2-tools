(ns cljsta.ec2.scratch
  (:use     [midje.sweet                                                    ]
            [clojure
             [pprint              :only [pprint pp                          ]]
             [repl                :only [doc find-doc apropos               ]]
             [inspector           :only [inspect-tree inspect inspect-table ]]]
            [clojure.java.javadoc :only [javadoc                            ]]
            [clojure.tools.trace  :only [trace deftrace trace-forms trace-ns
                                         untrace-ns trace-vars              ]]
            [table.core           :only [table                              ]])
  (:require [clojure
             [set                      :as set    ]
             [string                   :as str    ]
             [xml                      :as xml    ]
             [walk                     :as w      ]]
            [clojure.java
             [shell                    :as sh     ]
             [io                       :as io     ]]
            [clj-http.client           :as c      ]
            [clj-time.core             :as time   ]
            [clojure.data.codec.base64 :as b64    ]
            [clj-time.format           :as format ]
            [digest                    :as digest]))
