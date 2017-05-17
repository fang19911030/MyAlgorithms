import java.util.Comparator;
public class Myheap<K> {
	public class Node<K>{
		public Node<K> left;
		public Node<K> right;
		public Node<K> parent;
		public K value;
		public Node(K data){
			value = data;
			
		}
		
	}
	
	private Node<K> head;
	private Node<K> last;
	private long size;
	private Comparator<K> comp;
	
	public Myheap(Comparator<K> compare){
		head = null;
		last = null;
		size = 0;
		comp = compare;
	}
	
	public K getHead(){
		return head==null? null:head.value;
	}
	
	public long getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void add(K value){
		Node<K> newNode = new Node<K>(value);
		if(size==0){
			head = newNode;
			last = newNode;
			size++;
			return;
		}
		
		Node<K> node = last;
		Node<K> parent = node.parent;
		while(parent!= null && node!= parent.left){
			node = parent;
			parent = node.parent;
		}
		Node<K> nodeToAdd = null;
		if(parent == null){
			nodeToAdd = mostLeft(head);
			newNode.parent = nodeToAdd;
			nodeToAdd.left = newNode;
		}else if(parent.right == null){
			parent.right = newNode;
			newNode.parent = parent;
		}else{
			nodeToAdd = mostLeft(parent.right);
			nodeToAdd.left = newNode;
			newNode.parent = nodeToAdd;
		}
		last = newNode;
		heapInsertModify();
		size++;
	}
	
	public K popHead(){
		if(size == 0){
			return null;
		}
		
		Node<K> res = head;
		if(size == 1){
			head = null;
			last = null;
			size--;
			return res.value;
		
		}
		
		Node<K> oldLast = popLastAndSetPrevious();
		if(size==1){
			head = oldLast;
			last = oldLast;
			return res.value;
		}
		
		Node<K> headLeft = res.left;
		Node<K> headRight = res.right;
		oldLast.left = headLeft;
		if(headLeft != null){
			headLeft.parent = oldLast;
		}
		oldLast.right = headRight;
		if(headRight!= null){
			headRight.parent = oldLast;
		}
		res.left = null;
		res.right = null;
		head = oldLast;
		heapify(oldLast);
		return res.value;
		
	}
	
	private Node<K> mostLeft(Node<K> node){
		while(node.left!=null){
			node = node.left;
		}
		return node;
	}
	
	private Node<K> mostRight(Node<K> node){
		while(node.right != null){
			node = node.right;
		}
		return node;
	}
	
	private void heapInsertModify(){
		Node<K> node  = last;
		Node<K> parent = node.parent;
		
		if(parent!= null && comp.compare(node.value, parent.value)<0){
			last = parent;
		}
		
		while(parent != null && comp.compare(node.value,parent.value)<0){
			swapClosedTwoNodes(node,parent);
			parent = node.parent;
		}
		
		if(head.parent != null){
			head = head.parent;
		}
	}
	
	private void heapify(Node<K> node){
		Node<K> left = node.left;
		Node<K> right = node.right;
		Node<K> most = node;
		while(left != null){
			if(left != null && comp.compare(left.value, most.value)<0){
				most = left;
			}
			if(right != null && comp.compare(right.value, most.value)<0){
				most = right;
			}
			if(most != node){
				swapClosedTwoNodes(most, node);
			}else{
				break;
			}
			left = node.left;
			right = node.right;
			most = node;
		}
		if(node.parent==last){
			last = node;
		}
		while(node.parent!= null){
			node = node.parent;
		}
		head = node;
	}
	
	private void swapClosedTwoNodes(Node<K>node, Node<K>parent){
		if(node == null || parent == null){
			return;
		}
		Node<K> parentParent = parent.parent;
		Node<K> parentLeft = parent.left;
		Node<K> parentRight = parent.right;
		Node<K> nodeLeft = node.left;
		Node<K> nodeRight = node.right;
		if(parentParent!= null){
			if(parent == parentParent.left){
				parentParent.left = node;
			}else{
				parentParent.right = node;
			}
		}
		parent.parent = node;
		if(nodeLeft != null){
			nodeLeft.parent = parent;
		}
		if(nodeRight != null){
			nodeRight.parent=parent;
		}
		if(node==parent.left){
			node.left = parent;
			node.right = parentRight;
			if(parentRight != null){
				parentRight.parent = node;
			}
		}else{
			node.right = parent;
			node.left = parentLeft;
			if(parentLeft != null){
				parentLeft.parent = node;
			}
		}
		parent.left = nodeLeft;
		parent.right = nodeRight;
	}
	
	private Node<K> popLastAndSetPrevious(){
		Node<K> node = last;
		Node<K> parent = node.parent;
		while(parent != null && node !=parent.right){
			node = parent;
			parent = node.parent;
		}
		if(parent == null){
			node = last;
			parent = node.parent;
			node.parent = null;
			if(node == parent.left){
				parent.left = null;
			}else{
				parent.right = null;
			}
			last = mostRight(head);
		}else{
			Node<K> newLast = mostRight(parent.left);
			node = last;
			parent = node.parent;
			node.parent = null;
			if( node == parent.left){
				parent.left = null;
			}else{
				parent.right = null;
			}
			last = newLast;
		}
		size--;
		return node;
	}
	
	

}
