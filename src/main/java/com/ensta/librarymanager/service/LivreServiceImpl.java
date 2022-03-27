package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class LivreServiceImpl implements LivreService {
	
	private static LivreServiceImpl instance;
	
	private LivreServiceImpl() {}
	
	public static LivreServiceImpl getInstance(){
		if (instance == null){
		instance = new LivreServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Livre> getList() throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		List<Livre> wholeList = new ArrayList<>();
		try {
			wholeList = livreHndl.getList();
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		List<Livre> wholeList= new ArrayList<>();
		List<Livre> listDispo = new ArrayList<>();
		try {
			wholeList = livreHndl.getList();
			
			EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();
			
			for(Livre livre : wholeList) {
				if(empruntHndl.isLivreDispo(livre.getId())) {
					listDispo.add(livre);
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return listDispo;
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		Livre book;
		try {
			book = livreHndl.getById(id);
			return book;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		int newId;
		try {
			newId = livreHndl.create(titre, auteur, isbn);
			return newId;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		try {
			livreHndl.update(livre);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		try {
			livreHndl.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() throws ServiceException {
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance();
		int nombre;
		try {
			nombre = livreHndl.count();
			return nombre;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
