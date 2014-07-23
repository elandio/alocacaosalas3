package br.usp.icmc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import br.usp.icmc.entidades.Horario;

public class HorarioDao extends BDConnection{
	public HorarioDao(){
		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
	}
	
	public int getNumHorario(){
		int num = 0;
		String sql = ("SELECT * FROM HORARIO WHERE diasem = segunda-feira ORDER BY HOR");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet horas = pstt.executeQuery(sql);
			
			while(horas.next()){
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		return num;
	}
	
	public ArrayList<Horario> buscarHorario(int idhor){
		ArrayList<Horario> hors = new ArrayList<Horario>();
		String sql = ("SELECT * FROM HORARIO WHERE IDHOR = "+idhor+" ORDER BY HOR");
		Statement pstt;
		Horario  horario; 
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet horas = pstt.executeQuery(sql);
			
			while(horas.next()){
				horario = new Horario();
				horario.setIdhor(horas.getInt(1));
				horario.setHor(horas.getTime(2));
				horario.setDiasem(horas.getString(3));
				hors.add(horario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		
		return hors;
	}
	
	public ArrayList<Integer> horarioSemanal(String dia){
		ArrayList<Integer> hors = new ArrayList<Integer>();
		String sql = ("SELECT IDHOR FROM HORARIO WHERE diasem = "+dia+" ORDER BY HOR");
		Statement pstt;
		int idhor; 
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet horas = pstt.executeQuery(sql);
			
			while(horas.next()){
				hors.add(horas.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		
		return hors;
	}
	
	public ArrayList<Time> ListarHoras(){
		String h;
		ArrayList<Time> hrs = new ArrayList<Time>();
		String sql = ("SELECT HOR FROM HORARIO WHERE diasem ='segunda-feira' ORDER BY HOR");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet horas = pstt.executeQuery(sql);
			while(horas.next()){
				hrs.add(horas.getTime(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		
		return hrs;
	}
	
	public ArrayList<String> ListarHora(){
		String h;
		ArrayList<String> hrs = new ArrayList<String>();
		String sql = ("SELECT HOR FROM HORARIO WHERE diasem ='segunda-feira' ORDER BY HOR");
		Statement pstt;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet horas = pstt.executeQuery(sql);
			while(horas.next()){
				h = horas.getString(1);
				String[] hs = h.split(":");
				hrs.add(hs[0]+":"+hs[1]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		
		return hrs;
	}
}
