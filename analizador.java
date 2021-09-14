/**
 * Autors: 
 *          JOSE ALFREDO LOPEZ HERNANDEZ
 *          CATHERINE MONTSERRAT ORDOÑEZ
 */
import java.util.*;

public class analizador {
    
    /**
    * Indica en donde se guardara,lado izquierdo, lado derecho, nodo.
    * */
    //Declarar variables globales
    analizador izquierda;
    analizador derecha;
    Integer dato;
    
    //Constructor
    public analizador(){}
    //Obtener los signos
    public analizador(Integer dato){
        this.dato=dato;
    }
    //Asignacion de las ramas del arbol (izquierda, derecha)
    public void Tree(analizador n){
        if(n.izquierda!=null){
            Tree(n.izquierda);
        }
        if(n.derecha!=null){
            Tree(n.derecha);
        }
        System.out.print(n.dato);
    }



    public static void main (String [] args){
        //declarar variables
        Scanner scan = new Scanner(System.in);
        char list[];
        String exp;

        //salida y entrada de datos
        System.out.println("Ingresar expresion");//mensaje
        exp = scan.nextLine();//resibe expresion
        list = exp.toCharArray();//conversion de String a Char
        
        //pasa a hacer el Analisis Lexico
        AL(list);
    }

    /**
     * Analisis Lexico
     * analisa la expresión ingresada para indicar si es valida 
     * con el lenguaje (*,+,-,/,0...9)
     */
    public static void AL(char list[]){
        //Declarar varibales
        ArrayList<Character> lista = new ArrayList<Character>();
        char val [] = {'0','1','2','3','4','5','6','7','8','9','+','-','*','/','(',')'};//Lenguaje valido
        
        //Verifica que la expresion cumpla con el lenguaje (*,+,-,/,0...9)
        for(int i=0; i<list.length; i++){
            boolean chek = false;//declara bandera

            //Se compara la expresion y el lenguaje
            for(int j=0; j<val.length; j++){
                
                //si la expresion no cumple con el lenguaje hace un break
                if(list[i] == val[j]){
                    lista.add(list[i]);//lista la expresion al array
                    chek = true;
                }
                if(chek == true) break;
            }

        }

        //Confirmar si el ArrayList es igual a la expresion 
        if(list.length == lista.size()) {
            System.out.println("\nRecorrido de Analisis Lexico finalizado");
            ASi(lista);
        }else {
            //Si la expresion no es valida manda un error
            System.out.println("ERROR LEXICO \nLa expresion no cumple con el Analisis Lexico, no se puede continuar");
        };
    }
    
