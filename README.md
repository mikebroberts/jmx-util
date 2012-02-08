# jmx-util - Command line access to JMX

Right now just allows invoking no-arg actions on local or remote MBeans.

To setup: Get leiningen (if you haven't already), then (assuming lein in your path):

    $ lein uberjar

To use:

e.g. for help:

    $ java -jar jmx-util-1.0.0-standalone.jar --help

e.g. to actually run

    $ java -jar jmx-util-1.0.0-standalone.jar -h localhost -p 1234 -b "myapp:name=MyBean" -a "myoperation"
    Invoking action myoperation on bean myapp:name=MyBean at localhost:1234
