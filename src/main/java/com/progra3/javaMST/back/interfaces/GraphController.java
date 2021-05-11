package com.progra3.javaMST.back.interfaces;

import com.progra3.javaMST.back.application.exceptions.*;
import com.progra3.javaMST.back.domain.services.GraphService;

public class GraphController {
  private final GraphService service;

  public GraphController() { service = new GraphService(); }

  public int[][] initializeGraph(final Integer graphSize)
    throws InvalidGraphSizeException
  {
    return service.initializeGraph(graphSize);
  }

  public int[][] addEdge(
    final Integer x,
    final Integer y,
    final Integer edgeWeight
  ) throws
    InvalidVertexException,
    VertexIndexOutOfBoundsException,
    InvalidEdgeWeightException,
    EdgeAlreadyExistException
  {
    return service.addEdge(x, y, edgeWeight);
  }

  public int[][] removeEdge(
    final Integer x,
    final Integer y
  ) throws
    InvalidVertexException,
    VertexIndexOutOfBoundsException
  {
    return service.removeEdge(x, y);
  }

  public int[][] divideInRegions(final Integer amountOfRegions)
    throws InvalidAmountOfRegionsException
  {
    return service.divideInRegions(amountOfRegions);
  }

}
