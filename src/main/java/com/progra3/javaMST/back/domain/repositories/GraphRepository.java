package com.progra3.javaMST.back.domain.repositories;

import com.progra3.javaMST.back.application.exceptions.*;
import com.progra3.javaMST.back.application.utils.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;

public class GraphRepository {

  private final int[][] graph;

  public GraphRepository(final Integer size) {
    graph = new int[size][size];
    Stream.iterate(0, x -> x = x + 1)
      .limit(size)
      .forEach( x ->
        Stream.iterate(0, y -> y = y + 1)
          .limit(size)
          .forEach(y -> graph[x][y] = NULL)
    );
  }

  public void removeEdge(final Integer x, final Integer y) throws InvalidVertexException, VertexIndexOutOfBoundsException {
    checkEdge(x,y);

    graph[x][y] = NULL;
    graph[y][x] = NULL;
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

    if(weight == null || weight < 0 ) throw new InvalidEdgeWeightException("An edge cannot have negative nor null weight");

    if(existsEdge(x,y)) throw new EdgeAlreadyExistException("That edge already exists!");

    graph[x][y] = weight;
    graph[y][x] = weight;
  }

  private Boolean existsEdge(final Integer x, final Integer y) {
    return graph[y][x] != NULL && graph[x][y] == graph[y][x];
  }

  public Integer size() {
    return graph.length;
  }

  private void checkEdge(final Integer x, final Integer y) throws InvalidVertexException, VertexIndexOutOfBoundsException {
    checkVertex(x);
    checkVertex(y);

    if (x.equals(y)) {
      throw new CircularReferenceException("not supported loops (" + x + ", " + y + ")");
    }
  }

  public void checkVertex(final Integer vertex) throws InvalidVertexException, VertexIndexOutOfBoundsException   {
    if ( vertex == null || vertex < 0 ) throw new InvalidVertexException("not supported negative nor null vertex: "+ vertex);
    if( vertex >= size()) throw new VertexIndexOutOfBoundsException("vertex out of graph range: " + vertex);
  }

  public int[][] getGraph() {
    return graph.clone();
  }


  public void divideInRegions(Integer amountOfRegions) throws InvalidAmountOfRegionsException {

    final var edgesToDelete = findHeavierEdges(amountOfRegions);

    edgesToDelete.forEach(edge -> graph[edge.getX()][edge.getY()] = NULL);

  }

  private List<Edge> findHeavierEdges(Integer amountOfRegions) throws InvalidAmountOfRegionsException {
    final var edges = new ArrayList<Edge>();

    if (amountOfRegions > size()) throw new InvalidAmountOfRegionsException(
      "There's not enough vertex in graph to satisfy this request"
    );

    for(int x = 0; x < size(); x++) {

      for(int y = 0; y < size(); y++) {

        var edgeWeight = graph[x][y];
        if(edgeWeight < NULL) {
          edges.add(new Edge(x, y, edgeWeight));
        }
      }
    }

    return edges.stream()
      .sorted((e1, e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
      .limit((amountOfRegions - 1) * 2L)
      .collect(Collectors.toList());
  }
}
