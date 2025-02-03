package Ejercicio1;

public class Lectores extends Thread{
	
	private int[][] matriz;
	private int i;
	private int valor_fila;
	private Suma suma;
	
	public Lectores(int id, int[][] matriz, Suma suma) {
		this.matriz = matriz;
		this.i = id;
		this.suma = suma;
	}
	
	public void run() {
		valor_fila = 0;
		
		
		for (int j = 0; j<matriz[0].length; j++) {
			valor_fila += this.matriz[this.i][j];
		}
				
		suma.adicionarAcumFila(valor_fila);
		Main.incrementarContador();
		
	}
	
	
}
