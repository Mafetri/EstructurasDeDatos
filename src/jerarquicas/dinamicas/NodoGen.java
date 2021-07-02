package jerarquicas.dinamicas;

class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    public NodoGen(Object elemento, NodoGen izq, NodoGen der){
        elem = elemento;
        hijoIzquierdo = izq;
        hermanoDerecho = der;
    } 

    public Object getElem(){
        return elem;
    }

    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }

    public void setElem(Object elemento){
        elem = elemento;
    }

    public void setHijoIzquierdo(NodoGen izq){
        hijoIzquierdo = izq;
    }

    public void setHermanoDerecho(NodoGen der){
        hermanoDerecho = der;
    }

}
