/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * ChapterDAO 
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-10   1.0         Doan Tu    First Implement
 * 2022-02-24   2.0         Doan Tu    Second Implement
 */
package Dao;

import Bean.ChapterBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contain method to find Chapter information from database Extend
 * BaseDAO class to call getConnection() method Implement IChapterDAO Interface
 *
 * @author Doan Tu
 */
public class ChapterDAO extends BaseDAO implements IChapterDAO {

    /**
     * getAllSubject method implement from IChapterDAO
     *
     * @param subId ID of Subject which Chapter belongs
     * <code>java.lang.Integer</code> object
     * @return chapters. <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<ChapterBean> getBySubId(int subId) {
        ArrayList<ChapterBean> chapters = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "select * from Chapter where SubjectID=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, subId);

            /*Querry and save in ResultSet*/
            ResultSet rs = statement.executeQuery();

            /*Assign data to an arraylist of ChapterBean*/
            while (rs.next()) {
                ChapterBean chapter = new ChapterBean();
                chapter.setChapterID(rs.getInt("ChapterID"));
                chapter.setChapterName(rs.getString("ChapterName"));
                chapter.setSemester(rs.getInt("Semester"));
                chapter.setChapterContent(rs.getString("Chapter Content"));
                chapter.setSubjectID(rs.getInt("SubjectID"));

                chapters.add(chapter);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chapters;
    }

    /**
     * getChapterById method implement from IChapterDAO
     *
     * @param chapId ID of the Chapter. <code>java.lang.Integer</code> object
     * @return chapter <code>Bean.ChapterBean</code> object
     */
    @Override
    public ChapterBean getChapterById(int chapId) {
        ChapterBean chapter = new ChapterBean();
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "select * from Chapter where ChapterID=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, chapId);

            /*Querry and save in ResultSet*/
            ResultSet rs = statement.executeQuery();

            /*Assign data to an ChapterBean*/
            while (rs.next()) {
                chapter.setChapterID(rs.getInt("ChapterID"));
                chapter.setChapterName(rs.getString("ChapterName"));
                chapter.setSemester(rs.getInt("Semester"));
                chapter.setChapterContent(rs.getString("Chapter Content"));
                chapter.setSubjectID(rs.getInt("SubjectID"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chapter;
    }

    /**
     * getNumberOfChapterById method This method count number of Chapter
     * available in database with corresponding Subject ID
     *
     * @param subId. Id of Subject. <code>java.lang.Integer</code> object
     * @return numberOfChapter. <code>java.lang.Integer</code> object
     */
    @Override
    public int getNumberOfChapterById(int subId) {
        int numberOfChapter = 0;
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "select COUNT(*) as Number from Chapter where SubjectID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, subId);
            /*Querry and save in ResultSet*/
            ResultSet rs = statement.executeQuery();

            /*Assign data to numberOfChapter Integer type*/
            while (rs.next()) {
                numberOfChapter = rs.getInt("Number");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfChapter;
    }

    /**
     * getNumberOfChapter method This method count number of Chapter available
     * in database
     *
     * @return numberOfChapter. <code>java.lang.Integer</code> object
     */
    @Override
    public int getNumberOfChapter() {
        int numberOfChapter = 0;
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "select COUNT(*) as Number from Chapter";
            PreparedStatement statement = conn.prepareStatement(sql);
            /*Querry and save in ResultSet*/
            ResultSet rs = statement.executeQuery();

            /*Assign data to numberOfChapter Integer type*/
            while (rs.next()) {
                numberOfChapter = rs.getInt("Number");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfChapter;
    }

    /**
     * searchByChapNameOfSubject method This method will check whether the
     * ChapterName with corresponding ID has existed in database
     *
     * @param chapName. Name of Chapter <code>java.lang.String</code> object
     * @param subId. Id of Subject <code>java.lang.Integer</code> object
     * @return check. <code>java.lang.Boolean</code> object
     */
    @Override
    public boolean searchByChapNameOfSubject(String chapName, int subId) {
        boolean check = true;
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "select * from Chapter where ChapterName=? and SubjectID=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, chapName);
            statement.setInt(2, subId);
            
            /*Querry and save in ResultSet*/
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                check = false;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    /**
     * CreateChapter method
     * This method will insert new Chapter in database
     * 
     * @param chapter. New Chapter which wanted to insert. <code>Bean.ChapterBean</code> object
     * @return numberOfRows. <code>java.lang.Integer</code> object
     */
    @Override
    public int CreateChapter(ChapterBean chapter) {
        int numberOfRow = 0;
        try {
            /*Set up connection and Sql statement for Querry*/
            Connection conn = getConnection();
            String sql = "Insert into Chapter(ChapterName, [Chapter Content], SubjectID)"
                    + "values(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, chapter.getChapterName());
            statement.setString(2, chapter.getChapterContent());
            statement.setInt(3, chapter.getSubjectID());
            
            /* Insert into database*/
            numberOfRow = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfRow;
    }

    public static void main(String[] args) {
        ChapterDAO dao = new ChapterDAO();
        int n = dao.CreateChapter(new ChapterBean(19, "hihi", 0, "haha", 1));
        System.out.println(n);
    }

}
