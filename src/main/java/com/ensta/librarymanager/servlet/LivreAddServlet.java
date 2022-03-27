package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet {
	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        try {
            int id = livreHndl.create(request.getParameter("titre"), request.getParameter("auteur"), request.getParameter("isbn"));
            
            response.sendRedirect("/TP3Ensta/livre_details?id=" + id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

}
