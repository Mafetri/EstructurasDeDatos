package test.jerarquicas;

import jerarquicas.dinamicas.*;
import lineales.dinamicas.*;

public class TestArbol {
    public static void main(String[] args) {
        // ARBOL FAM: https://imgur.com/a/byUNAhM
        ArbolBin fam = new ArbolBin();
        ArbolBin fam2 = new ArbolBin();
        Lista fam3 = new Lista();

        fam.insertar("Hector", "p", 'z');
        fam.insertar("Jorge", "Hector", 'I');
        fam.insertar("Alfredo", "Hector", 'D');
        fam.insertar("Manuel", "Jorge", 'I');
        fam.insertar("Lucia", "Jorge", 'D');
        fam.insertar("Jack", "Manuel", 'I');
        fam.insertar("Curie", "Lucia", 'D');
        fam.insertar("Rocco", "Lucia", 'I');
        fam.insertar("Rodrigo", "Alfredo", 'I');
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

        System.out.println("PreOrden: \n" + fam2.listarPreorden().toString());
        System.out.println("InOrden: \n" + fam2.listarInorden().toString());
        System.out.println("PosOrden: \n" + fam2.listarPosorden().toString());
        System.out.println("Por nivel: \n" + fam2.listarPorNiveles().toString());

        System.out.println(fam2.frontera().toString());

        // Verificar patron desde la raiz
        System.out.println("=========================");
        fam3.insertar("Hector", 1);
        fam3.insertar("Jorge", 2);
        fam3.insertar("Manuel", 3);
        fam3.insertar("Jack", 4);
        System.out.println(fam2.toString());
        System.out.println(fam2.verificarPatron(fam3));

        System.out.println(fam2.justificar().toString());

        fam2.cambiarHijos("Hector", "Jose", "Jorge");
        System.out.println(fam2.toString());

        ArbolBin parcial = new ArbolBin();
        ArbolBin parcial2 = new ArbolBin();
        ArbolBin parcial3 = new ArbolBin();
        ArbolBin parcial4 = new ArbolBin();
        ArbolBin parcial5 = new ArbolBin();
        ArbolBin parcial6 = new ArbolBin();
        parcial.insertar(34, "p", 'd');
        parcial.insertar(25, 34, 'I');
        parcial.insertar(17, 34, 'D');
        parcial.insertar(71, 25, 'I');
        parcial.insertar(63, 71, 'D');
        parcial.insertar(12, 17, 'I');
        parcial.insertar(23, 17, 'D');

        parcial2.insertar(34, "p", 'd');
        parcial2.insertar(25, 34, 'I');
        parcial2.insertar(67, 34, 'D');
        parcial2.insertar(71, 25, 'I');
        parcial2.insertar(63, 71, 'D');
        parcial2.insertar(12, 67, 'I');
        parcial2.insertar(23, 67, 'D');

        parcial3.insertar(34, "p", 'd');
        parcial3.insertar(25, 34, 'I');
        parcial3.insertar(17, 34, 'D');
        parcial3.insertar(71, 25, 'I');
        parcial3.insertar(12, 17, 'I');
        parcial3.insertar(23, 17, 'D');
        parcial3.insertar(63, 12, 'I');

        parcial4.insertar(34, "p", 'd');
        parcial4.insertar(25, 34, 'I');
        parcial4.insertar(17, 34, 'D');
        parcial4.insertar(71, 25, 'I');
        parcial4.insertar(12, 17, 'I');
        parcial4.insertar(23, 17, 'D');
        parcial4.insertar(63, 12, 'I');

        System.out.println(parcial.toString());
        System.out.println(parcial2.toString());
        System.out.println(parcial3.toString());
        System.out.println(parcial4.toString());
        System.out.println(parcial3.verificarDistintos(parcial4));
    }
}
