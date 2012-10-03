(ns ec2-tools.ec2-authenticate-test
  (:use [ec2-tools.ec2-authenticate]
        [midje.sweet])
  (:require [clojure.string :as s]))

(fact "complete-url permits to aggregate 2 sources into 1 to construct the final url"
  (complete-url {"Action" "toto"
                 "Region" "2"
                 "langua:ge" "fr"}
                ["abc;sklfj" "def;lsdfk"] ["1" "2"])
  => {"1" "2", "abc;sklfj" "def;lsdfk", "Region" "2", "Action" "toto", "langua:ge" "fr"})

(fact "Some fact around key-pair-url-encode"
  (key-pair-url-encode ["Action" "toto"])        => "Action=toto"
  (key-pair-url-encode ["Region+skfj" "1"])      => "Region%2Bskfj=1"
  (key-pair-url-encode ["Region " "1"])          => "Region+=1"
  (key-pair-url-encode ["language " "fr:sdfsd"]) => "language+=fr%3Asdfsd")

(fact "get-query-parameters permits to generate the common part used for signing the request."
  (let [r (get-query-parameters {"Action" "toto", "Region" "2", "langua:ge" "fr"})
        s (s/split r #"&")]
    s => (contains "Action=toto"
                   "Region=2"
                   "SignatureMethod=HmacSHA256"
                   "SignatureVersion=2"
                   "langua%3Age=fr"
                   "Version=2012-08-15" :in-any-order :gaps-ok)))

