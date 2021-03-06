/*
 * Copyright(C)2022, Group 2 SE1511 FPTU-HN
 * 
 * Constant Bean
 * Record of change:
 * DATE         Version     AUTHOR     Description
 * 2022-02-22   1.0         Doan Tu    First Implement
 */

package bean;

/**
 * The class contains information attribute about Constant Model
 * All attributes are declare in private ConstantID, ConstantName, value, Unit, description, sign, readingConvention
 * Get set methods used to retrieve data
 * The toString method is overridden to print the data
 * 
 * @author Doan Tu
 */
public class ConstantBean {
    private int constantID;
    private String constantName;
    private String value;
    private String unit;
    private String description;
    private String sign;
    private String readingConvention;

    public ConstantBean() {
    }
    /**
     * Initialization method
     * 
     * @param constantID the Id of Constant. <code>java.lang.Integer</code> object
     * @param constantName The Name of Constant. <code>java.lang.String</code> object
     * @param value value of Constant. <code>java.lang.String</code> object
     * @param unit Unit of Constant in SI system. <code>java.lang.String</code> object
     * @param description Short description for constant. <code>java.lang.String</code> object
     * @param sign Science sign of Constant. <code>java.lang.String</code> object
     * @param readingConvention Science reading convention of constant. <code>java.lang.String</code> object
     */
    public ConstantBean(int constantID, String constantName, String value, String unit, String description, String sign, String readingConvention) {
        this.constantID = constantID;
        this.constantName = constantName;
        this.value = value;
        this.unit = unit;
        this.description = description;
        this.sign = sign;
        this.readingConvention = readingConvention;
    }

    /**
     * ConstantID's data retrieve method
     * 
     * @return constantID. <code>java.lang.Integer</code> object 
     */
    public int getConstantID() {
        return constantID;
    }
    
    /**
     * Set value for Constant ID
     * 
     * @param constantID <code>java.lang.Integer</code> object
     */
    public void setConstantID(int constantID) {
        this.constantID = constantID;
    }

    /**
     * ConstantName's data retrieve method
     * 
     * @return constantName. <code>java.lang.String</code> object
     */
    public String getConstantName() {
        return constantName;
    }

    /**
     * Set value for Constant Name
     * 
     * @param constantName. <code>java.lang.String</code> object
     */
    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    /**
     * ConstantValue's data retrieve method
     * 
     * @return value. <code>java.lang.String</code> object
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value for ConstantValue
     * 
     * @param value. <code>java.lang.String</code> object
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Unit's data retrieve method
     * 
     * @return unit. <code>java.lang.String</code> object
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Set value for Unit 
     * 
     * @param unit. <code>java.lang.String</code> object 
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * ConstantDescription's data retrieve method
     * 
     * @return description. <code>java.lang.String</code> object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set value for ConstantDescription
     * 
     * @param description. <code>java.lang.String</code> object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ConstantSign's data retrieve method
     * 
     * @return sign. <code>java.lang.String</code> object
     */
    public String getSign() {
        return sign;
    }

    /**
     * Set value for C??ntantSign
     * 
     * @param sign. <code>java.lang.String</code> object 
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * ReadingConvention's data retrieve method
     * 
     * @return readingConvention. <code>java.lang.String</code> object
     */
    public String getReadingConvention() {
        return readingConvention;
    }

    /**
     * Set value for ReadingConvention 
     * 
     * @param readingConvention. <code>java.lang.String</code> object
     */
    public void setReadingConvention(String readingConvention) {
        this.readingConvention = readingConvention;
    }

    /**
     * The method used to print the attribute values of Chapter
     * 
     * @return <code>java.lang.String</code> object
     */
    @Override
    public String toString() {
        return "ConstantBean{" + "constantID=" + constantID + ", constantName=" + constantName + ", value=" + value + ", unit=" + unit + ", description=" + description + ", sign=" + sign + ", readingConvention=" + readingConvention + '}';
    }
    
    
}
