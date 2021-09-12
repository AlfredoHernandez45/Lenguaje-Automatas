/**
 * Clase para crear los arboles, indica en donde se guardara,
 * lado izquierdo, lado derecho, nodo.
 */
public class Nodos {
    //Declarar variables
    Nodos izquierda;
    Nodos derecha;
    Integer dato;
    
    //Constructor
    public Nodos(){}
    //Obtener los signos
    public Nodos(Integer dato){
        this.dato=dato;
    }
    //Asignacion de las ramas del arbol
    public void Tree(Nodos n){
        if(n.izquierda!=null){
            Tree(n.izquierda);
        }
        if(n.derecha!=null){
            Tree(n.derecha);
        }
        System.out.print(n.dato);
    }
}
