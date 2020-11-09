package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Facade Builder
// factoryMethod() implementation
// returns true if success
public class FacadeBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(0);
        parse(1);
        parse(2);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Facade
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(3);
            for (int j=1; j<files.get(i).size(); j++) { // for each component
                class2 = request.classNames.get(i).get(j);
                inter2 = Integer.toString(j);
                parse(4);
            }
            bw.newLine();
            parse(10);
            parse(11);
            parse(12);
            parse(13);
            bw.close();
        }

        // populate class2's
        for (int i=0; i<numClass1; i++) { // for each Facade implementation
            for (int j=1; j<files.get(i).size(); j++) { // for each component
                bw = new BufferedWriter(new FileWriter(files.get(i).get(j)));
                class2 = request.classNames.get(i).get(j);
                parse(14);
                parse(15);
                parse(16);
                bw.close();
            }
        }

        return true;
    }
}
