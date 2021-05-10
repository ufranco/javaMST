package com.progra3.javaMST.back.domain.repositories;

import com.progra3.javaMST.back.application.algorithms.Edge;

import java.util.*;
import java.util.stream.Collectors;

public class GraphRepository {

  private final int[][] graph;

  public GraphRepository(final Integer size) {
    graph = new int[size][size];
  }

  public void removeEdge(final Integer x, final Integer y) {
    checkEdge(x,y);

    graph[x][y] = Integer.MAX_VALUE;
    graph[y][x] = Integer.MAX_VALUE;
  }

  public void addEdge(final Integer x, final Integer y, final Integer weight) {
    checkEdge(x,y);

    graph[x][y] = weight;
    graph[y][x] = weight;
  }

  public Boolean existsEdge(final Integer x, final Integer y) {
    return graph[x][y] == graph[y][x];
  }

  public Integer getEdgeWeight(final Integer x, final Integer y) {
    checkEdge(x,y);
    return graph[x][y];
  }

  public boolean isVertex(final Integer x){
    return x < size() && x > 0;
  }

  public Set<Integer> getVertexNeighbors(final Integer x) {
    checkVertex(x);
    final Set<Integer> neighbors = new HashSet<>();

    for(int y = 0; y < size(); y++)
      if (x != y && existsEdge( x, y) ) {
        neighbors.add(y);
      }
    return neighbors;
  }

  public Integer size() {
    return graph.length;
  }

  public void checkEdge(final Integer x, final Integer y){
    checkVertex(x);
    checkVertex(y);

    if (x.equals(y)) {
      throw new IllegalArgumentException("not supported loops (" + x + ", " + y + ")");
    }
  }

  private void checkVertex(final Integer vertex) {
    if ( vertex < 0 ) throw new IllegalArgumentException("not supported negative vertex: "+ vertex);
    if( vertex >= size()) throw new IllegalArgumentException("vertex out of graph range: " + vertex);
  }

  public int[][] getGraph() {
    return graph.clone();
  }


  public void divideInRegions(Integer amountOfRegions) {
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
