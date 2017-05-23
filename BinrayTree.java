package MyAlgorithms;
import java.io.*;

/**
 * Created by fang on 5/21/17.
 */
public class BinrayTree {
    public class Node{
        public int key;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int i)
        {
            key = i;
        }
    }

    private Node root;

    public void inorder(){
        Node cur = root;
        inorder(cur);
    }

    private void inorder(Node root){
        if(root== null){
            return;
        }
        inorder(root.left);
        System.out.print(root.key);
        inorder(root.right);
    }

    public void preorder(){
        Node cur = root;
        preorder(cur);
    }

    private void preorder(Node node){
        if(node == null){
            return ;
        }
        System.out.print(node.key+" ");
        preorder(node.left);
        preorder(node.right);
    }

    public Node treeSearch(int data){
        return treeSearch(root, data);
    }

    private Node treeSearch(Node node, int data){
        if(node == null){
            return null;
        }
        if(node.key == data){
            return node;
        }else if(data >= node.key){
            return treeSearch(node.right,data);
        }else{
            return treeSearch(node.left, data);
        }
    }

    public Node treeMinimum(){
        return minimum(root);
    }

    private Node minimum(Node node){
        if(node != null){
            while(node.left!= null){
                node = node.left;
            }
            return node;
        }else{
            throw  new NullPointerException("the input node is null");
        }
    }

    public Node treeMaximum(){
        return maximum(root);
    }

    private Node maximum(Node node){
        if(node != null){
            while(node.right != null){
                node = node.right;
            }
            return node;
        }else{
            throw new NullPointerException("the input node is null");
        }
    }

    private Node treeSuccessor(Node node){
        if(node.right!= null){
            return minimum(node.right);
        }else{
            Node parent = node.parent;
            while(parent!= null && node == parent.right){
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public void treeInsert(int key){
        treeInsert(root, key);
    }

    private void treeInsert(Node root, int key){
        Node y = null;
        Node cur = root;
        while(cur != null){
            y=cur;
            if(key<cur.key){
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
        Node z = new Node(key);
        z.parent = y;
        if(y==null){
            this.root = z;
        }else if(z.key<y.key){
            y.left = z;
        }else{
            y.right = z;
        }
    }
    private void transplante(Node u, Node v){
        if(u.parent == null){
            root = v;
        }else if(u == u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        if(v!=null){
            v.parent = u.parent;
        }
    }

    public void treeDelete(int k){
        Node z = treeSearch(k);
        if(z == null){
            return;
        }else{
            if(z.left== null){
                transplante(z,z.right);
            }else if(z.right == null){
                transplante(z, z.left);
            }else{
                Node y = minimum(z.right);
                if(y.parent != z){
                    transplante(y,y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplante(z,y);
                y.left = z.left;
                y.left.parent = y;
            }
        }
    }

    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return 1+Math.max(left,right);
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 4, 7, 6, 8,9,10};
        BinrayTree test = new BinrayTree();
        for(Integer tmp:nums){
            test.treeInsert(tmp);
        }
        test.inorder();
        test.treeDelete(9);
        System.out.println();
        test.inorder();
        System.out.println();
        System.out.println(test.getHeight());

    }
}
