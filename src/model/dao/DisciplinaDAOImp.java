package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO{
	private Connection conexao;

	public DisciplinaDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Disciplina obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO disciplina (iddisciplina, nomedisciplina, cargahoraria) VALUES (?, ?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, obj.getIddisciplina());
			pst.setString(2, obj.getNomedisciplina());
			pst.setInt(3, obj.getCargahoraria());
			
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIddisciplina(linhas);
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
	public void update(Disciplina obj) {
	    PreparedStatement pst = null;
	    
	    try {
	        String sql = "UPDATE disciplina SET nomedisciplina = ?, cargahoraria = ? WHERE iddisciplina = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setString(1, obj.getNomedisciplina());
	        pst.setInt(2, obj.getCargahoraria());
	        pst.setInt(3, obj.getIddisciplina());
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println(obj.toString() + " foi atualizado com sucesso!");
	        } else {
	            System.out.println("Não foi possível atualizar a disciplina!");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteById(Integer id) {
	    PreparedStatement pst = null;
	    
	    try {
	        String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        int linhas = pst.executeUpdate();
	        
	        if (linhas > 0) {
	            System.out.println("Disciplina com ID " + id + " foi excluída com sucesso!");
	        } else {
	            System.out.println("Não foi possível excluir a disciplina com ID " + id + "!");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Disciplina findById(Integer id) {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    Disciplina disciplina = null;
	    
	    try {
	        String sql = "SELECT * FROM disciplina WHERE iddisciplina = ?";
	        pst = conexao.prepareStatement(sql);
	        pst.setInt(1, id);
	        
	        rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            int idDisciplina = rs.getInt("iddisciplina");
	            String nomeDisciplina = rs.getString("nomedisciplina");
	            int cargaHoraria = rs.getInt("cargahoraria");
	            
	            disciplina = new Disciplina(idDisciplina, nomeDisciplina, cargaHoraria);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pst != null) {
	                pst.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return disciplina;
	}

	@Override
	public List<Disciplina> findAll() {
	    Statement st = null;
	    ResultSet rs = null;
	    List<Disciplina> disciplinas = new ArrayList<>();
	    
	    try {
	        st = conexao.createStatement();
	        String sql = "SELECT * FROM disciplina";
	        rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	            int idDisciplina = rs.getInt("iddisciplina");
	            String nomeDisciplina = rs.getString("nomedisciplina");
	            int cargaHoraria = rs.getInt("cargahoraria");
	            
	            Disciplina disciplina = new Disciplina(idDisciplina, nomeDisciplina, cargaHoraria);
	            disciplinas.add(disciplina);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return disciplinas;
	}
}
