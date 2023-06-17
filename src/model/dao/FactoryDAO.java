package model.dao;

import model.db.DB;

public class FactoryDAO {

	public static AlunoDAO createAlunoDAO() {
		return new AlunoDAOImp(DB.getConexao());
	}
	
	public static DisciplinaDAO createDisciplinaDAO() {
		return new DisciplinaDAOImp(DB.getConexao());
	}
}
