import common.DBCatalog;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.jupiter.api.Test;

/**
 * Example class for getting started with JSQLParser. Reads SQL statements from a file and prints
 * them to screen; then extracts SelectBody from each query and also prints it to screen.
 */
public class ParserExample {
  @Test
  public void parserExampleTest() {
    try {
      ClassLoader classLoader = ParserExample.class.getClassLoader();
      String path = Objects.requireNonNull(classLoader.getResource("samples/input")).getPath();
      DBCatalog.getInstance().setDataDirectory(path + "/db");

      String queriesFile =
          Objects.requireNonNull(classLoader.getResource("samples/input/queries.sql")).getPath();

      String str = Files.readString(Path.of(queriesFile));

      System.out.println(str);

      Statements statements =
          CCJSqlParserUtil.parseStatements("SELECT * FROM tab1; SELECT * FROM tab2");
      for (Statement statement : statements.getStatements()) {
        System.out.println("Read statement: " + statement);
        Select select = (Select) statement;
        System.out.println("Select body is " + select.getSelectBody());
      }
    } catch (Exception e) {
      System.err.println("Exception occurred during parsing");
      e.printStackTrace();
    }
  }
}
