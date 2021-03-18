package querybuilder.implementations;

import querybuilder.NumericFunctions;

public class IntColumn extends ColumnImpl<Integer> implements NumericFunctions {

    public IntColumn(String name) {
        super(name);
    }

}
