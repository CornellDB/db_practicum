import common.DBCatalog;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.FromItem;
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
  public void parserExampleTest() {
    try {
      ClassLoader classLoader = ParserExample.class.getClassLoader();
      String path = Objects.requireNonNull(classLoader.getResource("samples/input")).getPath();
      DBCatalog.getInstance().setDataDirectory(path + "/db");

      String queriesFile =
          Objects.requireNonNull(classLoader.getResource("samples/input/queries.sql")).getPath();

      String str = Files.readString(Path.of(queriesFile));

      logger.info(str);

      Statements statements =
          CCJSqlParserUtil.parseStatements(str);

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

        for (Join join : plainSelect.getJoins()) {
          // Process joins..
        }
      }
    } catch (Exception e) {
      System.err.println("Exception occurred during parsing");
      logger.error(e.getMessage());
    }
  }
}
