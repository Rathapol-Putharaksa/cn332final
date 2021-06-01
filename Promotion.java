import java.util.HashMap;
import java.util.Map;

public class Promotion {
    private static Map<String, Double> promotion = new HashMap<>();
    static {
        promotion.put("newyear", 15.0);
        
    }
    public static void setPro(String code,Double percent ){
        promotion.put(code, percent);
    }
   
}
