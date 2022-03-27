package ensta;

import java.util.List;

import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.MembreServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.exception.ServiceException;

public class ServiceTest {
	public static void main(String[] args) {
		/*
		MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 
		try {
			List<Membre> all = membreHndl.getList();
			System.out.println("Nom de premier membre :" + all.get(0).getNom());
			
			List<Membre> possible = membreHndl.getListMembreEmpruntPossible();
			System.out.println("Nom de premier membre qui peut emprunter un livre :" + possible.get(0).getNom());
			
			Membre mem = membreHndl.getById(1);
			System.out.println("Nom de membre #1 :" + mem.getNom());
			
			int count = membreHndl.count();
			System.out.println("Il y a :" + count + " membres");

			membreHndl.create("Test", "TestP", "123", "123", "123");
			System.out.println("Membre de test cree");
			
			count = membreHndl.count();
			System.out.println("Il y a :" + count + " membres");
			
			mem.SetNom("Test2");
			membreHndl.update(mem);
			mem = membreHndl.getById(1);
			System.out.println("Nouveau nom de membre #1 :" + mem.getNom());
			
			membreHndl.delete(1);
			
			count = membreHndl.count();
			System.out.println("Il y a :" + count + " membres");
			
		}
		catch(ServiceException e) {
			e.printStackTrace();
		}
		*/
		LivreServiceImpl livreHndl = LivreServiceImpl.getInstance(); 
		try {
			List<Livre> all = livreHndl.getList();
			System.out.println("Titre de premier livre :" + all.get(0).getTitre());
			
			List<Livre> dispo = livreHndl.getListDispo();
			System.out.println("Titre de premier livre disponible :" + dispo.get(0).getTitre());
			
			Livre livre = livreHndl.getById(1);
			System.out.println("Titre de livre #1 :" + livre.getTitre());
			
			int count = livreHndl.count();
			System.out.println("Il y a :" + count + " livres");

			livreHndl.create("Test", "TestP", "123");
			System.out.println("Livre de test cree");
			
			count = livreHndl.count();
			System.out.println("Il y a :" + count + " livres");
			
			livre.setTitre("Test212313");
			livreHndl.update(livre);
			livre = livreHndl.getById(1);
			System.out.println("Nouveau titre de livre #1 :" + livre.getTitre());
			
			livreHndl.delete(1);
			
			count = livreHndl.count();
			System.out.println("Il y a :" + count + " membres");
			
		}
		catch(ServiceException e) {
			e.printStackTrace();
		}
	}
}
