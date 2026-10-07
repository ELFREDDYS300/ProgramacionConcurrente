class Contador {
    private int valor = 0;
    private static final boolean CON_SYNCHRONIZED = false;

    public void incrementar() {
        if (CON_SYNCHRONIZED) {
            incrementarSync();
        } else {
            valor++;
        }
    }

    private synchronized void incrementarSync() {
        valor++;
    }

    public int getValor() {
        return valor;
    }
}

class Incrementador extends Thread {
    private Contador contador;

    public Incrementador(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementar();
        }
    }
}

public class Ejercicio4_Contador {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();
        Incrementador[] hilos = new Incrementador[5];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Incrementador(contador);
            hilos[i].start();
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }

        System.out.println("Valor final del contador: " + contador.getValor());
        System.out.println("Valor esperado: " + (5 * 1000));
    }
}