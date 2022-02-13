package Bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Document: Java bean for Account object Created on: Feb 10, 2022, 8:20:03 PM
 *
 * @author Duc Minh
 */
public class AccountBean {

    private String username;
    private String password;
    private String mail;
    private String avatar;
    private String displayName;
    private Date dateOfBirth;
    private boolean sex;
    private String description;
    private BigDecimal cash;
    private Date createDate;
    private String role;
    private String status;
    private boolean state;
    private String token;

    public AccountBean() {
    }

    public AccountBean(String username, String password, String mail, String avatar, String displayName, Date dateOfBirth, boolean sex, String description, BigDecimal cash, Date createDate, String role, String status, boolean state) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.avatar = avatar;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.description = description;
        this.cash = cash;
        this.createDate = createDate;
        this.role = role;
        this.status = status;
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccountBean{" + "username=" + username + ", password=" + password + ", mail=" + mail + ", avatar=" + avatar + ", displayName=" + displayName + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", description=" + description + ", cash=" + cash + ", createDate=" + createDate + ", role=" + role + ", status=" + status + ", state=" + state + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String isSex() {
        return this.sex == false ? "male" : "female";
    }
    
    public boolean getSex(){
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
    
    public void setSex(String sex) {
        if(sex.equalsIgnoreCase("male")){
            this.sex = false;
        }
        else{
            this.sex = true;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}
