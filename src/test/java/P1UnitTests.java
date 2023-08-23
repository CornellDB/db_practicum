import common.DBCatalog;
import common.QueryPlanBuilder;
import common.Tuple;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import jdk.jshell.spi.ExecutionControl;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import operator.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P1UnitTests {
  private static List<Statement> statementList;
  private static QueryPlanBuilder queryPlanBuilder;
  private static Statements statements;

  @BeforeAll
  static void setupBeforeAllTests() throws IOException, JSQLParserException {
    ClassLoader classLoader = P1UnitTests.class.getClassLoader();
    String path = Objects.requireNonNull(classLoader.getResource("samples/input")).getPath();
    DBCatalog.getInstance().setDataDirectory(path + "/db");

    String queriesFile =
        Objects.requireNonNull(classLoader.getResource("samples/input/queries.sql")).getPath();
    statements = CCJSqlParserUtil.parseStatements(Files.readString(Path.of(queriesFile)));
    queryPlanBuilder = new QueryPlanBuilder();
    statementList = statements.getStatements();
  }

  @Test
  public void testQuery1() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(0));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 6;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(List.of(1, 200, 50))),
          new Tuple(new ArrayList<>(List.of(2, 200, 200))),
          new Tuple(new ArrayList<>(List.of(3, 100, 105))),
          new Tuple(new ArrayList<>(List.of(4, 100, 50))),
          new Tuple(new ArrayList<>(List.of(5, 100, 500))),
          new Tuple(new ArrayList<>(List.of(6, 300, 400)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery2() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(1));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 6;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(List.of(1))),
          new Tuple(new ArrayList<>(List.of(2))),
          new Tuple(new ArrayList<>(List.of(3))),
          new Tuple(new ArrayList<>(List.of(4))),
          new Tuple(new ArrayList<>(List.of(5))),
          new Tuple(new ArrayList<>(List.of(6)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery3() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(2));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 6;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(List.of(1))),
          new Tuple(new ArrayList<>(List.of(2))),
          new Tuple(new ArrayList<>(List.of(3))),
          new Tuple(new ArrayList<>(List.of(4))),
          new Tuple(new ArrayList<>(List.of(5))),
          new Tuple(new ArrayList<>(List.of(6)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery4() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(3));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 2;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(Arrays.asList(1, 200, 50))),
          new Tuple(new ArrayList<>(Arrays.asList(2, 200, 200)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery5() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(4));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 6;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(Arrays.asList(1, 200, 50, 1, 101))),
          new Tuple(new ArrayList<>(Arrays.asList(1, 200, 50, 1, 102))),
          new Tuple(new ArrayList<>(Arrays.asList(1, 200, 50, 1, 103))),
          new Tuple(new ArrayList<>(Arrays.asList(2, 200, 200, 2, 101))),
          new Tuple(new ArrayList<>(Arrays.asList(3, 100, 105, 3, 102))),
          new Tuple(new ArrayList<>(Arrays.asList(4, 100, 50, 4, 104)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery6() throws ExecutionControl.NotImplementedException {
    Operator plan =
        queryPlanBuilder.buildPlan(
            statementList.get(5)); // Assuming statementList index 5 corresponds to query 6

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 15; // Total number of combinations given the values

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(List.of(1, 200, 50, 2, 200, 200))),
          new Tuple(new ArrayList<>(List.of(1, 200, 50, 3, 100, 105))),
          new Tuple(new ArrayList<>(List.of(1, 200, 50, 4, 100, 50))),
          new Tuple(new ArrayList<>(List.of(1, 200, 50, 5, 100, 500))),
          new Tuple(new ArrayList<>(List.of(1, 200, 50, 6, 300, 400))),
          new Tuple(new ArrayList<>(List.of(2, 200, 200, 3, 100, 105))),
          new Tuple(new ArrayList<>(List.of(2, 200, 200, 4, 100, 50))),
          new Tuple(new ArrayList<>(List.of(2, 200, 200, 5, 100, 500))),
          new Tuple(new ArrayList<>(List.of(2, 200, 200, 6, 300, 400))),
          new Tuple(new ArrayList<>(List.of(3, 100, 105, 4, 100, 50))),
          new Tuple(new ArrayList<>(List.of(3, 100, 105, 5, 100, 500))),
          new Tuple(new ArrayList<>(List.of(3, 100, 105, 6, 300, 400))),
          new Tuple(new ArrayList<>(List.of(4, 100, 50, 5, 100, 500))),
          new Tuple(new ArrayList<>(List.of(4, 100, 50, 6, 300, 400))),
          new Tuple(new ArrayList<>(List.of(5, 100, 500, 6, 300, 400)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuples[i], actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery7() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(6));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 4;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(Arrays.asList(1))),
          new Tuple(new ArrayList<>(Arrays.asList(2))),
          new Tuple(new ArrayList<>(Arrays.asList(3))),
          new Tuple(new ArrayList<>(Arrays.asList(4)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }

  @Test
  public void testQuery8() throws ExecutionControl.NotImplementedException {
    Operator plan = queryPlanBuilder.buildPlan(statementList.get(7));

    List<Tuple> tuples = HelperMethods.collectAllTuples(plan);

    int expectedSize = 6;

    Assertions.assertEquals(expectedSize, tuples.size(), "Unexpected number of rows.");

    Tuple[] expectedTuples =
        new Tuple[] {
          new Tuple(new ArrayList<>(Arrays.asList(3, 100, 105))),
          new Tuple(new ArrayList<>(Arrays.asList(4, 100, 50))),
          new Tuple(new ArrayList<>(Arrays.asList(5, 100, 500))),
          new Tuple(new ArrayList<>(Arrays.asList(1, 200, 50))),
          new Tuple(new ArrayList<>(Arrays.asList(2, 200, 200))),
          new Tuple(new ArrayList<>(Arrays.asList(6, 300, 400)))
        };

    for (int i = 0; i < expectedSize; i++) {
      Tuple expectedTuple = expectedTuples[i];
      Tuple actualTuple = tuples.get(i);
      Assertions.assertEquals(expectedTuple, actualTuple, "Unexpected tuple at index " + i);
    }
  }
}
