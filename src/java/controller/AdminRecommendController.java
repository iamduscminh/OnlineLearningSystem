/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.RecommendBean;
import dao.IRecommendDAO;
import dao.ISubjectDAO;
import dao.RecommendDAO;
import dao.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Phong Vu
 */
@WebServlet(name = "AdminRecommendController", urlPatterns = {"/AdminRecommendController"})
public class AdminRecommendController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            IRecommendDAO recommendDAO = new RecommendDAO();
            ISubjectDAO subjectDAO = new SubjectDAO();
            ArrayList<RecommendBean> recommends = new ArrayList<>();
            if(action.equals("new")){
                recommends = recommendDAO.getByAction(true);
            }
            else{
                recommends = recommendDAO.getByAction(false);
            }
            
            ArrayList<String> subjects = new ArrayList<>();
            for (int i = 0; i < recommends.size(); i++) {
                String subjectName = subjectDAO.getSubjectById(recommends.get(i).getSubjectID()).getSubjectName();
                subjects.add(subjectName);
            }
            request.setAttribute("subjects", subjects);
            request.setAttribute("action", action);
            request.setAttribute("recommends",recommends);
            request.getRequestDispatcher("./view/AdminRecommend.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminRecommendController.class.getName()).log(Level.SEVERE, null, ex);
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
