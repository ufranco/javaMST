package com.progra3.javaMST;

import com.progra3.javaMST.back.application.exceptions.InvalidVertexException;
import com.progra3.javaMST.back.domain.services.GraphService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;

public class RemoveEdgeTest {

  private final GraphService service;

  public RemoveEdgeTest() {
    this.service = new GraphService();
    service.initializeGraph(5);
  }

  @Test
  public void validEdgeRemove() {
    final var expectedResult = new int[][] {
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL}
    };

    service.addEdge(2,3,5);

    assertThat(service.removeEdge(2,3))
      .describedAs("It should remove edge successfully")
      .isNotNull()
      .isInstanceOf(int[][].class)
      .isEqualTo(expectedResult);

  }

  @Test
  public void nullIndexes() {
    assertThatThrownBy(() -> service.removeEdge(null,null))
      .describedAs("It should fail due to null indexes")
      .isInstanceOf(InvalidVertexException.class)
      .hasMessage("not supported negative nor null vertex: null");
  }

  @Test
  public void invalidIndexes() {
    var x = -4;
    var y = -2;

    assertThatThrownBy(() -> service.removeEdge(x,y))
      .describedAs("It should fail due to invalid indexes")
      .isInstanceOf(InvalidVertexException.class)
      .hasMessage("not supported negative nor null vertex: " + x);

  }
}
