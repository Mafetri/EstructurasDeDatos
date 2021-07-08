package grafos.grafo.etiquetados;

public class GrafoEtiq {
    private NodoVert inicio = null;

    // ---- Ubicar Vertice ----
    private NodoVert ubicarVertice(Object buscado){
        NodoVert aux = this.inicio;
        while(aux != null && !aux.getElem().equals(buscado)){
            aux = aux.getSigVertice();
        }
        return aux;
    }
    
    // ---- Insertar Vertice ----
    public boolean insertarVertice(Object elem){
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(elem);
        if(aux == null){
            this.inicio = new NodoVert(elem, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    // ---- Eliminar Vertice ----
    public boolean eliminarVertice(Object elem){
        boolean exito = false;
        NodoVert verticeAnterior = this.inicio;
        NodoAdy adyacente;
        // Para cada vertice de la lista de vertice
        while(verticeAnterior != null){
            // Para cada adyacente al vertice actual
            adyacente = verticeAnterior.getPrimerAdy();
            while(adyacente != null){
                // Busco si el siguiente del adyacete es el elemento a eliminar y lo elimino
                if(adyacente.getSigAdyacente() != null && adyacente.getSigAdyacente().getVertice().getElem().equals(elem)){
                    adyacente.setSigAdyacente(adyacente.getSigAdyacente().getSigAdyacente());
                }
            }
            // Busco al vertice anterior de elem para eliminar a elem
            if(verticeAnterior.getSigVertice() != null && verticeAnterior.getSigVertice().getElem().equals(elem)){
                verticeAnterior.setSigVertice(verticeAnterior.getSigVertice().getSigVertice());
                exito = true;
            }else{
                verticeAnterior = verticeAnterior.getSigVertice();
            }
        }
        return exito;
    }

    // ---- Insertar Arco ----
    public boolean insertarArco(Object origen, Object destino, Object etiqueta){
        boolean exito = false;
        // Si la etiquieta no es nula
        if(etiqueta != null){
            // Busco al vertice de "origen" y el de "destino"
            NodoVert verticeOrigen = ubicarVertice(origen);
            NodoVert verticeDestino = ubicarVertice(destino);
    
            // Si ambos existe en la lista de vertices
            if( verticeOrigen != null && verticeDestino != null){
                // Le agrego en la primera adyacencia de la lista un nuevo nodo adyacente con el vertice de destino
                verticeOrigen.setPrimerAdy(new NodoAdy(verticeDestino, verticeOrigen.getPrimerAdy(), etiqueta));
                // Le agrego en la primera adyacencia de la lista un nuevo nodo adyacente con el vertice de origen
                verticeDestino.setPrimerAdy(new NodoAdy(verticeOrigen, verticeDestino.getPrimerAdy(), etiqueta));
                
                exito = true;
            }
        }
        return exito;
    }

    // ---- To String ----
    public String toString(){
        String enTexto = "";
        NodoVert vertice = this.inicio;
        NodoAdy adyacente;
        while(vertice != null){
            adyacente = vertice.getPrimerAdy();
            enTexto += vertice.getElem().toString() + ": ";
            while(adyacente != null){
                enTexto += adyacente.getVertice().getElem().toString() + "(" + adyacente.getEtiqueta().toString() + ")";
                if(adyacente.getSigAdyacente() != null){
                    enTexto += ", ";
                }else{
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
