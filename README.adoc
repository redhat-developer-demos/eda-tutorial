# Event-Driven Architecture Tutorial

[source,bash]
----
#!/bin/bash
minishift profile set eda-tutorial
minishift config set memory 8GB
minishift config set cpus 3
minishift config set vm-driver virtualbox ## or kvm, for Fedora
minishift config set image-caching true
minishift config set openshift-version v3.11.0
minishift addon enable admin-user
minishift addon enable anyuid

minishift start
minishift ssh -- sudo setenforce 0
----

[#environment]
== Setup environment

[source,bash]
----
eval $(minishift oc-env)
eval $(minishift docker-env)
oc login $(minishift ip):8443 -u admin -p admin
----
