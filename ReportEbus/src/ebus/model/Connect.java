package ebus.model;
import ebus.controller.Problem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        for(int i=0;i<l;i++){
            allProblem.remove(0);
        }
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mariadb://10.4.56.23/ebusplus-g2"+"?user=ebusplus&password=ebusplus2017");
            Statement st = connect.createStatement();
            ResultSet rsProblem = st.executeQuery("select * from problem");
            ResultSet rsStatus = st.executeQuery("select status.status_name from status inner join problem on problem.status_id=status.status_id");//Select * from DB Where foreKey = findKey
            ResultSet rsType = st.executeQuery("select type.type_name from type inner join problem on problem.type_id=type.type_id");
            String name = null;
            String detail = null;
            String status = null;
            String type = null;
            Date date = null;
            while(rsProblem.next()){
                name = rsProblem.getString("problem_name");
                detail = rsProblem.getString("description");
                date = rsProblem.getDate("problem_date");
            }
            while(rsStatus.next())    
                status = rsStatus.getString("status_name");
            while(rsType.next())
                type = rsType.getString("type_name");
            Problem p = new Problem(name, detail, status, type, date);
            allProblem.add(p);
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
    public static void addUser(int problemId,String email){
        
        
    }
    public static void main(String[] args) {
        setProblem();
    }
}
