
package lineales.dinamicas;

/*

=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Pila Dinamica             |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================

*/
public class Pila {
    private Nodo tope;
    
    //---- Constructor ----
    public Pila(){
        this.tope = null;
    }
    
    //---- Apilar ----
    public boolean apilar(Object nuevoElem){
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        
        this.tope = nuevo;
        
        return true;
    }
    
    //---- Desapilar ----
    public boolean desapilar(){
        boolean exito;
        
        //Si el tope de la pila no es null entonces desapila y devuelve exito true
        if(tope != null){
            this.tope = this.tope.getEnlace();
            exito = true;
        }else{
            exito = false;
        }
        
        return exito;
    }
    
    //---- Obetener Tope ----
    public Object obtenerTope(){
        Object elemento = null;
        
        //Si el tope no es null entonces a elemento le asigo el elemento tope de la pila
        if(this.tope != null){
            elemento = this.tope.getElem();
        }
        
        return elemento;
    }
    
    //---- Es Vacia ----
    public boolean esVacia(){
        return this.tope == null;
    }
    
    //---- Vaciar ----
    public boolean vaciar(){
        this.tope = null;
        return true;
    }
    
    //---- Clone ----
    public Pila clone(){
        //Creo una nueva pila y dos punteros auxiliares
        Pila clone = new Pila();
        Nodo aux1, aux2, nuevo;
        
        //Si el tope original es null, entonces copia va a quedar en null
        //Sino
        if(this.tope != null){
            //A aux1 le asigno el nodo tope de la pila original
            aux1 = this.tope;
            
            //A la pila clone le asigno el tope, siendo este el ultimo elemento 
            //de la pila original y un enlace vacio
            clone.tope = new Nodo(aux1.getElem(), null);
            
            //A aux2 lo asigno al tope de la nueva pila clonada
            aux2 = clone.tope;
            
            //Ahora, mienrtas el enlace de aux1 no sea null (osea que no ha 
            //llegado al final de la pila) realiza un loop
            while(aux1.getEnlace() != null){
                //A aux1 lo apunto a su propio enlace, el cual es el proximo nodo
                aux1 = aux1.getEnlace();
                
                //Creo un nuevo nodo con el elemento de aux1 y enlazado a nada
                nuevo = new Nodo(aux1.getElem(), null);
                
                //A aux2 le asigno un el enlace del nuevo nodo
                aux2.setEnlace(nuevo);
                
                //Ahora aux2 apunta a su propio enlace para modificarlo con un
                //posible nuevo nodo de la pila
                aux2 = aux2.getEnlace();
            }
        }
        return clone;
    }
    
    //---- ToString ----
    public String toString(){
        String enTexto;
        
        //Si el tope es null, entonces la pila esta vacia
        if(this.tope == null){
            enTexto = "Pila Vacia";
        }else{
            //Crea un nodo auxiliar con el tope de la pila para recorrerla
            Nodo aux = this.tope;
            enTexto = "[";
            
            while(aux != null){
                //Agrega el texto del elemento a enTexto y avanza
                enTexto = enTexto + aux.getElem().toString();
                aux = aux.getEnlace();
                
                //Si aux no es nulo, quiere decir que hay mas elementos
                if(aux != null){
                    enTexto = enTexto + ", ";
                }
            }
            enTexto = enTexto + "]";
        }
        return enTexto;
    }
    
}
