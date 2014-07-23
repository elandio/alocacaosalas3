package br.usp.icmc.dao;

import java.io.BufferedWriter;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.EOFException;

/**
*
* @author Bruno
*/

public class InputOutput extends BDConnection {

	FileWriter file;
	BufferedWriter buff;
	private int cont;

	public InputOutput() {

		super("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"postgres");
		this.conectar();
	}
	
	

	public void executarMetaHeuristica(){
		
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
	
	/**
	 * @throws SQLException
	 */
	public void escreverArquivo() throws SQLException{
		Integer numsla= 0, numhor= 0, numtur= 0, numrec= 0;
		int i, j;
		
		
		//TABELA TURMA
		String sql = ("SELECT IDTUR, NUMALU FROM TURMA ORDER BY IDTUR");
		Statement pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //cria um resultset rolável
		ResultSet turmas = pstt.executeQuery(sql);
		
		while(turmas.next()){
			numtur++;
		}

		//TABELA HORARIOS
		sql = ("SELECT * FROM HORARIO ORDER BY IDHOR");
		
		Statement psth = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet horarios = psth.executeQuery(sql);
		
		while(horarios.next()){
			numhor++;
		}
		
		Statement psts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		//TABELA SALA
		sql = ("SELECT IDSLA, CAPSLA FROM SALA ORDER BY IDSLA");
		
		
		ResultSet salas = psts.executeQuery(sql);
		
		while(salas.next()){
			numsla++;
		}
		
		
		//TABELA RECURSO
		sql = ("SELECT * FROM RECURSO ORDER BY IDREC");
		
		Statement pstr = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet recursos = pstr.executeQuery(sql);
		
		while(recursos.next()){
			numrec++;
			
		}
		
		//TABELA HORARIO_TURMA
		sql = ("SELECT * FROM HORARIO_TURMA ORDER BY CODHOR, CODTUR");
		Statement pstht = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet horTurm = pstht.executeQuery(sql);
		
		while(horTurm.next()){
			System.out.println(horTurm.getString(1)+" "+horTurm.getString(2)+"\n");
		}
		
		//TABELA TURMA SALA_RECURSO
		sql = ("SELECT * FROM SALA_RECURSO");
		Statement pstsr = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet recSlas = pstsr.executeQuery(sql);
		
		
		//TABELA TURMA_RECURSO
		Statement psttr = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet recturmas = psttr.executeQuery("SELECT * FROM TURMA_RECURSO");
			

		try {
			
			file = new FileWriter("input.txt");
			buff = new BufferedWriter(file);
			
			buff.newLine();
			buff.write(numhor.toString());
			buff.write("\t");
			buff.write(numtur.toString());
			buff.write("\t");
			buff.write(numsla.toString());
			buff.write("\t");
			buff.write(numrec.toString());
			buff.newLine();
			buff.newLine();
			buff.newLine();
			/*
			* Escreve uma matriz onde a coluna consiste no numero de horarios e a linha é o numero de turmas
			* A matriz possui um conteudo binário sendo 1 se há relação entre a turma e o horario e 0 caso não haja
			*/
			
			String[] hraturm1;
			boolean flag = true;
			boolean vazio = true;
			horarios.beforeFirst();
			while(horarios.next()){
				turmas.beforeFirst();
				horTurm.beforeFirst();
				while(horTurm.next()){
					if(horarios.getString(1).equals(horTurm.getString(1))){
						vazio = false;
						
						PreparedStatement prep = con.prepareStatement("SELECT CODTUR FROM HORARIO_TURMA WHERE CODHOR = ? ORDER BY CODTUR");
						prep.setInt(1, horarios.getInt(1));
						ResultSet ht = prep.executeQuery();
						hraturm1 = new String[numtur];
						j=0;
						//cria-se um vetor estático para salvar as turma relativos a um certo horario
						//mantem-se a ordem original das turmas.
						while(ht.next()){
							hraturm1[j] = ht.getString(1);
							j++;
						}
						j = 0;
						while(turmas.next()){
							if(turmas.getString(1).equals(hraturm1[j])){ 
								buff.write("1");
								buff.write("\t");
								j++;
							}else{
								buff.write("0");
								buff.write("\t");
							}
						}
					}
				}
				if(vazio)
					for(i=0;i<numtur;i++){
						buff.write("0");
						buff.write("\t");
					}
				vazio = true;
				buff.newLine();	
			}
			buff.newLine();
			buff.newLine();
			boolean contem = false;
			ArrayList<String> recsal = new ArrayList<String>();
			salas.beforeFirst();
			while(salas.next()){
				recSlas.beforeFirst();
				while(recSlas.next()){
					if(salas.getString(1).equals(recSlas.getString(1))){
						recsal.add(recSlas.getString(2));
					}
				}
				recursos.beforeFirst();
				while(recursos.next()){
					contem = false;
					for(String codrec: recsal) 
						if(recursos.getString(1).equals(codrec)) contem = true;
					if(contem) buff.write("1");
					else buff.write("0");
					buff.write("\t");	
				}
				buff.newLine();
				recsal.clear();
			}
			buff.newLine();
			buff.newLine();
			turmas.beforeFirst();
			while(turmas.next()){
				recturmas.beforeFirst();
				while(recturmas.next()){
					if(turmas.getString(1).equals(recturmas.getString(1))){
						recsal.add(recturmas.getString(2));
					}
				}
				recursos.beforeFirst();
				while(recursos.next()){
					contem = false;
					for(String codrect: recsal) 
						if(recursos.getString(1).equals(codrect)) contem = true;
					if(contem) buff.write("1");
					else buff.write("0");
					buff.write("\t");	
				}
				buff.newLine();
				recsal.clear();
			}
			buff.newLine();
			buff.newLine();
			salas.beforeFirst();
			while(salas.next()){
				buff.write(salas.getString(2));
				buff.newLine();
			}
			buff.newLine();
			buff.newLine();
			turmas.beforeFirst();
			while(turmas.next()){
				
				buff.write(turmas.getString(2));
				buff.newLine();
			}
			
			
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*decodifica o arquivo de saida e salva na tabela
	 * HORARIO_SALA_TURMA do banco de dados
	 */
	public void lerArquivo(String arquivosaida) throws SQLException, IOException{
		Integer numsla= 0, numhor= 0, numtur= 0;
		int i, j;
		
		
		
		//TABELA TURMA
		String sql = ("SELECT IDTUR FROM TURMA ORDER BY IDTUR");
		Statement pstt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //cria um resultset rolável
		ResultSet turmas = pstt.executeQuery(sql);
		ArrayList<Integer> t = new ArrayList<Integer>();
		while(turmas.next()){
			numtur++;
			t.add(turmas.getInt(1));
		}

		//TABELA HORARIOS
		sql = ("SELECT IDHOR FROM HORARIO ORDER BY IDHOR");
		
		Statement psth = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet horarios = psth.executeQuery(sql);
		
		i=0;
		while(horarios.next()){
			numhor++;
		}
		
		Integer[] horas = new Integer[numhor];
		
		horarios.beforeFirst();
		while(horarios.next()){
			horas[i] = Integer.decode(horarios.getString(1));
			i++;
		}
		
		Statement psts = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		//TABELA SALA
		sql = ("SELECT IDSLA FROM SALA ORDER BY IDSLA");
		
		
		ResultSet salas = psts.executeQuery(sql);
		i=0;
		while(salas.next()){
			numsla++;
		}
		
		Integer[] slas = new Integer[numsla];
		
		salas.beforeFirst();
		while(salas.next()){
			slas[i] = Integer.decode(salas.getString(1));
			i++;
		}
		
		Integer[][] alocacao = new Integer[numsla][numhor];
		
		
		 BufferedReader reader;
		try {
			
			reader = new BufferedReader(new FileReader(arquivosaida));
			
			String line;

            for(i=0;i<numhor;i++){
            	line = reader.readLine();
                String[] quebra = line.split("\t");
                System.out.println(quebra.length+" e "+numsla);
                for(j=0;j<numsla;j++){
            		alocacao[j][i] = Integer.decode(quebra[j]);
            	}
            }
            
            for(i=0;i<numhor;i++){
            	for(j=0;j<numsla;j++){
            		System.out.print(alocacao[j][i]+" ");
            	}
            	System.out.println();
            }
            
            for(i=0;i<numhor;i++){
            	for(j=0;j<numsla;j++){
            		if(alocacao[j][i] != -1)
            			this.inserir(horas[i], slas[j], t.get(alocacao[j][i]));
            	}
            	System.out.println();
            }
            
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println();
			e.printStackTrace();
		}
         
         
	}
	
	public void inserir(int codhor, int idsla,int codtur ) throws SQLException {
		String sql = ("INSERT INTO HORARIO_SALA_TURMA VALUES(?,?,?);");

		PreparedStatement pstm = con.prepareStatement(sql);

		pstm.setInt(1, codhor);
		pstm.setInt(2, idsla);
		pstm.setInt(3, codtur);

		pstm.execute();

		System.out.println("adicionado com secesso");
	}
}
