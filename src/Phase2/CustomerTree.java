package Phase2;

public class CustomerTree {

    private CustomerNode root;

    public CustomerTree() {
        root = null;
    }


    private int height(CustomerNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

 
    private int getBalance(CustomerNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }


    private CustomerNode rotateRight(CustomerNode y) {
        CustomerNode x = y.left;
        CustomerNode T2 = x.right;

        x.right = y;
        y.left = T2;

 
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; 
    }

  
    private CustomerNode rotateLeft(CustomerNode x) {
        CustomerNode y = x.right;
        CustomerNode T2 = y.left;

      
        y.left = x;
        x.right = T2;

         
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; 
    }

 
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
            return node; 

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

     

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

    public int count() {
        return countRec(root);
    }

    private int countRec(CustomerNode node) {
        if (node == null) return 0;
        return 1 + countRec(node.left) + countRec(node.right);
    }
    
    
    
    
    public Customers[] toArray() {
        int size = count();
        Customers[] arr = new Customers[size];
        fillArray(root, arr, 0);
        return arr;
    }

    private int fillArray(CustomerNode node, Customers[] arr, int index) {
        if (node == null) return index;

        // left
        index = fillArray(node.left, arr, index);

        // current
        arr[index] = node.customer;
        index++;

        // right
        index = fillArray(node.right, arr, index);

        return index;
    }  
    
    
    
    
    
    private void sortByName(Customers[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].getName().compareToIgnoreCase(arr[j+1].getName()) > 0) {
                    Customers temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    
    
    
    public void printCustomersAlphabetically() {
        Customers[] arr = toArray();
        sortByName(arr);

        System.out.println("Customers Sorted Alphabetically:\n");

        for (int i = 0; i < arr.length; i++) {
            System.out.println((i+1) + ". " + arr[i].getName());
        }
    }

    
    
    public boolean isEmpty() {
        return root == null;
    }

}
