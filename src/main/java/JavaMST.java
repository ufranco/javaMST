public class JavaMST {

  /* Let us create the following graph
          2   3
      (0)--(1)--(2)
       |   / \    |
      6| 8/   \ 5 |7
       | /     \  |
      (3)-------(4)
            9
  */

  public static void main(String[] args) {

    /*
    *
    * comunicanion front~back
    *
    * -nuevo main.java.javaAGM.domain.repositories.grafo cuando inicie el front
    * -se define K regiones
    * -se añaden X canitdad de aristas desde el front con peso incluido "GraphRepository.addEdge(int x, int y, int edgeWeight)"
    * -se espera que se devuelva el GraphRepository con todos los cambios que dice el enunciado
    *
    * tareas del back
    * -refactor de el codigo de mstGraph porque esta feo
    * -remover aristas por peso maximo
    * -refactor de la clase GraphRepository para seguir un estandar
    * -implementar getGrafo devuelve el int[][] initialGraph de GraphRepository
    * */

  }
}
