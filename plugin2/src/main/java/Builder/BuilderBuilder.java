package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Builder Builder
// factoryMethod() implementation
// returns true if success
public class BuilderBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(0);
        int minClass2 = request.classNames.get(0).size(); // min and max number of class2's implementing inter2
        int maxClass2 = request.classNames.get(0).size(); // for any class1
        for (ArrayList<String> sArr : request.classNames) {
            minClass2 = minClass2<sArr.size()?minClass2:sArr.size();
            maxClass2 = maxClass2>sArr.size()?maxClass2:sArr.size();
        }
        for (int i=1; i<minClass2; i++) {
            class2 = Integer.toString(i);
            parse(1);
        }
        parse(2);
        parse(3);
        bw.close();

        // populate interface2 with interfaces and complex object class
        bw = new BufferedWriter(new FileWriter(interface2));
        class2 = "";
        parse(4);
        parse(6);
        parse(7);
        bw.newLine();
        for (int i=1; i<maxClass2; i++) {
            class2 = Integer.toString(i);
            parse(5);
            parse(6);
            parse(7);
            bw.newLine();
        }
        parse(17);
        parse(18);
        parse(19);
        parse(20);
        parse(21);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Builder
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(8);
            parse(9);
            bw.newLine();
            for (int j=1; j<files.get(i).size(); j++) { // for each implementation of Product
                class2 = Integer.toString(j);
                inter2 = request.name2;
                parse(10);
                class2 = request.classNames.get(i).get(j);
                parse(11);
                parse(12);
            }
            parse(13);
            parse(14);
            parse(15);
            parse(16);
            bw.close();
        }

        // populate class2's
        for (int i=0; i<numClass1; i++) { // for each Product interface
            for (int j=1; j<files.get(i).size(); j++) { // for each implementation
                bw = new BufferedWriter(new FileWriter(files.get(i).get(j)));
                class2 = request.classNames.get(i).get(j);
                inter2 = Integer.toString(j);
                parse(22);
                parse(23);
                parse(24);
                bw.close();
            }
        }

        return true;
    }
}
