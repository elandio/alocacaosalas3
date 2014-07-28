package br.usp.icmc.entidades;

import java.sql.Time;

public class JHorSlaTur {
	private int codtur;
	private String coddis;
	private String nomdis;
	private Time hora;
	private String diasem;
	private String nomcur;
	private int numsla;
	private int numcre;
	private int idhor;
	
	public int getIdhor() {
		return idhor;
	}

	public void setIdhor(int idhor) {
		this.idhor = idhor;
	}

	public JHorSlaTur(){
		
	}
	
	public int getNumcre() {
		return numcre;
	}

	public void setNumcre(int numcre) {
		this.numcre = numcre;
	}

	

	public int getCodtur() {
		return codtur;
	}

	public void setCodtur(int codtur) {
		this.codtur = codtur;
	}

	public String getCoddis() {
		return coddis;
	}

	public void setCoddis(String coddis) {
		this.coddis = coddis;
	}

	public String getNomdis() {
		return nomdis;
	}

	public void setNomdis(String nomdis) {
		this.nomdis = nomdis;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getDiasem() {
		return diasem;
	}

	public void setDiasem(String diasem) {
		this.diasem = diasem;
	}

	public String getNomcur() {
		return nomcur;
	}

	public void setNomcur(String nomcur) {
		this.nomcur = nomcur;
	}

	public int getNumsla() {
		return numsla;
	}

	public void setNumsla(int numsla) {
		this.numsla = numsla;
	}
	
	
}
