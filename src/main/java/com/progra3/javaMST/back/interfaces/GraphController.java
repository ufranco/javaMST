package com.progra3.javaMST.back.interfaces;

import com.progra3.javaMST.back.domain.services.GraphService;

public class GraphController {
  private final GraphService repository;

  public GraphController() { this.repository = new GraphService(); }

  public int[][] initializeGraph(final Integer graphSize) {
    return repository.initializeGraph(graphSize);
  }

  public int[][] addEdge(
    final Integer x,
    final Integer y,
    final Integer edgeWeight
  ) {
    return repository.addEdge(x, y, edgeWeight);
  }

  public int[][] removeEdge(
    final Integer x,
    final Integer y
  ) {
    return repository.removeEdge(x, y);
  }

  public int[][] divideInRegions(final Integer amountOfRegions) {
    return repository.divideInRegions(amountOfRegions);
  }

}
