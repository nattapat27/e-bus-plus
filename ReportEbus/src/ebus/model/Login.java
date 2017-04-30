package ebus.model;
public class Login {
    private static boolean islogin = false;
    public static int cheak(String user,String password){
        int result = 0;
        if(user.equalsIgnoreCase("admin")){
            if(password.equalsIgnoreCase("admin")){
                result = -1;
            }
        }
        else{
            if(Connect.ckLogin(user, password))
                result = 1;
        }
        return result;
    }

    public static boolean isIslogin() {
        return islogin;
    }

    public static void setIslogin(boolean islogin) {
        Login.islogin = islogin;
    }
    
}
