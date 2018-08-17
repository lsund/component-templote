(ns template.core
  "Namespace that defines the system of components."
  (:require
   [com.stuartsierra.component :as c]
   [template.app :as app]
   [template.server :as server]
   [template.db :as db]))

(defn new-system
  [config]
  (c/system-map :server (c/using (server/new-server (:server config))
                                 [:app])
                :app (c/using (app/new-app (:app config))
                              [:db])
                :db (c/using (db/new-db (:db config))
                             [])))
