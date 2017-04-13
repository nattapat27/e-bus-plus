package ebus.model;
import ebus.controller.Problem;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;
public class Connect {
    protected static ObservableList<Problem> allProblem;

    public static ObservableList<Problem> getAllProblem() {
        return allProblem;
    }
    
    public static void setProblem() {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rsProblem = st.executeQuery("select * from problem");
            ResultSet rsStatus = st.executeQuery("select * from status");
            ResultSet rsType = st.executeQuery("select * from type");
            while(rsProblem.next()){
                Problem p = new Problem();
                p.setName(rsProblem.getString("problem_name"));
                p.setDetail(rsProblem.getString("description"));
                p.setDate(rsProblem.getDate("problem_date"));
                //p.setType(rsStatus.getString(rsProblem.getInt("status_id")));
                //p.setType(rsType.getString(rsProblem.getInt("type_id")));
                
                allProblem.add(p);
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
            Statement st = connect.createStatement();
            st.executeUpdate("insert into problem value("+"'"+name+"',"+"'"+date+"',"+"'"+detail+"',"+statu+","+type+")");
            connect.close();
            int l =allProblem.size();
            for(int i=0;i<l;i++){
                allProblem.remove(0);
            }
            setProblem();
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        setProblem();
        for(Problem p : allProblem)
            System.out.println(p);
        addProblem("Test","Testdetail",null, 0, 0);
        for(Problem p : allProblem)
            System.out.println(p);
    }
}
