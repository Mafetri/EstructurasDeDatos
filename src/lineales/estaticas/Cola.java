/*
=================================================
|     Estructuras de Datos 2021                 |
=================================================
|      Clase:                                   |
|       > Cola Estatica                         |
|      Alumnos:                                 |
|        > Manuel Felipe Triñanes (FAI-2738)    |
=================================================
*/

package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    // ---- Constructor ----
    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    // ---- Poner ----
    // Coloca el elemento enviado en la siguiente posicion de la cola
    public boolean poner(Object elemento) {
        boolean exito = false;

        // Si el arreglo no esta lleno, osea que la posicion siguiente a fin sea
        // distinto a el frente (el mod tamanio es para lograr la circularidad del arreglo)
        if (((this.fin + 1) % TAMANIO) != this.frente) {
            // Al final de la cola le agrego el nuevo elemento
            arreglo[fin] = elemento;
            // Ahora el puntero de final es la proxima posicion
            // Y cuando llega al maximo (10) al hacer MOD TAMANIO, da 0, entonces
            // fin va a valer 0 cuando llegue a posicion 10
            fin = (fin + 1) % TAMANIO;

            exito = true;
        }

        return exito;
    }

    // ---- Sacar ----
    // Saca el elemento en el frente de la cola
    public boolean sacar() {
        boolean exito;

        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            // Cuando frente sea 10, al hacer mod tamanio, va a dar 0
            this.frente = (this.frente + 1) % TAMANIO;
            exito = true;
        }

        return exito;
    }

    // ---- Obtener Frente ----
    // Devuelve el frente de la cola
    public Object obtenerFrente() {
        Object elFrente = null;

        if (this.frente != this.fin) {
            elFrente = arreglo[this.frente];
        }

        return elFrente;
    }

    // ---- Es Vacia ----
    // Retorna true en caso de que sea vacia o false en caso contrario
    public boolean esVacia() {
        return this.frente == this.fin;
    }

    // ---- Vaciar ----
    // Vacia la cola
    public void vaciar() {
        while(this.frente%TAMANIO != this.fin) {
            arreglo[this.frente] = null;
            this.frente = (this.frente+1)%TAMANIO;
        }
        this.fin = 0;
        this.frente = 0;
    }

    // ---- Clone ----
    // Devuelve una cola clonada de la original
    public Cola clone() {
        Cola clonada = new Cola();

        while(this.frente%TAMANIO != this.fin){
            clonada.arreglo[this.frente] = this.arreglo[this.frente];
            this.frente = (this.frente+1) % TAMANIO;
        }

        clonada.fin = this.fin;
        clonada.frente = this.frente;

        return clonada;
    }

    // ---- toString ----
    // Devulve en string la cola entera
    public String toString() {
        String enTexto = "[ ";
        int i = this.frente;

        if (this.esVacia() != true) {
            while (i != this.fin) {
                if (i != this.frente) {
                    enTexto += ", " + arreglo[i].toString();
                } else {
                    enTexto += arreglo[i].toString();
                }
                i = (i + 1) % TAMANIO;
            }

            enTexto += " ]";
        } else {
            enTexto += " COLA VACIA ]";
        }

        return enTexto;
    }

}
