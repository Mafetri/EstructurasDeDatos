package test.conjuntistas;
import conjuntistas.estaticas.*;
public class TestHeap {
    public static void main(String[] args) {
        ArbolHeap arbol = new ArbolHeap();
        arbol.insertar(15);
        arbol.insertar(10);
        arbol.insertar(5);
        System.out.println(arbol.toString());
        System.out.println(arbol.esVacio());
        System.out.println(arbol.recuperarCima());
    }
    

}
