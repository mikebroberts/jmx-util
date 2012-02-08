(ns jmx-util.core
  (:require clojure.java.jmx clojure.tools.cli)
  (:gen-class))

(defn invoke [{:keys [host port bean action]}]
  (println (str "Invoking action " action " on bean " bean " at " host ":" port))
  (clojure.java.jmx/with-connection {:host host :port port}
    (clojure.java.jmx/invoke bean action)))

(defn run-with [[options args banner]]
  (when (:help options)
    (println banner)
    (System/exit 1))
  (invoke options))

(defn -main [& args]
  (run-with (clojure.tools.cli/cli args
    ["-h" "--host" "Host to connect to" :default "localhost"]
    ["-p" "--port" "Port to connect to" :parse-fn #(Integer. %)]
    ["-b" "--bean" "Bean name"]
    ["-a" "--action" "Action name"]
    ["--help" "Show help" :default false :flag true])))

