package br.usp.icmc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.usp.icmc.entidades.Disciplina;


public class DisciplinaDao extends BDConnection{

	DisciplinaDao() {
		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
		// TODO Auto-generated constructor stub
	
	}
	public ArrayList<Disciplina> listarDisciplina(){
		ArrayList<Disciplina> discip = new ArrayList<Disciplina>();
		String sql = ("SELECT * FROM DISCIPLINA");
		Statement disc;
		try {
			disc = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet disciplinas = disc.executeQuery(sql);
			while(disciplinas.next()){
				Disciplina dp = new Disciplina();
				dp.setIddis(disciplinas.getString(1));
				dp.setNomdis(disciplinas.getString(2));
				dp.setNumcre(disciplinas.getInt(3));
				discip.add(dp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		return discip;
		
	}
	
	//selecionar todas as disciplinas do jupiterwe
	public ArrayList<Disciplina> listarDisciplinaJupiter(ArrayList<Disciplina> disc){
		
		ArrayList<Disciplina> discipJupiter = new ArrayList<Disciplina>();
		
		
		
		try {
			String sql = ("SELECT * FROM DISCIPLINA");
			Statement discJupiter;
			discJupiter = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet disciplinas = discJupiter.executeQuery(sql);
			while(disciplinas.next()){
				Disciplina dp = new Disciplina();
				dp.setIddis(disciplinas.getString(1));
				dp.setNomdis(disciplinas.getString(2));
				dp.setNumcre(disciplinas.getInt(3));
				discipJupiter.add(dp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Disciplina d: disc)
			for(Disciplina dj: discipJupiter)
				if(d.getIddis().equals(dj.getIddis())) discipJupiter.remove(dj);
		
		return discipJupiter;
		
	}
	
	public void inserirDisciplina(ArrayList<Disciplina> disc){
		 String sql = ("DELETE FROM DISCIPLINA");  
	     PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
	     for(Disciplina d: disc){
		     sql = ("INSERT INTO DISCIPLINA values(?,?,?);");  
		     PreparedStatement pstmtDIS = con.prepareStatement(sql);  
		     pstmtDIS.setString(1,d.getIddis());         
		     pstmtDIS.setString(2,d.getNomdis());       
		     pstmtDIS.setInt(3,d.getNumcre());
		     
		     pstmtDIS.execute();
	     }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
