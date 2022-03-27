package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService{

	private static MembreServiceImpl instance;
	
	private MembreServiceImpl() {}
	
	public static MembreServiceImpl getInstance(){
		if (instance == null){
		instance = new MembreServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		List<Membre> wholeList;
		try {
			wholeList = membreHndl.getList();
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		List<Membre> wholeList;
		List<Membre> listPossible = new ArrayList<>();
		try {
			wholeList = membreHndl.getList();
			
			EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();
			
			for(Membre member : wholeList) {
				if(empruntHndl.isEmpruntPossible(member)) {
					listPossible.add(member);
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return listPossible;
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		Membre memb;
		try {
			memb = membreHndl.getById(id);
			return memb;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		int newId;
		try {
			newId = membreHndl.create(nom, prenom, adresse, email, telephone);
			return newId;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		try {
			membreHndl.update(membre);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		try {
			membreHndl.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() throws ServiceException {
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		int nombre;
		try {
			nombre = membreHndl.count();
			return nombre;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
