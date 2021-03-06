/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * UpdateArticleControl
 * Record of change:
 * DATE         Version     AUTHOR               Description
 * 2022-02-22   1.0         Hoang Ngoc Long    First Implement
 */
package controller;

import bean.AccountBean;
import bean.ArticleBean;
import bean.NotificationBean;
import dao.ArticleDAO;
import dao.IArticleDAO;
import dao.INotificationDAO;
import dao.NotificationDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Document: UpdateArticleControl Create on: 2022-02-22 10:20:35 PM
 *
 * @author Hoang Ngoc Long
 */
@WebServlet(name = "UpdateArticleControl", urlPatterns = {"/updatearticle"})
public class UpdateArticleControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
         /*Get image,description,id title from request*/
        String image = request.getParameter("image");
        String title = request.getParameter("title").replaceAll("\\s\\s+", " ").trim();
        /*get txttitle from page*/
        String description = request.getParameter("description").replaceAll("\\s\\s+", " ").trim();
         String id = request.getParameter("id");
         /*Use DAO class to get data from database for Article with corresponding */
            IArticleDAO dao =new ArticleDAO();
            ArticleBean d= dao.getArticlebyid(id);
            
          if(dao.checkArticleUpdateCreate(title)){
         dao.editproduct(image, title, description, id);
         /*sendirect to articleloading */
        request.setAttribute("message", "Update successfull");
        response.sendRedirect("loadalreadyarticle");
           }
          else{
              request.setAttribute("detail", d);
              request.setAttribute("message", "Title is exist");
            request.getRequestDispatcher("./view/UpdateArticle.jsp").forward(request, response);
          }
   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
