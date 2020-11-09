package Factory;

import java.util.ArrayList;

// contains a request for a product
public class Request {
    public int pattern = 0;
    public String name1 = null; // main interface
    public String name2 = null; // secondary interface
    public ArrayList<ArrayList<String>> classNames = new ArrayList<>();
}
