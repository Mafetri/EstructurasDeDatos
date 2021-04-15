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
        Object aRetornar;
        // Si la lista no esta vacia
        if (this.cabecera != null) {
            // Si la posicion ingresada es valida
            if (1 <= pos && pos <= this.longitud) {
                // A un nodo auxiliar lo apunto a la cabecera
                Nodo aux = this.cabecera;
                int i = 1;
                // Voy apuntando el auxiliar al proximo nodo hasta llegar a la posicion deseada
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                aRetornar = aux.getElem();
            } else {
                aRetornar = "POSICION NO VALIDA";
            }
        } else {
            aRetornar = "LISTA VACIA";
        }

        return aRetornar;
    }

    public int localizar(Object elemento) {
        int localizacion = -1;
        // Si la lista no esta vacia
        if (this.cabecera != null) {
            int i = 1;
            Nodo aux = this.cabecera;
            // Mientras i sea mas chico o igual a longitud y la cabecera no sea nulla
            // (osea que la lista esta vacia) y que localizacion sea -1 (todavia no fue
            // encontrado), busco el objeto
            while (localizacion == -1 && i <= this.longitud && this.cabecera != null) {
                // Si el elemento de aux es el buscado
                if (aux.getElem() == elemento) {
                    // Guardo esa posicion
                    localizacion = i;
                } else {
                    if (i != this.longitud) {
                        aux = aux.getEnlace();
                    }
                    i++;
                }
            }
        }

        return localizacion;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public Lista clone() {
        Lista clonada = new Lista();

        if (this.cabecera != null) {
            Nodo aux, aux2, nuevo;
            int i = 1;

            // Apunto aux a la cabecera
            aux = this.cabecera;

            // A la cabecera de clonada le asigno el elemento de la lista original
            clonada.cabecera = new Nodo(aux.getElem(), null);
            clonada.longitud = 1;

            // Apunto aux2 a la nueva cabecera de clonada
            aux2 = clonada.cabecera;

            while (i < this.longitud) {
                // Apunto aux a su propio enlace
                aux = aux.getEnlace();

                // Al nodo nuevo le asigno el elemento sin enlace
                nuevo = new Nodo(aux.getElem(), null);

                // Aumento la longitud de clonada
                clonada.longitud++;

                // Seteo el enlace de aux2 al nuevo nodo
                aux2.setEnlace(nuevo);

                // Apunto aux2 a su propio enlace
                aux2 = aux2.getEnlace();

                i++;
            }
        }

        return clonada;

    }

    public int longitud() {
        return this.longitud;
    }

    public String toString() {
        String aTexto = "[ ";
        if (this.cabecera != null) {
            int i = 1;
            Nodo aux = this.cabecera;
            while (i <= longitud) {
                // Si aux es igual a cabcera, entonces esta apuntando al primer nodo
                if (aux == this.cabecera) {
                    aTexto += aux.getElem().toString();
                } else {
                    aTexto += ", " + aux.getElem().toString();
                }

                // Si el enlace de aux no es nulo (osea que no esta apuntando a la ultima
                // posicion)
                if (aux.getEnlace() != null) {
                    // Lo apunto al siguiente nodo
                    aux = aux.getEnlace();
                }

                i++;
            }
        } else {
            aTexto += "- LISTA VACIA -";
        }

        aTexto += " ]";

        return aTexto;
    }

    public void invertir() {
        if (this.cabecera != null) {
            // Envia la cabecera a un modulo recursivo que crea nodos
            // sin enlace igules a los originales y al desapilar los enalaza al reves
            invertirNodo(this.cabecera);
        }
    }

    private Nodo invertirNodo(Nodo aux) {
        // Creo un nodo en cada llamado recursivo con el elemento del nodo
        // llamado y sin enlace
        Nodo anterior = new Nodo(aux.getElem(), null);

        // Mientras en enlace de anterior no sea nulo, no he llagado al final
        if (aux.getEnlace() != null) {
            // Hago el llamado recursivo apuntando anterior a su propio enlace
            aux = invertirNodo(aux.getEnlace());

            // Enlazo auxiliar con el nodo anterior (por esa razon se llama asi)
            aux.setEnlace(anterior);

            // Apunto aux al nodo anterior
            aux = anterior;
        } else {
            // Cuando llego al final, apunto aux al nodo anterior
            aux = anterior;

            // Y ademas apunto la cabecera a aux ya que ahora aux es
            // el inicio de la lista invertida
            this.cabecera = aux;
        }

        return aux;
    }

    // Muy pero muy ineficiente y no funcional
    public void eliminarApariciones(Object elemento) {
        Nodo aux = this.cabecera;

        while (aux != null) {
            // Si la cabecera de la lista es igual a aux
            if (this.cabecera == aux) {
                // Y si el elemento de aux es igual al que estoy buscando
                if (aux.getElem() == elemento) {
                    // Y ademas si el enlace de aux no es nulo (osea que queda al menos un nodo mas)
                    if (aux.getEnlace() != null) {
                        // Muevo cabecera al nodo que acabo de analizar, reduzco longitud
                        // y avanzo en la lectura de nodos
                        this.cabecera = aux.getEnlace();
                        this.longitud--;
                        aux = aux.getEnlace();
                    } else {
                        // Sino, quiere decir que estoy en el ultimo nodo por ende
                        // como coinciden aux con cabecera y el ultimo elemento es nulo
                        // debo vaciar la lista
                        this.cabecera = null;
                        aux = null;
                        this.longitud = 0;
                    }
                }
            }

            // Si el elemento no es nulo y la cabecera y aux no son iguales
            if (aux != null && aux != this.cabecera) {
                // Y si la longitud es al menos mayor a 1 (quedan elementos)
                if (this.longitud > 1) {
                    // Entonces analizo si el siguiente elemento es el que quiero
                    if (aux.getEnlace().getElem() == elemento) {
                        // Si es asi, enlazo el nodo actual no al proximo sino al siguiente
                        aux.setEnlace(aux.getEnlace().getEnlace());
                        // Y como elimine un nodo, reduzco la longitud de la lista
                        this.longitud--;
                    }
                } else { // Si no quedan elementos entonces el nodo en el que estoy parado
                    aux.setEnlace(null);
                    this.longitud--;
                }
                aux = aux.getEnlace();

            }
        }

    }

    public Lista obtenerMultiplos(int num) {
        Lista multiplos = new Lista();

        // Si el numero es valido y la lista no esta vacia
        if (this.cabecera != null && num > 0) {
            int i = 1;
            Nodo aux = this.cabecera;
            Nodo aux2 = new Nodo(null, null);

            // Mientras i no sea mayor que la longitud de la lista origianl
            while (i <= this.longitud) {
                // Si i es multiplo de num entonces
                if (i % num == 0) {
                    // Si i es igual a num, quiere decir que es la primera posicion a llenar
                    if (i == num) {
                        multiplos.cabecera = new Nodo(aux.getElem(), null);

                        aux2 = multiplos.cabecera;
                    } else {
                        // Creo un nodo con el elemento de aux (lista original)
                        Nodo nuevo = new Nodo(aux.getElem(), null);

                        // Seteo el enlace de aux2 con el nuevo nodo
                        aux2.setEnlace(nuevo);

                        // Apunto aux2 a su enlace (el nuevo nodo)
                        aux2 = aux2.getEnlace();
                    }

                    // Y aumento la longitud de la lista en 1
                    multiplos.longitud++;
                }

                // Avanzo aux al proximo nodo
                aux = aux.getEnlace();

                i++;
            }

        }

        return multiplos;
    }
}
