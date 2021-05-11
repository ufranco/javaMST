package com.progra3.javaMST.back.application.algorithms;


import com.progra3.javaMST.back.domain.repositories.GraphRepository;

import java.util.stream.Stream;

import static com.progra3.javaMST.back.application.utils.GraphUtils.NULL;

public class KruskalProcedure {

  public static GraphRepository mst(final int[][] graph) {

    final var vertexCount = graph.length;
    final var mstGraph = new GraphRepository(vertexCount);
    final var vertexParent = new int[vertexCount];

    Stream.iterate( 0 , x -> x = x + 1)
      .limit(vertexCount)
      .forEach( x -> vertexParent[x] = x);

    var edgeCount = 0;

    while (edgeCount < vertexCount - 1) {

      var shortestWeight = NULL;
      var shortestFrom = -1;
      var shortestTo = -1;

      //se recorre la matriz
      for (int x = 0; x < vertexCount; x++) {

        for (int y = 0; y < vertexCount; y++) {
          if (!find(x, vertexParent).equals(find(y, vertexParent)) && graph[x][y] < shortestWeight) {
            shortestWeight = graph[x][y];
            shortestFrom = x;
            shortestTo = y;
          }
        }
      }

      union(shortestFrom, shortestTo, vertexParent);
      mstGraph.addEdge(shortestFrom, shortestTo, shortestWeight);

      edgeCount++;
    }

    return mstGraph;
  }

  // Find set of vertex x
  private static Integer find(Integer vertex, final int[] vertexParent) {
    while (vertexParent[vertex] != vertex) {
      vertex = vertexParent[vertex];
    }
    return vertex;
  }

 /*
   Does union of x and y. It returns
   false if x and y are already in same
   set.
 */

  private static void union(final Integer x, final Integer y, final int[] vertexParent) {
    final var shortestFrom = find(x, vertexParent);
    final var shortestTo = find(y, vertexParent);
    vertexParent[shortestFrom] = shortestTo;
  }
}