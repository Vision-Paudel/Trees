import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class BinarySearchTree
{
	protected TreeNode root;
	
	public BinarySearchTree() {
		root = null;
	}  // end default constructor
	
	public BinarySearchTree(String rootItem) {
		root = new TreeNode(rootItem, null, null);
	}  // end constructor
	
	public boolean isEmpty() {
		// Returns true if the tree is empty, else returns false.
		return root == null;
	}  // end isEmpty

	public void makeEmpty() {
			// Removes all nodes from the tree.
			root = null;
	}  // end makeEmpty
	
	public String getRootItem() throws TreeException {
		// Returns the item in the tree’s root.
		if (root == null) {
			throw new TreeException("TreeException: Empty tree");
		}
			else {
			return root.getItem();
			}  // end if
		}  // end getRootItem

	public TreeNode getRoot() throws TreeException {
		// additional method to return the root as TreeNode.
		return root;
	}  // end getRoot
	
	/* Function to calculate the height of the tree */
    int height(){
   		 
   	return height(root); 
   		 
    }
    /* Function to calculate the height of the tree recursively */
    int height (TreeNode r){

   		 if (r == null)
   		 		return 0;

   		 int recursiveLeft = height(r.getLeft());
   		 int recursiveRight = height(r.getLeft());
   		 return Math.max(recursiveLeft, recursiveRight)+1 ;
   }	
	
	public void insert(String newItem) {
		root = insertItem(root, newItem);
	}  // end insert
	
	public TreeNode retrieve(String searchKey) {
		    return retrieveItem(root, searchKey);
	}  // end retrieve
	
	protected TreeNode insertItem(TreeNode tNode, String newItem) {
			
			TreeNode newSubtree;
			
			if (tNode == null) {
				// position of insertion found; insert after leaf
				// create a new node
				tNode = new TreeNode(newItem, null, null);
				return tNode;
			}  // end if
			
			String nodeItem = tNode.getItem();

			// search for the insertion position
			if ( tNode.compareTo(newItem) > 0 )  {
				// search the left subtree
				newSubtree = insertItem(tNode.getLeft(), newItem);
				tNode.setLeft(newSubtree);
				return tNode;
			}
			else { // search the right subtree
				newSubtree = insertItem(tNode.getRight(), newItem);
				tNode.setRight(newSubtree);
				return tNode;
			}  // end if
	}  // end insertItem
		
	protected TreeNode retrieveItem(TreeNode tNode, String searchKey) {
			
			TreeNode treeItem;
			
			if (tNode == null) {
				treeItem = null;
			}
			else {
				String nodeItem = tNode.getKey();
				
				if (tNode.compareTo(searchKey) == 0) {
			        // item is in the root of some subtree
			        treeItem = tNode;
			      }
			      else if (tNode.compareTo(searchKey) > 0) {
			        // search the left subtree
			        treeItem = retrieveItem(tNode.getLeft(), searchKey);
			      }
			      else  { // search the right subtree
			        treeItem = retrieveItem(tNode.getRight(), searchKey);
			      }  // end if
			    }  // end if
			    return treeItem;
	}  // end retrieveItem
		
	abstract class KeyedItem
	{	
		private String searchKey;
	
		public KeyedItem (String newItem) {
				searchKey = newItem;	
		}   	// constructor
				
		public int compareTo(String key)
			{	// TODO Auto-generated method stub
				
				if ( (this.searchKey).compareTo((String)key) == 0 )
					return 0;
				else if ( (this.searchKey).compareTo((String)key) < 0 )				
					return -1;
				else
					return 1;				
			}
	
		public String getKey () { // returns search key
			return searchKey;
		} // end getKey
	}

	class TreeNode extends KeyedItem{
	
	  private String item;
	  private TreeNode leftChild;
	  private TreeNode rightChild;
	
	  TreeNode(String newItem) {
			  // Initializes tree node with item and no children.
			  super(newItem);
			  item = newItem;
			  leftChild  = null;
			  rightChild = null;
	  }  	// end constructor
	
	  TreeNode(String newItem, TreeNode left, TreeNode right) {
			  // Initializes tree node with item and
			  // the left and right children references.
			  super(newItem);
			  item = newItem;
			  leftChild  = left;
			  rightChild = right;
	  }  	// end constructor
	
	  public String getItem() {
	  // Returns the item field.
	    return item;
	  }  // end getItem
	
	  public void setItem(String newItem) {
	  // Sets the item field to the new value newItem.
	  item  = newItem;
	  }  // end setItem
	
	  public TreeNode getLeft() {
	  // Returns the reference to the left child.
	    return leftChild;
	  }  // end getLeft
	
	  public void setLeft(TreeNode left) {
	  // Sets the left child reference to left.
	    leftChild  = left;
	  }  // end setLeft
	
	  public TreeNode getRight() {
	  // Returns the reference to the right child.
	    return rightChild;
	  }  // end getRight
	
	  public void setRight(TreeNode right) {
	  // Sets the right child reference to right.
	    rightChild  = right;
	  }  // end setRight
	
	
	  public String toString() {
		  return "" + item;
	  }
	
	  public boolean isLeaf() {
	    return ((getLeft() == null) && (getRight() == null));
	  }
	
	}  // end TreeNode	
	
	public class TreeException extends RuntimeException {
		  public TreeException(String s) {
		    super(s);
		  }  // end constructor
	} // end TreeException
	
	public static void main(String[] args) throws FileNotFoundException {
		
		BinarySearchTree dictionaryTree = new BinarySearchTree();
		
		Scanner dictionaryFileInput = new Scanner(new File("dictionary_large.txt"));

		LinkedList<String> dictionaryWordList = new LinkedList<String>();
		while (dictionaryFileInput.hasNext()) {			
			dictionaryWordList.add(dictionaryFileInput.next());			
		}
		dictionaryFileInput.close();
		
		
		createBinarySearchTree(dictionaryWordList, dictionaryTree);								
		System.out.println("Complete!");
		
		
	}
	
	public static void createBinarySearchTree(LinkedList<String> dictionaryWordList, BinarySearchTree newBST) {	
		
		createBinarySearchTree(dictionaryWordList, newBST, 0, dictionaryWordList.size());			
		
	}

	//Recursively create balanced BST.
	private static void createBinarySearchTree(LinkedList<String> dictionaryWordList, BinarySearchTree newBST, int first, int last) {
					
		if (first < last) {
			int middle = (first + last) / 2;
						
			newBST.insert( dictionaryWordList.get(middle) );
			createBinarySearchTree(dictionaryWordList, newBST, first, middle);				
			createBinarySearchTree(dictionaryWordList, newBST, middle + 1, last);
							
		}			
	}
	
	
	
}
