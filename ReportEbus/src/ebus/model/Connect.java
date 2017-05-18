package ebus.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Connect {
    private static List<Problem> list = new ArrayList<Problem>();
    protected static ObservableList<Problem> allProblem = FXCollections.observableArrayList(list);

    public static ObservableList<Problem> getAllProblem() {
        return allProblem;
    }
    
    public static void setProblem() {
        int l = allProblem.size();
        for(int i=0;i<l;i++)
            allProblem.remove(0);
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rsProblem = st.executeQuery("select * from problem");
            ResultSet rsStatus = st.executeQuery("select status.status_name from status inner join problem on problem.status_id=status.status_id");            
            ResultSet rsType = st.executeQuery("select type.type_name from type inner join problem on problem.type_id=type.type_id");
            int i=0;
            while(rsProblem.next()){
                allProblem.add(new Problem());
                allProblem.get(i).setName(rsProblem.getString("problem_name"));
                allProblem.get(i).setDetail(rsProblem.getString("description"));
                allProblem.get(i).setDate(rsProblem.getDate("problem_date"));
                System.out.println(allProblem.get(i));
                i++;
            }
            i=0;
            while(rsStatus.next()){    
                allProblem.get(i).setStatus(rsStatus.getString("status_name"));
                i++;
            }
            i=0;
            while(rsType.next()){
                allProblem.get(i).setType(rsType.getString("type_name"));
                i++;
            }
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void addProblem(String name,String detail,Date date,int statu,int type){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
//            Statement st = connect.createStatement();
//            st.executeUpdate("insert into problem value("+"'"+name+"',"+"'"+date+"',"+"'"+detail+"',"+statu+","+type+")");
            PreparedStatement ps = connect.prepareStatement("insert into problem(problem_name,description,status_id,type_id,problem_date) value(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, detail);
            ps.setInt(3, statu);
            ps.setInt(4, type);
            ps.setDate(5, new java.sql.Date(date.getTime()));
            ps.execute();
               
            connect.close();
            //setProblem();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void addAction(int problemId,String user){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement ps = connect.prepareStatement("insert into user_action_problem(problem_id,user_id) value(?,?)");
            ps.setInt(1, problemId);
            ps.setString(2, user);
            ps.execute();
               
            connect.close();
            //setProblem();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static boolean ckLogin(String user ,String password){
        boolean result = false;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select user.password from user where user.user_id=\'"+user+"\'");
            //String newPassword = null;
            while(rs.next()){
                System.out.println(">>");
                if(rs.getString("user.password").equals(password)){
                    result = true;
                    break;
                }
            }
            connect.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static boolean ckUser(String user){
        boolean result = true;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select user.user_id from user where user.user_id=\'"+user+"\'");
            System.out.println(">>>");
            //String newPassword = null;
            while(rs.next()){
                if(rs.getString("user.user_id").equals(user)){
                    result = false;
                    break;
                }
            }
            connect.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static void addUser(String user_id, String password, String email, String fname, String lname, String tel){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement ps = connect.prepareStatement("insert into user(user_id,email,fname,lname,tel,password,user_role_id) value(?,?,?,?,?,?,?)");
            ps.setString(1, user_id);
            ps.setString(2, email);
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.setString(5, tel);
            ps.setString(6, password);
            ps.setInt(7, 2);
            ps.execute();
               
            connect.close();
            //setProblem();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        //setProblem();
        if(ckUser("59130500027"))    
            addUser("5913500027", "benzbenz", "nattapat27@gmail.com", "nattapat", "mapu", "0877333617");
    }
}
