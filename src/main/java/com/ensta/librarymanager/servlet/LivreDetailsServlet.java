package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet {
	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();
	EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Livre livre = null;
        
        List<Emprunt> empruntList = new ArrayList<>();
        
        try {
            livre = livreHndl.getById(id);
            empruntList = empruntHndl.getListCurrentByLivre(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("livre", livre);
        request.setAttribute("listEmprunts", empruntList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Livre livre = livreHndl.getById(id);
            
            livre.setTitre(request.getParameter("titre"));
            livre.setAuteur(request.getParameter("auteur"));
            livre.setIsbn(request.getParameter("isbn"));

            livreHndl.update(livre);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

}
