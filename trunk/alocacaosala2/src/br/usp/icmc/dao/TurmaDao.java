package br.usp.icmc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.usp.icmc.entidades.Turma;

public class TurmaDao extends BDConnection{

	public TurmaDao() {
		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
	
	}
	
	
	public void addTurma(int codtur, int codcur, String coddis, int numalu) throws SQLException{
		
		        String sql = ("INSERT INTO Turma values(?,?,?,?);");  
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1,codtur);
		        pstmt.setInt(2,codcur);
		        pstmt.setString(3,coddis);
		        pstmt.setInt(4,numalu);
		        pstmt.execute();  
	}
	
	public ArrayList<Turma> listarTurma(){
		ArrayList<Turma> turm = new ArrayList<Turma>();
		String sql = ("SELECT * FROM TURMA");
		Statement turmas;
		try {
			turmas = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet t = turmas.executeQuery(sql);
			while(t.next()){
				Turma turma = new Turma();
				turma.setCodtur(t.getInt(1));
				turma.setCodcur(t.getInt(2));
				turma.setCoddis(t.getString(3));
				turma.setNumalu(t.getInt(4));
				turm.add(turma);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //cria um resultset rolável
		return turm;
		
	}
}
