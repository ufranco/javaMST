package com.progra3.javaMST;

import com.progra3.javaMST.back.application.exceptions.*;
import com.progra3.javaMST.back.domain.services.GraphService;
import org.junit.jupiter.api.Test;

import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AddEdgeTest {

  private final GraphService service;

  public AddEdgeTest() {
    service = new GraphService();
    service.initializeGraph(5);
  }

  @Test
  public void validEdgeAddition() {

    final var expectedResult = new int [][] {
      {NULL, NULL, NULL ,NULL ,NULL},
      {NULL, NULL, NULL ,NULL ,NULL},
      {NULL, NULL, NULL ,5 ,NULL},
      {NULL, NULL, 5 ,NULL ,NULL},
      {NULL, NULL, NULL ,NULL ,NULL}
    };


    assertThat(service.addEdge(2,3,5))
      .describedAs("It should add edge successfully")
      .isNotNull()
      .isInstanceOf(int[][].class)
      .isEqualTo(expectedResult);
  }

  @Test
  public void nullIndexes() {
    assertThatThrownBy(() -> service.addEdge(null,null, 5))
      .describedAs("It should fail due to invalid indexes")
      .isInstanceOf(InvalidVertexException.class)
      .hasMessage("not supported negative nor null vertex: null");
  }

  @Test
  public void invalidIndexes() {
    var x = -4;
    var y = -2;

    assertThatThrownBy(() -> service.addEdge(x,y, 5))
      .describedAs("It should fail due to invalid indexes")
      .isInstanceOf(InvalidVertexException.class)
      .hasMessage("not supported negative nor null vertex: " + x);

  }

  @Test
  public void vertexIndexOutOfBounds() {
    var x = 600;
    var y = 2;

    assertThatThrownBy(() -> service.addEdge(x,y, 5))
      .describedAs("It should fail due to invalid indexes")
      .isInstanceOf(VertexIndexOutOfBoundsException.class)
      .hasMessage("vertex out of graph range: " + x);

  }

  @Test
  public void nullWeight() {
    assertThatThrownBy(() -> service.addEdge(2,3,null))
      .describedAs("It should fail due to null edge weight")
      .isInstanceOf(InvalidEdgeWeightException.class)
      .hasMessage("An edge cannot have negative nor null weight");

  }

  @Test
  public void invalidWeight() {
    assertThatThrownBy(() -> service.addEdge(2,3,-2))
      .describedAs("It should fail due to negative edge weight")
      .isInstanceOf(InvalidEdgeWeightException.class)
      .hasMessage("An edge cannot have negative nor null weight");

  }

  @Test
  public void edgeAlreadyExist() {

    service.addEdge(2,3,5);
    assertThatThrownBy(() -> service.addEdge(2,3, 5))
      .describedAs("It should fail, that edge already exist")
      .isInstanceOf(EdgeAlreadyExistException.class)
      .hasMessage("That edge already exists!");
  }


  @Test
  public void circularEdge() {
    assertThatThrownBy(() -> service.addEdge(2,2, 5))
      .describedAs("It should fail, that edge already exist")
      .isInstanceOf(CircularReferenceException.class)
      .hasMessage("not supported loops (2, 2)");
  }

}
