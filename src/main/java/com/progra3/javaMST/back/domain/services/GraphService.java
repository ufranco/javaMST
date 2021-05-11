package com.progra3.javaMST.back.domain.services;

import com.progra3.javaMST.back.application.algorithms.KruskalProcedure;
import com.progra3.javaMST.back.application.exceptions.*;
import com.progra3.javaMST.back.domain.repositories.GraphRepository;

public class GraphService {
  private GraphRepository repository;

  public int[][] initializeGraph(final Integer graphSize) throws InvalidGraphSizeException {

    if(graphSize == null || graphSize < 1) {
      throw new InvalidGraphSizeException("Graph cannot have null nor negative size");
    }

    repository = new GraphRepository(graphSize);
    return repository.getGraph();
  }

  public int[][] addEdge(
    final Integer x,
    final Integer y,
    final Integer edgeWeight
  )
    throws
    InvalidVertexException,
    VertexIndexOutOfBoundsException,
    InvalidEdgeWeightException,
    EdgeAlreadyExistException
  {
    repository.addEdge(x, y, edgeWeight);
    return repository.getGraph();
  }

  public int[][] removeEdge(
    final Integer x,
    final Integer y
  ) throws
    InvalidVertexException,
    VertexIndexOutOfBoundsException
  {

    repository.removeEdge(x, y);
    return repository.getGraph();

  }

  public int [][] divideInRegions(final Integer amountOfRegions) throws InvalidAmountOfRegionsException{

    if(amountOfRegions == null || amountOfRegions < 1) {
      throw new InvalidAmountOfRegionsException("Negative or null number of regions not allowed.");
    }

    repository = KruskalProcedure.mst(repository.getGraph());
    repository.divideInRegions(amountOfRegions);
    return repository.getGraph();
  }
}
