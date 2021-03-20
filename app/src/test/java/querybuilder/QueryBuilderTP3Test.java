package querybuilder;

import org.junit.Test;

import static querybuilder.Builder.*;

import daoo.query.*;
import querybuilder.implementations.*;

public class QueryBuilderTP3Test {

    @Test(expected = RuntimeException.class)
    public void invalidClauseTest() {
        final TableImpl t = table("student");
        final StrColumn lastName = string("lastName"); // not in the table
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

        final Query q = query()
                .select(age, fistName)
                .from(t)
                .where(fistName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(fistName.length())))
                .orderBy(age)
                .groupBy(lastName)
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void invalidCriteriaTest() {
        final TableImpl t = table("student");
        final StrColumn lastName = string("lastName"); // not in the table
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

        final Query q = query()
                .select(age, fistName)
                .from(t)
                .where(lastName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(fistName.length())))
                .orderBy(age)
                .groupBy(fistName)
                .build();
    }
}
