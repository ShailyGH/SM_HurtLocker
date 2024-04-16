import org.apache.commons.io.IOUtils;
import java.io.IOException;

public class Main {

    public static String readRawDataToString() throws Exception{
        ClassLoader classLoader = Main.class.getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();

        String[] resultArray = GroceryParser.parseObjects(output).toString().split("[\\[\\]]");
        String result = resultArray[1];

    }
}
