/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Arbol Binario             |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
*/

package jerarquicas.dinamicas;

import lineales.dinamicas.*;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    // ---- Insertar ----
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;

        // Si el arbol esta vacio
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // Si no lo esta busco al padre del elemento
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);

            // Si el padre existe
            if (nodoPadre != null) {
                // Si el lugar deseado es a la izquierda y el nodo padre es nulo a la izuierda
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    // Entonces seteo el lado izquierdo del padre con un nuevo nodo con elemNuevo
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else {
                    // Si lugar es D (derecha) y el nodo padre a la derecha es nulo
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        // Entonces le seteo el nuevo nodo
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                    } else {
                        // Si el padre no existe no ya tiene dos hijos, entonces retorna falso
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    private NodoArbol obtenerNodo(NodoArbol n, Object buscando) {
        NodoArbol resultado = null;

        // Si el nodo no es nulo
        if (n != null) {
            // Si el elemento que estoy buscando esta en n, resultado es el nodo n
            if (n.getElem().equals(buscando)) {
                resultado = n;
            } else {
                // Sino busco recursivamente hijo de la rama izquierda
                resultado = obtenerNodo(n.getIzquierdo(), buscando);

                // Si resultado sigue siendo null, entonces no esta en la rama izquierda
                if (resultado == null) {
                    // Por ende busco en la rama derecha
                    resultado = obtenerNodo(n.getDerecho(), buscando);
                }
            }
        }

        return resultado;
    }

    // ---- Es Vacio ----
    public boolean esVacio() {
        return this.raiz == null;
    }
    
    // ---- Padre ----
    public Object padre(Object hijo) {
        Object padre = null;

        // Si el arbol no esta vacio
        if (this.raiz != null) {
            // Busco a su padre desde la raiz
            padre = padreAux(this.raiz, hijo, null);
        }

        return padre;
    }
    private Object padreAux(NodoArbol aux, Object hijo, Object padre) {
        Object padreElem = null;

        // Si el nodo enviado es valido
        if (aux != null) {
            // Si el padre del hijo es el nodo enviado, entonces
            if (aux.getElem().equals(hijo)) {
                padreElem = padre;
            } else {
                // Busco al padre por el lado izquierdo
                padre = padreAux(aux.getIzquierdo(), hijo, padre);
                // Si no lo encuentra, lo busco por derecha
                if (padre == null) {
                    padre = padreAux(aux.getDerecho(), hijo, padre);
                }
            }
        }

        return padreElem;
    }

    // ---- Altura ----
    public int altura() {
        int alt = -1;

        // Si la raiz es nula, entonces el arbol esta vacio
        if (this.raiz != null) {
            // Calcula la altura del arbol -1 (ya que la raiz vale 0)
            alt = alturaAux(this.raiz) - 1;
        }

        return alt;
    }
    private int alturaAux(NodoArbol aux) {
        // alt representa la altura izquierda y altD la derecha
        int alt = 0;
        int altD = 0;

        // Mientras aux no valga nulo (fin del arbol)
        if (aux != null) {
            // Cuento la altura por izquierda recursivamente
            alt = alturaAux(aux.getIzquierdo()) + 1;

            // Cuento la altura por derecha recursivamente
            altD = alturaAux(aux.getDerecho()) + 1;

            // Si la altura derecha es mas grande que la izquierda, entonces
            // almaceno la derecha en izquierda
            if (altD > alt) {
                alt = altD;
            }
        }

        return alt;
    }

    // ---- Nivel ----
    public int nivel(Object elem) {
        int niv = -1;

        // Si el elemento enviado es valido y el arbol no esta vacio
        if (elem != null && this.raiz != null) {
            niv = nivelAux(this.raiz, elem, 0);
        }

        return niv;
    }
    private int nivelAux(NodoArbol aux, Object elem, int profundidad) {
        int nivel = -1;

        // Si aux no es nulo
        if (aux != null) {
            // Si encontre al elemento, entonces esta en la profundidad enviada
            if (aux.getElem().equals(elem)) {
                nivel = profundidad;
            } else {
                // Si no lo encuentro entonces recorro la izquierda aumentando la profundidad
                nivel = nivelAux(aux.getIzquierdo(), elem, profundidad + 1);
                // Si sigue siendo el nivel -1, quiere decir que no lo encontre en la izquierda
                if (nivel == -1) {
                    // Lo busco en la derecha
                    nivel = nivelAux(aux.getDerecho(), elem, profundidad + 1);
                }
            }
        }

        return nivel;
    }

    // ---- Vaciar ----
    public void vaciar() {
        this.raiz = null;
    }

    // ---- Clone ----
    public ArbolBin clone() {
        ArbolBin clone = new ArbolBin();

        if (this.raiz != null) {
            clone.raiz = cloneAux(this.raiz);
        }

        return clone;
    }
    private NodoArbol cloneAux(NodoArbol aux) {
        NodoArbol hijo = null;
        if (aux != null) {
            hijo = new NodoArbol(aux.getElem(), cloneAux(aux.getIzquierdo()), cloneAux(aux.getDerecho()));
        }
        return hijo;
    }

    // ---- toString ----
    public String toString() {
        String enTexto = "[ ARBOL VACIO ]";

        if (this.raiz != null) {
            enTexto = toStringAux(this.raiz);
        }

        return enTexto;
    }
    private String toStringAux(NodoArbol aux) {
        String enTexto = "";
        if (aux != null) {
            // enTexto += aux.getElem().toString() + ": " + "[ " + "HI: " +
            // aux.getIzquierdo().getElem() + " HD: " + aux.getDerecho().getElem() + " ]";
            enTexto += aux.getElem().toString() + "  HI: ";
            if (aux.getIzquierdo() == null) {
                enTexto += " - ";
            } else {
                enTexto += aux.getIzquierdo().getElem().toString();
            }
            enTexto += "  HD: ";
            if (aux.getDerecho() == null) {
                enTexto += " - ";
            } else {
                enTexto += aux.getDerecho().getElem().toString();
            }
            enTexto += "\n";
            enTexto += toStringAux(aux.getIzquierdo());
            enTexto += toStringAux(aux.getDerecho());
        }

        return enTexto;
    }

    // ---- Preorden ----
    public Lista preOrden() {
        Lista lis = new Lista();

        if( this.raiz != null){
            listaPreOrdenAux(this.raiz, lis);
        }

        return lis;
    }
    private void listaPreOrdenAux(NodoArbol aux, Lista lis) {
        if (aux != null) {
            // Visita el elemento en el primer nodo
            lis.insertar(aux.getElem(), lis.longitud() + 1);

            // Visita los hijos
            listaPreOrdenAux(aux.getIzquierdo(), lis);
            listaPreOrdenAux(aux.getDerecho(), lis);
        }

    }

    // ---- Posorden ----
    public Lista posOrden(){
        Lista lis = new Lista();

        if( this.raiz != null){
            listaPosOrdenAux(this.raiz, lis);
        }
        
        return lis;
    }
    private void listaPosOrdenAux(NodoArbol aux, Lista lis){
        if(aux != null){
            lis.insertar(aux.getElem(), 1);
            listaPosOrdenAux(aux.getDerecho(), lis);
            listaPosOrdenAux(aux.getIzquierdo(), lis);
        }
    }

    // ---- Inorden ----
    public Lista inOrden(){
        Lista lis = new Lista();

        if( this.raiz != null){
            listaInOrdenAux(this.raiz, lis);
        }

        return lis;
    }
    private void listaInOrdenAux(NodoArbol aux, Lista lis){
        if(aux != null){
            listaInOrdenAux(aux.getDerecho(), lis);
            lis.insertar(aux.getElem(), 1);
            listaInOrdenAux(aux.getIzquierdo(), lis);
        }
    }

    // ---- Por niveles ----
    public Lista listarNiveles() {
        Cola cAux = new Cola();     // -> Alamcenara los nodos de los hijos del nodo que se esta analizando
        Lista lis = new Lista();    // -> Almacenara la los elementos en una lista y los retornara
        NodoArbol nodo;
        int i = 1;

        // Coloco en la cola el nodo raiz
        cAux.poner(this.raiz);

        //Mientras que la cola no este vacia voy a pilando los elementos de los hijos de los nodos analizados
        while (!cAux.esVacia()) {
            // Guardo en nodo el nodo que esta en el frente de la cola y lo saco de la cola
            nodo = (NodoArbol)cAux.obtenerFrente();
            cAux.sacar();

            // Alamaceno en la lista en la posicion i, el elemento del nodo
            lis.insertar(nodo.getElem(), i);
            i++;

            // Si existe un hijo izquierdo
            if (nodo.getIzquierdo() != null){
                // Lo coloco en la cola para que en el proximo loop, lo analize
                cAux.poner(nodo.getIzquierdo());
            }
            // Si existe un hijo derecho
            if (nodo.getDerecho() != null){
                // Idem que izquierda
                cAux.poner(nodo.getDerecho());
            }
        }
        return lis;
    }

    // ---- Frontera ----
    public Lista frontera() {
        Lista lis = new Lista();

        if(this.raiz != null){
            fronteraAux(this.raiz, lis);
        }

        return lis;
    }
    private void fronteraAux(NodoArbol aux, Lista lis) {
        if (aux != null) {
            if(aux.getIzquierdo() == null && aux.getDerecho() == null){
                // Visita el elemento en el primer nodo
                lis.insertar(aux.getElem(), lis.longitud()+1);
            }

            // Visita los hijos
            fronteraAux(aux.getIzquierdo(), lis);
            fronteraAux(aux.getDerecho(), lis);
        }

    }
}