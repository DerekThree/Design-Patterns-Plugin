package Facade;

import com.intellij.openapi.roots.ProjectFileIndex;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.typesafe.config.ConfigFactory.parseFile;

// contains all the variables from config files
public class Configs {

    // *** this path should be changed ***
    public static Config config = ConfigFactory.load(parseFile(new File("C:\\Users\\Dommenthorn\\Desktop\\CS474\\derek_ochal_hw3\\plugin2\\src\\main\\resources\\application.conf")));

    // trace, debug, info, warn, error
    public static Logger logger = LoggerFactory.getLogger("Facade.Configs");

    //
    public static String outDir = config.getString("outDir");

    // pattern names used by DePaCoG class
    public static ArrayList<String> pNames = (ArrayList<String>) config.getStringList("pNames");

    //
    public static String inter1 = config.getString("inter1");
    public static String inter2 = config.getString("inter2");
    public static String class1 = config.getString("class1");
    public static String class2 = config.getString("class2");

    public static List<String> p1 = config.getStringList("p1");
    public static List<String> p2 = config.getStringList("p2");
    public static List<String> p3 = config.getStringList("p3");
    public static List<String> p4 = config.getStringList("p4");
    public static List<String> p5 = config.getStringList("p5");
    public static List<String> p6 = config.getStringList("p6");
    public static List<String> p7 = config.getStringList("p7");
    public static List<String> p8 = config.getStringList("p8");

    public static List<List<String>> patterns = new ArrayList<List<String>> (Arrays.<List<String>>asList(p1,p2,p3,p4,p5,p6,p7,p8));
}
