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
public class usuarioDAO {
    
    public void create (usuario l) {
		
		java.sql.Connection con= ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO usuario ("
					+ "Usuarios, "			
					+ "Sexo, "		
					+ "Contato, "
					+ "Nascimento) "
					+ "VALUES (?,?,?,?)");
			
			stmt.setString(1, l.getUsuarios());		
			stmt.setString(2, l.getSexo());	
			stmt.setString(3, l.getContato());
			stmt.setString(4, l.getNascimento());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<usuario> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<usuario> usuarios = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM usuario");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				usuario l = new usuario();
				
				l.setId(rs.getInt("Id"));
				l.setUsuarios(rs.getString("Usuario"));
				l.setSexo(rs.getString("Sexo"));
				l.setContato(rs.getString("Contato"));
				l.setNascimento(rs.getString("Nascimento"));
				
				usuario.add(l);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
                return usuarios;
	}

	public usuario readById(int Id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		usuario usuarios = new usuario();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM usuario WHERE ID = ?");
			stmt.setInt(1, Id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				usuarios.setId(rs.getInt("Id"));
				usuarios.setUsuarios(rs.getString("Usuario"));
				usuarios.setSexo(rs.getString("Sexo"));
				usuarios.setContato(rs.getString("Contato"));
				usuarios.setNascimento(rs.getString("Nascimento"));
			}
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por Id no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
        
        public usuario update(usuario l) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("UPDATE usuario SET Usuarios = ?, Sexo = ?, Contato = ?, Nascimento = ? WHERE Id = ?");
                stmt.setString(1, l.getUsuarios());
                stmt.setString(2, l.getSexo());
                stmt.setString(3, l.getContato());
                stmt.setString(4, l.getNascimento());
                stmt.setInt(5, l.getId());

                stmt.executeUpdate();
                return l;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar usuarios no banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        
        public boolean delete(int id) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("DELETE FROM usuario WHERE ID = ?");
                stmt.setInt(1, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao apagar usuario do banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
}

