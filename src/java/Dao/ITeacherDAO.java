/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * Interface ISubjectDAO
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-08   1.0         Duc Minh    First Implement
 */
package Dao;

import Bean.TeacherBean;
import java.util.ArrayList;

/**
 * This is the interface class that declares the methods to access the data of
 * the Teacher object Abstract method getAllTeacher used to retrieve all
 * teachers
 *
 *
 * @author Duc Minh
 */
public interface ITeacherDAO {

    /**
     * getAllTeacher method implement from ITeacherDAO
     *
     * @return teachers. <code>java.util.ArrayList</code> object
     */
    public ArrayList<TeacherBean> getAllTeacher();

    /**
     * getAllTeacherApply method implement from ITeacherDAO
     * 
     * @return teachers. <code>java.util.ArrayList</code> object  
     */
    public ArrayList<TeacherBean> getAllTeacherApply(int pageindex, int pagesize);
    
    /**
     * getTotalTeacherApply method implement from ITeacherDAO
     * 
     * @return total. <code>java.lang.Integer</code> object  
     */
    public int getTotalTeacherApply();
    
    /**
     * handleTeacherApply method implement from ITeacherDAO
     * @param username <code>java.lang.String</code>
     * @param status <code>java.lang.String</code>
     * 
     * @return number of row. <code>java.lang.Integer</code> object  
     */
    public int handleTeacherApply(String username, String status);
    
    /**
     * getSubjectId method implement from ITeacherDAO
     * get the id subject of teacher from database
     * @return subjectId. <code>Integer</code> object  
     */
    public TeacherBean getTeacherByUsername(String username);

    /**
     * getSubjectId method implement from ITeacherDAO get the id subject of
     * teacher from database
     *
     * @return subjectId. <code>Integer</code> object
     */
    /**
     * checkTeacherStatus method implement from ITeacherDAO check status cv of
     * teacher in db
     *
     * @return subjectId. <code>Integer</code> object
     */
    public boolean checkTeacherStatus(String username);

    /**
     * getSubjectId method implement from ITeacherDAO get the id subject of
     * teacher from database
     *
     * @return subjectId. <code>Integer</code> object
     */
    public int getSubjectId(String usernameTeacher);

    /**
     * getTeacherBySearching method implement from ITeacherDAO
     *
     * @return teachers. <code>java.util.ArrayList</code> object
     */
    public ArrayList<TeacherBean> getTeacherBySearching(String searchString);

    /**
     * insertNewTeacher method implement from ITeacherDAO
     *
     * @return boolean. <code>java.lang.Boolean</code> object
     */
    public boolean insertNewTeacher(TeacherBean teacher);
}
