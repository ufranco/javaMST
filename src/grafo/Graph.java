package grafo;

import java.util.HashSet;
import java.util.Set;

public class Graph {

  private int[][] A;

  public Graph(int vertices){
    A = new int[vertices][vertices];
  }

  public void addEdge(int i, int j, int peso) {
    checkEdge(i,j);
    A[i][j] = peso;
    A[j][i] = peso;
  }

  public boolean differentVertex(int i, int j){
    return !(i == j);
  }

  public boolean existsEdge(int i, int j) { return (A[i][j] == A[j][i]); }

  public int getPeso(int i, int j){
    checkEdge(i,j);
    return A[i][j];
  }

  public boolean existsVertex(int i){
    return i<this.size() && i>0;
  }

  public Set<Integer> neighbors(int i) {
    checkVertex(i);
    Set<Integer> ret = new HashSet<Integer>();
    for(int j = 0; j<this.size(); j++) if (i != j){

      if (this.existsEdge(i,j)){ret.add(j);}
    }
    return ret;
  }

  public int size() {
    return A.length;
  }

  public void checkEdge(int i,int j){
    checkVertex(i);
    checkVertex(j);
    if (!differentVertex(i,j))
      throw new IllegalArgumentException("not supported loops ("+i+","+j+")");
  }

  private void checkVertex(int i) {
    if (i<0)
      throw new IllegalArgumentException("not supported negative vertex: "+ i);
    if(i>= A.length)
      throw new IllegalArgumentException("vertex out of graph range: "+i);
  }
}
