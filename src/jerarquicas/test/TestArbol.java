package jerarquicas.test;

import jerarquicas.dinamicas.*;

public class TestArbol {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();

        arbol.insertar("Hector", "", 'a');
        arbol.insertar("Jorge", "Hector", 'I');
        arbol.insertar("Alfredo", "Hector", 'D');
        arbol.insertar("Manuel", "Jorge", 'D');
        arbol.insertar("Lucia", "Jorge", 'I');

    }
}
