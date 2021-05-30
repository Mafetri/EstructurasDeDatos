/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Tabla Hash (abierta)      |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
 */

package conjuntistas.dinamicas;
import lineales.dinamicas.Lista;

public class HashAbierto {
    private static final int TAMANIO = 20;
    private Nodo[] tabla;
    private int cant;

    public HashAbierto() {
        this.tabla = new Nodo[TAMANIO - 1];
        cant = 0;
    }

    // ---- Insertar ----
    public boolean insertar(Object elem) {
        int pos = elem.hashCode() % TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;

        // Busco si existe en los nodos de la posicion hash de elem
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }

        // Si no lo encontro, entonces crea un nuevo nodo con elem y el enlace al otro
        // nodo que esta en la posicion
        if (!encontrado) {
            this.tabla[pos] = new Nodo(elem, this.tabla[pos]);
            this.cant++;
        }

        return !encontrado;
    }

    // ---- Eliminar ----
    public boolean eliminar(Object elem) {
        int pos = elem.hashCode() % TAMANIO;
        int posNodo = 0;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;

        // Busco el elem
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
            posNodo++;
        }
        
        // Si fue encontrado entonces lo elimino
        if (encontrado) {
            // Si el elemento esta en el primer nodo de la posicion de la tabla
            if (posNodo == 1) {
                // Y no tiene nodos anidados
                if (this.tabla[pos].getEnlace() == null) {
                    this.tabla[pos] = null;
                } else {
                    this.tabla[pos] = this.tabla[pos].getEnlace();
                }
            } else {
                aux = this.tabla[pos];
                for(int i = 1; i < posNodo; i++){
                    // Si el siguiente nodo es el nodo del elemento a eliminar
                    if(i+1 == posNodo){
                        // Entonces seteo el enlace del nodo alctual al enlace del elemento a eliminar
                        aux.setEnlace(aux.getEnlace().getEnlace());
                    }else{
                        // Si no lo es, avanzo a aux
                        aux = aux.getEnlace();
                    }
                }
            }
            
            this.cant--;
        }

        return encontrado;
    }

    // ---- Pertenece ----
    public boolean pertenece(Object elem){
        int pos = elem.hashCode() % TAMANIO;
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;

        // Busco si existe en los nodos de la posicion hash de elem
        while (!encontrado && aux != null) {
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }

        return encontrado;
    }

    // ---- Es Vacia ----
    public boolean esVacia(){
        return this.cant <= 0;
    }

    // ---- Listar ----
    public Lista listar(){
        Lista lis = new Lista();
        Nodo aux;
        int i = 1, cantRecorridos = 0;

        while(i < TAMANIO-1 && cantRecorridos < this.cant){
            if(this.tabla[i] != null){
                aux = this.tabla[i];
                while(aux != null){
                    lis.insertar(aux.getElem(), 1);
                    cantRecorridos++;
                    aux = aux.getEnlace();
                }
            }
            i++;
        }

        return lis;
    }
}
