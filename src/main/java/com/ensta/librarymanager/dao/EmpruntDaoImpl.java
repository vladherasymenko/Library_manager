package com.ensta.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

public class EmpruntDaoImpl implements EmpruntDao {

	private static EmpruntDaoImpl instance;
	
	private EmpruntDaoImpl() {}
	
	public static EmpruntDaoImpl getInstance(){
		if (instance == null){
		instance = new EmpruntDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws DaoException {
		List<Emprunt> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n"
					+ "dateRetour\n"
					+ "FROM emprunt AS e\n"
					+ "INNER JOIN membre ON membre.id = e.idMembre\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\n"
					+ "ORDER BY dateRetour DESC;";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				int idMembre = rs.getInt("idMembre"); 
				String nom = rs.getString("nom"); 
				String prenom = rs.getString("prenom"); 
		        String adresse = rs.getString("adresse"); 
		        String email = rs.getString("email"); 
		        String telephone = rs.getString("telephone");
		        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
		        int idLivre = rs.getInt("idLivre"); 
		        String titre = rs.getString("titre"); 
		        String auteur = rs.getString("auteur"); 
		        String isbn = rs.getString("isbn"); 
		        LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
		        
		        LocalDate dateRetour = null;
		        if(rs.getDate("dateRetour") != null) {
		        	dateRetour = rs.getDate("dateRetour").toLocalDate();
		        }
		        

		        Emprunt res = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		List<Emprunt> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n"
					+ "dateRetour\n"
					+ "FROM emprunt AS e\n"
					+ "INNER JOIN membre ON membre.id = e.idMembre\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\n"
					+ "WHERE dateRetour IS NULL;";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				int idMembre = rs.getInt("idMembre"); 
				String nom = rs.getString("nom"); 
				String prenom = rs.getString("prenom"); 
		        String adresse = rs.getString("adresse"); 
		        String email = rs.getString("email"); 
		        String telephone = rs.getString("telephone");
		        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
		        int idLivre = rs.getInt("idLivre"); 
		        String titre = rs.getString("titre"); 
		        String auteur = rs.getString("auteur"); 
		        String isbn = rs.getString("isbn"); 
		        LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
		        
		        LocalDate dateRetour = null;
		        if(rs.getDate("dateRetour") != null) {
		        	dateRetour = rs.getDate("dateRetour").toLocalDate();
		        }
		        

		        Emprunt res = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		List<Emprunt> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n"
					+ "dateRetour\n"
					+ "FROM emprunt AS e\n"
					+ "INNER JOIN membre ON membre.id = e.idMembre\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\n"
					+ "WHERE dateRetour IS NULL AND membre.id ="+idMembre+";";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				int idMembreNew = rs.getInt("idMembre"); 
				String nom = rs.getString("nom"); 
				String prenom = rs.getString("prenom"); 
		        String adresse = rs.getString("adresse"); 
		        String email = rs.getString("email"); 
		        String telephone = rs.getString("telephone");
		        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
		        int idLivre = rs.getInt("idLivre"); 
		        String titre = rs.getString("titre"); 
		        String auteur = rs.getString("auteur"); 
		        String isbn = rs.getString("isbn"); 
		        LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
		        
		        LocalDate dateRetour = null;
		        if(rs.getDate("dateRetour") != null) {
		        	dateRetour = rs.getDate("dateRetour").toLocalDate();
		        }
		        

		        Emprunt res = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		List<Emprunt> resList = new ArrayList<>();
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,\n"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n"
					+ "dateRetour\n"
					+ "FROM emprunt AS e\n"
					+ "INNER JOIN membre ON membre.id = e.idMembre\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\n"
					+ "WHERE dateRetour IS NULL AND livre.id = "+idLivre+";";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				int idMembre = rs.getInt("idMembre"); 
				String nom = rs.getString("nom"); 
				String prenom = rs.getString("prenom"); 
		        String adresse = rs.getString("adresse"); 
		        String email = rs.getString("email"); 
		        String telephone = rs.getString("telephone");
		        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
		        int idLivreNew = rs.getInt("idLivre"); 
		        String titre = rs.getString("titre"); 
		        String auteur = rs.getString("auteur"); 
		        String isbn = rs.getString("isbn"); 
		        LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
		        
		        LocalDate dateRetour = null;
		        if(rs.getDate("dateRetour") != null) {
		        	dateRetour = rs.getDate("dateRetour").toLocalDate();
		        }
		        

		        Emprunt res = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
		        
		        resList.add(res);
	        
			}
	        
	        return resList;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,\n"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,\n"
					+ "dateRetour\n"
					+ "FROM emprunt AS e\n"
					+ "INNER JOIN membre ON membre.id = e.idMembre\n"
					+ "INNER JOIN livre ON livre.id = e.idLivre\n"
					+ "WHERE e.id = "+id+";";
		
			ResultSet rs = connection.createStatement().executeQuery(sql);	
			
			rs.next();
				
			int idNew = rs.getInt("idEmprunt"); 
			int idMembre = rs.getInt("idMembre"); 
			String nom = rs.getString("nom"); 
			String prenom = rs.getString("prenom"); 
	        String adresse = rs.getString("adresse"); 
	        String email = rs.getString("email"); 
	        String telephone = rs.getString("telephone");
	        Abonnement abonnement = Abonnement.valueOf(rs.getString("abonnement"));
	        int idLivre = rs.getInt("idLivre"); 
	        String titre = rs.getString("titre"); 
	        String auteur = rs.getString("auteur"); 
	        String isbn = rs.getString("isbn"); 
	        LocalDate dateEmprunt = rs.getDate("dateEmprunt").toLocalDate();
	        
	        LocalDate dateRetour = null;
	        if(rs.getDate("dateRetour") != null) {
	        	dateRetour = rs.getDate("dateRetour").toLocalDate();
	        }
	        

	        Emprunt res = new Emprunt(id, idMembre, idLivre, dateEmprunt, dateRetour);
	        
	        return res;
        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();
			
			String sql = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour)\n"
					+ "VALUES ("+idMembre+", "+idLivre+", '"+dateEmprunt+"', NULL);";
			PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			stmt.executeUpdate();

        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		Connection connection;
		try {
			
			connection = ConnectionManager.getConnection();

			String sql = "UPDATE emprunt\n"
					+ "SET idMembre = "+emprunt.getIdMembre()+", idLivre = "+emprunt.getIdLivre()+","
							+ " dateEmprunt = '"+emprunt.getDateEmprunt()+"', dateRetour = '"+emprunt.getDateRetour()+"'\n"
					+ "WHERE id = "+emprunt.getId()+";";
			
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

			String sql = "SELECT COUNT(id) AS count FROM emprunt;";
		
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
