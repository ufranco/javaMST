package com.progra3.javaMST.back.domain.services;

import com.progra3.javaMST.back.application.algorithms.KruskalProcedure;
import com.progra3.javaMST.back.domain.repositories.GraphRepository;

public class GraphService {
  private GraphRepository repository;

  public int[][] initializeGraph(final Integer graphSize) {
    repository = new GraphRepository(graphSize);
    return repository.getGraph();
  }

  public int[][] addEdge(
    final Integer x,
    final Integer y,
    final Integer edgeWeight
  ) {

    repository.addEdge(x, y, edgeWeight);
    return repository.getGraph();
  }

  public int[][] removeEdge(
    final Integer x,
    final Integer y
  ) {

    repository.removeEdge(x, y);
    return repository.getGraph();

  }

  public int [][] divideInRegions(final Integer amountOfRegions) {
    repository = KruskalProcedure.mst(repository.getGraph());
    repository.divideInRegions(amountOfRegions);
    return repository.getGraph();
  }
}
