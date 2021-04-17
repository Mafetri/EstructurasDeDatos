package test.jerarquicas;
import jerarquicas.dinamicas.*;

public class TestArbol {
    public static void main(String[] args){
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

        System.out.println(fam.toString());
        System.out.println(fam.altura());
        System.out.println(fam.nivel("Hector")); 

        fam.vaciar();

        System.out.println(fam2.toString());
        System.out.println(fam2.altura());
        System.out.println(fam2.nivel("Hector")); 

        System.out.println(fam2.toString());

        System.out.println(fam2.preOrden().toString());
        System.out.println(fam2.inOrden().toString());
        System.out.println(fam2.posOrden().toString());
    }
}
