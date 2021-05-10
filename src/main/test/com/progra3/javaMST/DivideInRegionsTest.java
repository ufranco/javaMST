package com.progra3.javaMST;

import com.progra3.javaMST.back.application.exceptions.InvalidAmountOfRegionsException;
import com.progra3.javaMST.back.domain.services.GraphService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;

public class DivideInRegionsTest {

  private final GraphService service;

  public DivideInRegionsTest() {
    service = new GraphService();
  }

  @Test
  public void validDivision() {


    final var expectedResult = new int[][] {
      { NULL, 2  , NULL, NULL  , NULL },
      { 2  , NULL, 3  , NULL  , 5 },
      { NULL, 3  , NULL, NULL, NULL },
      { NULL  , NULL  , NULL, NULL, NULL },
      { NULL, 5  , NULL  , NULL  , NULL },
    };

    initializeGraph();

    assertThat(service.divideInRegions(2))
      .describedAs("Initial graph devided in two separated regions")
      .isNotNull()
      .isInstanceOf(int[][].class)
      .isEqualTo(expectedResult);



  }

  private void initializeGraph() {
    service.initializeGraph(5);

    service.addEdge(1, 0,2);
    service.addEdge(1, 2,3);
    service.addEdge(1, 3,8);
    service.addEdge(1, 4,5);
    service.addEdge(2, 4,7);
    service.addEdge(3, 0,6);
    service.addEdge(3, 4,9);

    /*
    {
      { NULL, 2  , NULL, 6  , NULL },
      { 2  , NULL, 3  , 8  , 5 },
      { NULL, 3  , NULL, NULL, 7 },
      { 6  , 8  , NULL, NULL, 9 },
      { NULL, 5  , 7  , 9  , NULL },
    }
    */
  }

  @Test
  public void nullAmountOfRegions() {

    assertThatThrownBy(()  -> service.divideInRegions(null))
      .describedAs("It should fail due to null amount of regions")
      .isInstanceOf(InvalidAmountOfRegionsException.class)
      .hasMessage("Negative or null number of regions not allowed.");

  }

  @Test
  public void invalidAmountOfRegions() {

    assertThatThrownBy(()  -> service.divideInRegions(-13))
      .describedAs("It should fail due to negative amount of regions")
      .isInstanceOf(InvalidAmountOfRegionsException.class)
      .hasMessage("Negative or null number of regions not allowed.");

  }


  @Test
  public void moreRegionsThanPosible() {
    initializeGraph();

    assertThatThrownBy(() -> service.divideInRegions(155) )
      .describedAs(
        "It should fail due to the amount of regions requested" +
          " is greater than the amount of vertex in the graph."
      ).isInstanceOf(InvalidAmountOfRegionsException.class)
      .hasMessage("There's not enough vertex in graph to satisfy this request");




  }
}
