/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * Interface ISubjectDAO
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-22   1.0         Doan Tu    First Implement
 */
package dao;

import bean.ConstantBean;
import java.util.List;

/**
 * This is the interface class that declares the methods to access the data of the Constant object
 * 
 * @author Doan Tu
 */
public interface IConstantDAO {
    
    /**
     * getAllConstant() method
     * This method will get All Constant in database
     * 
     * @return constants. <code>java.util.List</code> object
     */
    public List<ConstantBean> getAllConstant();
    
    /**
     * getByConstantName method
     * This method will get All Constants with corresponding name
     * 
     * @param constantName, <code>java.lang.String</code> object
     * @return constants. <code>java.util.List</code> object
     */
    public List<ConstantBean> getByConstantName(String constantName);
    
    /**
     * getBySignOrConvention method
     * This method will get all Constant with corresponding sign or reading convention
     * 
     * @param sign. <code>java.lang.String</code> object
     * @return <code>java.util.List</code> object
     */
    public List<ConstantBean> getBySignOrConvention(String sign);
    
    /**
     * getByNameAndSign method
     * This method will get all constants by both Name and Sign
     * 
     * @param name. <code>java.lang.String</code> object
     * @param sign. <code>java.lang.String</code> object
     * @return <code>java.util.List</code> object
     */
    public List<ConstantBean> getByNameAndSign(String name, String sign);
}