class Buffer {
    private int dato;
    private boolean disponible = false;

    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.dato = valor;
        disponible = true;
        System.out.println("Productor puso: " + dato);
        notifyAll();
    }

    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        disponible = false;
        System.out.println("Consumidor obtuvo: " + dato);
        notifyAll();
        return dato;
    }
}

class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            buffer.put(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            buffer.get();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Ejercicio5_ProductorConsumidor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }
}