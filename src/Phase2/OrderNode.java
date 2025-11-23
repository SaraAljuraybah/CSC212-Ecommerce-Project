package Phase2;

public class OrderNode {
    Orders order;
    OrderNode left, right;
    int height;

    public OrderNode(Orders order) {
        this.order = order;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
