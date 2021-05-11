package com.progra3.javaMST;

import com.progra3.javaMST.back.application.exceptions.InvalidGraphSizeException;
import com.progra3.javaMST.back.domain.services.GraphService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InitializeGraphTest {

  private final GraphService service;

  public InitializeGraphTest() {
    service = new GraphService();
  }

  @Test
  public void validGraphInitialization() {

    final var expectedResult = new int[][] {
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL},
      {NULL, NULL, NULL, NULL, NULL}
    };

    Assertions.assertThat(service.initializeGraph(5))
      .describedAs("It should initialize sucessfully")
      .hasSize(5)
      .isEqualTo(expectedResult);

  }

  @Test
  public void nullGraphSize() {
    assertThatThrownBy(() -> service.initializeGraph(null))
      .describedAs("It should fail due to null graph size")
      .isInstanceOf(InvalidGraphSizeException.class)
      .hasMessage("Graph cannot have null nor negative size");
  }

  @Test
  public void invalidGraphSize() {
    assertThatThrownBy(() -> service.initializeGraph(0))
      .describedAs("It should fail due to null graph size")
      .isInstanceOf(InvalidGraphSizeException.class)
      .hasMessage("Graph cannot have null nor negative size");
  }
}
