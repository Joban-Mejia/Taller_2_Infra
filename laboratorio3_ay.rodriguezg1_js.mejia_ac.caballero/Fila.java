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
        while (filaClientes.isEmpty()) {
            if (generadorTerminado) {
                return null; // Si el generador terminó y no hay más clientes, salir
            }
            try {
                wait(); // Esperar si no hay clientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return filaClientes.remove(0);
    }
    
    public synchronized boolean estaVacia() {
        return filaClientes.isEmpty();}
    public synchronized boolean isGeneradorTerminado() {
        return generadorTerminado;}
    


    public synchronized void setGeneradorTerminado() {
        this.generadorTerminado = true;
        notifyAll(); }}