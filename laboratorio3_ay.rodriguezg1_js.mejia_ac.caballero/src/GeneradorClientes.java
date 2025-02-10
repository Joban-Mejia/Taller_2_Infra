import java.util.Random;

public class GeneradorClientes implements Runnable {
    private final Fila fila;
    private final int numeroDeClientes;
    private int uid = 1; // Identificador único de clientes

    public GeneradorClientes(Fila fila, int numeroDeClientes) {
        this.fila = fila;
        this.numeroDeClientes = numeroDeClientes;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < numeroDeClientes; i++) {
            int tiempoBase = random.nextInt(2001); // Entre 0 y 2000 ms
            Cliente cliente = new Cliente(uid++, tiempoBase);
            fila.agregarCliente(cliente);

            // Esperar entre 0 y 500 ms antes de generar otro cliente
            try {
                Thread.sleep(random.nextInt(501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
