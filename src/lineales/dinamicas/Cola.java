/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Cola Dinamica             |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
*/

package lineales.dinamicas;

public class Cola {
    private Nodo frente;
    private Nodo fin;
    
    //---- Constructor ----
    public Cola(){
        //Creo la cola con el frente y el fin ambos en nulo.
        this.frente = null;
        this.fin = null;
    }
    
    //---- Poner ----
    public boolean poner(Object elemento){
        Nodo nuevo = new Nodo(elemento, null);
        
        //Si la cola esta vacia
        if(this.fin == null){
            //Apunto el fin y el frente de la cola a este nuevo elemento
            this.fin = nuevo;
            this.frente = nuevo;
        }else{ 
            //Seteo el enlace del ultimo elemento (this.fin.setEnlace) a al nuevo nodo
            this.fin.setEnlace(nuevo);
            //Y ahora apunto fin a nuevo
            this.fin = nuevo;
        }
        
        return true;
    }
    
    //---- Sacar ----
    public boolean sacar(){
        boolean exito = true;
        
        //Si la cola esta vacia, "exito" es false
        if(this.frente == null){
            exito = false;
        }else{
            //Caso contrario, apunto frente a su mismo enlace (proximo nodo)
            this.frente = this.frente.getEnlace();
            //En caso de que el nuevo frente sea null, fin tambien tiene que se null
            if(this.frente == null){
                this.fin = null;
            }
        }
        
        return exito;
    }
    
    //---- Obtener Frente ----
    public Object obtenerFrente(){
        Object elemento;
        
        if(this.frente != null){
            elemento = this.frente.getElem();
        }else{
            elemento = "COLA VACIA";
        }
        
        return elemento;
    }
    
    //---- Es Vacia? ----
    public boolean esVacia(){
        return this.frente == null;
    }
    
    //---- Vaciar -----
    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }
    
    //---- Clone ----
    public Cola clone(){
        Cola clonada = new Cola();
        Nodo aux1, aux2, nuevo;
        
        //Si no hay nodo frente, entonces esta vacia y clonada quedara vacia
        if(this.frente != null){
            //Apunto aux1 al frente de la cola original
            aux1 = this.frente;
            
            //Asigno el frente y fin de clonada al elemento de aux1 y con enlace nulo
            clonada.frente = new Nodo(aux1.getElem(), null);
            clonada.fin = clonada.frente;
            
            //Apunto aux2 al fin de clonada
            aux2 = clonada.fin;
            
            //Apunto aux1 al siguiente nodo
            aux1 = aux1.getEnlace();
            
            //Si aux1 es nulo, entonces no hay otro nodo para clonar
            while(aux1 != null){
                //Inicializo al nodo nuevo con el elemento de aux1 y con el enlace nulo
                nuevo = new Nodo(aux1.getElem(), null);

                //Aux2 lo enlazo al nuevo nodo
                aux2.setEnlace(nuevo);
                
                //Apunto al final de la cola clonada al ultimo nodo creado (nuevo)
                clonada.fin = nuevo;

                //Ahora aux2 lo apunto al proximo nodo de la cola
                aux2 = aux2.getEnlace();
                
                //Ahora aux1 lo apunto al proximo nodo
                aux1 = aux1.getEnlace();
            }
        }
        
        return clonada;
    }
    
    //---- toString ----
    public String toString(){
        String enTexto = "[ ";
        
        if(this.frente == null){
            enTexto += " COLA VACIA ";
        }else{
            //Creo un nodo auxiliar apuntando al frente de la cola
            Nodo aux = this.frente;
            
            //Si hay un solo elemento en la cola entonces
            if(this.fin == this.frente){
                //Agrego ese elemento a la cadena
                enTexto += aux.getElem().toString();
                //Y hago que aux apunte a su enlace nulo
                aux = aux.getEnlace();
            }
            
            //Mientras que aux no sea nulo
            while(aux != null){
                //Coloco el elemento de aux en la cadena enTexto
                enTexto += aux.getElem().toString();
                //Apunto aux a su propio enlace, si este es null entonces aux
                //se comvertira en null haciendo que el while se corte
                aux = aux.getEnlace();
                
                //Si aux no es null, entonces se espera un proximo elemento 
                //colocando una coma y espacio a la cadena
                if(aux != null){
                    enTexto += ", ";
                }
            }
        }
        
        enTexto += " ]";
        
        return enTexto;
    }
}
