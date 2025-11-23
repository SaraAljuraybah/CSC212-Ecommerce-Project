package Phase2;

public class ProductNode {
    Products product;
    ProductNode left, right;
    int height;

    public ProductNode(Products product) {
        this.product = product;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
