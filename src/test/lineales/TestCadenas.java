package test.lineales;

import lineales.dinamicas.*;

public class TestCadenas {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();

        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        System.out.println(generar(c1).toString());

        c2.poner('{');
        c2.poner('5');
        c2.poner('+');
        c2.poner('[');
        c2.poner('8');
        c2.poner('*');
        c2.poner('9');
        c2.poner('-');
        c2.poner('(');
        c2.poner('4');
        c2.poner('/');
        c2.poner('2');
        c2.poner(')');
        c2.poner('+');
        c2.poner('7');
        c2.poner(']');
        c2.poner('-');
        c2.poner('1');
        c2.poner('}');
    }

    public static Cola generar(Cola c1) {
        Cola aRetornar = new Cola();
        Pila pAux = new Pila();
        Lista lAux = new Lista();

        while (!c1.esVacia()) {
            // Mientras la cola no este vacia y el frente sea distinto de #
            while (!c1.esVacia() && (char) c1.obtenerFrente() != '#') {
                // Apilo e inserto en la pila y lista auxiliares la cadena
                pAux.apilar(c1.obtenerFrente());
                lAux.insertar(c1.obtenerFrente(), 1);
                // Y eliminio el elemento ya almacenado
                c1.sacar();
            }

            // Almaceno en la cola a retornar el ultimo elemento de la lista
            // asi queda en la cola la cadena original
            for (int i = lAux.longitud(); i >= 1; i--) {
                aRetornar.poner(lAux.recuperar(i));
            }
            // Agrego a la cola a retornar la cadena invertida almacenada en la pila
            while (!pAux.esVacia()) {
                aRetornar.poner(pAux.obtenerTope());
                pAux.desapilar();
            }

            // Agrego a la cola la cadena original
            for (int i = lAux.longitud(); i >= 1; i--) {
                aRetornar.poner(lAux.recuperar(i));
            }

            // Mientras no este vacia, coloco # entre las cadenas
            if (!c1.esVacia()) {
                aRetornar.poner('#');
            }

            // Saco de c1 el # y vacio la lista auxiliar
            c1.sacar();
            lAux.vaciar();
        }

        return aRetornar;
    }

}
