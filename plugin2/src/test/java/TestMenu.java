import Builder.*;
import Builder.Product;
import Factory.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMenu {

    Factory f = new DePaCoG();
    Request r = new Request();
    ArrayList<Builder> builders = new ArrayList<Builder>(Arrays.<Builder>asList(
            new AFBuilder(),
            new BuilderBuilder(),
            new FMBuilder(),
            new FacadeBuilder(),
            new CoRBuilder(),
            new MediatorBuilder(),
            new VisitorBuilder(),
            new TMBuilder()
    ));

    @Before
    public void setup() {
        r.pattern = 2;
        r.name1 = "Assembler";
        r.name2 = "Component";
        ArrayList<String> class1 = new ArrayList<String>(Arrays.asList(
                "UpDownAssembler",
                "UpperPart",
                "MiddlePart",
                "LowerPart"));
        ArrayList<String> class2 = new ArrayList<String>(Arrays.asList(
                "InsideOutAssembler",
                "Inside",
                "Outside"));
        ArrayList<String> class3 = new ArrayList<String>(Arrays.asList(
                "LeftRightAssembler",
                "LeftSide",
                "InnerLeftPart",
                "InnerRightPrt",
                "RightSide"));
        r.classNames.add(class1);
        r.classNames.add(class2);
        r.classNames.add(class3);
    }

    @Test
    public void test1() {
        assertNotNull(f);
        assertNotNull(r);
        Product p = f.createProduct(r);
        assertNotNull(p);
        ArrayList<File> fileArr = p.getFiles();
        ArrayList<String> stringArr = new ArrayList<>();
        ArrayList<String> rArr = new ArrayList<>();
        rArr.add(r.name1);
        rArr.add(r.name2);
        for (ArrayList<String> sArr : r.classNames)
            rArr.addAll(sArr);

        for (File f : fileArr) {
            assertTrue(rArr.contains(f.getName().substring(0, f.getName().indexOf('.'))));
            stringArr.add(f.getName().substring(0, f.getName().indexOf('.')));
        }
        for (String s : rArr)
            assertTrue(stringArr.contains(s));
    }
}
