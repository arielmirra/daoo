package com.daoo.sqlqueryutils;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static com.daoo.sqlqueryutils.Builder.*;

import daoo.query.*;
import com.daoo.sqlqueryutils.implementations.*;

public class QueryBuilderTP2Test {

    @Test
    public void buildQueryTest() {
        final TableImpl t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

        final Query q = query()
                .select(age, fistName, lastName)
                .from(t)
                .where(lastName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(fistName.length())))
                .orderBy(age)
                .groupBy(lastName)
                .build();

        assertNotNull(q);
    }
}
