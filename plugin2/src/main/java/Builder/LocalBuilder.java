package Builder;

import Facade.Configs;
import Factory.Request;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// creates Builder.Product containing the code
// uses config files on local machine
public abstract class LocalBuilder implements Builder {

    Request request = null;
    // files that will become a part of the product
    File interface1 = null;
    File interface2 = null;
    ArrayList<ArrayList<File>> files = null;
    // names of interfaces and classes to populate files with,
    // used for key word substitution
    String inter1 = "ERROR1";
    String inter2 = "ERROR2";
    String class1 = "ERROR3";
    String class2 = "ERROR4";
    // numbers used by factoryMethod()
    int numClass1 = 0; // number of class1 implementations
    int numClass2 = 0; // max number of class2 implementations + 1
    // output directory
    File outDir;
    BufferedWriter bw;
    private boolean filesReady = false;
    private boolean contentReady = false;

    @Override
    // creates a file for each string in request
    // if string is empty skips creating it
    // puts files in files
    public void buildFiles(Request r) {
        Configs.logger.trace("Inside Builder.LocalBuilder.buildFiles()");

        // assign variable values
        outDir = new File(Configs.outDir);
        request = r;
        interface1 = new File(outDir.getAbsoluteFile() + File.separator + request.name1 + ".java");
        interface2 = request.name2 != null ?
                new File(outDir.getAbsoluteFile() + File.separator + request.name2 + ".java") : null;
        inter1 = request.name1;
        inter2 = request.name2;
        numClass1 = request.classNames.size();
        for (ArrayList<String> sArr : request.classNames)
            if (sArr.size() > numClass2)
                numClass2 = sArr.size();

        // create a File object for each class name
        // and store in a structure mirroring request.classNames
        files = new ArrayList<>();
        for (ArrayList<String> rowOfNames : request.classNames) {
            ArrayList<File> rowOfFiles = new ArrayList<>();
            for (String s : rowOfNames) {
                rowOfFiles.add(new File(outDir.getAbsoluteFile() + File.separator + s + ".java"));
            }
            files.add(rowOfFiles);
        }
        filesReady = true;
    }

    @Override
    // populates the files with code
    public void buildContent() {
        Configs.logger.trace("Inside LocalBuilder.buildContent()");
        filesReady = false;
        try {
            outDir.mkdir(); // create the output directory
            if (factoryMethod()) contentReady = true;
        } catch (Exception e) {
            Configs.logger.error(this.getClass().getSimpleName() + ".factoryMethod() exception");
        }
    }

    abstract boolean factoryMethod() throws Exception;

    // writes to bw (buffered writer) a string from config files
    // the string is in the current pattern object (p1, p2, etc.)
    // at index index
    // replaces class names where needed
    void parse(int index) throws IOException {
        String s = Configs.patterns.get(request.pattern-1).get(index);
            s = s.replaceAll(Configs.inter1, inter1);
            s = s.replaceAll(Configs.inter2, inter2);
            s = s.replaceAll(Configs.class1, class1);
            s = s.replaceAll(Configs.class2, class2);
            bw.write(s);
            bw.newLine();
    }

    @Override
    // assemble and return the product
    public Product getProduct() {
        Configs.logger.trace("Inside Builder.LocalBuilder.getProduct()");
        // if file contents not ready then abort
        if (!contentReady) {
            Configs.logger.error("Error in Builder.LocalBuilder.getProduct");
            return null;
        }

        // take interface1, interface2, and all files from 'files'
        // and put them in result
        ArrayList<File> result = new ArrayList<>();
        result.add(interface1);
        result.add(interface2);
        for (ArrayList<File> fileArr : files)
            for (File f : fileArr)
                result.add(f);

        return () -> result;
    }
}
