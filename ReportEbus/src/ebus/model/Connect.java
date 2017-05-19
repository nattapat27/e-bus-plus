package ebus.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
public class Connect {
    private static String user;
    private static ObservableList<ProblemImage> allProblem = FXCollections.observableArrayList();
    private static ObservableList<ProblemAdmin> allProblemAdmin = FXCollections.observableArrayList();

    public static ObservableList<ProblemImage> getAllProblem() {
        return allProblem;
    }
    public static ObservableList<ProblemAdmin> getAllProblemAdmin() {
        return allProblemAdmin;
    }

    public static String getUser() {
        return user;
    }
    
    public static void deleteProblem(int problemId){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement st = connect.prepareStatement("DELETE FROM user_action_problem WHERE user_action_problem.problem_id=?");
            st.setInt(1, problemId);
            st.execute();
            st = connect.prepareStatement("DELETE FROM problem WHERE problem.problem_id=?");
            st.setInt(1, problemId);
            st.execute();
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static String[] getUserVoteProblem(int problemId){
        String[] result = null;
        ArrayList<String> tem = new ArrayList();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select user.email from user_action_problem inner join user on user_action_problem.user_id=user.user_id where user_action_problem.problem_id="+problemId);
            while(rs.next()){
                tem.add(rs.getString("user.email"));
            }
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        result = new String[tem.size()];
        result = tem.toArray(result);
        return result;
    }
    public static void changeStatus(int index, int problemId){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement st = connect.prepareStatement("UPDATE problem SET status_id=? WHERE problem_id=?");
            st.setInt(1, index);
            st.setInt(2, problemId);
            st.execute();
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void setProblemAdmin() {
        int l = allProblemAdmin.size();
        for(int i=0;i<l;i++)
            allProblemAdmin.remove(0);
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rsProblem = st.executeQuery("select * from problem");
            ResultSet rsType = st.executeQuery("select type.type_name from problem inner join type on problem.type_id=type.type_id");
            int i=0;
            while(rsProblem.next()){
                allProblemAdmin.add(new ProblemAdmin(
                        rsProblem.getString("problem_name"), 
                        rsProblem.getInt("status_id")-1, 
                        (i+1), 
                        rsProblem.getString("description"), 
                        "", 
                        rsProblem.getDate("problem_date"),
                        rsProblem.getString("user_id")
                ));
                i++;
            }
            i=0;
            while(rsType.next()){
                allProblemAdmin.get(i).setType(rsType.getString("type.type_name"));
                i++;
            }
            
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
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
            ResultSet rsStatus = st.executeQuery("select status.status_name from problem inner join status on problem.status_id=status.status_id");            
            ResultSet rsType = st.executeQuery("select type.type_name from problem inner join type on problem.type_id=type.type_id");
            int i=0;
            while(rsProblem.next()){
                allProblem.add(new ProblemImage(""+(i+1), "", rsProblem.getString("problem_name"), "", new Button(), rsProblem.getString("user_id")));
                allProblem.get(i).setProblemDetail(rsProblem.getString("description"));
                allProblem.get(i).setProblemDate(rsProblem.getDate("problem_date"));
                System.out.println(allProblem.get(i));
                i++;
            }
            i=0;
            while(rsStatus.next()){    
                allProblem.get(i).setProblemStatus(rsStatus.getString("status_name"));
                i++;
            }
            i=0;
            while(rsType.next()){
                allProblem.get(i).setProblemType(rsType.getString("type_name"));
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
            PreparedStatement ps = connect.prepareStatement("insert into problem(problem_name,description,status_id,type_id,problem_date,user_id) value(?,?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, detail);
            ps.setInt(3, statu);
            ps.setInt(4, type);
            ps.setDate(5, new java.sql.Date(date.getTime()));
            ps.setString(6, Connect.getUser());
            ps.execute();
               
            connect.close();
            //setProblem();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void removeAction(int problemId,String user){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement st = connect.prepareStatement("DELETE FROM user_action_problem WHERE user_action_problem.problem_id=? and user_action_problem.user_id=?");
            st.setInt(1, problemId);
            st.setString(2, user);
            st.execute();
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void addAction(int problemId,String user){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            PreparedStatement ps = connect.prepareStatement("insert into user_action_problem(action_id,problem_id,user_id) value(?,?,?)");
            ps.setInt(2, problemId);
            ps.setString(3, user);
            ps.setInt(1,1);
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
            ResultSet rs = st.executeQuery("select user.* from user where user.user_id=\'"+user+"\'");
            //String newPassword = null;
            while(rs.next()){
                System.out.println(">>");
                if(rs.getString("user.password").equals(password)){
                    result = true;
                    Connect.user = rs.getString("user_id");
                    
                    break;
                }
            }
            connect.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Connect.user);
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
    public static int positionProblem(String problemName){
        int result = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select problem.problem_id from problem where problem.problem_name=\'"+problemName+"\'");
            while(rs.next()){
                result = rs.getInt("problem_id");
            }
            connect.close();
        }catch(ClassNotFoundException e){
        
        }catch(SQLException e){
            
        }
        return result;
    }
    public static boolean ckVote(int problemId, String user){
        boolean result = true;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rsCk = st.executeQuery("select * from user_action_problem where user_action_problem.problem_id="+problemId+" and user_action_problem.user_id=\'"+user+"\'");
            while(rsCk.next()){
                result = false;
            }
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    public static int[] countProblem(){
        int result[] = new int[4];
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select problem.type_id from problem");
            while(rs.next()){
                result[rs.getInt("type_id")-1]++;
            }
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }    
    
    public static String whoOwn(int problemId){
        String result = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("select user_action_problem.user_id from user_action_problem where user_action_problem.problem_id="+problemId);
            while(rs.next()){
                if((result = rs.getString("user_action_problem.user_id"))!= null)
                    break;
            }
            connect.close();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public static void main(String[] args) {
        removeAction(9, "59130500028");
    }
}
