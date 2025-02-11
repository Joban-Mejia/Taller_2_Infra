import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<int[]> filaClientes = new ArrayList<>();
    private boolean generadorTerminado = false;

    public synchronized void agregarCliente(int[] cliente) {
        filaClientes.add(cliente);
        notifyAll(); // Notificar a los cajeros que hay un nuevo cliente
    }

    public synchronized int[] retirarCliente() {
        while (filaClientes.isEmpty() && !generadorTerminado) {
            try {
                wait(); // Esperar si no hay clientes y el generador no ha terminado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (filaClientes.isEmpty() && generadorTerminado) {
            return null; // Si no hay más clientes y el generador terminó, retornar null
        }
        return filaClientes.remove(0);
    }
    public synchronized boolean estaVacia() {
        return filaClientes.isEmpty();}
    public synchronized boolean isGeneradorTerminado() {
        return generadorTerminado;}
    


    public synchronized void setGeneradorTerminado() {
        this.generadorTerminado = true;
        notifyAll(); // Notificar a los cajeros que el generador terminó
    }
}