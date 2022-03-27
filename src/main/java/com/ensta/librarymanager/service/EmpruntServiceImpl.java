package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntServiceImpl implements EmpruntService {
	
	private static EmpruntServiceImpl instance;
	
	private EmpruntServiceImpl() {}
	
	public static EmpruntServiceImpl getInstance(){
		if (instance == null){
		instance = new EmpruntServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		List<Emprunt> wholeList;
		try {
			wholeList = empruntHndl.getList();
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		List<Emprunt> wholeList;
		try {
			wholeList = empruntHndl.getListCurrent();
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		List<Emprunt> wholeList;
		try {
			wholeList = empruntHndl.getListCurrentByMembre(idMembre);
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		List<Emprunt> wholeList;
		try {
			wholeList = empruntHndl.getListCurrentByLivre(idLivre);
			return wholeList;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		Emprunt emprunt;
		try {
			emprunt = empruntHndl.getById(id);
			return emprunt;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		try {
			empruntHndl.create(idMembre, idLivre, dateEmprunt);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		try {
			
			Emprunt newEmprunt = empruntHndl.getById(id);
			
			newEmprunt.setDateRetour(LocalDate.now());
			
			empruntHndl.update(newEmprunt);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		int count;
		try {
			
			count = empruntHndl.count();
			
			return count;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		try {
			List<Emprunt> wholeList = empruntHndl.getListCurrentByLivre(idLivre);
			if(wholeList.size() == 0) {
				return true;
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		
		int maxLivres = membre.getAbonnement().getValue();
		try {
			List<Emprunt> wholeList = empruntHndl.getListCurrentByMembre(membre.getId());
			int currentLivres = wholeList.size();
			
			if(currentLivres < maxLivres) {
				return true;
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return false;
	}

}
