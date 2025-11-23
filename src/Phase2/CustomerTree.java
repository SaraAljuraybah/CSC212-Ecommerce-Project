package Phase2;

public class CustomerTree {

    private CustomerNode root;

    public CustomerTree() {
        root = null;
    }

    // ===========================
    //   HEIGHT
    // ===========================
    private int height(CustomerNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // ===========================
    //   BALANCE FACTOR
    // ===========================
    private int getBalance(CustomerNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // ===========================
    //   RIGHT ROTATION (RR)
    // ===========================
    private CustomerNode rotateRight(CustomerNode y) {
        CustomerNode x = y.left;
        CustomerNode T2 = x.right;

        // دوران
        x.right = y;
        y.left = T2;

        // حدّث الارتفاعات
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // الجذر الجديد
    }

    // ===========================
    //   LEFT ROTATION (LL)
    // ===========================
    private CustomerNode rotateLeft(CustomerNode x) {
        CustomerNode y = x.right;
        CustomerNode T2 = y.left;

        // دوران
        y.left = x;
        x.right = T2;

        // حدّث الارتفاعات
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // الجذر الجديد
    }

    // ===========================
    //   INSERT (AVL)
    // ===========================
    public void insert(Customers customer) {
        root = insertRec(root, customer);
    }

    private CustomerNode insertRec(CustomerNode node, Customers customer) {
        if (node == null)
            return new CustomerNode(customer);

        int id = customer.getCustomerId();

        if (id < node.customer.getCustomerId())
            node.left = insertRec(node.left, customer);
        else if (id > node.customer.getCustomerId())
            node.right = insertRec(node.right, customer);
        else
            return node; // duplicate ID → نتجاهله

        // حدّث الارتفاع
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // احسب الـ balance
        int balance = getBalance(node);

        // =====================
        //   حالات الـ AVL
        // =====================

        // Left-Left
        if (balance > 1 && id < node.left.customer.getCustomerId())
            return rotateRight(node);

        // Right-Right
        if (balance < -1 && id > node.right.customer.getCustomerId())
            return rotateLeft(node);

        // Left-Right
        if (balance > 1 && id > node.left.customer.getCustomerId()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Left
        if (balance < -1 && id < node.right.customer.getCustomerId()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // ===========================
    //   SEARCH BY ID
    // ===========================
    public Customers search(int id) {
        CustomerNode node = searchRec(root, id);
        return (node != null) ? node.customer : null;
    }

    private CustomerNode searchRec(CustomerNode node, int id) {
        if (node == null)
            return null;

        if (id == node.customer.getCustomerId())
            return node;

        if (id < node.customer.getCustomerId())
            return searchRec(node.left, id);

        return searchRec(node.right, id);
    }

    // ===========================
    //   IN-ORDER (BY ID)
    // ===========================
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(CustomerNode node) {
        if (node == null)
            return;

        inOrderRec(node.left);
        System.out.println(node.customer);
        inOrderRec(node.right);
    }

    // (اختياري) لو حبيتي تجيبين عدد العملاء
    public int count() {
        return countRec(root);
    }

    private int countRec(CustomerNode node) {
        if (node == null) return 0;
        return 1 + countRec(node.left) + countRec(node.right);
    }
    
    public boolean isEmpty() {
        return root == null;
    }

}
