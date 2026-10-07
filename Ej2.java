class MiTarea implements Runnable {
    @Override
    public void run() {
        System.out.println("¡Hola desde mi tarea con Runnable!");
    }
}

public class Ejercicio2_HilosRunnable {
    public static void main(String[] args) {
        MiTarea tarea = new MiTarea();
        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}