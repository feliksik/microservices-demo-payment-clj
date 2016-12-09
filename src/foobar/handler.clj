(ns foobar.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [clojure.pprint :as pp]))

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
  [req]
  (resp/content-type
   (resp/response "<h1>I'm here</h1>")
   "text/html"))

(defn parse [body]
  {:amount 42})

(defn authorized-response [authorized?]
  (resp/content-type
   (resp/response (str "{\"authorized\": " authorized? " }"))
   "application/json"))

(defn authorized? [value]
  true)

(defn authorize
  [body]
  (-> body
      parse
      :amount
      authorized?
      authorized-response))

(defroutes app-routes
  (GET "/" [] my-handler)
  (POST "/paymentAuth" {body :body} (authorize body))
  (GET "/debug" [] debug)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
