import java.util.*;

import jdk.internal.net.http.common.FlowTube.TubePublisher;

//import jdk.internal.org.jline.reader.impl.DefaultParser.Bracket;
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
        ArrayList<Character> lista = new ArrayList<Character>();
        char val [] = {'0','1','2','3','4','5','6','7','8','9','+','-','*','/','(',')'};//Lenguaje valido
        
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

        //Confirmar para 
        if(list.length == lista.size()) {
            ArrayList<Character> exp = new ArrayList<Character>();//System.out.println(lista);
            for(int i=0; i<lista.size(); i++){
                exp.add(lista.get(i));
            }
            ASi(exp);
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
        ArrayList<Character> arreglo = new ArrayList<Character>();
        Stack<Character> pila = new Stack<Character>();
        boolean bandera = true;

        //POSFIJO
        for(int i=0; i<lista.size(); i++){

            if(lista.get(i) == '0' || lista.get(i) == '1' || lista.get(i) == '2' || lista.get(i) == '3'  || 
            lista.get(i) == '4'  || lista.get(i) == '5'  || lista.get(i) == '6'  || lista.get(i) == '7' || 
            lista.get(i) == '8'  || lista.get(i) == '9' ){
                
                arreglo.add(lista.get(i));
            }else{
                try{
                    /** 
                    if(lista.get(i) == '('){
                        pila.push(lista.get(i));
                        System.out.println(pila);
                        //break;
                    }*/
                        
                    if(!pila.empty()){
                        char signo = lista.get(i);
                        
                        
                        switch(signo){
                            case'*':
                                for(int j=pila.size()-1; j>=0; j--){
                                    if(pila.peek() == signo || pila.peek() == '/'){   
                                        arreglo.add(pila.pop());
                                        pila.add(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                    if(pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '('){
                                        pila.push(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                }
                            break;
                            
                            case'/':
                                for(int j=pila.size()-1; j>=0; j--){
                                    if(pila.peek() == signo || pila.peek() == '*'){   
                                        arreglo.add(pila.pop());
                                        pila.add(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                    if(pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '('){
                                        pila.push(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                }
                            break;
        
                            case'+':
                                for(int j=pila.size()-1; j>=0; j--){
                                    if(pila.peek() == signo || pila.peek() == '-'){   
                                        arreglo.add(pila.pop());
                                        pila.add(signo);
                                        System.out.println(pila);
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
                                        System.out.println(pila);
                                        break;
                                    }
                                }
                            break;
        
                            case'-':
                                for(int j=pila.size()-1; j>=0; j--){
                                    if(pila.peek() == signo || pila.peek() == '+'){   
                                        arreglo.add(pila.pop());
                                        pila.add(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                    if(pila.peek() == '*' || pila.peek() == '/' ){
                                        arreglo.add(pila.pop());
                                        pila.add(signo);
                                        System.out.println(pila);
                                        break;
                                    }
                                    if(pila.peek() == '('){
                                        pila.push(signo);
                                        System.out.println(pila);
                                        break;
                                    }
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
                                        System.out.println(pila);
                                        
                                    }
                                }
                            break;
                                                       
                        }
                        
                        
                    }else{
                        
                        if(arreglo.isEmpty() && pila.peek() == '('){
                            pila.push(lista.get(i));
                            System.out.println(pila);
                            //System.out.println("ERROR SINTACTICO: hay un operador al inicio de la expresion");
                            //bandera = false;
                            //break;
                        } else{
                            if(arreglo.isEmpty()){
                                if(lista.get(i) == '('){
                                    pila.push(lista.get(i)); 
                                    System.out.println(pila);
                                }
                                System.out.println("ERROR SINTACTICO: hay un operador al inicio de la expresion");
                                bandera = false;
                                break;
                            }else  {pila.push(lista.get(i)); 
                                System.out.println(pila);}
                        } //pila.push(lista.get(i));
                        
                        
                    }
                }catch(Exception e){
                    //System.out.println("ERROR SINTACTICO: hay un operador al inicio");
                }
            }
            int lim = lista.size()-1;
            if(i == lim){
                int cont = pila.size();
                while (cont > 0 ) {
                    arreglo.add(pila.pop());
                    cont--;    
                }
                //System.out.println(pila);
                break;
            }
                    
            
                        
        }//Fin POSFIJO
        System.out.println(arreglo);
        if(bandera){
            //ARBOL
            ArbolSi(arreglo);
        }
        

    }
    /**
     * Arbol Sintatico 
     * se realiza el arbol
     */
    public static void ArbolSi(ArrayList<Character> arreglo){
        ArrayList<Integer> posfijo = new ArrayList<Integer>();
        Nodos n = new Nodos();
        //ArrayList<ArrayList<Integer>> pp = new ArrayList<ArrayList<Integer>>();
        Stack<Nodos> pilaArbol = new Stack<Nodos>();
        //Objects nomber [] = {0,1,2,3,4,5,6,7,8,9};
        boolean bandera = true;
        
        for(int i=0; i<arreglo.size(); i++){
            //String num = arreglo.get(i).toString();
            //int val = num.hashCode();
            posfijo.add(arreglo.get(i).hashCode()-48);
            System.out.println(posfijo);
            //System.out.println(val);
        }
        for(int i=0; i<posfijo.size(); i++){
            
            //Si son numeros del 0 al 9
            if(posfijo.get(i) >= 0){
                pilaArbol.push(new Nodos(posfijo.get(i)));

            }else{//else son operadores (*,+,-,/)
                
                Nodos simbolo = (new Nodos(posfijo.get(i)));//agrega el operador
                int signo = posfijo.get(i);//obtiene el operador
                if(signo < 0){//posiblemente innecesario
                    try{
                        for(int j=0;j<2;j++){
                            if(j==0){
                                simbolo.derecha = pilaArbol.pop();
                            }  
                            if(j==1){
                                simbolo.izquierda = pilaArbol.pop();
                            }
                        }
                        pilaArbol.push(simbolo);
                    }
                    catch(Exception ex){
                        bandera = false;
                        System.out.println ("\nERROR SINTACTICO: en la creacion del árbol");
                        break;
                    }     
                }
            }
            
        }
        if(bandera == true){
            System.out.println("\nRecorrido postorden del árbol");
            //n.Tree(pilaArbol.peek());
            ASe(posfijo);
        }

    }
    
    /**
     * Analisis Semantico
     * se realiza el arbol
     */
    public static void ASe(ArrayList<Integer> posfijo){
        try{
            Stack<Integer> pilaSe = new Stack<Integer>();
            
            
            for(int i=0; i<posfijo.size(); i++){
                if(posfijo.get(i) >= 0 && posfijo.get(i) <= 9){
                    pilaSe.push(posfijo.get(i));
                    //System.out.println("\nTe entro" + posfijo.get(i));

                }
                if(posfijo.get(i) < 0){
                    
                    int operacion = posfijo.get(i);
                    int resultadoTemp = 0;
                    int num1 = 0;
                    int num2 = 0;
                    switch(operacion){
                        //en caso de una suma (+)
                        case -5:
                            num1=pilaSe.pop();
                            num2=pilaSe.pop();
                            resultadoTemp = num2 + num1;
                            pilaSe.push(resultadoTemp);
                            //System.out.println(pilaSe);
                        break;
                        //en caso de una suma (-)
                        case -3:
                            num1=pilaSe.pop();
                            num2=pilaSe.pop();
                            resultadoTemp = num1 - num2;
                            pilaSe.push(resultadoTemp);
                        break;
                        //en caso de una suma (*)
                        case -6:
                            num1=pilaSe.pop();
                            num2=pilaSe.pop();
                            resultadoTemp = num2 * num1;
                            pilaSe.push(resultadoTemp);
                        break;
                        //en caso de una suma (/)
                        case -1:
                            num1=pilaSe.pop();
                            num2=pilaSe.pop();
                            resultadoTemp = num2 / num1;
                            pilaSe.push(resultadoTemp);
                        break;
                        
                    }
                }
            }
            System.out.println("Recorrido Semantico finalizado");
            

        }catch(Exception e){
            System.out.println ("\nERROR SEMANTICO: en la creacion del árbol");
        }
    }
}
