package test.jerarquicas;

import jerarquicas.dinamicas.*;

public class TestArbol {
    public static void main(String[] args) {
        // ARBOL FAM: https://imgur.com/a/byUNAhM
        ArbolBin fam = new ArbolBin();
        ArbolBin fam2 = new ArbolBin();

        fam.insertar("Hector", "p", 'z');
        fam.insertar("Jorge", "Hector", 'I');
        fam.insertar("Alfredo", "Hector", 'D');
        fam.insertar("Manuel", "Jorge", 'I');
        fam.insertar("Lucia", "Jorge", 'D');
        fam.insertar("Jack", "Manuel", 'I');
        fam.insertar("Curie", "Lucia", 'D');
        fam.insertar("Rocco", "Lucia", 'I');
        fam.insertar("Rodrigo", "Alfredo", 'I');
        
        fam2 = fam.clone();

        System.out.println("Familia 1: \n" + fam.toString());
        System.out.println("Familia altura: " + fam.altura());
        System.out.println("Familia nivel de Rocco: " + fam.nivel("Rocco"));

        fam.vaciar();
        System.out.println("\n");

        System.out.println("Familia 2: \n" + fam2.toString());
        System.out.println("Familiar 2 Altura: " + fam2.altura());
        System.out.println("Familia 2 Nivel de Rocco: " + fam2.nivel("Rocco"));

        System.out.println("\n");

        System.out.println("PreOrden: \n" + fam2.preOrden().toString());
        System.out.println("InOrden: \n" + fam2.inOrden().toString());
        System.out.println("PosOrden: \n" + fam2.posOrden().toString());
        System.out.println("Por nivel: \n" + fam2.listarNiveles().toString());

        System.out.println(fam2.frontera().toString());
    }
}
