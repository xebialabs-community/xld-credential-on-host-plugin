# Credential on Host plugin #

# Overview #

The credential-on-host plugin allows you to define the credentials information in a new type, `overthere.Credential`, in the Configuration node.
Two new types - `overthere.AliasSshHost` and `overthere.AliasCifsHost`-  provide a new attribute 'credential' and hide the username and password attributes from `overthere.SsHost` and `overthere.CifsHost`.
# Requirements #

* **Deployit requirements**
	* **Deployit**: version 3.8+

# Installation #

Place the plugin JAR file into your `SERVER_HOME/plugins` directory.

# Configuration #

The `checkConnection` property on the udm.DeployedApplication type offers to generate CheckConnection Step on all the credential hosts..

![Configuration] (/img/credential.png)


![Configured Host] (/img/aliasHost.png)


