package test.lineales;

import lineales.dinamicas.*;

public class PruebaLista {
    public static void main(String[] args) {
        Lista l1 = new Lista();
        Lista l2 = new Lista();

        l1.insertar(2, 1);
        l1.insertar(4, 2);
        l1.insertar(6, 3);

        l2.insertar(5, 1);
        l2.insertar(1, 2);
        l2.insertar(6, 3);
        l2.insertar(7, 4);

        Lista concatenada = concatenar(l1, l2).clone();

        System.out.println("Cadena 1: " + l1.toString());
        System.out.println("Cadena 2: " + l2.toString());
        System.out.println("Cadena concatenada: " + concatenada.toString());

    }

    public static Lista concatenar(Lista l1, Lista l2) {
        Lista concatenada = new Lista();
        int i;

        for (i = 1; i <= l1.longitud(); i++) {
            concatenada.insertar(l1.recuperar(i), i);
        }

        for (int j = 0; j < l2.longitud(); j++) {
            concatenada.insertar(l2.recuperar(j + 1), (i + j));
        }

        return concatenada;
    }
}
