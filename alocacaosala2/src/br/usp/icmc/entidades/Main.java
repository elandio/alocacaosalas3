package br.usp.icmc.entidades;

import java.io.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

/**
*
* @author Bruno
*/

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		/*
		
		InputOutput ins = new InputOutput();
		
			try {
				ins.escreverArquivo();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        try {
				ins.lerArquivo("output.txt");
				System.out.println("terminou");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        */
		
		try {
			Runtime.getRuntime().exec("java -jar MetaHeuristica.jar").waitFor();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
	}
}
	

	



    