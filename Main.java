class AVLTreeNode {
    int data;           
    int height;         
    AVLTreeNode lft;   
    AVLTreeNode rght;  

    public AVLTreeNode(int data) {
        this.data = data;
        this.height = 1; 
    }
}

class AVLTree {
    public AVLTreeNode root;

    public AVLTreeNode insert(AVLTreeNode node, int key) {
        if (node == null) return new AVLTreeNode(key);

        if (key < node.data) {
            node.lft = insert(node.lft, key);
        } else if (key > node.data) {
            node.rght = insert(node.rght, key);
        } else {
            return node; 
        }

        node.height = 1 + Math.max(height(node.lft), height(node.rght));

        int balance = getBalance(node);

        if (balance > 1 && key < node.lft.data) {
            return rotateRight(node);
        }
        
        if (balance < -1 && key > node.rght.data) {
            return rotateLeft(node);
        }
        
        if (balance > 1 && key > node.lft.data) {
            node.lft = rotateLeft(node.lft);
            return rotateRight(node);
        }
      
        if (balance < -1 && key < node.rght.data) {
            node.rght = rotateRight(node.rght);
            return rotateLeft(node);
        }

        return node; 
    }

    public AVLTreeNode rotateRight(AVLTreeNode y) {
        AVLTreeNode x = y.lft;
        AVLTreeNode usm = x.rght;

        x.rght = y;
        y.lft = usm;

        y.height = Math.max(height(y.lft), height(y.rght)) + 1;
        x.height = Math.max(height(x.lft), height(x.rght)) + 1;

        return x;
    }

    public AVLTreeNode rotateLeft(AVLTreeNode x) {
        AVLTreeNode y = x.rght;
        AVLTreeNode usm = y.lft;

        y.lft = x;
        x.rght = usm;

        x.height = Math.max(height(x.lft), height(x.rght)) + 1;
        y.height = Math.max(height(y.lft), height(y.rght)) + 1;

        return y;
    }

     int height(AVLTreeNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(AVLTreeNode node) {
        return node == null ? 0 : height(node.lft) - height(node.rght);
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public boolean search(AVLTreeNode node, int key) {
        if (node == null) return false;

        if (key < node.data) return search(node.lft, key);
        else if (key > node.data) return search(node.rght, key);
        else return true; 
    }

    public boolean search(int key) {
        return search(root, key);
    }

    public AVLTreeNode delete(AVLTreeNode node, int key) {
        if (node == null) return node;

        if (key < node.data) {
            node.lft = delete(node.lft, key);
        } else if (key > node.data) {
            node.rght = delete(node.rght, key);
        } else {
            if ((node.lft == null) || (node.rght == null)) {
                AVLTreeNode temp = (node.lft != null) ? node.lft : node.rght;

                if (temp == null) {
                    return null;
                } else {
                    node = temp; 
                }
            } else {
                AVLTreeNode temp = getMinValueNode(node.rght);
                node.data = temp.data;
                node.rght = delete(node.rght, temp.data);
            }
        }

        node.height = 1 + Math.max(height(node.lft), height(node.rght));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.lft) >= 0) {
            return rotateRight(node);
        }
        if (balance > 1 && getBalance(node.lft) < 0) {
            node.lft = rotateLeft(node.lft);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.rght) <= 0) {
            return rotateLeft(node);
        }
        if (balance < -1 && getBalance(node.rght) > 0) {
            node.rght = rotateRight(node.rght);
            return rotateLeft(node);
        }

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public AVLTreeNode getMinValueNode(AVLTreeNode node) {
        AVLTreeNode current = node;
        while (current.lft != null) {
            current = current.lft;
        }
        return current;
    }

    public void inOrder(AVLTreeNode node) {
        if (node != null) {
            inOrder(node.lft);
            System.out.print(node.data + " ");
            inOrder(node.rght);
        }
    }

    public void preOrder(AVLTreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.lft);
            preOrder(node.rght);
        }
    }

    public void postOrder(AVLTreeNode node) {
        if (node != null) {
            postOrder(node.lft);
            postOrder(node.rght);
            System.out.print(node.data + " ");
        }
    }

    public void dsplTrav() {
        System.out.println("\n In-order traversal:");
        inOrder(root);
        System.out.println("\n Pre-order traversal:");
        preOrder(root);
        System.out.println("\n Post-order traversal:");
        postOrder(root);
    }
}

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

         tree.insert(8);                System.out.println("Inserted " + 8);
        tree.insert(3);                 System.out.println("Inserted " +  3);
        tree.insert(9);                 System.out.println("Inserted " +  9);
        tree.insert(2);                 System.out.println("Inserted " +  2);
        tree.insert(5);                 System.out.println("Inserted " +  5);
        tree.insert(7);                 System.out.println("Inserted " +  7);
        tree.insert(4);                 System.out.println("Inserted " +  4);
         tree.dsplTrav();

        
   
        System.out.println("Searching for 5: " + tree.search(5));

        tree.delete(5);
        System.out.println("Deleted 5");
        tree.dsplTrav();
    }
}
