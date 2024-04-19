import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Main {

    private final static String nameRegex = "(?<=name: )[a-zA-Z]*?(?=,)";
    private final static String priceRegex = "(?<=price: )([0-9]+?.[0-9]+|null)(?=,)";
    private final static String typeRegex = "(?<=type: )[a-zA-Z]*?(?=,)";
    private final static String expirationRegex = "(?<=expiration: )(([0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4})|null)*?(?=\n)";

    public static String readRawDataToString() throws Exception{
        ClassLoader classLoader = Main.class.getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        int errorCount = 0;

        HashMap<String, GroceryProductList> hMap = new HashMap<>();

        String[] resultArray = GroceryParser.parseObjects(output).toString().split("[\\[\\]]");
        String result = resultArray[1];

        String[] myStringArray = result.split("(?<=}), (?=\\{)");
        String name = "";
        Double price = 0.0;
        String type = "";
        String expiration = "";

        for (String s : myStringArray)
        {
            Boolean error = false;

            String[] fieldArray = s.split("[{}\n]");
            String field = fieldArray[2];
            // Create a regex pattern for each field
            Pattern namePattern = Pattern.compile("(?<=name: )[a-zA-Z]*?(?=,)");
            Pattern pricePattern = Pattern.compile("(?<=price: )([0-9]+?.[0-9]+|null)(?=,)");
            Pattern typePattern = Pattern.compile("(?<=type: )[a-zA-Z]*?(?=,)");
            Pattern expiryPattern = Pattern.compile("(?<=expiry: )(([0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4})|null)*?(?=\n)");

            // Apply the pattern on the object string
            Matcher nameMatcher = namePattern.matcher(field);
            Matcher priceMatcher = pricePattern.matcher(field);
            Matcher typeMatcher = typePattern.matcher(field);
            Matcher expiryMatcher = expiryPattern.matcher(field);

            if(nameMatcher.find())
            {
                name = nameMatcher.group();
                if(name.equals("null"))
                {
                    error = true;
                    errorCount++;
                }
            }
            if(priceMatcher.find())
            {
                if(priceMatcher.group().equals("null"))
                {
                    price = null;
                    error = true;
                    errorCount++;
                }
                else
                {
                    price = Double.parseDouble(priceMatcher.group());
                }
            }
            if(typeMatcher.find())
            {
                type = typeMatcher.group();
                if(type.equals("null"))
                {
                    error = true;
                    errorCount++;
                }
            }
            if(expiryMatcher.find())
            {
                expiration = expiryMatcher.group();
                if(expiration.equals("null"))
                {
                    error = true;
                    errorCount++;
                }
            }

            if(!error)
            {
                if(hMap.containsKey(name))
                {
                    GroceryProductList product = hMap.get(name);
                    Double prices = product.getPrice();
                    //prices.add(price);
                    product.setPrice(prices);
                    hMap.put(name, product);
                }
                else
                {
                    GroceryProductList product = new GroceryProductList(name, price, type, expiration);
                    hMap.put(name, product);
                }
            }

            for (Map.Entry<String, GroceryProductList> entry : hMap.entrySet())
            {

            }

        }
    }
}
