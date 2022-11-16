package exercises;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class Ex068 implements Exercise {

  @Override
  public void solve() {

    List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    PermutationIterator<Integer> permutationIterator = new PermutationIterator<>(intList);

    long max = 0;

    while (permutationIterator.hasNext()) {

      List<Integer> currentIntList = permutationIterator.next();
      Iterator<Integer> intIterator = currentIntList.iterator();

      // generate the 5 inner nodes
      Set<Node> coreSet = getCoreSet(intIterator);

      // generate the 5 outer nodes and link each to the core
      Set<Node> edgeSet = getEdgeSet(intIterator, coreSet);

      // check if this arrangement makes sense
      int sum = testGraphSum(edgeSet);
      if (sum == 0) {
        continue;
      }
      String result = getGraphString(edgeSet);
      long resultInt = Long.parseLong(result);
      if (resultInt > max) {
        max = resultInt;
      }
    }

    System.out.println(max);
  }

  private String getGraphString(Set<Node> edgeSet) {

    Preconditions.checkState(edgeSet.size() == 5);

    Node minNode = edgeSet.stream().min(Comparator.comparing(Node::getValue)).get();
    Node[] nodes = edgeSet.toArray(new Node[0]);
    int i = ArrayUtils.indexOf(nodes, minNode);
    // rotate the array so that the lowest value node is at position 0
    ArrayUtils.shift(nodes, -i);

    StringBuilder builder = new StringBuilder();
    for (Node node : nodes) {
      builder.append(node).append(node.getNext()).append(node.getNext().getNext());
    }
    Preconditions.checkState(builder.length() == 16);
    return builder.toString();
  }

  private int testGraphSum(Set<Node> edgeSet) {

    int graphSum = 0;
    for (Node edgeNode : edgeSet) {
      int sum = getBranchSum(edgeNode);
      if (graphSum != 0 && sum != graphSum) {
        return 0;
      }
      graphSum = sum;
    }
    Preconditions.checkState(graphSum > 5);
    return graphSum;
  }

  private int getBranchSum(Node node) {
    return node.getValue() + node.getNext().getValue() + node.getNext().getNext().getValue();
  }

  private Set<Node> getEdgeSet(Iterator<Integer> intIterator, Set<Node> coreSet) {

    Set<Node> edgeSet = new LinkedHashSet<>();

    // because there can be only 16 digits, 10 must be on the edge
    Node maxNode = new Node(10);
    edgeSet.add(maxNode);

    Iterator<Node> coreIterator = coreSet.iterator();
    maxNode.setNext(coreIterator.next());

    while (coreIterator.hasNext()) {
      Node edgeNode = new Node(intIterator.next());
      Node coreNode = coreIterator.next();
      edgeNode.setNext(coreNode);
      edgeSet.add(edgeNode);
    }
    Preconditions.checkState(!intIterator.hasNext());
    return edgeSet;
  }

  private Set<Node> getCoreSet(Iterator<Integer> intIterator) {

    Set<Node> coreSet = new LinkedHashSet<>();
    Node previous = null;
    Node node = null;

    for (int i = 0; i < 5; i++) {
      node = new Node(intIterator.next());
      if (previous != null) {
        previous.setNext(node);
      }
      previous = node;
      coreSet.add(node);
    }
    // link first and last element of set
    node.setNext(coreSet.iterator().next());
    Preconditions.checkState(coreSet.size() == 5);
    return coreSet;
  }

  private static final class Node {

    private final int value;
    private Node next;

    public Node(int value) {
      Preconditions.checkArgument(value >= 1 && value <= 10);
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node next) {
      this.next = Objects.requireNonNull(next);
    }

    @Override
    public int hashCode() {
      return value;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Node)) {
        return false;
      }
      Node that = (Node) obj;
      return this.value == that.value;
    }

    @Override
    public String toString() {
      return Integer.toString(value);
    }
  }
}
