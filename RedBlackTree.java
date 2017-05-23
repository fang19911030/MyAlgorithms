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
        System.out.println(node.key+" "+ node.color+" "+node.p.key);
        inorder(node.right);

    }
    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(Node node){
        if(node == nil){
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return 1+Math.max(left,right);
    }

    public void printRoot(){
        System.out.println(root.key);
    }

    private void transplate(Node u, Node v){
        if(u.p==nil){
            root = v;
        }else if(u == u.p.left){
            u.p.left = v;
        }else{
            u.p.right = v;
        }
        v.p = u.p;
    }

    public Node findKey(int key){
        Node cur = root;
        while(cur != nil){
            if(key<cur.key){
                cur = cur.left;
            }else if(key > cur.key){
                cur = cur.right;
            }else{
                break;
            }
        }
        if(cur==nil){
            return null;
        }else{
            return cur;
        }
    }

    private Node minimum(Node node){
        while(node.left != nil){
            node = node.left;
        }
        return node;
    }

    public void delete(int key){
        Node z = findKey(key);
        if(z==null){
            return;
        }

        Node y = z;
        String yOriginalColor = y.color;
        Node x = null;
        if(z.left == nil){
            x = z.right;
            transplate(z, x);
        }else if(z.right == nil){
            x = z.left;
            transplate(z,x);                      //here is different from the book
        }else{
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if(y.p==z){
                x.p = y;
            }else{
                transplate(y,y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplate(z,y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if(yOriginalColor=="black"){
            deleteFixup(x);
        }

    }

    private void deleteFixup(Node x){
        while(x!=root && x.color == "black"){
            if(x == x.p.left){
                Node w = x.p.right;
                if(w.color == "red"){
                    w.color = "black";
                    x.p.color = "red";
                    leftRotate(x.p);
                    w = x.p.right;
                }
                if(w.left.color =="black" && w.right.color == "black"){
                    w.color = "red";
                    x = x.p;
                }else{
                    if(w.right.color =="black"){
                        w.left.color ="black";
                        w.color = "red";
                        rightRotate(w);
                        w = x.p.right;
                    }
                    w.color = x.p.color;
                    x.p.color = "black";
                    w.right.color = "black";
                    leftRotate(x.p);
                    x=root;
                }
            }else{
                Node w = x.p.left;
                if(w.color=="red"){
                    w.color = "black";
                    x.p.color = "red";
                    rightRotate(x.p);
                    w = x.p.left;
                }
                if(w.left.color == "black" && w.right.color=="black"){
                    w.color = "red";
                    x = x.p;
                }else{
                    if(w.left.color == "black"){
                        w.right.color ="black";
                        w.color = "red";
                        leftRotate(w);
                        w = x.p.left;
                    }
                    w.color = x.p.color;
                    x.p.color = "black";
                    w.left.color = "black";
                    rightRotate(x.p);
                    x=root;
                }
            }
        }
        x.color="black";
    }



    public static void main(String[] args){
        int[] nums = {3,2,1,5,7,9,10,33,22,15,17,90,45,65,18,12,55,8,0,-25};
        RedBlackTree test = new RedBlackTree();
        for(Integer tmp:nums){
            test.treeInsert(tmp);
        }
        test.inorder();
        //test.printRoot();
        //System.out.println(test.getHeight());
        for(Integer tmo:nums){
            test.delete(tmo);
        }
        test.inorder();
        test.printRoot();
        System.out.println(test.getHeight());
    }


}
