package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.utils.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet {
	MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	int id = membreHndl.create(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"), request.getParameter("email"),
					request.getParameter("telephone"));
            
            response.sendRedirect("/TP3Ensta/membre_details?id=" + id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
