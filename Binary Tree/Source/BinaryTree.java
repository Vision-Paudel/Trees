
public class BinaryTree {
	
	protected TreeNode root;

	public BinaryTree(){
		root = null;
	}  //end constructor
	
	public BinaryTree (Object rootItem) {
		root = new TreeNode (rootItem, null, null);
 	}  //end constructor

	public BinaryTree(Object rootItem, BinaryTree leftTree, BinaryTree rightTree) { 
		root = new TreeNode(rootItem, null, null);
		attachLeftSubtree(leftTree);
		attachRightSubtree(rightTree);
	}   // end constructor

	public void attachLeft(Object newItem) {
		  if (!isEmpty() && root.getLeft() == null)
		    	root.setLeft(new TreeNode(newItem, null, null));	
	}   // end attachLeft
	
	public void attachRight(Object newItem) {
		  if (!isEmpty() && root.getRight() == null)
		    	root.setRight(new TreeNode(newItem, null, null));	
	}   // end attachRight

	public void attachLeftSubtree(BinaryTree leftTree) throws TreeException
	{
	  	if (isEmpty()) throw new TreeException("Cannot attach left subtree to empty tree.");
	  	else if (root.getLeft() != null) throw new TreeException("Cannot overwrite left subtree.");
	       else {   //no empty tree, no left child
	    		root.setLeft(leftTree.root);
	    		leftTree.makeEmpty();
	    	}  //end attachLeftSubtree
		
	}
	
	public void attachRightSubtree(BinaryTree rightTree) throws TreeException
	{
	  	if (isEmpty()) throw new TreeException("Cannot attach left subtree to empty tree.");
	  	else if (root.getRight() != null) throw new TreeException("Cannot overwrite right subtree.");
	       else {   //no empty tree, no right child
	    		root.setRight(rightTree.root);
	    		rightTree.makeEmpty();
	    	}  //end attachRightSubtree
		
	}
	
	public BinaryTree detachLeftSubtree() throws TreeException
	{
	  	if (isEmpty()) throw new TreeException("Cannot 	detach empty tree.");
		else   { // create a tree that points to the left sub tree
			BinaryTree leftTree;
			leftTree = new BinaryTree (root.getLeft());
			root.setLeft (null);
			return leftTree;
		}
	  }  //end detachLeftSubtree
	
	public BinaryTree detachRightSubtree() throws TreeException
	{
	  	if (isEmpty()) throw new TreeException("Cannot 	detach empty tree.");
		else   { // create a tree that points to the right sub tree
			BinaryTree rightTree;
			rightTree = new BinaryTree (root.getRight());
			root.setRight (null);
			return rightTree;
		}
	 }  //end detachRightSubtree
		
		
	public boolean isEmpty() {    //true is tree is empty
		return root == null;
	}	
	
 	 public void makeEmpty() {   //sets root of tree to null
		root = null;
 	 }

 	public void setRootItem(Object newItem) 	{
		if (root == null)
			root = new TreeNode (newItem, null, null);
		else
		 root.setData(newItem);
	}   //end setRootItem
 	 
 	 public Object getRootItem() throws TreeException {
 		if (root == null)
 			throw new TreeException ("Empty tree");
 		else
 			return root.getData();
 	 } //end getRootItem()

 	 public TreeNode getRoot() {
 		return root;
 	 } //end getRoot()

 	public void preOrderPrint(TreeNode rootNode) throws TreeException
    {
       if (rootNode!=null)
       {
          System.out.print(rootNode.getData() + "  ");
 	    preOrderPrint(rootNode.getLeft());
 	    preOrderPrint(rootNode.getRight());
 	  }
    }
 	 
 	public void inOrderPrint(TreeNode rootNode) throws TreeException
    {
       if (rootNode!=null)
       {
 	    inOrderPrint(rootNode.getLeft());
 	    System.out.print(rootNode.getData() + "  ");
 	    inOrderPrint(rootNode.getRight());
 	  }
    } 
 	 
 	public void postOrderPrint(TreeNode rootNode) throws TreeException
    {
       if (rootNode!=null)
       {
 	    postOrderPrint(rootNode.getLeft());
 	    postOrderPrint(rootNode.getRight());
          System.out.print(rootNode.getData() + "  ");
 	  }
    }
 	 
	
	class TreeNode{	// Tree node class for binary tree
		private Object data;
		private TreeNode left;
		private TreeNode right;
		
		TreeNode(Object data){			
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		TreeNode(Object data, TreeNode left){			
			this.data = data;
			this.left = left;
			this.right = null;
		}
		
		TreeNode(Object data, TreeNode left, TreeNode right){			
			this.data = data;
			this.left = left;
			this.right = right;
			
		}
		
		public Object getData(){
			return data;
		}
		
		public void setData(Object data) {
			this.data = data;
		}
		
		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}
		
		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}
		
		public boolean isFull() {
			return  this.right != null &&
					this.left != null;
		}
		
	}
	
	class TreeException extends RuntimeException{ // Tree exception class for binary tree
		public TreeException(String s) {
			super (s);
		}
	} // end TreeException
	

	
	public static void main (String args[]) throws TreeException
	{ 
	  BinaryTree t = new BinaryTree(Integer.valueOf(1));
	  System.out.println("Element at root of tree = " + t.getRootItem());
	  System.out.println("Initial tree is: ");
	  t.preOrderPrint(t.getRoot());
	  System.out.println();
	  
	  BinaryTree tree1 = new BinaryTree(Integer.valueOf(2));
	  tree1.attachLeft(Integer.valueOf(4));
	  tree1.attachRight(Integer.valueOf(5));
	  System.out.println("Tree1 is: ");
	  tree1.preOrderPrint(tree1.getRoot());
	  System.out.println();

	  BinaryTree tree2 = new BinaryTree(Integer.valueOf(6));
	  tree2.attachLeft(Integer.valueOf(7));
	  tree2.attachRight(Integer.valueOf(8));
	  System.out.println("Tree2 is: ");
	  tree2.preOrderPrint(tree2.getRoot());
	  System.out.println(); 
	  BinaryTree tree3 = new BinaryTree(Integer.valueOf(3));
	  tree3.attachLeftSubtree(tree2);
	  System.out.println("Tree3 is: ");
	  tree3.preOrderPrint(tree3.getRoot());
	  System.out.println();
	
	  t.attachLeftSubtree(tree1);
	  t.attachRightSubtree(tree3);
	  System.out.println("Final tree is: ");
	  t.preOrderPrint(t.getRoot());
	  System.out.println();    
    }

	
	
	
	
	
	
	
	
	
	
}
