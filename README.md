# Custom Outbound Provisioning Listener

Note: This is implemented for WSO2IS-5.10.0. If you want to use it in some other WSO2 server, you can simply modify the pom.xml file’s project dependency versions matching the same version packed in the product

### Steps to deploy
- Build the component by running "mvn clean install"
- Copy following jar file which can be found in target directory into <IS_HOME>/repository/components/dropins/
    - org.wso2.custom.provisioning.listener-1.0.0.jar
- Configure the Outbound Provisioning Event Listener by adding following lines into deployment.toml file.
```
[[event_listener]]
id = "default_provisioning_listener"
type = "org.wso2.carbon.user.core.listener.UserOperationEventListener"
name = "org.wso2.carbon.identity.provisioning.listener.DefaultInboundUserProvisioningListener"
order = 30
enable = false

[[event_listener]]
id = "org_wso2_custom_provisioning_listener"
type = "org.wso2.carbon.user.core.listener.UserOperationEventListener"
name = "org.wso2.custom.provisioning.listener.CustomOutboundProvisioningListener"
order = 29
enable = true
```
- Restart WSO2 IS.
