package br.usp.icmc.entidades;

import java.sql.Time;

public class Horario {
	private int idhor;
	private Time hor;
	private String diasem;
	
	public Horario(){
		
	}
	
	public int getIdhor() {
		return idhor;
	}
	public void setIdhor(int idhor) {
		this.idhor = idhor;
	}
	public Time getHor() {
		return hor;
	}
	public void setHor(Time hor) {
		this.hor = hor;
	}
	public String getDiasem() {
		return diasem;
	}
	public void setDiasem(String diasem) {
		this.diasem = diasem;
	}
	
	
}