    /**
     * Analisis Sintactico
     * se realiza el posfijo y el arbol sintactico
     */
    public static void ASi(ArrayList<Character> lista){
        //Decarar variables
        ArrayList<Character> arreglo = new ArrayList<Character>();
        Stack<Character> pila = new Stack<Character>();
        boolean bandera = true;

        //POSFIJO
        for(int i=0; i<lista.size(); i++){
            //pregunta si en la expresion hay algun numero
            if(lista.get(i) == '0' || lista.get(i) == '1' || lista.get(i) == '2' || lista.get(i) == '3'  || 
            lista.get(i) == '4'  || lista.get(i) == '5'  || lista.get(i) == '6'  || lista.get(i) == '7' || 
            lista.get(i) == '8'  || lista.get(i) == '9' ){
                
                arreglo.add(lista.get(i));
            }else{
                try{
                        
                    if(!pila.empty()){
                        char signo = lista.get(i);//Obtiene el operador
                        
                        switch(signo){//Busca el operador
                            case'*':
                                
                                if(pila.peek() == signo || pila.peek() == '/'){   
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                if(pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '('){
                                    pila.push(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                
                            break;
                            
                            case'/':
                                
                                if(pila.peek() == signo || pila.peek() == '*'){   
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                if(pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '('){
                                    pila.push(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                
                            break;
        
                            case'+':
                                
                                if(pila.peek() == signo || pila.peek() == '-'){   
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                if(pila.peek() == '*' || pila.peek() == '/'){
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    System.out.println(pila);
                                    break;
                                        
                                }
                                if(pila.peek() == '('){
                                    pila.push(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                
                            break;
        
                            case'-':
                                
                                if(pila.peek() == signo || pila.peek() == '+'){   
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                if(pila.peek() == '*' || pila.peek() == '/' ){
                                    arreglo.add(pila.pop());
                                    pila.add(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                if(pila.peek() == '('){
                                    pila.push(signo);
                                    //System.out.println(pila);
                                    break;
                                }
                                
                            break;
        
                            case'(':
                                pila.add(lista.get(i));                            
                            break;
        
                            case')':
                                for(int j=pila.size(); j>=0; j--){
                                    if(pila.peek() == '('){
                                        pila.pop();
                                        break;
                                    }
                                    if(pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '*' || pila.peek() == '/'){
                                        arreglo.add(pila.pop());
                                        //System.out.println(pila);
                                        
                                    }
                                }
                            break;
                                                       
                        }
                        
                        
                    }else{
                        pila.push(lista.get(i));
                        
                    }   
                }catch(Exception e){
                    System.out.println("ERROR SINTACTICO:");
                    bandera = false;
                    break;
                }
            }
            int lim = lista.size()-1;
            if(i == lim){
                int cont = pila.size();
                while (cont > 0 ) {
                    arreglo.add(pila.pop());
                    cont--;    
                }
                break;
            }
                    
            
                        
        }//Fin POSFIJO
        //si el posfijo esta bien pasa al arbol
        if(bandera){
            //ARBOL
            System.out.println("\nPosfijo finalizado con exito");
            ArbolSi(arreglo);
        }
        

    }
    /**
     * Arbol Sintatico 
     * se realiza el arbol
     */
    public static void ArbolSi(ArrayList<Character> arreglo){
        //Declarar variable
        ArrayList<Integer> posfijo = new ArrayList<Integer>();
        Stack<analizador> pilaArbol = new Stack<analizador>();
        boolean bandera = true;
        
        for(int i=0; i<arreglo.size(); i++){
            posfijo.add(arreglo.get(i).hashCode()-48);//arreglo se convierte a numeros
                        
        }

        for(int i=0; i<posfijo.size(); i++){
            
            //Si son numeros del 0 al 9
            if(posfijo.get(i) >= 0){
                pilaArbol.push(new analizador(posfijo.get(i)));

            }else{//else son operadores (*,+,-,/)
                
                analizador simbolo = (new analizador(posfijo.get(i)));//agrega el operador
                int signo = posfijo.get(i);//obtiene el operador
                if(signo < 0){//posiblemente innecesario
                    try{
                        for(int j=0;j<2;j++){
                            if(j==0){
                                simbolo.derecha = pilaArbol.pop();//agrega al lado derecho
                            }  
                            if(j==1){
                                simbolo.izquierda = pilaArbol.pop();//agrega al lado izquierdo
                            }
                        }
                        pilaArbol.push(simbolo);//agrega todo a pilaArbol
                    }
                    //si hay un error al crear el arbol
                    catch(Exception ex){
                        bandera = false;
                        System.out.println ("\nERROR SINTACTICO: en la creacion del árbol");
                        break;
                    }     
                }
            }
        }
        //pasa al Analisis Semantico
        if(bandera == true){
            System.out.println("Recorrido de Analisis Sintactico finalizado");
            ASe(posfijo);
        }

    }
    
    /**
     * Analisis Semantico
     * se realiza el arbol
     */
    public static void ASe(ArrayList<Integer> posfijo){
        //Declarar variables
        Stack<Integer> pilaSe = new Stack<Integer>();
        
        try{
            
            for(int i=0; i<posfijo.size(); i++){
                //Si es un numero 
                if(posfijo.get(i) >= 0 && posfijo.get(i) <= 9){
                    pilaSe.push(posfijo.get(i));
                    
                }
                //Si es un operador
                if(posfijo.get(i) < 0){
                    
                    int operacion = posfijo.get(i);//guardo el operador
                    int resultadoTemp = 0;// variable temporal
                    int num1 = 0;//guarda el primer numero
                    int num2 = 0;//guarda el segundo numero
                    switch(operacion){
                        //en caso de una suma (+)
                        case -5:
                            num1=pilaSe.pop();//botiene el primero numero
                            num2=pilaSe.pop();//obtiene el segundo numero
                            resultadoTemp = num2 + num1;//hace la suma
                            pilaSe.push(resultadoTemp);
                            
                        break;
                        //en caso de una suma (-)
                        case -3:
                            num1=pilaSe.pop();//botiene el primero numero
                            num2=pilaSe.pop();//obtiene el segundo numero
                            resultadoTemp = num2 - num1;//hace la resta
                            pilaSe.push(resultadoTemp);
                        break;
                        //en caso de una suma (*)
                        case -6:
                            num1=pilaSe.pop();//botiene el primero numero
                            num2=pilaSe.pop();//obtiene el segundo numero
                            resultadoTemp = num2 * num1;//hace la multiplicacion
                            pilaSe.push(resultadoTemp);
                        break;
                        //en caso de una suma (/)
                        case -1:
                            num1=pilaSe.pop();//botiene el primero numero
                            num2=pilaSe.pop();//obtiene el segundo numero
                            resultadoTemp = num2 / num1;//hace la division
                            pilaSe.push(resultadoTemp);
                        break;
                        
                    }
                }
            }
            System.out.println("\nRecorrido Semantico finalizado");
            
        }catch(Exception e){
            System.out.println ("\nERROR SEMANTICO: en la creacion del árbol");
        }
    }
}
