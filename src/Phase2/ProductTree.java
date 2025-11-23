package Phase2;

public class ProductTree {

    ProductNode root;

    public ProductTree() {
        root = null;
    }

    // ====================================
    // 🌟 GET HEIGHT
    // ====================================
    private int height(ProductNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // ====================================
    // 🌟 BALANCE FACTOR
    // ====================================
    private int getBalance(ProductNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // ====================================
    // 🌟 RIGHT ROTATION (RR)
    // ====================================
    private ProductNode rotateRight(ProductNode y) {
        ProductNode x = y.left;
        ProductNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // ====================================
    // 🌟 LEFT ROTATION (LL)
    // ====================================
    private ProductNode rotateLeft(ProductNode x) {
        ProductNode y = x.right;
        ProductNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // ====================================
    // 🌟 INSERT (AVL)
    // ====================================
    public void insert(Products product) {
        root = insertRec(root, product);
    }

    private ProductNode insertRec(ProductNode node, Products product) {
        if (node == null)
            return new ProductNode(product);

        if (product.getProductId() < node.product.getProductId())
            node.left = insertRec(node.left, product);

        else if (product.getProductId() > node.product.getProductId())
            node.right = insertRec(node.right, product);

        else
            return node; // duplicate

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left-Left
        if (balance > 1 && product.getProductId() < node.left.product.getProductId())
            return rotateRight(node);

        // Right-Right
        if (balance < -1 && product.getProductId() > node.right.product.getProductId())
            return rotateLeft(node);

        // Left-Right
        if (balance > 1 && product.getProductId() > node.left.product.getProductId()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Left
        if (balance < -1 && product.getProductId() < node.right.product.getProductId()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // ====================================
    // 🌟 SEARCH
    // ====================================
    public Products search(int id) {
        ProductNode node = searchRec(root, id);
        return (node != null) ? node.product : null;
    }

    private ProductNode searchRec(ProductNode node, int id) {
        if (node == null)
            return null;

        if (id == node.product.getProductId())
            return node;

        if (id < node.product.getProductId())
            return searchRec(node.left, id);

        return searchRec(node.right, id);
    }

    // ====================================
    // 🌟 IN-ORDER (SORTED PRINTING)
    // ====================================
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(ProductNode node) {
        if (node == null)
            return;

        inOrderRec(node.left);
        System.out.println(node.product);
        inOrderRec(node.right);
    }
}
