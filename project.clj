(defproject ec2-tools "0.1.0-SNAPSHOT"
  :description "Small ec2 lib to admin ec2 accounts."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev
             {:dependencies
              [[midje "1.4.0"]
               [table "0.3.1" :exclusions [com.datomic/datomic]]]}}
  :dependencies [[org.clojure/clojure    "1.4.0"]
                 [clj-http               "0.4.0"]
                 [clj-time               "0.4.4"]
                 [org.clojure/data.codec "0.1.0"]
		 [robert/hooke           "1.1.2"]])
