/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * Project: Online Learning System

 * AccountManagerSearchController
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-07   1.0         Duc Minh    First Implement
 */
package controller;

import bean.AccountBean;
import dao.AccountDAO;
import dao.IAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author win
 */
public class AccountManagerSearchController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String searchString = request.getParameter("searchString").replaceAll("\\s\\s+", " ").trim();

            if (searchString.equals("")) {
                response.sendRedirect("AccountManager");
            }
            IAccountDAO iAccountDAO = new AccountDAO();

            String page = request.getParameter("page");

            if (page == null || page.length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);
            int pagesize = 7;
            int totalrow = iAccountDAO.totalAccountSearch(searchString);

            int totalpage = (totalrow % pagesize == 0) ? totalrow / pagesize : totalrow / pagesize + 1;
            List<AccountBean> accounts = iAccountDAO.getAllAccountBySearch(searchString, pageindex, pagesize);

            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("searchString", searchString);
            request.setAttribute("searchMode", "on");
            request.setAttribute("accounts", accounts);

            request.getRequestDispatcher("./view/AccountManager.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AccountManagerSearchController.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String searchString = request.getParameter("searchString").replaceAll("\\s\\s+", " ").trim();
            if (searchString.equals("")) {
                response.sendRedirect("AccountManager");
            }
            IAccountDAO iAccountDAO = new AccountDAO();

            String page = request.getParameter("page");
            if (page == null || page.length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);
            int pagesize = 7;
            int totalrow = iAccountDAO.totalAccountSearch(searchString);

            int totalpage = (totalrow % pagesize == 0) ? totalrow / pagesize : totalrow / pagesize + 1;
            List<AccountBean> accounts = iAccountDAO.getAllAccountBySearch(searchString, pageindex, pagesize);

            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("searchMode", "on");
            request.setAttribute("accounts", accounts);
            request.setAttribute("searchString", searchString);
            request.getRequestDispatcher("./view/AccountManager.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AccountManagerSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}