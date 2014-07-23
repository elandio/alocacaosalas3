package br.usp.icmc.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import br.usp.icmc.entidades.Usuario;

public class DadoUsuario extends BDConnection {

	private int cont;

	public DadoUsuario() {

		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
		
	}
	public Usuario consultar(String numusp, String senha){
		
		Usuario usuario = new Usuario();
		
		String sql = ("SELECT * FROM USUARIO");
		String nome = null;
		int nusp;
		Statement usu;
		boolean login = false;
		try {
			usu = con.createStatement();
			ResultSet usuarios = usu.executeQuery(sql);
			nusp = Integer.decode(numusp);
			while(usuarios.next()){
				if(usuarios.getString(1).equals(numusp)){
					
					if(usuarios.getString(4).equals(senha)){
						usuario.setNumusp(usuarios.getInt(1));
						usuario.setNome(usuarios.getString(2));
						usuario.setEmail(usuarios.getString(3));
						usuario.setSenha(usuarios.getString(4));
						login=true;
						break;
					}else login=false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectar();
		return usuario;
	}
}
