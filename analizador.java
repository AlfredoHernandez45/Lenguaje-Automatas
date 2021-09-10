import java.util.*;
public class analizador {
    
    public static void main (String [] args){
        //declarar variables
        Scanner scan = new Scanner(System.in);
        char list[];
        String exp;

        //salida y entrada de datos
        System.out.println("Ingresar expresion");
        exp = scan.nextLine();
        list = exp.toCharArray();
        
        AL(list);
    }

    /**
     * Analisis Lexico
     * analisa la expresión ingresada para indicar si es valida 
     * con el lenguaje (*,+,-,/,0...9)
     */
    public static void AL(char list[]){
        //Declarar varibales
        ArrayList<Object> lista = new ArrayList<Object>();
        char val [] = {'0','1','2','3','4','5','6','7','8','9','+','-','*','/'};
        
        //Verifica que la expresion cumpla con el lenguaje (*,+,-,/,0...9)
        for(int i=0; i<list.length; i++){
            boolean chek = false;

            //Se compara la expresion y el lenguaje
            for(int j=0; j<val.length; j++){
                
                if(list[i] == val[j]){
                    lista.add(list[i]);;
                    chek = true;
                }
                if(chek == true) break;
            }

        }
        if(list.length == lista.size()) {
            //ASi();
        }else {
            System.out.println("ERROR LEXICO \nLa expresion no cumple con el Analisis Lexico, no se puede continuar");
        };
    }
    
    /**
     * Analisis Sintactico
     * se realiza el posfijo y el arbol sintactico
     */
    public static void ASi(){
        //ArrayList<Object> arreglo = new ArrayList<Object>();
        //ArrayList<Object> pila = new ArrayList<Object>();
        //ArrayList<Object> len = new ArrayList<Object>();
        //len.add();
    }
    
    /**
     * Analisis Semantico
     * se realiza el arbol
     */
    public static void ASe(){

    }
}
