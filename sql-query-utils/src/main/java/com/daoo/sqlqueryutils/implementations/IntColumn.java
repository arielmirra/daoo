package com.daoo.sqlqueryutils.implementations;

import com.daoo.sqlqueryutils.NumericFunctions;

public class IntColumn extends ColumnImpl<Integer> implements NumericFunctions {

    public IntColumn(String name) {
        super(name);
    }

}
