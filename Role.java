import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*; 

public class Role {
    private static Map<String, String> role= new HashMap<>();
    static Properties properties = new Properties();
    static {
        role.put("a", "admin");
        
        
    }
    public static String getRole(String user){
           String roleAcc = role.get(user); 
           return roleAcc;
    }
    public static void setRole(String user){
        role.put(user,"user");
    }
    public static void setRoleAdmin(String userAdmin,String newUser){
        role.put(newUser,"admin");
    }
    public static void saveFile(){
        for (Map.Entry<String,String> entry : role.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        try {
            properties.store(new FileOutputStream("role.properties"), null);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void loadFile(){
        try {
            properties.load(new FileInputStream("role.properties"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for (String key : properties.stringPropertyNames()) {
           role.put(key, properties.get(key).toString());
        }
    }

}
