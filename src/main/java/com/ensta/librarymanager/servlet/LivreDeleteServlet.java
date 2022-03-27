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
 
@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Livre livre = livreHndl.getById(id);
            
            request.setAttribute("livre", livre);
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
        	livreHndl.delete(id);
        	
            response.sendRedirect("/TP3Ensta/livre_list");
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
