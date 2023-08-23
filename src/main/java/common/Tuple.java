package common;

import java.util.ArrayList;

/**
 * Class to encapsulate functionality about a database tuple. A tuple is an ArrayList of integers.
 */
public class Tuple {

  ArrayList<Integer> tupleArray;

  /**
   * Creates a tuple using string representation of the tuple. Delimiter between the columns is a
   * comma.
   *
   * @param s String representation of the tuple.
   */
  public Tuple(String s) {
    tupleArray = new ArrayList<Integer>();
    for (String attribute : s.split(",")) {
      tupleArray.add(Integer.parseInt(attribute));
    }
  }

  /**
   * Creates a tuple using an ArrayList of integers.
   *
   * @param elements ArrayList with elements of the tuple, in order
   */
  public Tuple(ArrayList<Integer> elements) {
    tupleArray = new ArrayList<Integer>();
    tupleArray.addAll(elements);
  }

  /**
   * Returns element at index i in the tuple.
   *
   * @param i The index of the element you need.
   * @return Element at index i in the tuple.
   */
  public int getElementAtIndex(int i) {
    return tupleArray.get(i);
  }

  /**
   * Returns a new ArrayList containing all the elements in the tuple.
   *
   * @return ArrayList containing the elements in the tuple.
   */
  public ArrayList<Integer> getAllElements() {
    return new ArrayList<Integer>(tupleArray);
  }

  /**
   * Returns a string representation of the tuple.
   *
   * @return string representation of the tuple, with attributes separated by commas.
   */
  @Override
  public String toString() {
    StringBuilder stringRepresentation = new StringBuilder();
    for (int i = 0; i < tupleArray.size() - 1; i++) {
      stringRepresentation.append(tupleArray.get(i)).append(",");
    }
    stringRepresentation.append(tupleArray.get(tupleArray.size() - 1));
    return stringRepresentation.toString();
  }

  /**
   * @param obj The tuple to compare with
   * @return True if the two tuples are the same; False otherwise.
   */
  @Override
  public boolean equals(Object obj) {

    Tuple temp = (Tuple) obj;
    if (temp == null) {
      return false;
    }

    return temp.toString().equals(this.toString());
  }
}
