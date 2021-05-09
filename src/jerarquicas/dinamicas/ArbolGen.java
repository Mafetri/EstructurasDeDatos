package jerarquicas.dinamicas;

import lineales.dinamicas.*;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    // ---- Insertar ----
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = false;
        NodoGen nuevo = new NodoGen(elemNuevo, null, null);

        if (this.raiz != null) {
            NodoGen padre = buscarPadre(this.raiz, elemPadre);

            // Si existe el padre
            if (padre != null) {
                // Si no tiene hijos le inserto el nuevo hijo
                if (padre.getHijoIzquierdo() == null) {
                    padre.setHijoIzquierdo(nuevo);
                } else {
                    // Sino apunto padre a su hijo izquierdo
                    padre = padre.getHijoIzquierdo();

                    // Para despues apuntarlo al hermano del extremo derecho
                    while (padre.getHermanoDerecho() != null) {
                        padre = padre.getHermanoDerecho();
                    }

                    // Una vez a la derecha de todos los hermanos, enlazo el hermano
                    // al nuevo nodo
                    padre.setHermanoDerecho(nuevo);
                }
                exito = true;
            }
        }
        return exito;
    }
    private NodoGen buscarPadre(NodoGen nodo, Object elemPadre) {
        NodoGen padre = null;
        if (nodo != null) {
            // Si el nodo es el padre buscado, guardo en padre este nodo
            if (nodo.getElem().equals(padre)) {
                padre = nodo;
            } else {
                // Sino busco en los hermanos derechos
                padre = buscarPadre(nodo.getHermanoDerecho(), padre);
                // Si padre sigue siendo nulo entonces busco en el proximo nivel (los hijos)
                if (padre == null) {
                    padre = buscarPadre(nodo.getHijoIzquierdo(), padre);
                }
            }
        }
        return padre;
    }

    // ---- Pertenece ----
    public boolean pertenece(Object elem){
        boolean exito = false;
        if(buscarPadre(this.raiz, elem) != null){
            exito = true;
        }
        return exito;
    }

    // ----

    // ---- Recorridos ----

    // ---- Inorden ----
    public Lista listarInorden() {
        Lista aRetornar = new Lista();
        if (this.raiz != null) {
            listarInordenAux(this.raiz, aRetornar);
        }
        return aRetornar;
    }
    private void listarInordenAux(NodoGen nodo, Lista ls) {
        if (nodo != null) {
            // Mientras el hijo izquierdo no sea nulo, llamo recursivamente con el hijo
            // izquierdo
            if (nodo.getHijoIzquierdo() != null) {
                listarInordenAux(nodo.getHijoIzquierdo(), ls);
            }

            // Inserto el elemento en la lista
            ls.insertar(nodo.getElem(), ls.longitud() + 1);

            // Si hay un hijo izquierdo, entonces recorro y guardo sus hermanos derechos
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    // ---- To String ----
    public String toString() {
        return toStringAux(this.raiz);
    }
    private String toStringAux(NodoGen nodo) {
        String enTexto = "";

        if (nodo != null) {
            enTexto += nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                enTexto += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = hijo.getHijoIzquierdo();
            while (hijo != null) {
                enTexto += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return enTexto;
    }
}
