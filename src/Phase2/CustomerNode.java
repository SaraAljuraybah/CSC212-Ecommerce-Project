package Phase2;

public class CustomerNode {
    Customers customer;
    CustomerNode left, right;
    int height;

    public CustomerNode(Customers customer) {
        this.customer = customer;
        this.left = null;
        this.right = null;
        this.height = 1; // مهم للـ AVL
    }
}
