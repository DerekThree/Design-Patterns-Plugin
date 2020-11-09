package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Factory Method Builder
// factoryMethod() implementation
// returns true if success
public class FMBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(0);
        parse(2);
        bw.newLine();
        parse(3);
        parse(4);
        parse(5);
        parse(6);
        bw.close();

        // populate interface2
        bw = new BufferedWriter(new FileWriter(interface2));
        parse(12);
        parse(13);
        parse(14);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Creator
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            class2 = request.classNames.get(i).get(1);
            parse(7);
            parse(8);
            parse(9);
            parse(10);
            parse(11);
            bw.close();
        }

        // populate class2's
        for (int i=0; i<numClass1; i++) { // for each Product interface
            bw = new BufferedWriter(new FileWriter(files.get(i).get(1))); // implementation name
            class2 = request.classNames.get(i).get(1);
            parse(15);
            parse(16);
            parse(17);
            bw.close();
        }
        return true;
    }
}
