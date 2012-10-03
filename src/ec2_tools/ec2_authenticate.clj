(ns ec2-tools.ec2-authenticate
  (:require [clojure.string            :as s     ]
            [clj-time.core             :as time  ]
            [clojure.data.codec.base64 :as b64   ]
            [clj-time.format           :as format]))

;; first load credentials (read a map inside the ~/.ec2-config/config.clj file)
;; here is an example of such a map (do respect the name):
;; (def aws-ec2-credentials {:aws-access-key-id    "your-public-access-key
;;                          :aws-secret-access-key "your-secret-access-key
(load-file (str (System/getProperty "user.home") "/.ec2-config/config.clj"))

(def ^{:private true
       :doc "The ec2 host"}
  aws-access-key-id (:aws-access-key-id aws-ec2-credentials))

(def ^{:private true
       :doc "The ec2 host"}
  aws-secret-access-key (:aws-secret-access-key aws-ec2-credentials))

;; ####################### FUNCTIONS

(defn url-encode
  "Interface to url encoding (to be able to change the implementation if need be."
  [s]
  (java.net.URLEncoder/encode s))

(defn now-in-ec2-format
  "Format the needed dates into the good format"
  [] (->> (time/now)
          (format/unparse (format/formatters :date-time-no-ms))))

(defn complete-url
  "Complete the initial url with the needed remaining ones."
  [partial-url & other-params]
  (apply conj partial-url other-params))

#_(complete-url {"Action" "toto", "Region" "2", "langua:ge" "fr"} ["abc;sklfj" "def;lsdfk"] ["1" "2"])

(defn- key-pair-url-encode
  "Url encode the key value pair and join them with the '='."
  [[k v :as kv]]
  (s/join \= (map url-encode kv)))

#_(map key-pair-url-encode [["Action" "toto"] ["Region+skfj" "1"] ["language" "fr:sdfsd"] ["Reg;ion" "2"]])

(defn get-query-parameters
  "The parameters needed to sign the full query."
  [params]
  (->> (complete-url params
                     ["Version"          "2012-08-15"]
                     ["AWSAccessKeyId"   aws-access-key-id]
                     ["Timestamp"        (now-in-ec2-format)]
                     ["SignatureVersion" "2"]
                     ["SignatureMethod"  "HmacSHA256"])
       sort
       (map key-pair-url-encode)
       (s/join \&)))

#_(get-query-parameters {"Action" "toto", "Region" "2", "langua:ge" "fr"})

(defn ec2-sign
  "Compute the Signature field to add to the query parameters."
  [params]
  (let [key-spec (javax.crypto.spec.SecretKeySpec. (.getBytes aws-secret-access-key) "HmacSHA256")
        mac (doto (javax.crypto.Mac/getInstance "HmacSHA256")
              (.init key-spec))]
    (->> params
         (.getBytes)
         (.doFinal mac) ;; sign with the private key
         (b64/encode)   ;; encode in base64
         (String.)
         (url-encode))))

#_(ec2-sign "Action=toto&Region=1&Region=2&langua:ge=fr")

(defn ec2-sign-params
  "This is the function that will create the string to sign the ec2 way:
 StringToSign = HTTPVerb + \n +
               ValueOfHostHeaderInLowercase + \n +
               HTTPRequestURI + \n +
               CanonicalizedQueryString <from the preceding step>"
  [params]
  (ec2-sign
   (s/join "\n" ["GET" ec2-host "/" params])))

(defn compute-url-parameters
  "The authentication for the ec2 api."
  [action]
  (let [params (get-query-parameters action)]
    (format "%s&Signature=%s" params (ec2-sign-params params))))
