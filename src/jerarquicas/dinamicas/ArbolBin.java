package jerarquicas.dinamicas;

public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

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

    public boolean esVacio() {
        return this.raiz == null;
    }

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
        if(aux != null){
            // Si el padre del hijo es el nodo enviado, entonces
            if(aux.getElem().equals(hijo)){
                padreElem = padre;
            }else{
                // Busco al padre por el lado izquierdo
                padre = padreAux(aux.getIzquierdo(), hijo, padre);
                // Si no lo encuentra, lo busco por derecha
                if(padre == null){
                    padre = padreAux(aux.getDerecho(), hijo, padre);
                }
            }
        }

        return padreElem;
    }
}