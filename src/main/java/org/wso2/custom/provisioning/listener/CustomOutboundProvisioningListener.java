package org.wso2.custom.provisioning.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;
import org.wso2.carbon.identity.provisioning.listener.DefaultInboundUserProvisioningListener;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import java.util.Map;


public class CustomOutboundProvisioningListener extends DefaultInboundUserProvisioningListener {

    private static final Log log = LogFactory.getLog(CustomOutboundProvisioningListener.class);

    public CustomOutboundProvisioningListener() throws IdentityProvisioningException {
    }

    @Override
    public int getExecutionOrderId() {

        return 2;
    }

    @Override
    public boolean doPreAddUser(String userName, Object credential, String[] roleList,
                                Map<String, String> inboundAttributes, String profile, UserStoreManager userStoreManager)
            throws UserStoreException {

        log.info("###CustomOutboundProvisioningListener, custom pre");
        return true;
    }

    @Override
    public boolean doPostAddUser(String userName, Object credential, String[] roleList, Map<String, String> claims,
                                 String profile, UserStoreManager userStoreManager) throws UserStoreException {

        log.info("###CustomOutboundProvisioningListener, custom post");
        super.doPreAddUser(userName, credential, roleList, claims, profile, userStoreManager);
        return true;
    }
}
