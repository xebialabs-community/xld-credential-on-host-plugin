# Credential on Host plugin #

# Overview #

The credential-on-host plugin allows you to define the credentials information in a new type, `overthere.Credential`, in the Configuration node.
Two new types - `overthere.AliasSshHost` and `overthere.AliasCifsHost`-  provide a new attribute 'credential' and hide the username and password attributes from `overthere.SsHost` and `overthere.CifsHost`.
# Requirements #

* **Deployit requirements**
	* **XL Deploy**: version 5.0.0+
	* XL Deploy need to support overthere.SmbHost type

# Installation #

Place the plugin JAR file into your `SERVER_HOME/plugins` directory.

# Configuration #

The `checkConnection` property on the udm.DeployedApplication type offers to generate CheckConnection Step on all the credential hosts..

![Configuration] (/img/credential.png)


![Configured Host] (/img/aliasHost.png)

# Controltasks #

A controltask delegate was added to allow script-based controltasks to work. In order to execute script-based control tasks on a credential host you need to make two adjustments:
- Instead of "shellScript" as the delegate now use "credentialShellScript"
- Instead of providing the path to the controltask script through the "script" attribute now use the "argument1" atribute (if argument1 is not provided the path defaults to a script in the root with same name as the method)



