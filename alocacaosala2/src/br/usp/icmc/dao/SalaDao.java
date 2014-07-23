package br.usp.icmc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.usp.icmc.entidades.Sala;

public class SalaDao extends BDConnection {
	public SalaDao() {
		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
	}
	
	
public ArrayList<Integer> ListarNumeroSla() {
		
		ArrayList<Integer> slas = new ArrayList<Integer>();
		String sql = ("SELECT NUMSLA FROM SALA ORDER BY numsla");
		Statement pstt;
		Sala s;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet salas = pstt.executeQuery(sql);
			while (salas.next()) {
				slas.add(salas.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cria um resultset rolável

		return slas;
	}
	
	public int getNumSalas(){
		int num = 0;
		String sql = ("SELECT COUNT(*) FROM SALA");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet sala = pstt.executeQuery(sql);
			sala.next();
			num = sala.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		return num;
	}
	
	public ArrayList<Integer> ListarSala(int pavimento) {
		
		ArrayList<Integer> slas = new ArrayList<Integer>();
		String sql = ("SELECT numsla FROM SALA WHERE codpav ="+pavimento+" ORDER BY numsla");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet salas = pstt.executeQuery(sql);
			while (salas.next()) {
				slas.add(salas.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cria um resultset rolável

		return slas;
	}
	

	
	public ArrayList<Integer> listarPavimento(int bloco) {
	
		ArrayList<Integer> pav = new ArrayList<Integer>();
		String sql = ("SELECT idpav FROM PAVIMENTO WHERE codblo ="+bloco+" ORDER BY idpav");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet paviment = pstt.executeQuery(sql);
			while (paviment.next()) {
				pav.add(paviment.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cria um resultset rolável

		return pav;
	}
	
	public ArrayList<Integer> listarBloco() {
		
			ArrayList<Integer> blo = new ArrayList<Integer>();
			String sql = ("SELECT IDBLO FROM BLOCO ORDER BY IDBLO");
			Statement pstt;
			try {
				pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet b = pstt.executeQuery(sql);
				while (b.next()) {
					blo.add(b.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // cria um resultset rolável

			return blo;
		}
}
