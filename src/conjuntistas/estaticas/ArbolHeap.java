/*
=================================================
|     Estructuras de Datos 2021                 |
=================================================
|      Clase:                                   |
|       > Arbol Heap Estatico                   |
|      Alumno:                                  |
|        > Manuel Felipe Triñanes (FAI-2738)    |
=================================================
*/

package conjuntistas.estaticas;

public class ArbolHeap {
    private static final int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo;

    // ---- Constructor ----
    public ArbolHeap() {
        this.ultimo = 0;
        this.heap = new Comparable[TAMANIO];
    }

    // ---- Insertar ----
    public boolean insertar(Comparable aux) {
        boolean exito = false;

        // Si al arreglo le queda al menos un lugar
        if (ultimo + 1 < TAMANIO) {
            // Lo guardo en la ultima posicion y lo hago subir de ser necesario
            exito = true;
            ultimo++;
            heap[ultimo] = aux;
            hacerSubir(ultimo);
        }
        return exito;
    }
    private void hacerSubir(int posHijo) {
        int posPadre;
        Comparable temp = this.heap[posHijo];
        boolean exito = true;

        // Mientras exito sea true (osea que el elemento a ingresar todavia no esta
        // en la posicion del mas chico posible)
        while (exito) {
            // La posicion del padre es la mitad que la del hijo
            posPadre = posHijo / 2;

            // Si la posicion del padre existe en el arbol
            if (posPadre >= 1) {
                // Si el elemento en la posicion del padre es mas grande que el hijo
                if (this.heap[posPadre].compareTo(temp) > 0) {
                    // Cambio a al padre de lugar y pongo al hijo en el suyo
                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = temp;
                    posHijo = posPadre;
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
    }

    // ---- Elimnar Cima ----
    public boolean eliminarCima(){
        boolean exito = false;

        if(this.ultimo != 0){
            this.heap[1] = this.heap[ultimo];
            this.ultimo++;

            hacerBajar(1);
            exito = true;
        }

        return exito;
    }
    private void hacerBajar(int posPadre){
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while(!salir){
            posH = posPadre * 2;
            if(posH <= this.ultimo){
                if(posH < this.ultimo){
                    if(this.heap[posH+1].compareTo(this.heap[posH]) < 0){
                        posH++;
                    }
                }
                if(this.heap[posH].compareTo(temp) < 0){
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                }else{
                    salir = true;
                }
            }else{
                salir = true;
            }
        }
    }

    // ---- Recuperar Cima ----
    public Comparable recuperarCima(){
        Comparable aRetornar = null;

        if(ultimo > 0){
            aRetornar = this.heap[1];
        }

        return aRetornar;
    }

    // ---- esVacio ----
    public boolean esVacio(){
        return this.ultimo <= 0;
    }

    // ---- To String ----
    public String toString() {
        String enTexto = "[ Heap Vacio ]";

        // Si el arbol no esta vacio
        if (this.ultimo != 0) {
            enTexto = "";

            // Desde 1 a la posicion final del arreglo
            for (int i = 1; i <= this.ultimo; i++) {
                // Guardo en la variable enTexto el elemento en la posicion i
                enTexto += this.heap[i].toString() + " :";

                // Si tiene hijo izquierdo lo guardo
                if (2 * i <= this.ultimo) {
                    enTexto += " HI->" + this.heap[2 * i].toString();
                }

                // Si tiene hijo derecho lo guardo
                if (2 * i + 1 <= this.ultimo) {
                    enTexto += " HD->" + this.heap[2 * i + 1].toString();
                }

                // Salto de linea
                enTexto += "\n";
            }
        }
        return enTexto;
    }
}
