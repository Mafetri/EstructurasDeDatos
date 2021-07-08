/*
=================================================
|     Estructuras de Datos 2021                 |
=================================================
|      Clase:                                   |
|       > Grafo Etiquetado Dinamico             |
|         (no esta pensado para multigrafos)    |
|      Alumnos:                                 |
|        > Manuel Felipe Triñanes (FAI-2738)    |
=================================================
*/

package grafos.grafo.etiquetados;

public class GrafoEtiq {
    private NodoVert inicio = null;

    // ---- Ubicar Vertice ----
    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        if (buscado != null) {
            while (aux != null && !aux.getElem().equals(buscado)) {
                aux = aux.getSigVertice();
            }
        }
        return aux;
    }

    // ---- Insertar Vertice ----
    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        if (elem != null) {
            NodoVert aux = this.ubicarVertice(elem);
            if (aux == null) {
                this.inicio = new NodoVert(elem, this.inicio, null);
                exito = true;
            }
        }
        return exito;
    }

    // ---- Eliminar Vertice ----
    public boolean eliminarVertice(Object elem) {
        boolean exito = false;
        if (elem != null && this.inicio != null) {
            NodoVert verticeAnterior = this.inicio;

            // Para cada vertice de la lista de vertice
            while (verticeAnterior != null) {
                // Caso especial que el elemento a eliminar es el inicio
                if (this.inicio.getElem().equals(elem)) {
                    this.inicio = this.inicio.getSigVertice();
                }

                // Busco al vertice anterior de elem para eliminar a elem
                if (verticeAnterior.getSigVertice() != null && verticeAnterior.getSigVertice().getElem().equals(elem)) {
                    verticeAnterior.setSigVertice(verticeAnterior.getSigVertice().getSigVertice());
                    exito = true;
                }

                eliminarAdyacente(verticeAnterior, elem);

                verticeAnterior = verticeAnterior.getSigVertice();
            }
        }
        return exito;
    }

    // ---- Insertar Arco ----
    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = false;
        // Si la etiquieta no es nula
        if (this.inicio != null && etiqueta != null) {
            // Busco al vertice de "origen" y el de "destino"
            NodoVert verticeOrigen = ubicarVertice(origen);
            NodoVert verticeDestino = ubicarVertice(destino);

            // Si ambos existe en la lista de vertices
            if (verticeOrigen != null && verticeDestino != null) {
                // Le agrego en la primera adyacencia de la lista un nuevo nodo adyacente con el
                // vertice de destino
                verticeOrigen.setPrimerAdy(new NodoAdy(verticeDestino, verticeOrigen.getPrimerAdy(), etiqueta));
                // Le agrego en la primera adyacencia de la lista un nuevo nodo adyacente con el
                // vertice de origen
                verticeDestino.setPrimerAdy(new NodoAdy(verticeOrigen, verticeDestino.getPrimerAdy(), etiqueta));

                exito = true;
            }
        }
        return exito;
    }

    // ---- Eliminar Arco ----
    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;

        if (this.inicio != null) {
            NodoVert verticeOrigen = ubicarVertice(origen);
            NodoVert verticeDestino = ubicarVertice(destino);

            if (verticeOrigen != null && destino != null) {
                eliminarAdyacente(verticeOrigen, destino);
                eliminarAdyacente(verticeDestino, origen);
            }
        }

        return exito;
    }

    // ---- Eliminar Adyacente ----
    // Busca dentro de los nodos adyacentes a nodo, el vertice que tenga como elemento a elem
    // y lo elimina de la lista de adyacencia del nodo
    private void eliminarAdyacente(NodoVert nodo, Object elem) {
        NodoAdy adyOrigen = nodo.getPrimerAdy();
        if (adyOrigen != null) {
            // Caso especial adyecente en primer lugar
            if (adyOrigen.getVertice().getElem().equals(elem)) {
                nodo.setPrimerAdy(adyOrigen.getSigAdyacente());
            } else {
                // Si no esta en la primera posicion, lo busco
                while (adyOrigen != null) {
                    // Si el siguiente adyacente es el elemento origen lo borro
                    if (adyOrigen.getSigAdyacente() != null
                            && adyOrigen.getSigAdyacente().getVertice().getElem().equals(elem)) {
                        nodo = adyOrigen.getSigAdyacente().getVertice();
                        adyOrigen.setSigAdyacente(adyOrigen.getSigAdyacente().getSigAdyacente());
                        adyOrigen = null;
                    } else {
                        adyOrigen = adyOrigen.getSigAdyacente();
                    }
                }
            }
        }

    }

    // ---- Existe Vertice ----
    public boolean existeVertice(Object nodo){
        return ubicarVertice(nodo) != null;
    }

    // ---- Existe Arco ----
    public boolean existeArco(Object origen, Object destino ){
        boolean existe = false;

        if(this.inicio != null && origen != null && destino != null){
            NodoAdy adyOrigen = ubicarVertice(origen).getPrimerAdy();
            while (adyOrigen != null) {
                // Si el siguiente adyacente es el elemento origen lo borro
                if (adyOrigen.getVertice().getElem().equals(destino)) {
                    existe = true;
                    adyOrigen = null;
                } else {
                    adyOrigen = adyOrigen.getSigAdyacente();
                }
            }
        }

        return existe;
    }

    // ---- Es Vacio ----
    public boolean esVacio() {
        return this.inicio == null;
    }

    // ---- To String ----
    public String toString() {
        String enTexto = "";
        NodoVert vertice = this.inicio;
        NodoAdy adyacente;

        while (vertice != null) {
            adyacente = vertice.getPrimerAdy();
            enTexto += vertice.getElem().toString() + ": ";
            while (adyacente != null) {
                enTexto += adyacente.getVertice().getElem().toString() + "(" + adyacente.getEtiqueta().toString() + ")";
                if (adyacente.getSigAdyacente() != null) {
                    enTexto += ", ";
                } else {
                    enTexto += ". ";
                }
                adyacente = adyacente.getSigAdyacente();
            }
            enTexto += "\n";
            vertice = vertice.getSigVertice();
        }
        return enTexto;
    }
}
