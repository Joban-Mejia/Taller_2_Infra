package LAB3;

import java.util.ArrayList;
import java.util.List;

public class Fila {
    private List<int[]> filaClientes = new ArrayList<>();
    private boolean generadorTerminado = false;

    public synchronized void agregarCliente(int[] cliente) {
        filaClientes.add(cliente);
        notifyAll(); 
    }

    public synchronized int[] retirarCliente() {
        while (filaClientes.isEmpty()) {
            if (generadorTerminado) {
                return null; 
            }
            try {
                wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return filaClientes.remove(0);
    }
    
    public synchronized boolean estaVacia() {
        return filaClientes.isEmpty();
    }
    
    public synchronized boolean isGeneradorTerminado() {
        return generadorTerminado;}

    public synchronized void setGeneradorTerminado() {
        this.generadorTerminado = true;
        notifyAll(); 
    }
}