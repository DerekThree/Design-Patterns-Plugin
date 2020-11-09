package Builder;

import Factory.Request;

// Builds a Builder.Product that contains the code
public interface Builder {
    void buildFiles(Request request);
    void buildContent();
    Product getProduct();
}
