/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * AdminSubjectController
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-23   1.0         Doan Tu    First Implement
 */
package controller;

import bean.AccountBean;
import bean.NotificationBean;
import bean.SubjectBean;
import dao.ChapterDAO;
import dao.IChapterDAO;
import dao.IKnowledgeDAO;
import dao.INotificationDAO;
import dao.ISubjectDAO;
import dao.KnowledgeDAO;
import dao.NotificationDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a Servlet responsible for handling the task when the user wants to
 * see the list of subjects for Admin manage. /AdminSubjectController is the URL
 * of the web site Extend HttpServlet class
 *
 * @author Doan Tu
 */
@WebServlet(name = "AdminSubjectController", urlPatterns = {"/AdminSubjectController"})
public class AdminSubjectController extends HttpServlet {

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

            /*Declare variables*/
            ISubjectDAO subjectDAO = new SubjectDAO();
            IChapterDAO chapterDAO = new ChapterDAO();
            IKnowledgeDAO knowledgeDAO = new KnowledgeDAO();

            /*Queries to get number of Subject, Knowledge, Chapter*/
            int numberOfSubject = subjectDAO.getNumberOfSubject();
            int numberOfChapter = chapterDAO.getNumberOfChapter();
            int numberOfKnowledge = knowledgeDAO.getNumbberOfKnowledge();

            int[] numbers = new int[3];
            numbers[0] = numberOfSubject;
            numbers[1] = numberOfChapter;
            numbers[2] = numberOfKnowledge;

            /*Get All Subject Query*/
            List<SubjectBean> subjects = new ArrayList<>();
            subjects = subjectDAO.getAllSubject();

            /*Attach numbers, subjects to request and ridirect*/
            request.setAttribute("numbers", numbers);
            request.setAttribute("subjects", subjects);
            request.getRequestDispatcher("./view/AdminSubject.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
