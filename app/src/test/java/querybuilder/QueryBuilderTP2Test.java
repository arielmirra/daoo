package querybuilder;

import org.junit.Test;

import static querybuilder.Builder.*;

import daoo.query.*;
import querybuilder.implementations.*;

public class QueryBuilderTP2Test {

    @Test
    public void createQueryTest() {
        final TableImpl t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

        final Query q = query()
                .select(age, fistName)
                .from(t)
                .where(lastName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(fistName.length())))
                .orderBy(age)
                .groupBy(lastName)
                .build();
    }
}
