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
    
    //%%%%%%%%%%%%%%
    // ====================================
    //  QUERY 2: PRODUCTS WITHIN PRICE RANGE
    // ====================================
    public void printProductsInPriceRange(double minPrice, double maxPrice) {

        if (minPrice > maxPrice) {
            System.out.println("❌ Minimum price cannot be greater than maximum price.");
            return;
        }

        int count = printProductsInPriceRangeRec(root, minPrice, maxPrice);

        if (count == 0) {
            System.out.println("⚠ No products found in this price range.");
        }
    }

    // helper – traverses the AVL and prints matching products
    private int printProductsInPriceRangeRec(ProductNode node, double minPrice, double maxPrice) {
        if (node == null)
            return 0;

        int count = 0;

        // traverse left subtree
        count += printProductsInPriceRangeRec(node.left, minPrice, maxPrice);

        // check current product
        double price = node.product.getPrice();
        if (price >= minPrice && price <= maxPrice) {
            System.out.println(node.product);   // uses Products.toString()
            count++;
        }

        // traverse right subtree
        count += printProductsInPriceRangeRec(node.right, minPrice, maxPrice);

        return count;
    }
//&&&&&&&&&&&&&
    
    
    private int countNodes(ProductNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int count() {
        return countNodes(root);
    }

    
    
    public Products[] toArray() {
        int size = count();
        Products[] arr = new Products[size];
        fillArray(root, arr, 0);
        return arr;
    }

    private int fillArray(ProductNode node, Products[] arr, int index) {
        if (node == null) return index;

        index = fillArray(node.left, arr, index);

        arr[index] = node.product;  
        index++;

        index = fillArray(node.right, arr, index);

        return index;
    }
    
    

    private void sortByReviewCount(Products[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].getReviews().getSize() < arr[j+1].getReviews().getSize()) {
                    Products temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    
    
    
    public void printTop3MostReviewed() {
        Products[] arr = toArray();
        sortByReviewCount(arr);

        System.out.println("Top 3 Most Reviewed Products:\n");

        for (int i = 0; i < 3 && i < arr.length; i++) {
            System.out.println((i+1) + ". " + arr[i].getName() + " | Reviews: " + arr[i].getReviews().getSize() );
        }
    }

    
}
