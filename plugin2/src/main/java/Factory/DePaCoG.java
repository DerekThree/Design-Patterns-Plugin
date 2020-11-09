package Factory;

import Builder.*;
import Facade.Configs;

import java.util.ArrayList;
import java.util.Arrays;

public class DePaCoG implements Factory {

    private ArrayList<Builder> builders = new ArrayList<Builder>(Arrays.<Builder>asList(
            new AFBuilder(),
            new BuilderBuilder(),
            new FMBuilder(),
            new FacadeBuilder(),
            new CoRBuilder(),
            new MediatorBuilder(),
            new VisitorBuilder(),
            new TMBuilder()
    ));
    private Handler handler = new FilterReceiver(new UppercaseReceiver(new FinalReceiver()));

    @Override
    // returns Factory.Report with names of available design patterns
    public Report createReport() {
        Configs.logger.trace("Inside Factory.DePaCoG.createReport()");
        return () -> Configs.pNames;
    }

    @Override
    // returns a Builder.Product with the requested design pattern
    public Product createProduct(Request request) {
        Configs.logger.trace("Inside Factory.DePaCoG.createProduct()");
        return handler.handle(request);
    }

    // Factory.DePaCoG sends Factory.Request up a Chain of Responsibility through different Handlers
    private class Handler {
        private Handler successor = null;

        Product handle(Request request) {
            Configs.logger.trace("Inside Factory.DePaCoG.Handler.Handle()");
            return successor!=null ? successor.handle(request) : null;
        }

        public Handler (Handler successor) {
            this.successor = successor;
        }

        public Handler() {}

    }

    // checks for errors in Factory.Request
    // returns an empty Builder.Product if error found
    // or passes the request to the successor if ok
    // TODO: implement handle()
    private class FilterReceiver extends Handler {

        public FilterReceiver (Handler successor) {
            super(successor);
        }

        @Override
        Product handle(Request request) {
            Configs.logger.trace("Inside Factory.DePaCoG.FilterReceiver.handle()");
            // TODO: check for errors (length, duplicates, etc)
            if (false) return null;
            // everything ok so pass the request up the Chain
            return super.handle(request);
        }
    }

    // modifies the request so the names start with uppercase letters
    // then passes to the successor
    // TODO: implement handle()
    private class UppercaseReceiver extends Handler {

        @Override
        Product handle(Request request) {
            Configs.logger.trace("Inside Factory.DePaCoG.UppercaseReceiver.handle()");
            // TODO: make names in the request start with uppercase letter
            return super.handle(request);
        }

        public UppercaseReceiver (Handler successor) {
            super(successor);
        }
    }

    // last receiver in the Chain of Responsibility
    // calls the Builder.Builder to build the Builder.Product
    private class FinalReceiver extends Handler {

        Builder builder;

        @Override
        Product handle(Request request) {
            Configs.logger.trace("Inside Factory.DePaCoG.FinalReceiver.handle()");
            builder = builders.get(request.pattern-1);
            builder.buildFiles(request);
            builder.buildContent();
            return builder.getProduct();
        }
    }

}
