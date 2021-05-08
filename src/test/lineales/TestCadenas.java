package test.lineales;

import lineales.dinamicas.*;

public class TestCadenas {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        Cola c3 = new Cola();
        Pila p1 = new Pila();

        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');

        c3 = c1.clone();
        c1.vaciar();
        System.out.println(c1.toString());
        System.out.println(c3.toString());
        System.out.println(c3.obtenerFrente());

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

        System.out.println(verificarBalanceo(c2));

        p1.apilar('R');
        p1.apilar('T');
        p1.apilar('Z');
        p1.apilar('@');
        p1.apilar('T');
        p1.apilar('Y');
        p1.apilar('@');
        p1.apilar('Z');
        p1.apilar('R');
        p1.apilar('@');
        p1.apilar('W');
        p1.apilar('Y');
        p1.apilar('X');

        System.out.println(p1.toString());
        System.out.println(formarLista(p1).toString());
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

    public static boolean verificarBalanceo(Cola q) {
        int cantIguales = 0;
        Lista lAux = new Lista();
        Pila pAux = new Pila();

        while (!q.esVacia()) {
            if ((char) q.obtenerFrente() == '{' || (char) q.obtenerFrente() == '}' || (char) q.obtenerFrente() == '('
                    || (char) q.obtenerFrente() == ')' || (char) q.obtenerFrente() == '['
                    || (char) q.obtenerFrente() == ']') {
                lAux.insertar(q.obtenerFrente(), 1);
            }
            q.sacar();
        }
        int longitud = lAux.longitud();

        if (longitud % 2 == 0) {
            for (int i = longitud; i > longitud / 2; i--) {
                pAux.apilar(lAux.recuperar(i));
                lAux.eliminar(i);
            }
            int i = longitud / 2;
            while (i > 0 && (char) lAux.recuperar(i) == invertido((char) pAux.obtenerTope())) {
                cantIguales++;
                i--;
                pAux.desapilar();
            }
        }
        return cantIguales == longitud / 2;
    }

    public static char invertido(char elem) {
        char retorno = '-';

        if (elem == '{') {
            retorno = '}';
        }
        if (elem == '(') {
            retorno = ')';
        }
        if (elem == '[') {
            retorno = ']';
        }

        return retorno;
    }

    // ------------ PARCIAL ------------
    public static Lista formarLista(Pila pila1){
        Lista lAux = new Lista();
        Pila pAux = new Pila();
        int cantCadenas = 1;

        while(!pila1.esVacia()){
            lAux.insertar(pila1.obtenerTope(), 1);
            pila1.desapilar();
        }

        for(int i = 1; i <= lAux.longitud(); i++){
            
            if((char)lAux.recuperar(i) == '@'){
                cantCadenas++;
                i++;
                if(cantCadenas % 2 == 0){
                    while(lAux.recuperar(i) == null|| (char)lAux.recuperar(i) != '@'){
                        pAux.apilar(lAux.recuperar(i));
                        lAux.eliminar(i);
                    }
                    while(!pAux.esVacia()){
                        lAux.insertar(pAux.obtenerTope(), i);
                        pAux.desapilar();
                        i++;
                    }
                }
            }
        }
        return lAux;
    }

}
