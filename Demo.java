import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

/**
 * World first console e-commerce application.
 */
public class Demo {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 600);
        priceOnProducts.put(2, 1000);
        priceOnProducts.put(3, 700);
        priceOnProducts.put(4, 1100);
        
    }
    
    public static void main(String[] args) throws IOException {
        Login.loadFile();
        Role.loadFile();
        Scanner sc = new Scanner(System.in);
        String role = "user" ;
        System.out.print(" Login Press '1' / Register Press '2' ");
        
        int RorL = sc.nextInt();
        if(RorL == 2 ){
            System.out.print("Enter username for register: ");
            String password = sc.next();
            
            System.out.print("Enter password for register: ");
            
            String username = sc.next();
            Login.register(username ,password);
            Login.saveFile();
        
        }
        else{
            System.out.print("Enter username : ");
            String user = sc.next();
            System.out.print("Enter password : ");
            String pass = sc.next();
            boolean a = Login.loginAcc(user, pass);
            if (!a){
                System.out.print("Enter username for register: ");
            String password = sc.next();
            
            System.out.print("Enter password for register: ");
            
            String username = sc.next();
            Login.register(username ,password);
            Login.saveFile();
            }
            Role.getRole(user);
            
        }
        Login.saveFile();
        Role.saveFile();
        
    if(role == "user"){
        
        while (!order.isClosed()) {
            int cost;
            int dayCount;
           

            String continueChoice;
            do {
                System.out.print("select number of days : ");
                 dayCount = Integer.parseInt(reader.readLine());
                System.out.print("Please, select a room:" + "\n" +
                        "1 - 1 Single Bed" + "\n" +
                        "2 - 2 Single Bed" + "\n" +
                        "3 - 1 Single Bed + wifi" + "\n" +
                        "4 - 2 Single Bed + wifi" + "\n" );
            
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                
                System.out.print("Count: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
            
        
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = reader.readLine();
            } 
            while (continueChoice.equalsIgnoreCase("Y") );

            if (strategy == null) {
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Credit Card");
                String paymentMethod = reader.readLine();

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            // Order object delegates gathering payment data to strategy object,
            // since only strategies know what data they need to process a
            // payment.
            order.processOrder(strategy);

            System.out.print("Pay " + order.getTotalCost()*dayCount + " units or Continue select room? P/C: ");
            String proceed = reader.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                // Finally, strategy handles the payment.
                if (strategy.pay(order.getTotalCost())) {
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a");
                    String formattedDate = myDateObj.format(myFormatObj);
                    System.out.println("Payment has been successful.");
                    System.out.println("check-in at "+formattedDate);
                    String checkoutTime = formattedDate.substring(0,5)+(Integer.parseInt(formattedDate.substring(4,6))+dayCount)+ formattedDate.substring(6);
                    System.out.println("check-out before "+checkoutTime);
                } else {
                    System.out.println("FAIL! Please, check your data.");
                }
                order.setClosed();
            }
        }
    }

    else if (role == "admin"){


    System.out.print("Enter code");
    String code = sc.nextLine();
    System.out.print("Enter percent");
    Double percent = sc.nextDouble();
    Promotion.setPro(code, percent);
    }

    }
}