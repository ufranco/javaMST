package com.progra3.javaMST.back.domain.repositories;

import java.util.HashSet;
import java.util.Set;

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
}
