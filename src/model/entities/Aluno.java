package model.entities;

import java.util.Date;

public class Aluno {
	private int idaluno;
	private String nome;
	private String sexo;
	private Date dt_nasc;
	private double nota;
	
	public Aluno() {

	}

	public Aluno(int idaluno, String nome, String sexo, Date dt_nasc, double nota) {
		super();
		this.idaluno = idaluno;
		this.nome = nome;
		this.sexo = sexo;
		this.dt_nasc = dt_nasc;
		this.nota = nota;
	}

	public int getIdaluno() {
		return idaluno;
	}

	public void setIdaluno(int idaluno) {
		this.idaluno = idaluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	@Override
	public String toString() {
		return "Aluno [idaluno=" + idaluno + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + "]";
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
}
