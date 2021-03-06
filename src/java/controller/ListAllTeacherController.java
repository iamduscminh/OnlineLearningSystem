/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * Project: Online Learning System

 * ListAllTeacherController
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-07   1.0         Duc Minh    First Implement
 */
package controller;

import bean.AccountBean;
import bean.NotificationBean;
import bean.TeacherBean;
import dao.INotificationDAO;
import dao.ISubjectDAO;
import dao.ITeacherDAO;
import dao.NotificationDAO;
import dao.SubjectDAO;
import dao.TeacherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a Servlet responsible for handling the task when the user wants to
 * see the list of Teacher /ListALlTeacher is the URL of the Servlet Extend
 * HttpServlet class
 *
 * @author Duc Minh
 */
public class ListAllTeacherController extends HttpServlet {

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

            ITeacherDAO iTeacherDAO = new TeacherDAO(); //Use ITeacherDAO interface to call   
            String page = request.getParameter("page");
            String filter = "allTeacher";
            if (request.getParameter("filterTeacher") != null) {
                filter = request.getParameter("filterTeacher");
            }
            if (page == null || page.length() == 0) {
                page = "1";
            }
            int pageindex = Integer.parseInt(page);
            int pagesize = 10;
            int totalrow = filter.equals("onlineTeacher") ? iTeacherDAO.getTotalOnlineTeacher() : iTeacherDAO.getTotalTeacher();
            int totalpage = (totalrow % pagesize == 0) ? totalrow / pagesize : totalrow / pagesize + 1;
            List<TeacherBean> teacherList = filter.equals("onlineTeacher") ? iTeacherDAO.getAllOnlineTeacher(pageindex, pagesize) : iTeacherDAO.getAllTeacher(pageindex, pagesize);

            request.setAttribute("totalpage", totalpage);
            request.setAttribute("pageindex", pageindex);
            request.setAttribute("filterTeatcher", filter);
            ISubjectDAO iSubjectDAO = new SubjectDAO(); //Use ISubjectDAO interface to call
            Map<Integer, String> SubjectNames = iSubjectDAO.getSubjectNames();

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
            /*Attach Attribute teachers for request and redirect it to ListAllTeacher.jsp*/
            request.setAttribute("subjectNames", SubjectNames);
            request.setAttribute("teachers", teacherList);
            request.getRequestDispatcher("./view/ListAllTeacher.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListAllTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
