/*
 * This file is generated by jOOQ.
 */
package com.getartur.billingcore.shared.domain.jooq.tables;


import com.getartur.billingcore.shared.domain.jooq.Getartur;
import com.getartur.billingcore.shared.domain.jooq.Keys;
import com.getartur.billingcore.shared.domain.jooq.tables.records.InvoiceRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Invoice extends TableImpl<InvoiceRecord> {

    private static final long serialVersionUID = -699361515;

    /**
     * The reference instance of <code>getartur.invoice</code>
     */
    public static final Invoice INVOICE = new Invoice();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InvoiceRecord> getRecordType() {
        return InvoiceRecord.class;
    }

    /**
     * The column <code>getartur.invoice.id</code>.
     */
    public final TableField<InvoiceRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>getartur.invoice.customer_id</code>.
     */
    public final TableField<InvoiceRecord, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>getartur.invoice.number</code>.
     */
    public final TableField<InvoiceRecord, String> NUMBER = createField(DSL.name("number"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>getartur.invoice.issued</code>.
     */
    public final TableField<InvoiceRecord, LocalDate> ISSUED = createField(DSL.name("issued"), org.jooq.impl.SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>getartur.invoice.delivery</code>.
     */
    public final TableField<InvoiceRecord, String> DELIVERY = createField(DSL.name("delivery"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>getartur.invoice.intro</code>.
     */
    public final TableField<InvoiceRecord, String> INTRO = createField(DSL.name("intro"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>getartur.invoice.outro</code>.
     */
    public final TableField<InvoiceRecord, String> OUTRO = createField(DSL.name("outro"), org.jooq.impl.SQLDataType.CLOB.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.CLOB)), this, "");

    /**
     * The column <code>getartur.invoice.created</code>.
     */
    public final TableField<InvoiceRecord, LocalDateTime> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * Create a <code>getartur.invoice</code> table reference
     */
    public Invoice() {
        this(DSL.name("invoice"), null);
    }

    /**
     * Create an aliased <code>getartur.invoice</code> table reference
     */
    public Invoice(String alias) {
        this(DSL.name(alias), INVOICE);
    }

    /**
     * Create an aliased <code>getartur.invoice</code> table reference
     */
    public Invoice(Name alias) {
        this(alias, INVOICE);
    }

    private Invoice(Name alias, Table<InvoiceRecord> aliased) {
        this(alias, aliased, null);
    }

    private Invoice(Name alias, Table<InvoiceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Invoice(Table<O> child, ForeignKey<O, InvoiceRecord> key) {
        super(child, key, INVOICE);
    }

    @Override
    public Schema getSchema() {
        return Getartur.GETARTUR;
    }

    @Override
    public Identity<InvoiceRecord, Long> getIdentity() {
        return Keys.IDENTITY_INVOICE;
    }

    @Override
    public UniqueKey<InvoiceRecord> getPrimaryKey() {
        return Keys.KEY_INVOICE_PRIMARY;
    }

    @Override
    public List<UniqueKey<InvoiceRecord>> getKeys() {
        return Arrays.<UniqueKey<InvoiceRecord>>asList(Keys.KEY_INVOICE_PRIMARY);
    }

    @Override
    public List<ForeignKey<InvoiceRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<InvoiceRecord, ?>>asList(Keys.FK_CUSTOMER_INVOICE);
    }

    public Customer customer() {
        return new Customer(this, Keys.FK_CUSTOMER_INVOICE);
    }

    @Override
    public Invoice as(String alias) {
        return new Invoice(DSL.name(alias), this);
    }

    @Override
    public Invoice as(Name alias) {
        return new Invoice(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Invoice rename(String name) {
        return new Invoice(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Invoice rename(Name name) {
        return new Invoice(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, Long, String, LocalDate, String, String, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
