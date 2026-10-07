import java.util.concurrent.*;

class TareaSumadora implements Callable<Integer> {
    private int numero;

    public TareaSumadora(int numero) {
        this.numero = numero;
    }

    @Override
    public Integer call() throws Exception {
        int suma = 0;
        for (int i = 0; i <= numero; i++) {
            suma += i;
            Thread.sleep(10);
        }
        System.out.println("Suma de " + numero + " calculada.");
        return suma;
    }
}

public class Ejercicio6_ExecutorFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<Integer> futuro1 = executor.submit(new TareaSumadora(10));
        Future<Integer> futuro2 = executor.submit(new TareaSumadora(20));
        Future<Integer> futuro3 = executor.submit(new TareaSumadora(30));

        System.out.println("Resultado 1: " + futuro1.get());
        System.out.println("Resultado 2: " + futuro2.get());
        System.out.println("Resultado 3: " + futuro3.get());

        executor.shutdown();
    }
}