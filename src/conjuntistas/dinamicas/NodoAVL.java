package conjuntistas.dinamicas;

public class NodoAVL {
    private Comparable elem;
    private int alt;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elemento, NodoAVL izq, NodoAVL der){
        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;
        this.alt = 0;
    }

    public Comparable getElem(){
        return this.elem;
    }

    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }

    public NodoAVL getDerecho(){
        return this.derecho;
    }

    public int getAltura(){
        return this.alt;
    }

    public void recalcularAltura() {
        int altD = -1;
        int altI = -1;

        if (this.derecho != null) {
            altD = this.derecho.getAltura();
        }

        if (this.izquierdo != null) {
            altI = this.izquierdo.getAltura();
        }

        this.alt = Math.max(altI, altD) + 1;
    }
    
    public void setElem(Comparable unElem){
        this.elem = unElem;
    }

    public void setIzquierdo(NodoAVL izq){
        this.izquierdo = izq;
    }

    public void setDerecho(NodoAVL der){
        this.derecho = der;
    }
}
