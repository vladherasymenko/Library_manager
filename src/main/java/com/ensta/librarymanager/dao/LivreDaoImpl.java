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
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {
	
	private static LivreDaoImpl instance;
	
	private LivreDaoImpl() {}
	
	public static LivreDaoImpl getInstance(){
		if (instance == null){
		instance = new LivreDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws DaoException {
		List<Livre> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT id, titre, auteur, isbn FROM livre;";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				String titre = rs.getString("titre"); 
		        String auteur = rs.getString("auteur"); 
		        String isbn = rs.getString("isbn"); 
		        
		        Livre res = new Livre(id, titre, auteur, isbn);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Livre getById(int id) throws DaoException { // TODO DaoException ??? 
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT id, titre, auteur, isbn FROM livre WHERE id ="+id+";";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			rs.next();
			
			String titre = rs.getString("titre"); 
	        String auteur = rs.getString("auteur"); 
	        String isbn = rs.getString("isbn"); 
	        
	        Livre res = new Livre(id, titre, auteur, isbn);
	        
	        return res;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return null;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();
			
			String sql = "INSERT INTO livre(titre, auteur, isbn) VALUES ('"+titre + "','" + auteur + "','" + isbn + "');";
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
	public void update(Livre livre) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "UPDATE livre SET titre = '" + livre.getTitre()+"', auteur ='" + livre.getAuteur() 
			+ "', isbn ='" + livre.getIsbn() + "' WHERE id =" + livre.getId() + ";";
			
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

			String sql = "DELETE FROM livre WHERE id ="+ id +";";
			
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

			String sql = "SELECT COUNT(id) AS count FROM livre;";
		
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
