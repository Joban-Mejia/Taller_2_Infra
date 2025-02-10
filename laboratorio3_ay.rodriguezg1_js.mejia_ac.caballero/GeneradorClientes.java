import java.util.Random;

public class GeneradorClientes {

    private int uid; // numero entero que identificara a cada cliente
    private int numeroDeClientes;
    private Fila fila;


    Random random= new Random();
    
    Thread[] clientes = new Thread[50];
    
    public GeneradorClientes(Fila fila, int numeroDeClientes){
        this.fila = fila;
        this.numeroDeClientes = numeroDeClientes;


         try {
            Thread.sleep(random.nextInt(5000) +10000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        
    System.out.println("Cliente" + uid + "agregado a la fila (tiempo de procesamiento:"+ uid + ")");
    }
       

}
