package MyAlgorithms;

/**
 * Created by fang on 5/22/17.
 */
public class RedBlackTree {
    public class Node{
        private String color;
        private int key;
        private Node left;
        private Node right;
        private Node p;

        public Node(){
            color = "black";
        }
        public Node(int data){
            key = data;
        }

    }

    private Node root;
    private Node nil;

    public RedBlackTree(){
        nil= new Node();
        root = nil;
    }

    private void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if(y.left != nil){
            y.left.p = x;
        }
        y.p = x.p;
        if(x.p == nil){
            root = y;
        }else if(x==x.p.left){
            x.p.left = y;
        }else{
            x.p.right = y;
        }
        y.left = x;
        x.p = y;
    }

    private void rightRotate(Node y){
        Node x = y.left;
        y.left = x.right;
        if(x.right != nil){
            x.right.p =y;
        }
        x.p = y.p;
        if(y.p==nil){
            root = x;
        }else if(y == y.p.left){
            y.p.left = x;
        }else{
            y.p.right = x;
        }
        x.right = y;
        y.p = x;
    }

    public void treeInsert(int key){
        Node y = nil;
        Node x = root;
        while(x != nil){
            y=x;
            if(key<x.key){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        Node z = new Node(key);
        z.p =y;
        if(y==nil){
            root = z;
        }else if(z.key<y.key){
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = nil;
        z.right = nil;
        z.color = "red";
        insertFixup(z);
    }

    private void insertFixup(Node z){
        while(z.p.color=="red"){
            if(z.p ==z.p.p.left){
                Node y = z.p.p.right;
                if(y.color == "red"){
                    z.p.color="black";
                    y.color = "black";
                    z.p.p.color = "red";
                    z = z.p.p;
                    continue;
                }else if(z == z.p.right){
                    z = z.p;
                    leftRotate(z);
                }
                z.p.color = "black";
                z.p.p.color = "red";
                rightRotate(z.p.p);
            }else if(z.p == z.p.p.right){
                Node y = z.p.p.left;
                if(y.color =="red"){
                    z.p.color="black";
                    y.color ="black";
                    z.p.p.color = "red";
                    z= z.p.p;
                    continue;
                }else if(z == z.p.left){
                    z = z.p;
                    rightRotate(z);
                }
                z.p.color = "black";
                z.p.p.color = "red";
                leftRotate(z.p.p);

            }
        }
        root.color="black";
    }

    public void inorder(){
        inorder(root);
    }

    private void inorder(Node node){
        if(node == nil){
            return;
        }
        inorder(node.left);
        System.out.print(node.key+" ");
        inorder(node.right);

    }

    public static void main(String[] args){
        int[] nums = {3,2,1,5,7,9,10,33,22,15,17};
        RedBlackTree test = new RedBlackTree();
        for(Integer tmp:nums){
            test.treeInsert(tmp);
        }
        test.inorder();
    }


}
