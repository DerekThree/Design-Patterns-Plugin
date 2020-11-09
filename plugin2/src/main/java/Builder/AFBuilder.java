package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Abstract Factory Builder
// factoryMethod() implementation
// returns true if success
public class AFBuilder extends LocalBuilder{

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(1);
        int minProducts = request.classNames.get(0).size(); // min and max number of class2's implementing inter2
        int maxProducts = request.classNames.get(0).size(); // for any class1
        for (ArrayList<String> sArr : request.classNames) {
            minProducts = minProducts<sArr.size()?minProducts:sArr.size();
            maxProducts = maxProducts>sArr.size()?maxProducts:sArr.size();
        }
        for (int i=1; i<minProducts; i++) {
            inter2 = request.name2 + Integer.toString(i);
            parse(2);
        }
        parse(3);
        bw.close();

        // populate interface2 with n interfaces
        bw = new BufferedWriter(new FileWriter(interface2));
        for (int i=1; i<maxProducts; i++) {
            inter2 = request.name2 + Integer.toString(i);
            parse(4);
            parse(5);
            parse(6);
            bw.newLine();
        }
        bw.close();

        // populate class1's
        for (int i=0; i<files.size(); i++) { // for each implementation of AF
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(7);
            for (int j=1; j<files.get(i).size(); j++) {
                class2 = request.classNames.get(i).get(j);
                inter2 = request.name2 + Integer.toString(j);
                parse(8);
                parse(9);
                parse(10);
            }
            parse(11);
            bw.close();
        }

        // populate class2's
        for (int i=0; i<numClass1; i++) { // for each Product interface
            for (int j=1; j<files.get(i).size(); j++) { // for each implementation
                bw = new BufferedWriter(new FileWriter(files.get(i).get(j)));
                class2 = request.classNames.get(i).get(j);
                inter2 = request.name2 + Integer.toString(j);
                parse(12);
                parse(13);
                parse(14);
                bw.close();
            }
        }
        return true;
    }
}
