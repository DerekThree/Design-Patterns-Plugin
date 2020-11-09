package Factory;

import Builder.Product;

public interface Factory {
    Report createReport();
    Product createProduct(Request request);
}
