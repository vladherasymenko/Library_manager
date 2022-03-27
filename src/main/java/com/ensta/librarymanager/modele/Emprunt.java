package com.ensta.librarymanager.modele;

import java.time.LocalDate;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class Emprunt {
	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt() { }
	
	public Emprunt(int id, int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour) {
		this.id = id;
		this.idMembre = idMembre;
		this.idLivre = idLivre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	
	public String getLivreTitre() {
		LivreServiceImpl livreHndl = LivreServiceImpl.getInstance(); 
		try {
			return livreHndl.getById(idLivre).getTitre();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to find";
	}
	
	public String getLivreAuteur() {
		LivreServiceImpl livreHndl = LivreServiceImpl.getInstance(); 
		try {
			return livreHndl.getById(idLivre).getAuteur();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to find";
	}
	
	public String getMembreNom() {
		MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 
		try {
			return membreHndl.getById(idMembre).getNom() + " " + membreHndl.getById(idMembre).getPrenom();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed to find";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
}
