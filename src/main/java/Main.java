import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

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

        String[] myStringArray = result.split("(?<=}),(?=\\{)");
        String name = "";
        Double price = 0.0;
        String type = "";
        String expiration = "";

        for (String s : myStringArray)
        {
            Boolean error = false;

            String field = s.split("[{}\n]")[2];

            if(Pattern.compile(nameRegex).matcher(field).find())
            {
                name = Pattern.compile(nameRegex).matcher(field).group();
                if(name.equals("null"))
                {
                    error = true;
                    errorCount++;
                }
            }
            if(Pattern.compile(priceRegex).matcher(field).find())
            {
                if (!Pattern.compile(priceRegex).matcher(field).group().equals("null")) {
                    price = Double.parseDouble(Pattern.compile(priceRegex).matcher(field).group());
                }
                else
                {
                    price = null;
                    error = true;
                    errorCount++;
                }
            }
            if(Pattern.compile(typeRegex).matcher(field).find())
            {
                type = Pattern.compile(typeRegex).matcher(field).group();
                if(type.equals("null"))
                {
                    error = true;
                    errorCount++;
                }
            }
            if(Pattern.compile(expirationRegex).matcher(field).find())
            {
                expiration = Pattern.compile(expirationRegex).matcher(field).group();
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

                }
                else
                {
                    GroceryProductList product = new GroceryProductList(name, price, type, expiration);
                    hMap.put(name, product);
                }
            }

        }
    }
}
