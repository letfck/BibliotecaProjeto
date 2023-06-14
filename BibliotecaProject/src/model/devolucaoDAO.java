package model;

import config.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aluno-16
 */
public class devolucaoDAO {
    
    public void create (devolucao l) {
		
		java.sql.Connection con= ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO devolucao ("
					+ "Nome, "			
					+ "Livro, "		
					+ "Dataquepegou, "
					+ "Devolver) "
					+ "VALUES (?,?,?,?)");
			
			stmt.setString(1, l.getNome());		
			stmt.setString(2, l.getLivro());	
			stmt.setString(3, l.getDataquepegou());
			stmt.setString(4, l.getDevolver());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<devolucao> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<devolucao> devolucoes = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM devolucao");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				devolucao l = new devolucao();
				
				l.setId(rs.getInt("Id"));
				l.setNome(rs.getString("Nome"));
				l.setLivro(rs.getString("Livro"));
				l.setDataquepegou(rs.getString("Dataquepegou"));
				l.setDevolver(rs.getString("Devolver"));
				
				devolucao.add(l);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
                return devolucoes;
	}

	public devolucao readById(int Id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		devolucao devolucoes = new devolucao();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM devolucao WHERE ID = ?");
			stmt.setInt(1, Id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				devolucoes.setId(rs.getInt("Id"));
				devolucoes.setNome(rs.getString("Nome"));
				devolucoes.setLivro(rs.getString("Livro"));
				devolucoes.setDataquepegou(rs.getString("Dataquepegou"));
				devolucoes.setDevolver(rs.getString("Devolver"));
			}
			return devolucoes;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por Id no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
        
        public devolucao update(devolucao l) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("UPDATE devolucao SET Nome = ?, Livro = ?, Dataquepegou = ?, Devolver = ? WHERE Id = ?");
                stmt.setString(1, l.getNome());
                stmt.setString(2, l.getLivro());
                stmt.setString(3, l.getDataquepegou());
                stmt.setString(4, l.getDevolver());
                stmt.setInt(5, l.getId());

                stmt.executeUpdate();
                return l;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar devolucoes no banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        
        public boolean delete(int id) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("DELETE FROM devolucao WHERE ID = ?");
                stmt.setInt(1, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao apagar devolucao do banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
}
