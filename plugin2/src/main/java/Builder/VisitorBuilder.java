package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VisitorBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(0);
        for (int i=1; i<numClass2; i++) {
            class2 = request.classNames.get(0).get(i);
            parse(1);
        }
        parse(2);
        bw.close();

        // populate interface2
        bw = new BufferedWriter(new FileWriter(interface2));
        parse(3);
        parse(4);
        parse(5);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Visitor
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(6);
            for (int j=1; j<files.get(0).size(); j++) { // for each implementation of Element
                class2 = request.classNames.get(0).get(j);
                parse(7);
                parse(8);
                parse(9);
            }
            parse(10);
            bw.close();
        }

        // populate class2's
        for (int j=1; j<files.get(0).size(); j++) { // for each implementation of Element
            bw = new BufferedWriter(new FileWriter(files.get(0).get(j)));
            class2 = request.classNames.get(0).get(j);
            parse(11);
            parse(12);
            parse(13);
            parse(14);
            parse(15);
            bw.close();
        }

        return true;
    }
}
