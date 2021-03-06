/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * Project: Online Learning System

 * DeleteRequestController
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-07   1.0         Duc Minh    First Implement
 */
package controller;

import bean.AccountBean;
import bean.NotificationBean;
import dao.INotificationDAO;
import dao.IRequestDAO;
import dao.NotificationDAO;
import dao.RequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a Servlet responsible for handling the task when the student wants to delete Request
 * /DeleteRequest is the URL of the Servlet
 * Extend HttpServlet class
 * @author Duc Minh
 */
public class DeleteRequestController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        
        IRequestDAO iRequestDAO = new RequestDAO();
        int daoCheck = iRequestDAO.deleteRequest(requestId);
        HttpSession session = request.getSession();
        AccountBean account = (AccountBean)session.getAttribute("user");
        INotificationDAO iNotificationDAO = new NotificationDAO();
        if ((daoCheck!=0)) {
            iNotificationDAO.insertNotification(new NotificationBean(account.getUsername(),"Request", "You have successfully deleted your request."));
        }        
        else iNotificationDAO.insertNotification(new NotificationBean(account.getUsername(),"Request", "You have failed deleted your request."));
        
        //Redirect it to request list
        response.sendRedirect("ListAllRequest");
    }


}
