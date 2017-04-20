package ebus.model;
import ebus.controller.Problem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
