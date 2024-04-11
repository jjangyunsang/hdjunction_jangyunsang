package com.hdjunction.project.yunsang.infrastructure.datasource;

import com.hdjunction.project.yunsang.global.util.ConstantUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? ConstantUtil.SLAVE
                : ConstantUtil.MASTER;
    }
}
