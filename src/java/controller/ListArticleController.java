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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AppUtils;

/**
 * Document: ListArticleController Create on: Feb 9, 2022, 10:20:35 PM
 *
 * @author Hoang Ngoc Long
 */
@WebServlet(name = "ListArticleController", urlPatterns = {"/listarticle"})
public class ListArticleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*Use DAO class to get data from database for Article with corresponding */
        IArticleDAO articleDAO = new ArticleDAO();
        /*Get index ID from request*/
           /*Use session*/
         HttpSession session = request.getSession();
        AccountBean a = AppUtils.getLoginedUser(session);
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
        //get top 4 newest article and total article
     List<ArticleBean> list = articleDAO.pagingAricle(idex);
        List<ArticleBean> list2 = articleDAO.getTop4Article();
        //Attach Attribute for request and redirect it to ListArticle.jsp
        request.setAttribute("listP", list);
        request.setAttribute("listT", list2);
        request.setAttribute("tag", idex);
        request.setAttribute("a", idex);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("./view/ListArticle.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
