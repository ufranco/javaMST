package main;
import  grafo.Graph;
import ArbolGeneradorMinimo.ArbolGeneradorMinimo;

public class main {

  public static void main(String[] args)
  {
    int valorMax = Integer.MAX_VALUE;
/* Let us create the following graph
        2   3
    (0)--(1)--(2)
     |   / \    |
    6| 8/   \ 5 |7
     | /     \  |
    (3)-------(4)
          9
*/

    int cost[][] = {
        { valorMax, 2  , valorMax, 6  , valorMax },
        { 2  , valorMax, 3  , 8  , 5 },
        { valorMax, 3  , valorMax, valorMax, 7 },
        { 6  , 8  , valorMax, valorMax, 9 },
        { valorMax, 5  , 7  , 9  , valorMax },
    };

    // Print the solution
    ArbolGeneradorMinimo gfg = new ArbolGeneradorMinimo(cost.length);
    Graph agm = gfg.kruskalMST(cost);

    System.out.println(
        agm.getPeso(0,1)+
        agm.getPeso(1,2)+
        agm.getPeso(1,4)+
        agm.getPeso(0,3)
    );

    /*
    *
    * comunicanion front~back
    *
    * -nuevo grafo cuando inicie el front
    * -se define K regiones
    * -se añaden X canitdad de aristas desde el front con peso incluido "grafo.añadirArista(int i, int j, int peso)"
    * -se espera que se devuelva el grafo con todos los cambios que dice el enunciado
    *
    * tareas del back
    * -refactor de el codigo de agm porque esta feo
    * -remover aristas por peso maximo
    * -refactor de la clase Graph para seguir un estandar
    * -implementar getGrafo devuelve el int[][] A de Graph
    * */



  }
}
