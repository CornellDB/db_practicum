import common.Tuple;
import java.util.ArrayList;
import java.util.List;
import operator.Operator;

public class HelperMethods {
  public static List<Tuple> collectAllTuples(Operator operator) {
    Tuple tuple;
    List<Tuple> tuples = new ArrayList<>();
    while ((tuple = operator.getNextTuple()) != null) {
      tuples.add(tuple);
    }

    return tuples;
  }
}
