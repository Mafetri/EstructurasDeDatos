package conjuntistas.dinamicas;

class NodoBB {
    private Comparable elem;
    private NodoBB izquierdo;
    private NodoBB derecho;

    public NodoBB(Comparable unElem, NodoBB izq, NodoBB der){
        this.elem = unElem;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public Comparable getElem(){
        return this.elem;
    }

    public NodoBB getIzquierdo(){
        return this.izquierdo;
    }

    public NodoBB getDerecho(){
        return this.derecho;
    }

    public void setElem(Comparable unElem){
        this.elem = unElem;
    }

    public void setIzquierdo(NodoBB izq){
        this.izquierdo = izq;
    }

    public void setDerecho(NodoBB der){
        this.derecho = der;
    }
}

