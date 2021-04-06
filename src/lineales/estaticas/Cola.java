
package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;
    
    public Cola(){
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }
    
    //---- Poner ---- OK!
    public boolean poner(Object elemento){
        boolean exito = false;
        
        //Si el arreglo no esta lleno, osea que la posicion siguiente a fin sea
        //distinto a el frente
        //(coloco el modulo de tamanio ya que la ultima posicion del arreglo no
        // debe ser llenada a menos que el frente que esta en 0 sea movido)
        if(((this.fin + 1) % TAMANIO) != this.frente){
            //Al final de la cola le agrego el nuevo elemento
            arreglo[fin] = elemento;
            //Ahora el puntero de final es la proxima posicion
            //Y cuando llega al maximo (10) al hacer MOD TAMANIO, da 0, entonces
            //fin va a valer 0 cuando llegue a posicion 10
            fin = (fin+1) % TAMANIO;
            
            exito = true;
        }
        
        return exito;
    }
    
    //---- Sacar ---- OK!
    public boolean sacar(){
        boolean exito;
        
        if(this.esVacia()){
            exito = false;
        }else{
            this.arreglo[frente] = null;
            //Cuando frente sea 10, al hacer mod tamanio, va a dar 0
            frente = (frente + 1) % TAMANIO;
            exito = true;
        }
        
        return exito;
    }
    
    //---- Obtener Frente ----
    public Object obtenerFrente(){
        Object elFrente = "Cola Vacia!";
        
        if(!this.esVacia()){
            elFrente = arreglo[frente];
        }
        
        return elFrente;
    }
    
    //---- Es Vacia ---- OK!
    public boolean esVacia(){
        boolean vacia;
        
        if(this.frente == this.fin){
            vacia = true;
        }else{
            vacia = false;
        }
        
        return vacia;
    }
    
    //---- Vaciar ----
    public void vaciar(){
        this.arreglo = null;
        this.fin = 0;
        this.frente = 0;
    }
    
    //---- Clone ----
    public Cola clone(){
        Cola clonada = new Cola();
        int frenteClonadoMovil = this.frente;
        int finClonado = this.fin;
        
        //Lleno la cola hasta el frente con null
        for(int i=0; i<frenteClonadoMovil;i++){
            clonada.poner(null);
        }
        
        //Una vez que llegue a frente
        while(frenteClonadoMovil != finClonado){
            clonada.poner(this.arreglo[frenteClonadoMovil]); 
            frenteClonadoMovil = (frenteClonadoMovil+1) % TAMANIO;
        }
        
        return clonada;
    }
    
    //---- toString ----
    public String toString(){
        String enTexto = "[ ";
        int i = this.frente;
        
        if(this.esVacia() != true){
            while(i != this.fin){
                if(i != this.frente){
                    enTexto += ", " + arreglo[i].toString();
                }else{
                    enTexto += arreglo[i].toString();
                }
                i = (i+1) % TAMANIO;
            }

            enTexto += " ]";
        }else{
            enTexto += " LA COLA ESTA VACIA ]";
        }
        
        return enTexto;
    }
    
}
