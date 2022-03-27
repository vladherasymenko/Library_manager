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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/livre_list")
public class LivreListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();

        List<Livre> listLivres = new ArrayList<>();
        
        try {
        	listLivres = livreHndl.getList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("listLivres", listLivres);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_list.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
