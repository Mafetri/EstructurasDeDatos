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
        boolean exito = false;

        //Si la lista no esta vacia (longitud > 0) y la posicion a eliminar es
        //mas grande o igual a 1 (si es mas chico no se podria eliminar nada)
        //y que es mas chico que la longitud (para no intentar eliminar algo que no existe)
        if(this.longitud > 0 && 1 <= pos && pos <= this.longitud){
            //Si se quiere eliminar el primer elemento, entonces apunto cabecera a su enlace
            if(pos == 1){
                this.cabecera = this.cabecera.getEnlace();
            }else{
                //A un nodo auxiliar lo apunto a la cabecera
                Nodo aux = this.cabecera;
                int i = 1;
                //Voy apuntando el auxiliar al proximo nodo hasta llegar a la posicion deseada
                while(i < pos-1){
                    aux = aux.getEnlace();
                    i++;
                }
                //Ahora conecto el nodo que apunta aux al que le sigue al siguiente
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
        }
        return exito;
    }
}
