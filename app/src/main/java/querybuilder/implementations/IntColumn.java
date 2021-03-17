package querybuilder.implementations;

import daoo.query.*;
import querybuilder.NumericFunctions;

public class IntColumn extends ColumnImpl<Integer> implements NumericFunctions {

    public IntColumn(String name) {
        super(name);
    }

}
