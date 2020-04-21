/*
 * This file is generated by jOOQ.
 */
package com.getartur.billingcore.shared.domain.jooq;


import com.getartur.billingcore.shared.domain.jooq.tables.Address;
import com.getartur.billingcore.shared.domain.jooq.tables.Customer;
import com.getartur.billingcore.shared.domain.jooq.tables.FlywaySchemaHistory;
import com.getartur.billingcore.shared.domain.jooq.tables.Invoice;
import com.getartur.billingcore.shared.domain.jooq.tables.Project;
import com.getartur.billingcore.shared.domain.jooq.tables.TimeTracking;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Getartur extends SchemaImpl {

    private static final long serialVersionUID = -535700609;

    /**
     * The reference instance of <code>getartur</code>
     */
    public static final Getartur GETARTUR = new Getartur();

    /**
     * The table <code>getartur.address</code>.
     */
    public final Address ADDRESS = Address.ADDRESS;

    /**
     * The table <code>getartur.customer</code>.
     */
    public final Customer CUSTOMER = Customer.CUSTOMER;

    /**
     * The table <code>getartur.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>getartur.invoice</code>.
     */
    public final Invoice INVOICE = Invoice.INVOICE;

    /**
     * The table <code>getartur.project</code>.
     */
    public final Project PROJECT = Project.PROJECT;

    /**
     * The table <code>getartur.time_tracking</code>.
     */
    public final TimeTracking TIME_TRACKING = TimeTracking.TIME_TRACKING;

    /**
     * No further instances allowed
     */
    private Getartur() {
        super("getartur", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Address.ADDRESS,
            Customer.CUSTOMER,
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Invoice.INVOICE,
            Project.PROJECT,
            TimeTracking.TIME_TRACKING);
    }
}
