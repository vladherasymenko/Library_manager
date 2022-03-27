package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {
    MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 
    EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        Membre mem = null;
        List<Emprunt> listEmprunts = new ArrayList<>();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {
            mem = membreHndl.getById(id);
            listEmprunts = empruntHndl.getListCurrentByMembre(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("membre", mem);
        request.setAttribute("empruntList", listEmprunts);

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Membre membre = null;

        try {
            membre = membreHndl.getById(id);

            membre.SetNom(request.getParameter("nom"));
            membre.SetPrenom(request.getParameter("prenom"));
            membre.SetAbonnement(Abonnement.valueOf(request.getParameter("abonnement")));
            membre.SetAdresse(request.getParameter("adresse"));
            membre.SetEmail(request.getParameter("email"));
            membre.SetTelephone(request.getParameter("telephone"));

            membreHndl.update(membre);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
