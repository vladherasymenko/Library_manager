package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;
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

@WebServlet("/membre_list")
public class MembreListServlet extends HttpServlet {
	MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        List<Membre> listMembres = new ArrayList<>();
        
        try {
        	listMembres = membreHndl.getList();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("listMembres", listMembres);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_list.jsp") .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        doGet(request, response);
    }
}
