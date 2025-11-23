package Phase2;

import java.util.Date;

public class OrderTree {

    private OrderNode root;

    public OrderTree() {
        root = null;
    }

    // ===========================
    //   HEIGHT
    // ===========================
    private int height(OrderNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // ===========================
    //   BALANCE FACTOR
    // ===========================
    private int getBalance(OrderNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // ===========================
    //   RIGHT ROTATION (RR)
    // ===========================
    private OrderNode rotateRight(OrderNode y) {
        OrderNode x = y.left;
        OrderNode T2 = x.right;

        // Rotate
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; 
    }

    // ===========================
    //   LEFT ROTATION (LL)
    // ===========================
    private OrderNode rotateLeft(OrderNode x) {
        OrderNode y = x.right;
        OrderNode T2 = y.left;

        // Rotate
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // ===========================
    //   INSERT (AVL)
    // ===========================
    public void insert(Orders order) {
        root = insertRec(root, order);
    }

    private OrderNode insertRec(OrderNode node, Orders order) {
        if (node == null)
            return new OrderNode(order);

        int id = order.getOrderId();

        if (id < node.order.getOrderId())
            node.left = insertRec(node.left, order);

        else if (id > node.order.getOrderId())
            node.right = insertRec(node.right, order);

        else
            return node; // duplicate

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // =====================
        //   AVL CASES
        // =====================

        // Left-Left
        if (balance > 1 && id < node.left.order.getOrderId())
            return rotateRight(node);

        // Right-Right
        if (balance < -1 && id > node.right.order.getOrderId())
            return rotateLeft(node);

        // Left-Right
        if (balance > 1 && id > node.left.order.getOrderId()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Left
        if (balance < -1 && id < node.right.order.getOrderId()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // ===========================
    //   SEARCH BY ID
    // ===========================
    public Orders search(int id) {
        OrderNode node = searchRec(root, id);
        return (node != null) ? node.order : null;
    }

    private OrderNode searchRec(OrderNode node, int id) {
        if (node == null)
            return null;

        if (id == node.order.getOrderId())
            return node;

        if (id < node.order.getOrderId())
            return searchRec(node.left, id);

        return searchRec(node.right, id);
    }

    // ===========================
    //   IN-ORDER (SORTED BY ID)
    // ===========================
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(OrderNode node) {
        if (node == null)
            return;

        inOrderRec(node.left);
        System.out.println(node.order);
        inOrderRec(node.right);
    }

    // ===========================
    //   ORDERS BETWEEN DATES
    // ===========================
    public void printOrdersBetweenDates(Date start, Date end) {
        printBetweenRec(root, start, end);
    }

    private void printBetweenRec(OrderNode node, Date start, Date end) {
        if (node == null)
            return;

        printBetweenRec(node.left, start, end);

        Date d = node.order.getOrderDate();

        if (!d.before(start) && !d.after(end)) {
            System.out.println(node.order);
        }

        printBetweenRec(node.right, start, end);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}
