/*
=================================================
|     Estructuras de Datos 2021                 |
=================================================
|      Clase:                                   |
|       > Arbol Binario de Busqueda             |
|      Alumnos:                                 |
|        > Manuel Felipe Triñanes (FAI-2738)    |
=================================================
*/

package conjuntistas.dinamicas;
import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoBB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    // ---- Insertar ----
    public boolean insertar(Comparable elem) {
        boolean exito = false;

        // Si el arbol esta vacio coloco elem en su raiz
        if (this.raiz == null) {
            this.raiz = new NodoBB(elem, null, null);
        } else {
            // Sino lo agrego en la posicion correspondiente
            exito = insertarAux(this.raiz, elem);
        }

        return exito;
    }
    private boolean insertarAux(NodoBB nodo, Comparable elem) {
        boolean exito = true;

        // Si el elemento del nodo actual es igual a elem
        if (elem.compareTo(nodo.getElem()) == 0) {
            // La insersion es falsa ya que no puede haber elementos repetidos
            exito = false;
        } else if (elem.compareTo(nodo.getElem()) < 0) {
            // Si elem es mas chico que el elemento del nodo
            // Y si el nodo tiene hijo izquierdo, baja por ese lado
            if (nodo.getIzquierdo() != null) {
                exito = insertarAux(nodo.getIzquierdo(), elem);
            } else {
                // Sino agrega el elemento
                nodo.setIzquierdo(new NodoBB(elem, null, null));
            }
        } else {
            // Si el elemento entonces es mas grande
            // Y si tiene hijo derecho
            if (nodo.getDerecho() != null) {
                // Bajo por derecha
                exito = insertarAux(nodo.getDerecho(), elem);
            } else {
                // Sino lo arego a la derecha
                nodo.setDerecho(new NodoBB(elem, null, null));
            }
        }

        return exito;
    }

    // ---- Pertenece ----
    public boolean pertenece(Comparable elem) {
        boolean exito = false;
        if (this.raiz != null && elem != null) {
            exito = perteneceAux(this.raiz, elem);
        }
        return exito;
    }
    private boolean perteneceAux(NodoBB nodo, Comparable elem) {
        boolean exito = false;

        // Si nodo no es nulo
        if (nodo != null) {
            // Si el nodo es el elemento que busco, la busqueda fue un exito
            if (elem.compareTo(nodo.getElem()) == 0) {
                exito = true;
            } else {
                // Sino busco por izquierda o por derecha
                if (elem.compareTo(nodo.getElem()) < 0) {
                    exito = insertarAux(nodo.getIzquierdo(), elem);
                } else {
                    exito = insertarAux(nodo.getDerecho(), elem);
                }
            }
        }
        return exito;
    }

    // ---- Eliminar ----
    public boolean eliminar(Comparable elem){
        boolean exito = false;
        if(this.raiz != null && elem != null){
            exito = eliminarAux(this.raiz, null, elem);
        }
        return exito;
    }
    private boolean eliminarAux(NodoBB nodo, NodoBB padre,Comparable elem){
        boolean exito = false;

        if (nodo != null) {
    
            if (elem.compareTo(nodo.getElem()) == 0) {
                // Caso 1: el elemento buscado no tiene hijos
                if(nodo.getDerecho() == null && nodo.getIzquierdo() == null){
                    eliminarCaso1(padre, elem);
                } else {
                    // Caso 2: el elemento buscado tiene al menos un hijo
                    if(nodo.getDerecho() == null || nodo.getIzquierdo() == null){
                        eliminarCaso2(nodo, padre, elem);
                    } else {
                        // Caso 3: el elemento buscado tiene ambos hijos
                        eliminarCaso3(nodo);
                    }
                }
            } else {
                // Sino busco por izquierda o por derecha
                if (elem.compareTo(nodo.getElem()) < 0) {
                    exito = eliminarAux(nodo.getIzquierdo(), nodo, elem);
                } else {
                    exito = eliminarAux(nodo.getDerecho(), nodo, elem);
                }
            }
        }

        return exito;
    }
    private void eliminarCaso1(NodoBB nodo, Comparable elem){
        // Si el elemento es la raiz entonces el nodo (padre) es nulo
        if(nodo == null){
            this.raiz = null;
        }else{
            // Sino elimino a ese hijo si es que esta a la izquierda o a la derecha
            if(elem.compareTo(nodo.getElem()) < 0){
                nodo.setIzquierdo(null);
            }else{
                nodo.setDerecho(null);
            }
        }
    }
    private void eliminarCaso2(NodoBB nodo, NodoBB padre, Comparable elem){
        NodoBB izq = nodo.getIzquierdo();
        NodoBB der = nodo.getDerecho();

        // Si el elemento buscado es la raiz
        if( padre == null){
            // Pregunto cual de los dos hijos tiene y el que no sea nulo lo hago raiz
            if (izq != null) {
                this.raiz = izq;
            } else {
                this.raiz = der;
            }
        } else {
            // Si el elemento esta a la izquierda
            if (elem.compareTo(padre.getElem()) < 0) {
                if (izq != null) {
                    padre.setIzquierdo(izq);
                } else {
                    padre.setIzquierdo(der);
                }
            } else {
                // Si esta a la derecha
                if (izq != null) {
                    padre.setDerecho(izq);
                } else {
                    padre.setDerecho(der);
                }
            }
        }
    }
    private void eliminarCaso3(NodoBB nodo){
        // Candidato A: El mayor elemento del subárbol izquierdo de N
        NodoBB nodoCandidato = nodo.getIzquierdo(); 
        NodoBB padreCandidato = nodo;

        // Busco el mayor elemento del subárbol izquierdo de N
        while (nodoCandidato.getDerecho() != null) {
            padreCandidato = nodoCandidato;
            nodoCandidato = nodoCandidato.getDerecho();
        }

        nodo.setElem(nodoCandidato.getElem());

        // Si el hijo izquierdo del nodo actual es igual al candidato
        if (nodo.getIzquierdo() == nodoCandidato) {
            if(nodoCandidato.getDerecho() != null){
                // Si el derecho existe, seteo el enlace al hijo izquierdo el hijo derecho del nodo candidato
                nodo.setIzquierdo(nodoCandidato.getDerecho());
            } else {
                // Si el derecho no existe, seteo el enlace al hijo izquierdo el hijo izquierdo del nodo candidato
                nodo.setIzquierdo(nodoCandidato.getIzquierdo());
            }
        } else {
            if(padreCandidato.getDerecho() != null){
                // Si el derecho existe, entonces seteo en enlace al padre del nodo (para saltear el nodo)
                nodo.setIzquierdo(padreCandidato.getDerecho());
            } else {
                // Si el no derecho existe, entonces seteo en enlace al padre del nodo izquierdo (para saltear el nodo)
                nodo.setIzquierdo(padreCandidato.getIzquierdo());
            }
        }
    }

    // ---- Listar (preorden) ----
    public Lista listar() {
        Lista lis = new Lista();

        if( this.raiz != null){
            listarAux(this.raiz, lis);
        }

        return lis;
    }
    private void listarAux(NodoBB aux, Lista lis) {
        if (aux != null) {
            // Guardo en la lista el elemento
            lis.insertar(aux.getElem(), lis.longitud() + 1);

            // Visita los hijos
            listarAux(aux.getIzquierdo(), lis);
            listarAux(aux.getDerecho(), lis);
        }

    }

    // ---- Listar Rango ----
    public Lista listarRango(Comparable min, Comparable max) {
        Lista lis = new Lista();
        
        if (min != null && max != null) {
            listarRangoAux(this.raiz, lis, min, max);
        }

        return lis;
    }
    private void listarRangoAux(NodoBB nodo, Lista lis, Comparable min, Comparable max) {
        // Mientras el nodo no sea nulo
        if (nodo != null) {
            Comparable elem = nodo.getElem();
            
            // Si el elemento es mas chico que max hago la llamada recursiva por derecha
            if (elem.compareTo(max) < 0) {
                listarRangoAux(nodo.getDerecho(), lis, min, max);
            }
            // Si esta entre min y max entonces lo guardo en la lista
            if (elem.compareTo(min) >= 0 && elem.compareTo(max) <= 0) {
                lis.insertar(elem, 1);
            }
            // Si es mas grande que min, entonces hago la llamada recursiva por izquierda
            if (elem.compareTo(min) > 0) {
                listarRangoAux(nodo.getIzquierdo(), lis, min, max);
            }
        }
    }

    // ---- Elemento Minimo ----
    public Comparable minimoElem() {
        Comparable min = null;
        if (this.raiz != null) {
            min = minimoElemAux(this.raiz);
        }
        return min;
    }
    private Comparable minimoElemAux(NodoBB aux) {
        // Bajo por izquierda hasta que no haya mas nodos
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux.getElem();
    }

    // ---- Elemento Maximo ----
    public Comparable maximoElem() {
        Comparable min = null;
        if (this.raiz != null) {
            min = maximoElemAux(this.raiz);
        }
        return min;
    }
    private Comparable maximoElemAux(NodoBB nodo) {
        // Bajo por derecha hasta que no haya mas nodos
        while (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
        }
        return nodo.getElem();
    }

    // ---- Es Vacio ----
    public boolean vacio(){
        return this.raiz == null;
    }

    // ---- Vaciar ----
    public void vaciar(){
        this.raiz = null;
    }

    // ---- Clone ----
    public ArbolBB clone() {
        ArbolBB clone = new ArbolBB();

        if (this.raiz != null) {
            clone.raiz = cloneAux(this.raiz);
        }

        return clone;
    }
    private NodoBB cloneAux(NodoBB aux) {
        NodoBB clonado = null;

        // Si el aux no es nulo
        if (aux != null) {
            // Guardo en clonado un nuevo nodo con el elemento actual y sus hijos clonados recursivos
            clonado = new NodoBB(aux.getElem(), cloneAux(aux.getIzquierdo()), cloneAux(aux.getDerecho()));
        }
        
        return clonado;
    }

    // ---- To String ----
    public String toString() {
        String enTexto = "[ ARBOL VACIO ]";

        if (this.raiz != null) {
            enTexto = toStringAux(this.raiz);
        }

        return enTexto;
    }
    private String toStringAux(NodoBB aux) {
        String enTexto = "";
        if (aux != null) {
            enTexto += aux.getElem().toString() + "  HI: ";
            if (aux.getIzquierdo() == null) {
                enTexto += " - ";
            } else {
                enTexto += aux.getIzquierdo().getElem().toString();
            }
            enTexto += "  HD: ";
            if (aux.getDerecho() == null) {
                enTexto += " - ";
            } else {
                enTexto += aux.getDerecho().getElem().toString();
            }
            enTexto += "\n";
            enTexto += toStringAux(aux.getIzquierdo());
            enTexto += toStringAux(aux.getDerecho());
        }

        return enTexto;
    }
}