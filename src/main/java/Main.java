import org.apache.commons.io.IOUtils;
import java.io.IOException;
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

        String[] resultArray = GroceryParser.parseObjects(output).toString().split("[\\[\\]]");
        String result = resultArray[1];

        String[] myStringArray = result.split("(?<=}),(?=\\{)");
        String name = "";
        Double price = 0.0;
        String type = "";
        String expiration = "";

        for (String s : myStringArray)
        {
            String field = s.split("[{}\n]")[2];

            if(Pattern.compile(nameRegex).matcher(field).find())
            {
                name = Pattern.compile(nameRegex).matcher(field).group();
            }
            if(Pattern.compile(priceRegex).matcher(field).find())
            {
                if (!Pattern.compile(priceRegex).matcher(field).group().equals("null")) {
                    name = Pattern.compile(nameRegex).matcher(field).group();
                }
            }
            if(Pattern.compile(typeRegex).matcher(field).find())
            {
                type = Pattern.compile(typeRegex).matcher(field).group();
            }
            if(Pattern.compile(expirationRegex).matcher(field).find())
            {
                expiration = Pattern.compile(expirationRegex).matcher(field).group();
            }


        }
    }
}
