import java.util.Random;

public class GeneradorClientes implements Runnable {
    private Fila fila;
    private int uid = 1;
    private Random random = new Random();
    private int numeroDeClientes = 20; // Número de clientes a generar antes de detenerse

    public GeneradorClientes(Fila fila) {
        this.fila = fila;
    }

    @Override
    public void run() {
        for (int i = 0; i < numeroDeClientes; i++) {
            int tiempoProcesamiento = random.nextInt(2001); // Tiempo base entre 0 y 2000 ms
            int[] cliente = {uid, tiempoProcesamiento};
            fila.agregarCliente(cliente);
            System.out.println("Cliente creado - ID: " + uid + ", Tiempo base: " + tiempoProcesamiento + " ms");

            uid++;

            try {
                Thread.sleep(random.nextInt(5000)); // Espera aleatoria entre 0 y 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Generador de clientes ha terminado de crear " + numeroDeClientes + " clientes.");
        fila.setGeneradorTerminado(); // Notificar a la fila que el generador ha terminado
    }
}