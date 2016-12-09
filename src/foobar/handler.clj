(ns foobar.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn my-handler
  [req]
  (resp/content-type
    (resp/response "I'm here")
    "text/plain"))

(defn my-another-handler
  [req]
  (resp/content-type
    (resp/response "I'm also here")
    "text/plain"))

(defroutes app-routes
  (GET "/" [] my-handler)
  (GET "/another" [] my-another-handler)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
