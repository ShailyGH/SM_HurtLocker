import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroceryProductListTest
{
    GroceryProductList groceryProductList;

    @Before
    public void setUp() throws Exception
    {
        groceryProductList = new GroceryProductList("Milk", 3.23, "Food", "06/24/2018");
    }

    @Test
    public void getName() throws Exception {
        String expected = "Milk";
        String actual = groceryProductList.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void setName() throws Exception {
        String expected = "George";
        groceryProductList.setName("George");
        String actual = groceryProductList.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getPrice() throws Exception {
        Double expected = 3.23;
        Double actual = groceryProductList.getPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void setPrice() throws Exception {
        Double expected = 7.50;
        groceryProductList.setPrice(7.50);
        Double actual = groceryProductList.getPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void getType() throws Exception {
        String expected = "Food";
        String actual = groceryProductList.getType();
        assertEquals(expected, actual);
    }

    @Test
    public void setType() throws Exception {
        String expected = "Book";
        groceryProductList.setType("Book");
        String actual = groceryProductList.getType();
        assertEquals(expected, actual);
    }

    @Test
    public void getExpiration() throws Exception {
        String expected = "06/24/2018";
        String actual = groceryProductList.getExpiration();
        assertEquals(expected, actual);
    }

    @Test
    public void setExpiration() throws Exception {
        String expected = "12/31/1999";
        groceryProductList.setExpiration("12/31/1999");
        String actual = groceryProductList.getExpiration();
        assertEquals(expected, actual);
    }
}
