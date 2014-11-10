/**
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS
 * FOR A PARTICULAR PURPOSE. THIS CODE AND INFORMATION ARE NOT SUPPORTED BY XEBIALABS.
 */
package ext.deployit.community.plugin.overthere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xebialabs.deployit.plugin.api.flow.Step;
import com.xebialabs.deployit.plugin.api.udm.ConfigurationItem;
import com.xebialabs.deployit.plugin.api.udm.Container;
import com.xebialabs.deployit.plugin.api.udm.Delegate;
import com.xebialabs.deployit.plugin.generic.step.ScriptExecutionStep;
import com.xebialabs.deployit.plugin.overthere.Host;

public class CredentialHostTasks {

    @Delegate(name = "checkConnectionOnAliasHost")
    public List<Step> checkConnectionOnAliasHost(ConfigurationItem ci, String method, Map<String, String> arguments) {
        Host host = (Host) ci;
        new CredentialProcessor().setCredentials(host, "username", "password");
        return host.checkConnection();
    }

    @Delegate(name = "runInjectedControlTaskScript")
    public List<Step> runInjectedControlTaskScript(ConfigurationItem ci, String method, Map<String, String> arguments) {
        Host host = null;
        Container container = null;
        if(ci instanceof Host) host = (Host) ci;
        else {
        	container = (Container) ci;
        	host = container.getProperty("host");
        }

        if(CredentialProcessor.SUPPORTED_TYPES.contains(host.getType())) {
            new CredentialProcessor().setCredentials(host, "username", "password");
        }
        
        List<Step> output = new ArrayList<Step>();
        HashMap<String, Object> context = new HashMap<String,Object>();
        context.put("host", host);
        context.put("container", container);

        String scriptPath = "./" + method;
        if(arguments.size()>0){
            scriptPath = arguments.get("argument1");
        }

        ScriptExecutionStep scriptStep = new ScriptExecutionStep(1, scriptPath, host, context, "Run " + method + " on [" + host.getName() + "]");
        output.add(scriptStep);
        return output;
    }

}
