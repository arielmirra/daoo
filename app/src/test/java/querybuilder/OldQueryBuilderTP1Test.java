package querybuilder;

import org.junit.Test;

import static org.junit.Assert.*;

public class OldQueryBuilderTP1Test {
    @Test public void testValidQueryFormat() {
        OldQuery.Builder qb = new OldQuery.Builder();

        String query = qb
                .select("*")
                .from("students")
                .where("grade >= 7")
                .orderBy("grade")
                .build();

        assertEquals("SELECT * FROM students WHERE grade >= 7 ORDER BY grade;", query);
    }

    @Test public void testMultipleSelection() {
        OldQuery.Builder qb = new OldQuery.Builder();

        String query = qb
                .select("column1, column2")
                .from("students")
                .where("grade >= 7")
                .build();

        assertEquals("SELECT column1, column2 FROM students WHERE grade >= 7;", query);
    }

    @Test(expected = RuntimeException.class)
    public void testMissingClauses() {
        new OldQuery.Builder()
                .select("*")
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testDuplicatedClauses() {
        new OldQuery.Builder()
                .select("*")
                .from("students")
                .groupBy("grade")
                .from("anotherTable")
                .build();
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidColumn() {
        new OldQuery.Builder()
                .select("*")
                .from("students")
                .groupBy("grade")
                .from("anotherTable")
                .build();
    }
}
