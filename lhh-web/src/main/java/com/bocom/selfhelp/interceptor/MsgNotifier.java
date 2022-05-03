package com.bocom.selfhelp.interceptor;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.model.StatusInfo;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sliu
 * @date 2022/4/19 16:32
 */
@Slf4j
public class MsgNotifier extends AbstractStatusChangeNotifier {

    @Override
    protected void doNotify(ClientApplicationEvent clientApplicationEvent) throws Exception {
        StatusInfo statusInfo = clientApplicationEvent.getApplication().getStatusInfo();


    }


}
