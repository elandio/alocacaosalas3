package br.usp.icmc.dao;

import java.io.IOException;
import java.sql.SQLException;

public class Alocsalas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputOutput io = new InputOutput();
		
		try {
			io.lerArquivo("output.txt");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
