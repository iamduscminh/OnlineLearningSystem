package Dao;

import Bean.AccountBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author win
 */
public class AccountDAO extends BaseDAO implements IAccountDAO {

    @Override
    public Map<String, String> getDisplayNames() {
        Map<String, String> DisplayNames = new HashMap<>();
        try {
            //Set up connection and Sql statement for Querry
            Connection conn = getConnection();
            String sql = "select * from Account";
            PreparedStatement statement = conn.prepareStatement(sql);

            //Querry and save in ResultSet
            ResultSet rs = statement.executeQuery();

            //Assign data to an arraylist of SubjectBean
            while (rs.next()) {
                DisplayNames.put(rs.getString("Username"), rs.getString("DisplayName"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DisplayNames;
    }

    public AccountBean getAccountByUsername(String username) throws SQLException {
        AccountBean x = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            String sql = "select * from Account where Username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            rs = statement.executeQuery();
            while (rs.next()) {
                x = new AccountBean();
                x.setUsername(rs.getString("Username"));
                x.setPassword(rs.getString("Password"));
                x.setMail(rs.getString("Mail"));
                x.setAvatar(rs.getString("Avatar"));
                x.setDisplayName(rs.getString("DisplayName"));
                x.setDateOfBirth(rs.getDate("DateOfBirth"));
                x.setSex(rs.getBoolean("Sex"));
                x.setDescription(rs.getString("Description"));
                x.setCash(rs.getBigDecimal("Cash In Account"));
                x.setCreateDate(rs.getDate("CreatedDate"));
                x.setRole(rs.getString("Role"));
                x.setStatus(rs.getString("Status"));
                x.setState(rs.getBoolean("State"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            rs.close();
            statement.close();
            conn.close();
        }
        return x;
    }

    public AccountBean getAccountByMail(String email) {
        if(email == null || email.equals("")){
            return null;
        }
        AccountBean x = null;
        try {
            Connection conn = getConnection();
            String sql = "select * from Account where Mail = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                x = new AccountBean();
                x.setUsername(rs.getString("Username"));
                x.setPassword(rs.getString("Password"));
                x.setMail(rs.getString("Mail"));
                x.setAvatar(rs.getString("Avatar"));
                x.setDisplayName(rs.getString("DisplayName"));
                x.setDateOfBirth(rs.getDate("DateOfBirth"));
                x.setSex(rs.getBoolean("Sex"));
                x.setDescription(rs.getString("Description"));
                x.setCash(rs.getBigDecimal("Cash In Account"));
                x.setCreateDate(rs.getDate("CreatedDate"));
                x.setRole(rs.getString("Role"));
                x.setStatus(rs.getString("Status"));
                x.setState(rs.getBoolean("State"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public boolean updateNewPassword(AccountBean account) {
        AccountBean x = getAccountByMail(account.getMail());
        if (x == null) {
            return false;
        }
        try {
            Connection conn = getConnection();
            String sql = "update Account \n"
                    + "set Password  = ?\n"
                    + "where Mail = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getMail());
            int status = statement.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean insertNewAccount(AccountBean account) throws SQLException {
        AccountBean x = getAccountByUsername(account.getUsername());
        if (x != null) {
            return false;
        }
        
        try {
            Connection conn = getConnection();
            String sql = "Insert into Account([Username], [Password], [Mail], [Sex]) values (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getMail());
            statement.setBoolean(4, account.getSex());
            int status = statement.executeUpdate();
            if(status == 0){
                return false;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

//    public static void main(String[] args) throws SQLException {
//        AccountDAO db = new AccountDAO();
//        AccountBean x = new AccountBean();
//        System.out.println(db.insertNewAccount(x));
//    }
}
