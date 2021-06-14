package org.wso2.custom.provisioning.listener.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;
import org.wso2.carbon.user.core.listener.UserOperationEventListener;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.custom.provisioning.listener.CustomOutboundProvisioningListener;

@Component(
        name = "org.wso2.custom.provisioning.listener",
        immediate = true
)
public class ServiceComponent {

    private static Log log = LogFactory.getLog(ServiceComponent.class);
    private static RealmService realmService;

    @Activate
    protected void activate(ComponentContext context) throws IdentityProvisioningException {

        CustomOutboundProvisioningListener listener = new CustomOutboundProvisioningListener();
        context.getBundleContext().registerService(UserOperationEventListener.class.getName(), listener, null);
        log.info("CustomOutboundProvisioningListener bundle activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("CustomOutboundProvisioningListener is deactivated ");
        }
    }

    @Reference(
            name = "user.realmservice.default",
            service = org.wso2.carbon.user.core.service.RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService"
    )
    protected void setRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
        this.realmService = realmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        if (log.isDebugEnabled()) {
            log.debug("UnSetting the Realm Service");
        }
        this.realmService = null;
    }

    public static RealmService getRealmService() {
        return realmService;
    }
}
