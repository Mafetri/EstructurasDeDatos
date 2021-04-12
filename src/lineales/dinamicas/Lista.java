/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Lista                     |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
*/

package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object elemento, int pos) {
        boolean exito = true;

        // Si la posicion a la que se quiere agregar el elemento es
        // mas chico que uno o mas grande que la longitud de la lista
        if (pos < 1 || pos > this.longitud + 1) {
            // No se puede agregar el elemento
            exito = false;
        } else {
            // Si la posicion es 1, entonces se lo quiere colocar al principio de la lista
            if (pos == 1) {
                this.cabecera = new Nodo(elemento, this.cabecera);
            } else {
                // Creo y apunto un nodo auxiliar al nodo de cabecera
                Nodo aux = this.cabecera;
                int i = 1;
                // Mientas i sea mas chico que la posicion a la que se quiere
                // guardar el elemento
                while (i < pos - 1) {
                    // Apunto aux a su enlace (proximo nodo)
                    aux = aux.getEnlace();
                    // E incremento i
                    i++;
                }
                // Una vez que aux este apuntando a la posicion a la que se quiere
                // que este el nuevo elemento entonces creo un nodo nuevo y lo enlazo
                // al elemento siquiente
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = false;

        // Si la lista no esta vacia (longitud > 0) y la posicion a eliminar es
        // mas grande o igual a 1 (si es mas chico no se podria eliminar nada)
        // y que es mas chico que la longitud (para no intentar eliminar algo que no
        // existe)
        if (this.longitud > 0 && 1 <= pos && pos <= this.longitud) {
            // Si se quiere eliminar el primer elemento, entonces apunto cabecera a su
            // enlace
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                // A un nodo auxiliar lo apunto a la cabecera
                Nodo aux = this.cabecera;
                int i = 1;
                // Voy apuntando el auxiliar al proximo nodo hasta llegar a la posicion deseada
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // Ahora conecto el nodo que apunta aux al que le sigue al siguiente
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
            this.longitud--;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        //Si la pos no es valida, osea que es mas chico que 1 o es mas grande que la longitud
        //entonces va a retornar POSICION NO VALIDA
        Object aRetornar = "POSICION NO VALIDA";
        
        if (this.longitud > 0 && 1 <= pos && pos <= this.longitud) {
            // A un nodo auxiliar lo apunto a la cabecera
            Nodo aux = this.cabecera;
            int i = 1;
            // Voy apuntando el auxiliar al proximo nodo hasta llegar a la posicion deseada
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            aRetornar = aux.getElem().toString();
        }
        return aRetornar;
    }

    public int localizar(Object elemento) {
        int localizacion = -1;
        int i = 1;
        Nodo aux = this.cabecera;
        // Mientras i sea mas chico o igual a longitud, busco el objeto         //HAY DOS FORMAS, PONER UN OR PARA QUE CORTE CUANDO ENCUENTRE EL ELEMENTO O FORZAR EL CORTE CUANDO LO ENCUENTRE O QUE GASTE TIEMPO DE EJECUCION DE MAS.
        while (i <= this.longitud) {
            // Si el elemento de aux es el buscado
            if (aux.getElem() == elemento) {
                // Guardo esa posicion
                localizacion = i;
                // Asigo la longitud+1 a i para que el while corte
                i = this.longitud + 1;
            } else {
                if (i != this.longitud) {
                    aux = aux.getEnlace();
                }
                i++;
            }
        }
        return localizacion;
    }
}
