package org.wso2.custom.provisioning.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.util.IdentityCoreConstants;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;
import org.wso2.carbon.identity.provisioning.listener.DefaultInboundUserProvisioningListener;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import java.util.Map;


public class CustomOutboundProvisioningListener extends DefaultInboundUserProvisioningListener {

    private static final Log log = LogFactory.getLog(CustomOutboundProvisioningListener.class);
    private static final String MODIFIED_CLAIM = "http://wso2.org/claims/modified";

    public CustomOutboundProvisioningListener() throws IdentityProvisioningException {
    }

    @Override
    public int getExecutionOrderId() {
        int orderId = getOrderId();
        if (orderId != IdentityCoreConstants.EVENT_LISTENER_ORDER_ID) {
            return orderId;
        }
        return 29;
    }

    @Override
    public boolean doPreAddUser(String userName, Object credential, String[] roleList,
                                Map<String, String> inboundAttributes, String profile, UserStoreManager userStoreManager)
            throws UserStoreException {

        if (!isEnable()) {
            return true;
        }
        return true;
    }

    @Override
    public boolean doPostAddUser(String userName, Object credential, String[] roleList, Map<String, String> claims,
                                 String profile, UserStoreManager userStoreManager) throws UserStoreException {

        if (!isEnable()) {
            return true;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(credential);
        return super.doPreAddUser(userName, sb, roleList, claims, profile, userStoreManager);
    }

    @Override
    public boolean doPreSetUserClaimValues(String userName, Map<String, String> inboundAttributes,
                                           String profileName, UserStoreManager userStoreManager) throws UserStoreException {

        if (!isEnable()) {
            return true;
        }
        return true;
    }

    @Override
    public boolean doPostSetUserClaimValues(String userName, Map<String, String> inboundAttributes,
                                           String profileName, UserStoreManager userStoreManager) throws UserStoreException {

        if (!isEnable()) {
            return true;
        }

        log.debug("custom provisioning listener, doPostSetUserClaimValues inbound attributes:" + inboundAttributes);
        return super.doPreSetUserClaimValues(userName, inboundAttributes, profileName, userStoreManager);
    }

}
