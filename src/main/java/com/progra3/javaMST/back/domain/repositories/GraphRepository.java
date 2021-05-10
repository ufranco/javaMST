package com.progra3.javaMST.back.domain.repositories;

import com.progra3.javaMST.back.application.algorithms.Edge;
import com.progra3.javaMST.back.application.exceptions.*;

import java.util.*;
import java.util.stream.Collectors;

public class GraphRepository {

  private final int[][] graph;

  public GraphRepository(final Integer size) {
    graph = new int[size][size];
  }

  public void removeEdge(final Integer x, final Integer y) throws InvalidVertexException, VertexIndexOutOfBoundsException {
    checkEdge(x,y);

    graph[x][y] = Integer.MAX_VALUE;
    graph[y][x] = Integer.MAX_VALUE;
  }

  public void addEdge(
    final Integer x,
    final Integer y,
    final Integer weight
  ) throws
    InvalidVertexException,
    VertexIndexOutOfBoundsException,
    InvalidEdgeWeightException,
    EdgeAlreadyExistException
  {
    checkEdge(x,y);

    if(weight < 0 ) throw new InvalidEdgeWeightException("An edge cannot have negative weight");

    if(existsEdge(x,y)) throw new EdgeAlreadyExistException("That edge already exists!");

    graph[x][y] = weight;
    graph[y][x] = weight;
  }

  public Boolean existsEdge(final Integer x, final Integer y) {
    return graph[y][x] != Integer.MAX_VALUE && graph[x][y] == graph[y][x];
  }

  public Integer getEdgeWeight(final Integer x, final Integer y) throws InvalidVertexException, VertexIndexOutOfBoundsException {
    checkEdge(x,y);
    return graph[x][y];
  }

  public Integer size() {
    return graph.length;
  }

  public void checkEdge(final Integer x, final Integer y) throws InvalidVertexException, VertexIndexOutOfBoundsException {
    checkVertex(x);
    checkVertex(y);

    if (x.equals(y)) {
      throw new CircularReferenceException("not supported loops (" + x + ", " + y + ")");
    }
  }

  private void checkVertex(final Integer vertex) throws InvalidVertexException, VertexIndexOutOfBoundsException   {
    if ( vertex < 0 ) throw new InvalidVertexException("not supported negative vertex: "+ vertex);
    if( vertex >= size()) throw new VertexIndexOutOfBoundsException("vertex out of graph range: " + vertex);
  }

  public int[][] getGraph() {
    return graph.clone();
  }


  public void divideInRegions(Integer amountOfRegions) throws InvalidAmountOfRegionsException {
    if(amountOfRegions < 1) throw new InvalidAmountOfRegionsException("Negative number of regions not allowed.");
    final var edgesToDelete = findHeavierEdges(amountOfRegions);

    System.out.println(edgesToDelete);

    edgesToDelete.forEach(
      edge -> graph[edge.getX()][edge.getY()] = Integer.MAX_VALUE
    );

  }

  private List<Edge> findHeavierEdges(Integer amountOfRegions) {

    final var edges = new ArrayList<Edge>();

    for(int x = 0; x < size(); x++) {

      for(int y = 0; y < size(); y++) {

        var edgeWeight = graph[x][y];
        if(edgeWeight < Integer.MAX_VALUE) {
          edges.add(new Edge(x, y, edgeWeight));
          edges.add(new Edge(y, x, edgeWeight));
        }
      }
    }

    return edges.stream()
      .sorted(Comparator.comparing(Edge::getWeight))
      .limit((amountOfRegions - 1) * 2L)
      .collect(Collectors.toList());

  }
}
