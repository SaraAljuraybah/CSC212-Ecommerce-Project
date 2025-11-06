package Phase1;

public class ProductList implements ListInterface<Products> {
    private class Node {
        Products data;
        Node next;
        Node(Products data) { this.data = data; }
    }

    private Node head;
    private int size;

    @Override
    public void add(Products product) {
        Node newNode = new Node(product);
        if (head == null)
            head = newNode;
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public Products searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getProductId() == id)
                return current.data;
            current = current.next;
        }
        return null;
    }

    public Products searchByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name))
                return current.data;
            current = current.next;
        }
        return null;
    }

    public void displayOutOfStock() {
        Node current = head;
        System.out.println("Out-of-stock products:");
        while (current != null) {
            if (current.data.getStock() == 0)
                System.out.println("- " + current.data.getName());
            current = current.next;
        }
    }

    @Override
    public int getSize() { 
    	return size; }
    
    //**********************************************************
    public void addProduct(int id, String name, double price, int stock) {//O(n)
        // تأكدي إن المنتج ما هو مكرر
        if (searchById(id) != null) {//O(n)
            System.out.println("❌ Product with ID " + id + " already exists!");
            return;
        }

        // إنشاء المنتج الجديد
        Products newProduct = new Products(id, name, price, stock);

        // استخدام الدالة add اللي عندنا من قبل
        add(newProduct);//O(1)

        System.out.println("✅ Product added successfully: " + name);
    }
    //****************************************************************
}
