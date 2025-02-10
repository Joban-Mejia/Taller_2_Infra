import java.util.LinkedList;
import java.util.Queue;

public class Fila {
    private final Queue<int[]> filaClientes;

    public Fila() {
        this.filaClientes = new LinkedList<>();
    }

    public synchronized void agregarCliente(int uid, int tiempoBase) {
        filaClientes.add(new int[]{uid, tiempoBase});
        System.out.println("Cliente agregado - ID: " + uid + ", Tiempo base: " + tiempoBase + " ms");
        notify();
    }

    public synchronized int[] retirarCliente() {
        while (filaClientes.isEmpty()) {
            try {
                System.out.println("Cajero esperando, no hay clientes en la fila");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return filaClientes.poll();
    }
}