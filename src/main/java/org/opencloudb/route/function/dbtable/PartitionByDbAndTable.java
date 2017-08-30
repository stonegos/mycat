/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */
package org.opencloudb.route.function.dbtable;


import org.opencloudb.config.model.rule.RuleAlgorithm;
import org.opencloudb.route.function.AbstractPartitionAlgorithm;

/**
 * 分库分表规则
 *
 * @author sxl
 */
public class PartitionByDbAndTable extends AbstractPartitionAlgorithm implements RuleAlgorithm {

    private int totalDataBases;

    private int totalTables;

    private int tablesByPerDataBase;

    @Override
    public void init() {
        if (totalDataBases <= 0 || totalTables <= 0) {
            throw new IllegalArgumentException("totalDataBases and totalTables must be both positive!");
        }
        this.tablesByPerDataBase = (totalTables / totalDataBases);
        if (this.tablesByPerDataBase <= 0) {
            throw new IllegalArgumentException("the expression 'totalTables/totalDataBases'<=0!");
        }

    }

    @Override
    public Integer calculate(String columnValue) {
        return getTableIndex(columnValue);
    }

    public int getPartitionNum() {
        return totalTables;
    }


    public int getDbIndex(Object routeFactor) {
        if (routeFactor instanceof Integer || routeFactor instanceof Long || routeFactor instanceof String) {
            return (int) (Math.abs(MurmurHash.hash(String.valueOf(routeFactor))) % totalTables / tablesByPerDataBase);
        }
        throw new IllegalArgumentException("Unsupported RouteFactor parameter type! the support RouteFactor parameter is Integer, Long and String.");
    }

    public int getTableIndex(Object routeFactor) {
        if (routeFactor instanceof Integer || routeFactor instanceof Long || routeFactor instanceof String) {
            return (int) (Math.abs(MurmurHash.hash(String.valueOf(routeFactor))) % totalTables);
        }
        throw new IllegalArgumentException("Unsupported RouteFactor parameter type! the support RouteFactor parameter is Integer, Long and String.");
    }


    public int getTotalDataBases() {
        return totalDataBases;
    }

    public void setTotalDataBases(int totalDataBases) {
        this.totalDataBases = totalDataBases;
    }

    public int getTotalTables() {
        return totalTables;
    }

    public void setTotalTables(int totalTables) {
        this.totalTables = totalTables;
    }

    public int getTablesByPerDataBase() {
        return tablesByPerDataBase;
    }

    public void setTablesByPerDataBase(int tablesByPerDataBase) {
        this.tablesByPerDataBase = tablesByPerDataBase;
    }
}
