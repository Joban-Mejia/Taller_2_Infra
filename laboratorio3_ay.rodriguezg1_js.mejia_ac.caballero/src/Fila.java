import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<int[]> filaClientes = new ArrayList<>();

    public synchronized void agregarCliente(int[] cliente) {
        filaClientes.add(cliente);
        notifyAll(); // Notificar a los cajeros que hay un nuevo cliente
    }

    public synchronized int[] retirarCliente() {
        while (filaClientes.isEmpty()) {
            try {
                wait(); // Esperar si no hay clientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return filaClientes.remove(0);
    }
}