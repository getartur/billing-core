/*
 * This file is generated by jOOQ.
 */
package com.getartur.billingcore.shared.domain.jooq.tables;


import com.getartur.billingcore.shared.domain.jooq.Getartur;
import com.getartur.billingcore.shared.domain.jooq.Keys;
import com.getartur.billingcore.shared.domain.jooq.tables.records.SubProjectRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class SubProject extends TableImpl<SubProjectRecord> {

    private static final long serialVersionUID = 1397013284;

    /**
     * The reference instance of <code>getartur.sub_project</code>
     */
    public static final SubProject SUB_PROJECT = new SubProject();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SubProjectRecord> getRecordType() {
        return SubProjectRecord.class;
    }

    /**
     * The column <code>getartur.sub_project.id</code>.
     */
    public final TableField<SubProjectRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>getartur.sub_project.project_id</code>.
     */
    public final TableField<SubProjectRecord, Long> PROJECT_ID = createField(DSL.name("project_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>getartur.sub_project.name</code>.
     */
    public final TableField<SubProjectRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>getartur.sub_project.pass_factor</code>.
     */
    public final TableField<SubProjectRecord, BigDecimal> PASS_FACTOR = createField(DSL.name("pass_factor"), org.jooq.impl.SQLDataType.DECIMAL(3, 2).nullable(false), this, "");

    /**
     * Create a <code>getartur.sub_project</code> table reference
     */
    public SubProject() {
        this(DSL.name("sub_project"), null);
    }

    /**
     * Create an aliased <code>getartur.sub_project</code> table reference
     */
    public SubProject(String alias) {
        this(DSL.name(alias), SUB_PROJECT);
    }

    /**
     * Create an aliased <code>getartur.sub_project</code> table reference
     */
    public SubProject(Name alias) {
        this(alias, SUB_PROJECT);
    }

    private SubProject(Name alias, Table<SubProjectRecord> aliased) {
        this(alias, aliased, null);
    }

    private SubProject(Name alias, Table<SubProjectRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> SubProject(Table<O> child, ForeignKey<O, SubProjectRecord> key) {
        super(child, key, SUB_PROJECT);
    }

    @Override
    public Schema getSchema() {
        return Getartur.GETARTUR;
    }

    @Override
    public Identity<SubProjectRecord, Long> getIdentity() {
        return Keys.IDENTITY_SUB_PROJECT;
    }

    @Override
    public UniqueKey<SubProjectRecord> getPrimaryKey() {
        return Keys.KEY_SUB_PROJECT_PRIMARY;
    }

    @Override
    public List<UniqueKey<SubProjectRecord>> getKeys() {
        return Arrays.<UniqueKey<SubProjectRecord>>asList(Keys.KEY_SUB_PROJECT_PRIMARY);
    }

    @Override
    public List<ForeignKey<SubProjectRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SubProjectRecord, ?>>asList(Keys.FK_PROJECT_SUB_ROJECT);
    }

    public Project project() {
        return new Project(this, Keys.FK_PROJECT_SUB_ROJECT);
    }

    @Override
    public SubProject as(String alias) {
        return new SubProject(DSL.name(alias), this);
    }

    @Override
    public SubProject as(Name alias) {
        return new SubProject(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SubProject rename(String name) {
        return new SubProject(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SubProject rename(Name name) {
        return new SubProject(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, String, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
