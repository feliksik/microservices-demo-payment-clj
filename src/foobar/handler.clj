(ns foobar.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn my-handler
  [req]
  (resp/content-type
   (resp/response "<h1>I'm here</h1>")
   "text/html"))

(defn my-another-handler
  [req]
  (resp/content-type
    (resp/response "<h1>I'm also here</h1>")
    "text/html"))

(defroutes app-routes
  (GET "/" [] my-handler)
  (GET "/another" [] my-another-handler)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
