(defproject ring-todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.13.0"]
                 [ring/ring-json "0.5.1"]
                 [compojure "1.7.1"]]
  :repl-options {:init-ns ring-todo.core})
