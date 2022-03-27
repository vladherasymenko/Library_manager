package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException; 
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet {
	EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        List<Emprunt> listEmprunts;

        try {
        	listEmprunts = empruntHndl.getListCurrent();
            
            request.setAttribute("listEmprunts", listEmprunts);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
        	empruntHndl.returnBook(id);
        	
            response.sendRedirect("/TP3Ensta/emprunt_list");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
