package test.jerarquicas;
import jerarquicas.dinamicas.*;
import lineales.dinamicas.*;
public class TestArbolGen {
    public static void main(String[] args){
        ArbolGen arbol = new ArbolGen();
        Lista lis = new Lista();
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

        /*
                            A
                B           C           D
                E       F   G   H
            I   J   K
        */

        System.out.println(arbol.toString());
        lis = arbol.listarEntreNiveles(0, 3);
        System.out.println(lis.toString());

        System.out.println(arbol.esHermanoAnterior('F', 'D'));

    }
}
