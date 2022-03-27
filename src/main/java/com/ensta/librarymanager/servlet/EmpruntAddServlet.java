package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet {
	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();
    MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 
    EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Livre> listLivres;
        List<Membre> listMembres;

        try {
            listLivres = livreHndl.getListDispo();
            listMembres = membreHndl.getListMembreEmpruntPossible();

            request.setAttribute("listLivres", listLivres);
            request.setAttribute("listMembres", listMembres);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int idMembre = Integer.parseInt(request.getParameter("idMembre"));
        int idLivre = Integer.parseInt(request.getParameter("idLivre"));

        try {
        	empruntHndl.create(idMembre, idLivre, LocalDate.now());
        	
            response.sendRedirect("emprunt_list");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
