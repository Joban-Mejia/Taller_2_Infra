package Ejercicio1;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	public static int hilosTerminados = 0;
	
	public static int[][] generarMatrizAleatoria(int n) {
        Random random = new Random();
        int[][] matriz = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = random.nextInt(100); 
            }
        }
        return matriz;
    }
	
	public static synchronized void incrementarContador() {
        hilosTerminados++;
    }
	
	public static void main(String[] args) {
		Scanner Lector = new Scanner(System.in);
		Suma suma = new Suma();
		
		System.out.println("Ingrese el tamaño de la matriz: ");
		int[][] matriz = generarMatrizAleatoria(Lector.nextInt());
		
		for (int i = 0; i < matriz.length; i++) {
            new Lectores(i, matriz, suma).start();
        }
		
		while (true) {
            synchronized (Main.class) {
                if (hilosTerminados >= matriz.length) {
                    break;
                }
            }
            Thread.yield(); 
        }

        /*int temporal = 0;
        System.out.println("Matriz generada:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t");
                temporal += matriz[i][j];
            }
            System.out.println();
        } 
		
        System.out.println("El resultado esperado es: " + temporal); */
				
		System.out.println("El valor final de la matriz con los hilo es: "+suma.darTotal());
		
		
	}

}
