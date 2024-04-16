import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class GroceryParser
{
    private final static String objRegex  = "#{2}";
    private final static String fieldRegex  = "[;@:^*%]";
    private final static String keyvalRegex  = "[:@^*%]";
    private final static String nameRegex  = "[nN][aA][mM][eE]";
    private final static String priceRegex  = "[pP][rR][iI][cC][eE]]";
    private final static String typeRegex  = "[tT][yY][pP][eE]";
    private final static String expirationRegex  = "[eE][xX][pP][iI][rR][aA][tT][iI][oO][nN]";


    public static ArrayList<String> parseObjects(String parseMe)
    {
        ArrayList<String> objectList = new ArrayList<>();

        String[] objectArray = parseMe.split(objRegex);

        for (String obj : objectArray)
        {
            String field = parseFields(obj);
            field = "{\n" + field.substring(1, field.length() - 1) + "\n}";
            objectList.add((field));
        }
        return objectList;
    }

    public static String parseFields(String parseMe)
    {
        ArrayList<String> fieldList = new ArrayList<>();

        String[] fieldArray = parseMe.split(fieldRegex);

        for (String field : fieldArray)
        {
            StringBuilder sb = new StringBuilder();
            if(Pattern.compile(nameRegex).matcher(sb).find())
            {
                sb.append("name: ");
            }
            else if (Pattern.compile(priceRegex).matcher(sb).find())
            {
                sb.append("price: ");
            }
            else if (Pattern.compile(typeRegex).matcher(sb).find())
            {
                sb.append("type: ");
            }
            else if (Pattern.compile(expirationRegex).matcher(sb).find())
            {
                sb.append("expiration: ");
            }
            else
            {
                sb.append("null: ");
            }

            String val;
            try
            {
                String[] valArray = field.split(keyvalRegex);
                val = valArray[1];
            }
            catch (IndexOutOfBoundsException e)
            {
                val = "null";
            }

            if (!val.equals("null"))
            {
                sb.append(val.toUpperCase(Locale.ROOT).charAt(0)).append(val.toLowerCase(Locale.ROOT).substring(1));
            }
            else
            {
                sb.append(val);
            }

            fieldList.add(field.toString());
        }
        return fieldList.toString();
    }

}
