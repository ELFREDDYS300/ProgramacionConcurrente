import java.util.concurrent.CountDownLatch;

class TareaInicial extends Thread {
    private CountDownLatch latch;
    private String nombre;

    public TareaInicial(CountDownLatch latch, String nombre) {
        this.latch = latch;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            System.out.println("Tarea " + nombre + " iniciando su trabajo...");
            Thread.sleep((long) (Math.random() * 2000));
            System.out.println("Tarea " + nombre + " ha terminado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown();
        }
    }
}

public class Ejercicio7_CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        int numeroDeTareas = 3;
        CountDownLatch latch = new CountDownLatch(numeroDeTareas);

        for (int i = 1; i <= numeroDeTareas; i++) {
            new TareaInicial(latch, "Tarea-" + i).start();
        }

        System.out.println("Hilo principal esperando a que las tareas terminen...");
        latch.await();
        System.out.println("Todas las tareas han terminado. Hilo principal continuando.");
    }
}