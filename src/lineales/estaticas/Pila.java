package lineales.estaticas;
/*

=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Pila Estatica             |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================

*/

public class Pila {
    private Object[] arreglo;
    private int tope;
    private final static int TAMANIO = 10;
    
    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }
    
    //-----  Apilar -----
    //Añade un nuevo objeto a la ultima posicion de la Pila
    public boolean apilar(Object nuevoElem){
        boolean exito;
        
        //Si el tope es igual o mayor al tamaño maximo el arreglo entonces
        //no se puede asignar el nuevo elemento por falta de espacio, exito es falso
        if(this.tope+1 >= TAMANIO){
            exito = false;
        }//Sino se incrementa tope en 1 y en la posicion del arreglo del nuevo tope
        //se almacena el nuevo elemento. Por lo tanto la variable "exito" se vuelve
        //true ya que se almaceno con exito el nuevo objeto
        else{
            this.tope++;
            arreglo[tope] = nuevoElem;
            exito = true;
        }
        
        return exito;
    }
    
    //----- Desapilar -----
    //Elimina el ultimo objeto de la pila
    public boolean desapilar(){
        boolean exito = false;
        
        if(tope <= -1){
            //Si el tope es -1 o menor (por cualquiera sea la razon) la pila esta vacia
            exito = false;
        }else{
            //la ultima posicion del arreglo se vuelve null
            arreglo[tope] = null;
            //el tope se reduce en 1
            tope--;
            exito = true;
        }
        return exito;
    }
    
    //---- Obtener Tope -----
    //Devuelve el objeto tope del arreglo de Pila
    public Object obtenerTope(){
        Object elementoTope;
        if(tope > -1){
            elementoTope = this.arreglo[tope];
        }else{
            elementoTope = null;
        }
        return elementoTope;
    }
    
    //---- Es Vacio ---- 
    //Devuelve true si la pila esta vacia o false en caso contrario
    public boolean esVacia(){
        return tope <= -1;
    }
    
    //---- Vaciar ----
    //Vacia la pila
    public void vaciar(){
        for(int i=0; i<this.tope; i++){
            this.arreglo[i] = null;
        }
        tope = -1;
    }
    
    //---- Clone ---- 
    //Clona la pila y la retorna en una una Pila
    public Pila clone(){
        //Declaro e inicializo una nueva Pila llamada pilaClonada
        Pila pilaClonada = new Pila();
        
        pilaClonada.tope = this.tope;
        
        for(int i=0; i<=pilaClonada.tope; i++){
            pilaClonada.arreglo[i] = this.arreglo[i];
        }
        
        return pilaClonada;
    }
    
    //---- ToString ----
    //Devuelve un String con todos los elementos de la pila
    public String toString(){
        String enTexto = "[ ";
        if(this.tope >= 0){
            //La varialbe enTexto con el primer objeto del arreglo
            enTexto += this.arreglo[0].toString();
            //Añado a la variable enTexto un toString() de los objetos siguientes
            for(int i=1; i<=tope; i++){
                enTexto = enTexto + ", " + arreglo[i].toString();
            }
        }else{
            enTexto += "Pila Vacia";
        }

        return enTexto += " ]";
    }
}

