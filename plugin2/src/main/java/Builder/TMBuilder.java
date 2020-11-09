package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TMBuilder extends LocalBuilder {

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
        bw.newLine();
        parse(2);
        for (int i=1; i<numClass2; i++) {
            class2 = request.classNames.get(0).get(i);
            parse(3);
        }
        parse(4);
        parse(5);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of AbstractClass
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(6);
            bw.newLine();
            for (int j=1; j<numClass2; j++) { // for each primitive method
                class2 = request.classNames.get(0).get(j);
                parse(7);
                parse(8);
                parse(9);
            }
            parse(10);
            bw.close();
        }

        return true;
    }
}
