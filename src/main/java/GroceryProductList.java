public class GroceryProductList
{
    String name;
    Double price;
    String type;
    String expiration;

    public GroceryProductList()
    {
        this.name = "";
        this.price = 0d;
        this.type = "";
        this.expiration = "";
    }

    public GroceryProductList(String name, Double price, String type, String expiration) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

//    @Override
//    public String toString()
//    {
//        return "GroceryProductList{" +
//                "name = '" + name + '\'' +
//                 ", price = " + price +
//                "type = '" + type + '\'' +
//                ", expiration = " + expiration +
//                '}';
//    }
}
