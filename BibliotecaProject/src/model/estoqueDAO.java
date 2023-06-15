/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import config.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno-16
 */
public class estoqueDAO {
    public void create (estoque l) {
		
		java.sql.Connection con= ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO estoque ("
					+ "Livro, "			
					+ "Autor, "		
					+ "Codigodebarra) "
					+ "VALUES (?,?,?)");
			
			stmt.setString(1, l.getLivro());		
			stmt.setString(2, l.getAutor());	
			stmt.setString(3, l.getCodigodebarra());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<estoque> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<estoque> estoques = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM estoque");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				estoque l = new estoque();
				
				l.setId(rs.getInt("Id"));
				l.setLivro(rs.getString("Livro"));
				l.setAutor(rs.getString("Autor"));
				l.setCodigodebarra(rs.getString("Codigodebarra"));
				
				estoque.add(l);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
                return estoques;
	}

	public estoque readById(int Id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		estoque estoques = new estoque();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM estoque WHERE ID = ?");
			stmt.setInt(1, Id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				estoques.setId(rs.getInt("Id"));
				estoques.setLivro(rs.getString("Livro"));
				estoques.setAutor(rs.getString("Autor"));
				estoques.setCodigodebarra(rs.getString("Codigodebarra"));
			}
			return estoques;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por Id no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
        
        public estoque update(estoque l) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("UPDATE Estoque SET Estoques = ?, Autor = ?, Codigodebarra = ?, WHERE Id = ?");
                stmt.setString(1, l.getLivro());
                stmt.setString(2, l.getAutor());
                stmt.setString(3, l.getCodigodebarra());
                stmt.setInt(5, l.getId());

                stmt.executeUpdate();
                return l;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar estoques no banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        
        public boolean delete(int id) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("DELETE FROM estoque WHERE ID = ?");
                stmt.setInt(1, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao apagar estoque do banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
}
