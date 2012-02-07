(ns jmx-util.core
  (:require clojure.java.jmx clojure.tools.cli))

(defn run-with [host port bean action]
  (println (str "Invoking action " action " on bean " bean " at " host ":" port))
  (clojure.java.jmx/with-connection {:host host :port port}
    (clojure.java.jmx/invoke bean action)))

(defn -main [& args]
  (let [[options args banner] (clojure.tools.cli/cli args
    ["-h" "--host" "Host to connect to" :default "localhost"]
    ["-p" "--port" "Port to connect to" :parse-fn #(Integer. %)]
    ["-b" "--bean" "Bean name"]
    ["-a" "--action" "Action name"]
    ["-h" "--help" "Show help" :default false :flag true])]
    (when (:help options)
      (println banner)
      (System/exit 1))
    (run-with (:host options) (:port options) (:bean options) (:action options))))

