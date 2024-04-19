import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GroceryParserTest
{
    GroceryParser groceryParser;

    @Before
    public void setUp() throws Exception
    {
        groceryParser = new GroceryParser();
    }

    @Test
    public void testParseObjects() {
        String exp = "{\n" +
                "name: Milk, price: 3.23, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 1/02/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 2/25/2016\n" +
                "}, {\n" +
                "name: Milk, price: 3.23, type: Food, expiration: 1/11/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 3/22/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Milk, price: 3.23, type: Food, expiration: 1/17/2016\n" +
                "}, {\n" +
                "name: Milk, price: 1.23, type: Food, expiration: 4/25/2016\n" +
                "}, {\n" +
                "name: Apples, price: 0.25, type: Food, expiration: 1/23/2016\n" +
                "}, {\n" +
                "name: Apples, price: 0.23, type: Food, expiration: 5/02/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: null, price: 3.23, type: Food, expiration: 1/04/2016\n" +
                "}, {\n" +
                "name: Milk, price: 3.23, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 1/02/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 2/25/2016\n" +
                "}, {\n" +
                "name: Milk, price: null, type: Food, expiration: 1/11/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Co0kies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 3/22/2016\n" +
                "}, {\n" +
                "name: Cookies, price: 2.25, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: Milk, price: 3.23, type: Food, expiration: 1/17/2016\n" +
                "}, {\n" +
                "name: Milk, price: null, type: Food, expiration: 4/25/2016\n" +
                "}, {\n" +
                "name: Apples, price: 0.25, type: Food, expiration: 1/23/2016\n" +
                "}, {\n" +
                "name: Apples, price: 0.23, type: Food, expiration: 5/02/2016\n" +
                "}, {\n" +
                "name: Bread, price: 1.23, type: Food, expiration: 1/25/2016\n" +
                "}, {\n" +
                "name: null, price: 3.23, type: Food, expiration: 1/04/2016\n" +
                "}";

        ArrayList<String> arr = GroceryParser.parseObjects("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##");

        String actual = arr.toString().split("[\\[\\]]")[1];

        assertEquals(exp, actual);
    }

}
