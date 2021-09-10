import java.util.*;
public class analizador {
    
    ArrayList arreglo = new ArrayList();
    ArrayList pila = new ArrayList();
    
    public void main (String [] args){
        //declarar variables
        Scanner scan = new Scanner(System.in);
        Object exp;

        
        //salida y entrada de datos
        System.out.println("Ingresar expresion");
        exp = scan.nextLine();
        
        System.out.println(exp);
    }

    /**
     * Analisis Lexico
     * analisa la expresión ingresada para indicar si es valida 
     * con el lenguaje (*,+,-,/,0...9)
     */
    public void AL(){

    }
    
    /**
     * Analisis Sintactico
     * se realiza el posfijo y el arbol sintactico
     */
    public void ASi(){

    }
    
    /**
     * Analisis Semantico
     * se realiza el arbol
     */
    public void ASe(){

    }
}
