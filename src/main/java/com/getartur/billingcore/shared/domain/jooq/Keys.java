/*
 * This file is generated by jOOQ.
 */
package com.getartur.billingcore.shared.domain.jooq;


import com.getartur.billingcore.shared.domain.jooq.tables.Address;
import com.getartur.billingcore.shared.domain.jooq.tables.Customer;
import com.getartur.billingcore.shared.domain.jooq.tables.FlywaySchemaHistory;
import com.getartur.billingcore.shared.domain.jooq.tables.Project;
import com.getartur.billingcore.shared.domain.jooq.tables.TimeTracking;
import com.getartur.billingcore.shared.domain.jooq.tables.records.AddressRecord;
import com.getartur.billingcore.shared.domain.jooq.tables.records.CustomerRecord;
import com.getartur.billingcore.shared.domain.jooq.tables.records.FlywaySchemaHistoryRecord;
import com.getartur.billingcore.shared.domain.jooq.tables.records.ProjectRecord;
import com.getartur.billingcore.shared.domain.jooq.tables.records.TimeTrackingRecord;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>getartur</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AddressRecord, Long> IDENTITY_ADDRESS = Identities0.IDENTITY_ADDRESS;
    public static final Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Identities0.IDENTITY_CUSTOMER;
    public static final Identity<ProjectRecord, Long> IDENTITY_PROJECT = Identities0.IDENTITY_PROJECT;
    public static final Identity<TimeTrackingRecord, Long> IDENTITY_TIME_TRACKING = Identities0.IDENTITY_TIME_TRACKING;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AddressRecord> KEY_ADDRESS_PRIMARY = UniqueKeys0.KEY_ADDRESS_PRIMARY;
    public static final UniqueKey<CustomerRecord> KEY_CUSTOMER_PRIMARY = UniqueKeys0.KEY_CUSTOMER_PRIMARY;
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = UniqueKeys0.KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = UniqueKeys0.KEY_PROJECT_PRIMARY;
    public static final UniqueKey<TimeTrackingRecord> KEY_TIME_TRACKING_PRIMARY = UniqueKeys0.KEY_TIME_TRACKING_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AddressRecord, CustomerRecord> FK_CUSTOMER_ADRESS = ForeignKeys0.FK_CUSTOMER_ADRESS;
    public static final ForeignKey<ProjectRecord, CustomerRecord> FK_CONTRACT_PROJECT = ForeignKeys0.FK_CONTRACT_PROJECT;
    public static final ForeignKey<TimeTrackingRecord, ProjectRecord> FK_PROJECT_TIME_TRACKING = ForeignKeys0.FK_PROJECT_TIME_TRACKING;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AddressRecord, Long> IDENTITY_ADDRESS = Internal.createIdentity(Address.ADDRESS, Address.ADDRESS.ID);
        public static Identity<CustomerRecord, Long> IDENTITY_CUSTOMER = Internal.createIdentity(Customer.CUSTOMER, Customer.CUSTOMER.ID);
        public static Identity<ProjectRecord, Long> IDENTITY_PROJECT = Internal.createIdentity(Project.PROJECT, Project.PROJECT.ID);
        public static Identity<TimeTrackingRecord, Long> IDENTITY_TIME_TRACKING = Internal.createIdentity(TimeTracking.TIME_TRACKING, TimeTracking.TIME_TRACKING.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AddressRecord> KEY_ADDRESS_PRIMARY = Internal.createUniqueKey(Address.ADDRESS, "KEY_address_PRIMARY", new TableField[] { Address.ADDRESS.ID }, true);
        public static final UniqueKey<CustomerRecord> KEY_CUSTOMER_PRIMARY = Internal.createUniqueKey(Customer.CUSTOMER, "KEY_customer_PRIMARY", new TableField[] { Customer.CUSTOMER.ID }, true);
        public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "KEY_flyway_schema_history_PRIMARY", new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = Internal.createUniqueKey(Project.PROJECT, "KEY_project_PRIMARY", new TableField[] { Project.PROJECT.ID }, true);
        public static final UniqueKey<TimeTrackingRecord> KEY_TIME_TRACKING_PRIMARY = Internal.createUniqueKey(TimeTracking.TIME_TRACKING, "KEY_time_tracking_PRIMARY", new TableField[] { TimeTracking.TIME_TRACKING.ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AddressRecord, CustomerRecord> FK_CUSTOMER_ADRESS = Internal.createForeignKey(Keys.KEY_CUSTOMER_PRIMARY, Address.ADDRESS, "fk_customer_adress", new TableField[] { Address.ADDRESS.CUSTOMER_ID }, true);
        public static final ForeignKey<ProjectRecord, CustomerRecord> FK_CONTRACT_PROJECT = Internal.createForeignKey(Keys.KEY_CUSTOMER_PRIMARY, Project.PROJECT, "fk_contract_project", new TableField[] { Project.PROJECT.CUSTOMER_ID }, true);
        public static final ForeignKey<TimeTrackingRecord, ProjectRecord> FK_PROJECT_TIME_TRACKING = Internal.createForeignKey(Keys.KEY_PROJECT_PRIMARY, TimeTracking.TIME_TRACKING, "fk_project_time_tracking", new TableField[] { TimeTracking.TIME_TRACKING.PROJECT_ID }, true);
    }
}
