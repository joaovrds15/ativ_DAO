package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Aluno;

public class AlunoDAOImp implements AlunoDAO {

	private Connection conexao;

	public AlunoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO aluno (nome, sexo, dt_nasc, nota) VALUES (?, ?, ?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getSexo());
			pst.setDouble(4, obj.getNota());

			Date dataSql = new Date(obj.getDt_nasc().getTime());

			pst.setDate(3, dataSql);
			
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdaluno(linhas);
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel cadastrar o curso !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Aluno obj) {
	    PreparedStatement pst = null;
	    
	    try {
	        String sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ?, nota = ? WHERE idaluno = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setString(1, obj.getNome());
	        pst.setString(2, obj.getSexo());
	        pst.setDate(3, new java.sql.Date(obj.getDt_nasc().getTime()));
	        pst.setDouble(4, obj.getNota());
	        pst.setInt(5, obj.getIdaluno());
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println(obj.toString() + " foi atualizado com sucesso!");
	        } else {
	            System.out.println("Não foi possível atualizar o aluno!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteById(Integer id) {
	    PreparedStatement pst = null;
	    
	    try {
	        String sql = "DELETE FROM aluno WHERE idaluno = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println("Aluno com ID " + id + " foi excluído com sucesso!");
	        } else {
	            System.out.println("Não foi possível excluir o aluno com ID " + id + "!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Aluno findById(Integer id) {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    Aluno aluno = null;
	    
	    try {
	        String sql = "SELECT * FROM aluno WHERE idaluno = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            aluno = new Aluno();
	            aluno.setIdaluno(rs.getInt("idaluno"));
	            aluno.setNome(rs.getString("nome"));
	            aluno.setSexo(rs.getString("sexo"));
	            aluno.setDt_nasc(rs.getDate("dt_nasc"));
	            aluno.setNota(rs.getDouble("nota"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return aluno;
	}

	@Override
	public List<Aluno> findAll() {
	    Statement stmt = null;
	    ResultSet rs = null;
	    List<Aluno> alunos = new ArrayList<>();
	    
	    try {
	        String sql = "SELECT * FROM aluno";
	        stmt = conexao.createStatement();
	        rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	            Aluno aluno = new Aluno();
	            aluno.setIdaluno(rs.getInt("idaluno"));
	            aluno.setNome(rs.getString("nome"));
	            aluno.setSexo(rs.getString("sexo"));
	            aluno.setDt_nasc(rs.getDate("dt_nasc"));
	            aluno.setNota(rs.getDouble("nota"));
	            alunos.add(aluno);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return alunos;
	}
	
}
