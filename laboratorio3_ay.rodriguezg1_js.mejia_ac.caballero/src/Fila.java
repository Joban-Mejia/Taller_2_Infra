import java.util.LinkedList;
import java.util.Queue;

public class Fila {
    private final Queue<Cliente> filaClientes = new LinkedList<>();

    public synchronized void agregarCliente(Cliente cliente) {
        filaClientes.add(cliente);
        System.out.println("Cliente añadido: ID " + cliente.getId() + " | Tiempo Base: " + cliente.getTiempoBase() + " ms");
        notify(); // Notifica a los cajeros que hay clientes
    }

    public synchronized Cliente obtenerCliente() {
        while (filaClientes.isEmpty()) {
            try {
                System.out.println("Cajero esperando: No hay clientes en la fila.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return filaClientes.poll();
    }
}
