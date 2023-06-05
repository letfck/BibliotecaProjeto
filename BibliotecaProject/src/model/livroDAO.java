 package model;



import config.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.livro;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

/**
 *
 * @author aluno-16
 */
public class livroDAO {

    private List<livro> livro;
    private Object livros;
	
	public void create (livro l) {
		
		java.sql.Connection con= ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO livro ("
					+ "Titulo, "			
					+ "Autor, "		
					+ "Editora, "
					+ "Descricao) "
					+ "VALUES (?,?,?,?)");
			
			stmt.setString(1, l.getTitulo());		
			stmt.setString(2, l.getAutor());	
			stmt.setString(3, l.getEditora());
			stmt.setString(4, l.getDescricao());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<livro> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<livro> livros = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM livro");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				livro l = new livro();
				
				l.setId(rs.getInt("Id"));
				l.setTitulo(rs.getString("Titulo"));
				l.setAutor(rs.getString("Autor"));
				l.setEditora(rs.getString("Editora"));
				l.setDescricao(rs.getString("Descricao"));
				
				livro.add(l);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
                return livro;
	}

	public livro readById(int Id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		livro livros = new livro();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM livro WHERE ID = ?");
			stmt.setInt(1, Id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				livros.setId(rs.getInt("Id"));
				livros.setTitulo(rs.getString("Titulo"));
				livros.setAutor(rs.getString("Autor"));
				livros.setEditora(rs.getString("Editora"));
				livros.setDescricao(rs.getString("Descricao"));
			}
			return livros;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por Id no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
        
        public livro update(livro l) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("UPDATE livro SET Titulo = ?, Autor = ?, Editora = ?, Descricao = ? WHERE Id = ?");
                stmt.setString(1, l.getTitulo());
                stmt.setString(2, l.getAutor());
                stmt.setString(3, l.getEditora());
                stmt.setString(4, l.getDescricao());
                stmt.setInt(5, l.getId());

                stmt.executeUpdate();
                return l;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar livros no banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        
        public boolean delete(int id) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("DELETE FROM livro WHERE ID = ?");
                stmt.setInt(1, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao apagar livro do banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
}
