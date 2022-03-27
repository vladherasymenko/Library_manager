package ensta;

import java.time.LocalDate; 
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.utils.Abonnement;

public class DaoTest {
	
	public static void main(String[] args) {
		
		LivreDaoImpl livreHndl = LivreDaoImpl.getInstance(); 
		MembreDaoImpl membreHndl = MembreDaoImpl.getInstance();
		EmpruntDaoImpl empruntHndl = EmpruntDaoImpl.getInstance();
		try {
			/* Test de DaoLivre 
			int test = livreHndl.create("Test titre", "Auteur test", "123");
			Livre testLivre = livreHndl.getById(test);
			System.out.println("Titre du livre de test : " + testLivre.getTitre());
			
			List<Livre> myList = livreHndl.getList();
			System.out.println("Titre de premier livre dans la librairie : " + myList.get(0).getTitre());
			
			testLivre.setTitre("Test2");
			livreHndl.update(testLivre);
			Livre testLivre2 = livreHndl.getById(test);
			System.out.println("Nouveau titre du livre de test : " + testLivre2.getTitre());
			
			System.out.println("Il y a : " + livreHndl.count() + " livre(s)");
			
			livreHndl.delete(test);
			
			System.out.println("Suppresion ...");
			
			System.out.println("Il y a : " + livreHndl.count() + " livre(s)");
			
			*/
			/* Test de DaoMembre */
			List<Membre> myListMembre = membreHndl.getList();
			System.out.println("Nom de premier membre dans la clientèle : " + myListMembre.get(0).getPrenom());
			
			int testId = membreHndl.create("Test nom", "Prenom", "Adresse", "mail@mail", "1234");
			Membre testMembre = membreHndl.getById(testId);
			System.out.println("Nom du membre de test : " + testMembre.getNom());
			System.out.println("Son abonnement : " + testMembre.getAbonnement());
			testMembre.SetAbonnement(Abonnement.PREMIUM);
			membreHndl.update(testMembre);
			Membre testMembre2 = membreHndl.getById(testId);
			System.out.println("Nom du membre de test (mis a jour) : " + testMembre2.getNom());
			System.out.println("Son abonnement : " + testMembre2.getAbonnement());
			
			System.out.println("Il y a : " + membreHndl.count() + " membre(s)");
			
			membreHndl.delete(testId);
			
			System.out.println("Suppresion ...");
			
			System.out.println("Il y a : " + membreHndl.count() + " membre(s)");
			
			/* Test de DaoEmprunt 
			System.out.println("Il y a : " + empruntHndl.count() + " emprunts(s)");
			
			List<Emprunt> myListEmprunt = empruntHndl.getList();
			System.out.println("Id de livre de premier emprunt : " + myListEmprunt.get(0).getIdLivre());
			
			List<Emprunt> myListEmpruntCurrent = empruntHndl.getListCurrent();
			System.out.println("Id de deuxieme emprunt pas encore rendu: " + myListEmpruntCurrent.get(1).getId());
			
			List<Emprunt> myListEmpruntCurrentId2 = empruntHndl.getListCurrentByMembre(4);
			System.out.println("Id de membre doit etre egal a 4 ici : " + myListEmpruntCurrentId2.get(0).getIdMembre());
			*/
			//empruntHndl.create(100, 3, LocalDate.now()); ;
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
