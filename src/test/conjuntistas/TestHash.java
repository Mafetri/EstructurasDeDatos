package test.conjuntistas;
import conjuntistas.dinamicas.HashAbierto;
public class TestHash {
    public static void main(String[] args) {
        HashAbierto hash = new HashAbierto();
        hash.insertar(15);
        hash.insertar(55);
        hash.insertar(68);
        hash.insertar(24);
        hash.insertar(26);
        hash.insertar(57);

        System.out.println(hash.listar().toString());
        hash.eliminar(15);
        hash.eliminar(55);
        hash.eliminar(68);
        hash.eliminar(24);
        hash.eliminar(26);
        
        System.out.println(hash.listar().toString());
    }
}
