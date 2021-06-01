import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*; 
public class Login {
    private static Map<String, String> account= new HashMap<>();
    static Properties properties = new Properties();
  
    public static void loadFile(){
        try {
            properties.load(new FileInputStream("user.properties"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for (String key : properties.stringPropertyNames()) {
           account.put(key, properties.get(key).toString());
        }
    }

    public static void saveFile(){
        for (Map.Entry<String,String> entry : account.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        try {
            properties.store(new FileOutputStream("user.properties"), null);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("save");
    }

    public static void register(String username ,String password)
    {   Scanner sc = new Scanner(System.in);
        
        account.put(username, password);
        
        System.out.print("Do you want to connect your paypal account (Y/N)");
        String check = sc.nextLine();
        if(check == "Y" || check == "y"){
        
        System.out.print("Enter your account : ");
        String user = sc.nextLine();
        System.out.print("Enter your password : ");
        String pass = sc.nextLine();
        PayByPayPal.register(user, pass);
    
        
    }

    }
    public static boolean loginAcc(String user, String pass){
        Login.loadFile();
        if(pass == account.get(user)){
            return true;
        }
        else{return false;}
    }
}
