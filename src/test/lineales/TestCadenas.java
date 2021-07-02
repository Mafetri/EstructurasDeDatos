package test.lineales;

import lineales.dinamicas.*;

public class TestCadenas {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        Cola c3 = new Cola();
        Cola c4 = new Cola();
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

        System.out.println(c2.toString());
        System.out.println(verificarBalanceo(c2));

        p1.apilar('X');
        p1.apilar('Y');
        p1.apilar('W');
        p1.apilar('@');
        p1.apilar('R');
        p1.apilar('Z');
        p1.apilar('@');
        p1.apilar('Y');
        p1.apilar('T');
        p1.apilar('@');
        p1.apilar('Z');
        p1.apilar('T');
        p1.apilar('R');

        System.out.println(p1.toString());
        System.out.println(formarLista(p1).toString());

        c4.poner('8');
        c4.poner('9');
        c4.poner('6');
        c4.poner('&');
        c4.poner('6');
        c4.poner('9');
        c4.poner('8');
        c4.poner('8');
        c4.poner('9');
        c4.poner('6');
        c4.poner('&');
        c4.poner('1');
        c4.poner('7');
        c4.poner('&');
        c4.poner('7');
        c4.poner('1');
        c4.poner('1');
        c4.poner('7');

        
        System.out.println("===================================");
        System.out.println(c4.toString());
        System.out.println(controlarPatron2(c4));
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

            // Almaceno en la cola a retornar el ultimo elemento de la lista                    // SE PUEDE SIMPLIFICAR INGRESANDO PREVIAMENTE A LA LISTA LOS ITEMS EN ORDEN
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

    // ---- Verificar Balanceo ----
    // Este modulo guarda solo los corchetes en una lista, luego, si la lista tiene longitud par
    // apila de derecha a izquierda los elementos de la lista hasta la mitad
    // Luego compara la mitad que queda en la lista para ver si al invertirlo (ej: [ -> ]) conincide con
    // la ultima entrada a la pila. Si la cantidad de coincidentes es la misma que la longitud de la lista 
    // dividido 2 (la totalidad de corchetes) entonces esta balanceado
    // ESTE ALGORITMO ES MUY SIMILAR A COMO SE HARIA EN UN AUTOMATA A PILA
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

        switch(elem){
            case '{' : retorno = '}'; break;
            case '}' : retorno = '{'; break;
            case '(' : retorno = ')'; break;
            case ')' : retorno = '('; break;
            case '[' : retorno = ']'; break;
            case ']' : retorno = '['; break;

        }
        return retorno;
    }

    // ------------ PARCIAL ------------
    /*
    Punto a tener en cuenta:
        > Cada vez que agrego algo a la lista, tengo que si o si aumentar en uno la posicion
        > Si no esta parado en un arroba simplemente agrega los items hasta encontrarla
        > Si la encuentra se hace la pregunta si es par, si es impar hace una ejecucion aparte
          que es girar y agregar a la lista
          Si no es impar, entonces directamente hace de vuelta el loop y como no hay un arroba
          en el tope, entonces agrega normalmente el elemento a la lista
    */
    public static Lista formarLista(Pila p1){
        Lista lis = new Lista();
        Pila pAux = new Pila();
        int cantArrobas = 0;
        int pos = 1;

        while(!p1.esVacia()){
            if((char)p1.obtenerTope() == ('@')){
                // Inserto en la lista el arroba de la pila
                lis.insertar('@', pos);
                pos++;
                p1.desapilar();
                cantArrobas++;

                // Si la cantidad de arrobas es impar, si no es impar entonces que siga la ejecucion
                if(cantArrobas % 2 != 0){
                    // Agrego en la pila auxiliar, la cual da vuelta los items hasta que la pila enviada este vacia
                    // o que encuentre el proximo arroba
                    while(!p1.esVacia() && !((char)p1.obtenerTope() == ('@'))){
                        pAux.apilar(p1.obtenerTope());
                        p1.desapilar();
                    }
                    // Agrego a la lista a retornar los elementos de la pila auxiliar dados vuelta
                    while(!pAux.esVacia()){
                        lis.insertar(pAux.obtenerTope(), pos);
                        pAux.desapilar();
                        pos++;
                    }
                }
            }else{
                // Si no estoy parado en un arroba, agrego simplemente el item a la lista
                lis.insertar(p1.obtenerTope(), pos);
                p1.desapilar();
                pos++;
            }
        }
        return lis;
    }

    public static boolean controlarPatron(Cola q){
        boolean exito = true;
        Pila pAux = new Pila();
        Cola cAux = new Cola();

        while(!q.esVacia() && exito){
            while (!q.esVacia() && (char)q.obtenerFrente() != '&') {
                pAux.apilar(q.obtenerFrente());
                cAux.poner(q.obtenerFrente());
                q.sacar();
            }
            q.sacar();

            while(!q.esVacia() && !pAux.esVacia() &&(char)q.obtenerFrente() != '&' && exito){
                if(pAux.obtenerTope() != q.obtenerFrente()){
                    exito = false;
                }else{
                    pAux.desapilar();
                    q.sacar();
                }
            }

            if(exito){
                while(!q.esVacia() && !cAux.esVacia() && (char)q.obtenerFrente() != '&' && exito){
                    if(cAux.obtenerFrente() != q.obtenerFrente()){
                        exito = false;
                    }else{
                        cAux.sacar();
                        q.sacar();
                    }
                }
            }
            pAux.vaciar();
            cAux.vaciar();
            if(!q.esVacia() && (char)q.obtenerFrente() == '&'){
                q.sacar();
            }
        }

        return exito;
    }

    public static boolean controlarPatron2(Cola q){
        boolean exito = true;
        Pila pAux = new Pila();
        Cola cAux = new Cola();

        while(!q.esVacia() && exito){
            while (!q.esVacia() && (char)q.obtenerFrente() != '&') {
                pAux.apilar(q.obtenerFrente());
                cAux.poner(q.obtenerFrente());
                q.sacar();
            }
            q.sacar();
            while(!q.esVacia() && !pAux.esVacia() &&(char)q.obtenerFrente() != '&' && exito){
                if(pAux.obtenerTope() == q.obtenerFrente()){
                    pAux.desapilar();
                    q.sacar();
                }else{
                    exito = false;
                }
            }

            if(exito){
                while(!q.esVacia() && !cAux.esVacia() && (char)q.obtenerFrente() != '&' && exito){
                    if(cAux.obtenerFrente() == q.obtenerFrente()){
                        cAux.sacar();
                        q.sacar();
                    }else{
                        exito = false;
                    }
                }
            }
            
            if(!pAux.esVacia() || !cAux.esVacia()){
                exito = false;
            }

            if(!q.esVacia()){
                if((char)q.obtenerFrente() == '&'){
                    q.sacar();
                }else{
                    exito = false;
                }
            }
        }

        return exito;
    }
}
