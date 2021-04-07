/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Ejercicio 2.4                |
|                                   |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
*/

/*
En una nueva clase MixLineales en el paquete test.lineales, implementar el método: generarOtraCola(Cola
c1) que recibe por parámetro una estructura de tipo Cola c1 con elementos de tipo char que tiene el
siguiente formato: a1$a2$a3$. . . .$an, donde cada ai en una sucesión de letras mayúsculas y a partir de c1
debe generar como salida otra Cola de la forma: a1a1$a2a2$ . . . .$anan donde cada ai es la secuencia de
letras de ai
invertida. Ejemplo: Si c1 es : AB$C$DEF, la operación generarOtraCola devolverá una Cola
con el siguiente formato: ABBA$CC$DEFFED
*/

package test.lineales;

import lineales.dinamicas.*;

public class MixLineales {
    public static void main(String[] args) {
        Cola c2 = new Cola();
        Cola cola = new Cola();

        cola = generarCola();

        c2 = generarOtraCola(cola);

        System.out.println(cola.toString());
        System.out.println(c2.toString());
    }

    public static Cola generarOtraCola(Cola cola) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        Pila pilaAux = new Pila();

        c1 = cola.clone();

        // Mientras que la cola c1, copia de la original, no sea vacia
        while (c1.esVacia() == false) {
            // Si el char del frente de la cola no es $
            if ((char) c1.obtenerFrente() != '$') {
                // Apilo en la pila auxiliar el frente de la cola colonada
                pilaAux.apilar(c1.obtenerFrente());

                // Y pongo en la cola a retornar el frente de la cola clonada
                c2.poner(c1.obtenerFrente());

                // Saco el elemento de la cola que ya utilize
                c1.sacar();
            }

            // Si la cola esta vacia (osea que la pilaAux ya fue cargada con los ultimos
            // elementos)
            // o el frente de la cola sea el separador '$')
            if (c1.esVacia() == true || (char) c1.obtenerFrente() == '$') {
                // Saco el elemento del frente de la cola, si esta esta vacia no
                // realiza nada
                c1.sacar();

                // Y realizo un loop hasta que la pila esta vacia
                while (pilaAux.esVacia() == false) {
                    // Coloco en la cola a retornar el tope de la pila
                    c2.poner(pilaAux.obtenerTope());

                    // y lo quito de la pila
                    pilaAux.desapilar();
                }

                // Como ya no va a haber mas elementos a colocar del grupito, coloco el
                // separador $ y si la cola ya esta vacia no lo coloco ya que no va a haber mas
                // elementos
                if (!c1.esVacia()) {
                    c2.poner('$');
                }
            }
        }

        // Retorno la cola segun lo pedido
        return c2;
    }

    public static Cola generarCola() {
        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('$');
        c1.poner('C');
        c1.poner('$');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');

        return c1;
    }
}
