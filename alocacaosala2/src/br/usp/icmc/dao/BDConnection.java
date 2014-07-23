package br.usp.icmc.dao;

	import java.sql.*;  
	
	/**
	 *
	 * @author Bruno
	 */
	public class BDConnection {  
	    //Variável que guarda as informações de autenticação da conexão     
		protected Connection con;  
		private String url, usuario, senha;     
		BDConnection(String url, String usuario, String senha) {         
			con = null;         
			this.url = url;         
			this.usuario = usuario;         
			this.senha = senha;     } 
	 
		public void conectar(){  
	        try {             
	        	//Busca o driver             
	        	Class.forName("org.postgresql.Driver");             
	        	//Informações para conexão com o banco             
	        	con = DriverManager.getConnection (url,usuario,senha);  
	            System.out.println("Conexão realizada com sucesso.");   
	        } catch (SQLException e) {             
	        	System.out.println("Erro na conexão com o banco de dados.\n");             
	        	e.getMessage();         
	        } catch (ClassNotFoundException e) {             
	        	e.getMessage();         }     
	        }  
	    public void desconectar(){  
	        try {             con.close();  
	        } catch (Exception e) {             
	        	e.getMessage();         
	        	}     
	        } 
	    }


