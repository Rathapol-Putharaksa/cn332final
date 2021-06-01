import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Concrete strategy. Implements PayPal payment method.
 */
public class PayByPayPal implements PayStrategy {
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

   

    /**
     * Collect customer's data.
     */
    public static void register(String user,String password){
        DATA_BASE.put(user, password);
    }
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                Scanner sc = new Scanner(System.in);
                


                
                System.out.print("Enter the user's email or username: ");
                email = READER.readLine();
                System.out.print("Enter the password: ");
                password = READER.readLine();
                if (verify()) {
                    System.out.println("Data verification has been successful.");
                } else {
                    
                    System.out.println("Wrong email/username or password!");
                    System.out.println("please register paypal account");
                    System.out.print("Enter username : ");
                    
                    String user = sc.nextLine();
                    System.out.print("Enter password : ");
                    String password = sc.nextLine();
                    
                    this.register(user, password);
                }
                }
                
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * Save customer data for future shopping attempts.
     */
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Paying " + paymentAmount + " using PayPal.");
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}