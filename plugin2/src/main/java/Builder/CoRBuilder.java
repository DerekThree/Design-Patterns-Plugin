package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CoRBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        for (int i=0; i<12; i++)
            parse(i);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Builder
            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            for (int j=12; j<21; j++)
                parse(j);
            bw.close();
        }

        return true;
    }
}
