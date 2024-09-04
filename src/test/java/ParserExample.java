import common.DBCatalog;
import common.QueryPlanBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * Example class for getting started with JSQLParser. Reads SQL statements from a file and prints
 * them to screen; then extracts SelectBody from each query and also prints it to screen.
 */
public class ParserExample {
  private final Logger logger = LogManager.getLogger();

  @Test
  public void parserExampleTest() throws IOException, JSQLParserException, URISyntaxException {
    ClassLoader classLoader = P1UnitTests.class.getClassLoader();

    URI resourceUri = Objects.requireNonNull(classLoader.getResource("samples/input")).toURI();

    Path resourcePath = Paths.get(resourceUri);

    DBCatalog.getInstance().setDataDirectory(resourcePath.resolve("db").toString());

    URI queriesUri =
        Objects.requireNonNull(classLoader.getResource("samples/input/queries.sql")).toURI();
    Path queriesFilePath = Paths.get(queriesUri);

    Statements statements = CCJSqlParserUtil.parseStatements(Files.readString(queriesFilePath));

    QueryPlanBuilder queryPlanBuilder = new QueryPlanBuilder();

    for (Statement statement : statements.getStatements()) {
      logger.info("Read statement: " + statement);

      Select select = (Select) statement;
      PlainSelect plainSelect = (PlainSelect) select.getSelectBody();

      Table fromItem = (Table) plainSelect.getFromItem();

      logger.info("Select body is " + select.getSelectBody());
      logger.info("From item is " + fromItem);

      Alias alias = fromItem.getAlias();
      String name = fromItem.getName();

      logger.info("Alias: " + alias);
      logger.info("Name: " + name);

      List<Join> joins = plainSelect.getJoins();

      if (joins != null) {
        for (Join join : plainSelect.getJoins()) {
          // Process joins..
        }
      }
    }
  }
}
