package test.jerarquicas;
import jerarquicas.dinamicas.*;
public class TestArbolGen {
    public static void main(String[] args){
        ArbolGen arbol = new ArbolGen();

        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');
        arbol.insertar('F', 'C');
        arbol.insertar('G', 'C');
        arbol.insertar('H', 'C');
        arbol.insertar('I', 'E');
        arbol.insertar('J', 'E');
        arbol.insertar('K', 'E');

        System.out.println(arbol.toString());
        System.out.println(arbol.listarInorden().toString());
        System.out.println(arbol.listarPreorden().toString());
        System.out.println(arbol.padre('J'));
        System.out.println(arbol.nivel('I'));
        
    }
}
