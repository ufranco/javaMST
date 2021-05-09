package java;

import com.progra3.javaMST.back.application.algorithms.KruskalProcedure;
import org.junit.jupiter.api.Test;


public class JavaMSTTest {

  @Test
  public void test() {
    final var valorMax = Integer.MAX_VALUE;

    final var initialGraph = new int [][] {
      { valorMax, 2  , valorMax, 6  , valorMax },
      { 2  , valorMax, 3  , 8  , 5 },
      { valorMax, 3  , valorMax, valorMax, 7 },
      { 6  , 8  , valorMax, valorMax, 9 },
      { valorMax, 5  , 7  , 9  , valorMax },
    };

    final var mstGraph = KruskalProcedure.mst(initialGraph);


    //TODO: DEFINE ASSERTIONS
    System.out.println(mstGraph.getEdgeWeight(0,1));
    System.out.println(mstGraph.getEdgeWeight(1,2));
    System.out.println(mstGraph.getEdgeWeight(1,4));
    System.out.println(mstGraph.getEdgeWeight(0,3));

  }
}
