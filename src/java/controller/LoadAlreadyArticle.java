/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * LoadAlreadyArticle
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
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Document: LoadAlreadyArticle Create on: 2022-02-22 10:20:35 PM
 *
 * @author Hoang Ngoc Long
 */
@WebServlet(name = "LoadAlreadyArticle", urlPatterns = {"/loadalreadyarticle"})
public class LoadAlreadyArticle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /*Notification*/
        HttpSession session = request.getSession();
        AccountBean account = (AccountBean) session.getAttribute("user");
        if (account != null) {
            INotificationDAO iNotificationDAO = new NotificationDAO();

            int notiUnread = iNotificationDAO.getTotalNotiUnread(account.getUsername());
            request.setAttribute("notiUnread", notiUnread);
            List<NotificationBean> notiList = iNotificationDAO.getTopNotification(account.getUsername());
            request.setAttribute("notificationList", notiList);
        }

        /*Use DAO class to get data from database for Article with corresponding */
        IArticleDAO articleDAO = new ArticleDAO();
        /*Get index ID from request*/
        String indexpage = request.getParameter("index");
        /*Caculate total page*/
        if (indexpage == null) {
            indexpage = "1";
        }
        /*Caculate total page*/
        int idex = Integer.parseInt(indexpage);
        int count = articleDAO.totalArticle();
        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }
         //total view
        int a = articleDAO.totalview();
          //total comment
        int b = articleDAO.totalcomment();
        //total article
        int c = articleDAO.totalArticle();
        //get top 4 newest article and total article
        List<ArticleBean> list = articleDAO.pagingAricle(idex);
        //Attach Attribute for request and redirect it to AlreadyAcceptArticle.jsp
        request.setAttribute("listP", list);
        request.setAttribute("tag", idex);
        request.setAttribute("a", idex);
        request.setAttribute("articlenumber", c);
        request.setAttribute("commentnumber", b);
          request.setAttribute("viewnumber", a);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("./view/AlreadyAcceptArticle.jsp").forward(request, response);
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
