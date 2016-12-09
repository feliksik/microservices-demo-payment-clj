(ns foobar.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [clojure.pprint :as pp]
            [ring.middleware.json :as json]))

(defn json-response [b]
  (resp/content-type
    (resp/response b)
    "application/json"))

(defn debug
  [req]
  (let [req-as-str (with-out-str (pp/pprint req))
        title "<h1>the request was:</h1>"
        pre (str "<pre>" req-as-str "</pre>")
        body (str title pre)]
    (resp/content-type
     (resp/response body)
     "text/html")))

(defn my-handler
  [_]
   (resp/response "<h1>I'm here</h1>"))

(defn authorized-response [auth]
   { :authorized auth } )

(defn authorized? [value]
  (< value 100))

(defn debug-body [b]
  (pp/pprint b)
  b)

(defn authorize
  [body]
  (-> body
      debug-body
      (get "amount")
      authorized?
      authorized-response
      json-response))

(defn get-health [_]
  (json-response { :service "payment" :status "OK" :time (quot (System/currentTimeMillis) 1000) } ))


(defroutes app-routes
  (GET "/" [] my-handler)
  (POST "/paymentAuth" {body :body} (authorize body))
  (GET "/debug" [] debug)
  (GET "/health" [] get-health)
  (route/not-found "Not Found"))

(def app
  (json/wrap-json-response
    (json/wrap-json-body
      (wrap-defaults app-routes api-defaults))))
