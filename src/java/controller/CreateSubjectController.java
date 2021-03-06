/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * CreateSubjectController
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-24   1.0         Doan Tu    First Implement
 */
package controller;

import bean.SubjectBean;
import dao.ISubjectDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * This is a Servlet responsible for handling the task when the user wants to
 * Create new subject and insert it into database. /CreateSubjectController is the URL of the web site Extend
 * HttpServlet class
 *
 * @author Doan Tu
 */
@MultipartConfig
@WebServlet(name = "CreateSubjectController", urlPatterns = {"/CreateSubjectController"})
public class CreateSubjectController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ISubjectDAO subjectDAO = new SubjectDAO();
            /*get total Number Of Subject*/
            int numberOfSubject = subjectDAO.getNumberOfSubject() + 1;
            
            /*Attach nextId Attribute, This is the id of next Subject If you want to insert*/
            request.setAttribute("nextId", numberOfSubject);
            request.getRequestDispatcher("./view/CreateSubject.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*get data from parameter of request*/
            String subName = request.getParameter("subName").replaceAll("\\s\\s+", " ").trim();
            String subId = request.getParameter("subId");
            String description = request.getParameter("description").replaceAll("\\s\\s+", " ").trim();
            Part part = request.getPart("Image");
            String subImage = part.getSubmittedFileName();

            /*Query for check whether Subject Name has existed*/
            ISubjectDAO subjectDAO = new SubjectDAO();
            boolean check = subjectDAO.searchBySubName(subName);
            /*If existed, reiderect*/
            if (check == false) {
                request.setAttribute("nextId", subId);
                request.setAttribute("check", check);
                request.getRequestDispatcher("./view/CreateSubject.jsp").forward(request, response);
            }else{//If not, Inset new Subject into database
                SubjectBean subject = new SubjectBean(Integer.parseInt(subId), subName, description, "assets/image/"+subImage);
                int numberOfRows = subjectDAO.createNewSubject(subject);
                response.sendRedirect("AdminSubjectController");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
