package com.ensta.librarymanager.servlet;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	LivreServiceImpl livreHndl = LivreServiceImpl.getInstance();
    MembreServiceImpl membreHndl = MembreServiceImpl.getInstance(); 
    EmpruntServiceImpl empruntHndl = EmpruntServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	request.setAttribute("nombreMembres", membreHndl.count());
            request.setAttribute("nombreLivres", livreHndl.count());
            request.setAttribute("nombreEmprunts", empruntHndl.count());
            request.setAttribute("empruntList", empruntHndl.getListCurrent());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp")
                .forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}