/*
 * This file is generated by jOOQ.
 */
package com.getartur.billingcore.shared.domain.jooq.tables.records;


import com.getartur.billingcore.shared.domain.jooq.tables.Customer;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends UpdatableRecordImpl<CustomerRecord> implements Record6<Long, String, String, String, String, String> {

    private static final long serialVersionUID = 2069639074;

    /**
     * Setter for <code>getartur.customer.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>getartur.customer.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>getartur.customer.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>getartur.customer.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>getartur.customer.number</code>.
     */
    public void setNumber(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>getartur.customer.number</code>.
     */
    public String getNumber() {
        return (String) get(2);
    }

    /**
     * Setter for <code>getartur.customer.phone</code>.
     */
    public void setPhone(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>getartur.customer.phone</code>.
     */
    public String getPhone() {
        return (String) get(3);
    }

    /**
     * Setter for <code>getartur.customer.website</code>.
     */
    public void setWebsite(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>getartur.customer.website</code>.
     */
    public String getWebsite() {
        return (String) get(4);
    }

    /**
     * Setter for <code>getartur.customer.vatin</code>.
     */
    public void setVatin(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>getartur.customer.vatin</code>.
     */
    public String getVatin() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Customer.CUSTOMER.ID;
    }

    @Override
    public Field<String> field2() {
        return Customer.CUSTOMER.NAME;
    }

    @Override
    public Field<String> field3() {
        return Customer.CUSTOMER.NUMBER;
    }

    @Override
    public Field<String> field4() {
        return Customer.CUSTOMER.PHONE;
    }

    @Override
    public Field<String> field5() {
        return Customer.CUSTOMER.WEBSITE;
    }

    @Override
    public Field<String> field6() {
        return Customer.CUSTOMER.VATIN;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getNumber();
    }

    @Override
    public String component4() {
        return getPhone();
    }

    @Override
    public String component5() {
        return getWebsite();
    }

    @Override
    public String component6() {
        return getVatin();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getNumber();
    }

    @Override
    public String value4() {
        return getPhone();
    }

    @Override
    public String value5() {
        return getWebsite();
    }

    @Override
    public String value6() {
        return getVatin();
    }

    @Override
    public CustomerRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public CustomerRecord value3(String value) {
        setNumber(value);
        return this;
    }

    @Override
    public CustomerRecord value4(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public CustomerRecord value5(String value) {
        setWebsite(value);
        return this;
    }

    @Override
    public CustomerRecord value6(String value) {
        setVatin(value);
        return this;
    }

    @Override
    public CustomerRecord values(Long value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerRecord
     */
    public CustomerRecord() {
        super(Customer.CUSTOMER);
    }

    /**
     * Create a detached, initialised CustomerRecord
     */
    public CustomerRecord(Long id, String name, String number, String phone, String website, String vatin) {
        super(Customer.CUSTOMER);

        set(0, id);
        set(1, name);
        set(2, number);
        set(3, phone);
        set(4, website);
        set(5, vatin);
    }
}
