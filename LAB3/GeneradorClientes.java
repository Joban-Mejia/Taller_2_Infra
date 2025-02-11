package LAB3;

import java.util.Random;

public class GeneradorClientes extends Thread {
    private Fila fila;
    private int uid;
    private int numeroDeClientes; 

    public GeneradorClientes(Fila fila, int cantidad) {
        this.fila = fila;
        this.numeroDeClientes = cantidad;
    }

	@Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < numeroDeClientes; i++) {
            this.uid = i+1;
            int tiempoProcesamiento = random.nextInt(2001); 
            int[] cliente = {uid, tiempoProcesamiento};
            fila.agregarCliente(cliente);
            System.out.println("Cliente creado - ID: " + uid + ", Tiempo base: " + tiempoProcesamiento + " ms");
            try {
                Thread.sleep(random.nextInt(5000)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Generador de clientes ha terminado de crear " + numeroDeClientes + " clientes.");
        fila.setGeneradorTerminado(); 
    }
}