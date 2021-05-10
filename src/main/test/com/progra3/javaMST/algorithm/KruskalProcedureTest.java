package com.progra3.javaMST.algorithm;

import com.progra3.javaMST.back.application.algorithms.KruskalProcedure;
import com.progra3.javaMST.back.domain.services.GraphService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;


public class KruskalProcedureTest {

  @Test
  public void validMST() {

    final var initialGraph = new int [][] {
      { NULL, 2  , NULL, 6  , NULL },
      { 2  , NULL, 3  , 8  , 5 },
      { NULL, 3  , NULL, NULL, 7 },
      { 6  , 8  , NULL, NULL, 9 },
      { NULL, 5  , 7  , 9  , NULL },
    };

    final var expectedResult = new int[][] {
      { NULL, 2  , NULL, 6  , NULL },
      { 2  , NULL, 3  , NULL  , 5 },
      { NULL, 3  , NULL, NULL, NULL },
      { 6  , NULL  , NULL, NULL, NULL },
      { NULL, 5  , NULL  , NULL  , NULL },
    };

    final var actualResult = KruskalProcedure.mst(initialGraph).getGraph();

    assertThat(actualResult)
      .describedAs("Initial graph processed with kruskal's algorithm to get a mst graph")
      .isNotNull()
      .isEqualTo(expectedResult);


  }
}
