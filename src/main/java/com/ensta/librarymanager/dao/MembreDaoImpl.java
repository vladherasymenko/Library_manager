package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

public class MembreDaoImpl implements MembreDao {

	private static MembreDaoImpl instance;
	
	private MembreDaoImpl() {}
	
	public static MembreDaoImpl getInstance(){
		if (instance == null){
		instance = new MembreDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws DaoException {
		List<Membre> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT id, nom, prenom, adresse, email, telephone, abonnement\n"
					+ "FROM membre\n"
					+ "ORDER BY nom, prenom;";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				String nom = rs.getString("nom"); 
				String prenom = rs.getString("prenom"); 
		        String adresse = rs.getString("adresse"); 
		        String email = rs.getString("email"); 
		        String telephone = rs.getString("telephone");
		        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
		        
		        Membre res = new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Membre getById(int id) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT id, nom, prenom, adresse, email, telephone, abonnement\n"
					+ "FROM membre\n"
					+ "WHERE id ="+ id +";";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			rs.next();
			
			int id2 = rs.getInt("id"); 
			String nom = rs.getString("nom"); 
			String prenom = rs.getString("prenom"); 
	        String adresse = rs.getString("adresse"); 
	        String email = rs.getString("email"); 
	        String telephone = rs.getString("telephone");
	        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
	        
	        Membre res = new Membre(id2, nom, prenom, adresse, email, telephone, abonnement);
	        
	        return res;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return null;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();
			
			String sql = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement)\n"
					+ "VALUES ('"+nom+"', '"+prenom+"', '"+adresse+"','"+email+"', '"+telephone+"', 'BASIC');";
			PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			resultSet.next();
			
	        return resultSet.getInt(1);
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void update(Membre membre) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "UPDATE membre\n"
					+ "SET nom = '"+ membre.getNom() +"', prenom = '"+membre.getPrenom()+"', adresse = '"
					+membre.getAdresse()+"', email = '"+membre.getEmail()+"', telephone = '"+membre.getTelephone()+"',\n"
					+ "abonnement = '"+membre.getAbonnement().toString()+"'\n"
					+ "WHERE id = "+ membre.getId() +";";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
					
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "DELETE FROM membre WHERE id ="+ id +";";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
					
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT COUNT(id) AS count FROM membre;";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			rs.next();
			
			int res = rs.getInt(1);
	        
	        return res;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return 0;
	}

}
