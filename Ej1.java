class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("¡Hola desde mi hilo!");
    }
}

public class Ejercicio1_HolaHilo {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}