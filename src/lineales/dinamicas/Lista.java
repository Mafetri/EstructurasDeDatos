/*
=====================================
|     Estructuras de Datos 2021     |
=====================================
|      Clase:                       |
|       > Lista                     |
|      Alumno:                      |
|        > Manuel Felipe Triñanes   |
|        > FAI - 2738               |
=====================================
*/

package lineales.dinamicas;

public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista(){
        this.cabecera = null; 
        this.longitud = 0;
    }

    public boolean insertar(Object elemento, int pos){
        boolean exito = true;

        //Si la posicion a la que se quiere agregar el elemento es
        //mas chico que uno o mas grande que la longitud de la lista
        if(pos < 1 || pos > this.longitud+1){
            //No se puede agregar el elemento
            exito = false;
        }else{
            //Si la posicion es 1, entonces se lo quiere colocar al principio de la lista
            if(pos == 1){
                this.cabecera = new Nodo(elemento, this.cabecera);
            }else{
                //Creo y apunto un nodo auxiliar al nodo de cabecera
                Nodo aux = this.cabecera;
                int i = 1;
                //Mientas i sea mas chico que la posicion a la que se quiere
                //guardar el elemento
                while(i < pos-1){
                    //Apunto aux a su enlace (proximo nodo)
                    aux = aux.getEnlace();
                    //E incremento i
                    i++;
                }
                //Una vez que aux este apuntando a la posicion a la que se quiere
                //que este el nuevo elemento entonces creo un nodo nuevo y lo enlazo
                //al elemento siquiente   
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
            this.longitud++;
        }
        return exito;
    }
        
    public boolean eliminar(int pos){

    }
}
