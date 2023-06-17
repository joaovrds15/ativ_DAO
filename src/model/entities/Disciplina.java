package model.entities;

public class Disciplina {
	private int iddisciplina;
	private String nomedisciplina;
	private int cargahoraria;

	public Disciplina() {
		
	}

	public Disciplina(int iddisciplina, String nomedisciplina, int cargahoraria) {
		this.iddisciplina = iddisciplina;
		this.nomedisciplina = nomedisciplina;
		this.cargahoraria = cargahoraria;
	}

	public int getIddisciplina() {
		return iddisciplina;
	}

	public void setIddisciplina(int iddisciplina) {
		this.iddisciplina = iddisciplina;
	}

	public int getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(int cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public String getNomedisciplina() {
		return nomedisciplina;
	}

	public void setNomedisciplina(String nomedisciplina) {
		this.nomedisciplina = nomedisciplina;
	}

	@Override
	public String toString() {
		return "Disciplina [iddisciplina=" + iddisciplina + ", nomedisciplina=" + nomedisciplina + ", cargahoraria="
				+ cargahoraria + "]";
	}
	
	
	
}
