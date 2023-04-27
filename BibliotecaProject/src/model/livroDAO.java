/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

/**
 *
 * @author aluno-16
 */
public class livroDAO {
	
	public void create (livro) {
		
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO city ("
					+ "Name, "			
					+ "CountryCode, "		
					+ "District, "
					+ "Population) "
					+ "VALUES (?,?,?,?)");
			
			stmt.setString(1, c.getName());		
			stmt.setString(2, c.getCountryCode());	
			stmt.setString(3, c.getDistrict());
			stmt.setInt(4, c.getPopulation());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir no banco de dados: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<City> readAll() {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<City> cidades = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM City");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				City c = new City();
				
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("Name"));
				c.setDistrict(rs.getString("District"));
				c.setPopulation(rs.getInt("Population"));
				c.setCountryCode(rs.getString("CountryCode"));
				
				cidades.add(c);
				
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler dados no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		return cidades;
	}

	public City readById(int id) {
		java.sql.Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		City cidade = new City();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM city WHERE ID = ?");
			stmt.setInt(1, id);
			
			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				cidade.setId(rs.getInt("ID"));
				cidade.setName(rs.getString("Name"));
				cidade.setDistrict(rs.getString("District"));
				cidade.setPopulation(rs.getInt("Population"));
				cidade.setCountryCode(rs.getString("CountryCode"));
			}
			return cidade;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao ler dado por ID no banco de dados!");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);	
		}
		
	}
        
        public City update(City city) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("UPDATE city SET Name = ?, District = ?, Population = ?, CountryCode = ? WHERE ID = ?");
                stmt.setString(1, city.getName());
                stmt.setString(2, city.getDistrict());
                stmt.setInt(3, city.getPopulation());
                stmt.setString(4, city.getCountryCode());
                stmt.setInt(5, city.getId());

                stmt.executeUpdate();
                return city;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar cidade no banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        
        public boolean delete(int id) {
            java.sql.Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = con.prepareStatement("DELETE FROM city WHERE ID = ?");
                stmt.setInt(1, id);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao apagar cidade do banco de dados!");
            } finally {
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }

}
}
