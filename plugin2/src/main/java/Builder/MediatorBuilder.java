package Builder;

import Facade.Configs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MediatorBuilder extends LocalBuilder {

    @Override
    boolean factoryMethod() throws IOException {
        Configs.logger.trace("Inside " + this.getClass().getSimpleName() + ".factoryMethod()");

        // populate interface1
        bw = new BufferedWriter(new FileWriter(interface1));
        parse(0);
        parse(1);
        parse(2);
        bw.close();

        // populate interface2
        bw = new BufferedWriter(new FileWriter(interface2));
        parse(3);
        parse(4);
        parse(5);
        bw.close();

        // populate class1's
        for (int i=0; i<numClass1; i++) { // for each implementation of Mediator
            if (files.get(i).size()<3) {
                Configs.logger.error("Not enough class2 names. Null pointer risk in MediatorBuilder.factoryMethod()");
                return false;
            }

            File f = files.get(i).get(0);
            bw = new BufferedWriter(new FileWriter(f));
            class1 = request.classNames.get(i).get(0);
            parse(6);
            class2 = request.classNames.get(i).get(1);
            parse(7);
            class2 = request.classNames.get(i).get(2);
            parse(8);
            bw.newLine();
            parse(9);
            parse(10);
            parse(11);
            parse(12);
            parse(13);
            class2 = request.classNames.get(i).get(1);
            parse(14);
            parse(15);
            parse(16);
            parse(17);
            bw.close();
        }

        // populate class2's
        for (int i=0; i<numClass1; i++) { // for each implementation of Mediator abstract class
            for (int j=1; j<3; j++) { // for each of the two implementations of Colleague
                bw = new BufferedWriter(new FileWriter(files.get(i).get(j)));
                class2 = request.classNames.get(i).get(j);
                for (int k=18; k<26; k++)
                    parse(k);
                bw.close();
            }
        }

        return true;
    }
}
