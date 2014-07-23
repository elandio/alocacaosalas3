package br.usp.icmc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.usp.icmc.entidades.Horario;
import br.usp.icmc.entidades.HorarioSalaTurma;
import br.usp.icmc.entidades.JHorSlaTur;

public class HorarioSalaTurmaDao extends BDConnection {

	public HorarioSalaTurmaDao() {
		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
	}

	public ArrayList<JHorSlaTur> listarSaida(String dia){
		ArrayList<JHorSlaTur> jhst = new ArrayList<JHorSlaTur>();
		String sql = ("SELECT HORARIO_SALA_TURMA.CODTUR, TURMA.CODDIS, DISCIPLINA.NOMDIS, DISCIPLINA.NUMCRE, HORARIO.HOR, HORARIO.DIASEM, CURSOANO.NOMCUR, SALA.NUMSLA "
				+ "FROM HORARIO_SALA_TURMA "
				+	"INNER JOIN HORARIO ON(HORARIO.IDHOR = HORARIO_SALA_TURMA.IDHOR) "
				+	"INNER JOIN SALA ON(SALA.IDSLA = HORARIO_SALA_TURMA.IDSLA) "
				+	"INNER JOIN TURMA ON(HORARIO_SALA_TURMA.CODTUR = TURMA.IDTUR) "
				+	"INNER JOIN CURSOANO ON (TURMA.CODCUR = CURSOANO.IDCUR) "
				+	"INNER JOIN DISCIPLINA ON (TURMA.CODDIS = DISCIPLINA.IDDIS) "
				+	"WHERE HORARIO.DIASEM = '"+dia+"'"
				+	" ORDER BY HORARIO_SALA_TURMA.CODTUR;");
		Statement pstt;
		JHorSlaTur horslatur; 
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet hsturma = pstt.executeQuery(sql);
			
			while(hsturma.next()){
				horslatur = new JHorSlaTur();
				horslatur.setCodtur(hsturma.getInt(1));
				horslatur.setCoddis(hsturma.getString(2));
				horslatur.setNomdis(hsturma.getString(3));
				horslatur.setNumcre(hsturma.getInt(4));
				horslatur.setHora(hsturma.getTime(5));
				horslatur.setDiasem(hsturma.getString(6));
				horslatur.setNomcur(hsturma.getString(7));
				horslatur.setNumsla(hsturma.getInt(8));
				jhst.add(horslatur);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return jhst;
	}

	public ArrayList<HorarioSalaTurma> listarHorSlaTur() {
		ArrayList<HorarioSalaTurma> hst = new ArrayList<HorarioSalaTurma>();
		String sql = ("SELECT * FROM HORARIO_SALA_TURMA ORDER BY IDSLA");
		Statement pstt;
		HorarioSalaTurma horslatur;
		try {
			pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet hsturma = pstt.executeQuery(sql);

			while (hsturma.next()) {
				horslatur = new HorarioSalaTurma();
				horslatur.setIdhor(hsturma.getInt(1));
				horslatur.setIdsla(hsturma.getInt(2));
				horslatur.setIdtur(hsturma.getInt(3));
				hst.add(horslatur);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // cria um resultset rolável
		return hst;
	}
}
