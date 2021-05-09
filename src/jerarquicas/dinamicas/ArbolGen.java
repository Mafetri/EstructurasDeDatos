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
    public boolean pertenece(Object elem) {
        boolean exito = false;
        if (this.raiz != null && buscarPadre(this.raiz, elem) != null) {
            exito = true;
        }
        return exito;
    }

    // ---- Ancestros ----
    public Lista ancestros(Object elem) {
        Lista lis = new Lista();
        // Si el arbol no esta vacio y el primer elemento no es el elemento buscado
        if (this.raiz != null && !this.raiz.getElem().equals(elem)) {
            ancestrosAux(elem, lis, this.raiz);
        }
        return lis;
    }

    private boolean ancestrosAux(Object elem, Lista lis, NodoGen nodo) {
        boolean exito = false;

        if (nodo != null) {
            // Si el elemento buscado es el nodo actual, la busqueda fue exitosa
            if (nodo.getElem().equals(elem)) {
                exito = true;
            } else {
                // Si no lo fue, busco en su hijo si es exitosa
                exito = ancestrosAux(elem, lis, nodo.getHijoIzquierdo());
                if (exito) {
                    // Si fue encontrado en si hijo lo agrego a la lista
                    lis.insertar(nodo.getElem(), lis.longitud() + 1);
                } else {
                    // Sino busco entre sus hermanos
                    exito = ancestrosAux(elem, lis, nodo.getHermanoDerecho());
                }
            }
        }

        return exito;
    }

    // ---- Es Vacio ----
    public boolean esVacio() {
        return this.raiz == null;
    }

    // ---- Altura ----
    public int altura() {
        int alt = -1;

        if (this.raiz != null) {
            alt = alturaAux(this.raiz);
        }

        return alt;
    }

    private int alturaAux(NodoGen nodo) {
        int alt = 0;
        int altD = 0;

        if (nodo != null) {
            // Profundizo por izquierda
            alt = alturaAux(nodo.getHijoIzquierdo()) + 1;

            // Ahora profundizo a traves de los hermanos
            while (nodo.getHermanoDerecho() != null) {
                // Apunto el nodo al hermano
                nodo = nodo.getHermanoDerecho();

                // Si tiene un hijo profundizo
                if (nodo.getHijoIzquierdo() != null) {
                    altD = alturaAux(nodo.getHijoIzquierdo()) + 1;
                }
            }

            // Si la altura a traves de los hermanos es mas alta que la de la izq
            if (altD > alt) {
                alt = altD;
            }
        }

        return alt;
    }

    // ---- Nivel ----
    public int nivel(Object elem) {
        int niv = -1;
        if (this.raiz != null) {
            niv = nivelAux(this.raiz, elem);
        }
        return niv;
    }
    private int nivelAux(NodoGen nodo, Object elem) {
        int niv = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                niv = 0;
            } else {
                // Busco entre los hermanos sin aumentar el nivel, ya que estoy en el mismo
                niv = nivelAux(nodo.getHermanoDerecho(), elem);

                // Si todavia no lo encontro osea que nivel sigue siendo -1
                if (niv == -1) {
                    // Bajo de nivel y busco en el hijo
                    niv = nivelAux(nodo.getHijoIzquierdo(), elem) + 1;          
                }
            }
        }
        return niv;
    }

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
