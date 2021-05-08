package ArbolGeneradorMinimo;

import grafo.Graph;

import java.util.*;
public class ArbolGeneradorMinimo
  {

    private int V = 5;
    private int[] parent;
    static int INF = Integer.MAX_VALUE;
    private Graph agm;
    public ArbolGeneradorMinimo(int V){
      this.V = V;
      this.parent = new int[V];
      agm = new Graph(V);
    }

    // Find set of vertex i
    private int find(int i)
    {
      while (parent[i] != i)
        i = parent[i];
      return i;
    }

    // Does union of i and j. It returns
// false if i and j are already in same
// set.
    private void union1(int i, int j)
    {
      int a = find(i);
      int b = find(j);
      parent[a] = b;
      System.out.println("-------------");

    }

    // Finds MST using Kruskal's algorithm
    public Graph kruskalMST(int cost[][])
    {

      //costominimototal
      int mincost = 0; // Cost of min MST.

      // Initialize sets of disjoint sets.

      //asigna a todos los vertices como padres
      for (int i = 0; i < V; i++)
        parent[i] = i;

      // Include minimum weight edges one by one
      int edge_count = 0;//cantidad de aritas
      while (edge_count < V - 1)
      {
        int min = INF, a = -1, b = -1;
        //se recorre la matriz
        for (int i = 0; i < V; i++)
        {
          for (int j = 0; j < V; j++)
          {
            //si find()
            if (find(i) != find(j) && cost[i][j] < min)
            {
              min = cost[i][j];
              a = i;
              b = j;
            }
          }
        }
        union1(a, b);
        agm.addEdge(a,b,min);
      }
      return agm;
    }


  }