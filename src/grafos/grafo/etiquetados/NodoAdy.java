package grafos.grafo.etiquetados;

class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert unVertice, NodoAdy unSigAdyacente, Object unaEtiqueta) {
        vertice = unVertice;
        sigAdyacente = unSigAdyacente;
        etiqueta = unaEtiqueta;
    }

    public NodoVert getVertice() {
        return vertice;
    }
    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }
    public Object getEtiqueta() {
        return etiqueta;
    }
    public void setVertice(NodoVert nodo){
        vertice = nodo;
    }
    public void setSigAdyacente(NodoAdy nodo){
        sigAdyacente = nodo;
    }
    public void setEtiqueta(Object unaEtiqueta){
        etiqueta = unaEtiqueta;
    }
}
