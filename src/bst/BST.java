
package bst;

/**
* This is a class that contains a binary search tree and it's methods
 * @param <E>
*/
public class BST<E extends Comparable<E>> 
{
    // private variable of the tree itself
    private BSTNode<E> root;
    
    // constructor
    public BST()
    {
        // create empty tree with an empty node
        root = null;
    }
    
    /**
    * Find the min value in a node's parent
    * used for find a predecessor
    * 
    * @param  cursor    the current node being searched
    * @return           String of the min value in a node    
    */
    public String findMinReverse(BSTNode<E> cursor)
    {
        // if cursor has reached an end, or parent is null
        if (cursor == null || cursor.getParent() == null)
            // return null
            return null;
        // if the parent's right child is not the current node
        if (cursor.getParent().getRChild() != cursor)
            // recursive method to find the min value 
            // (the next one down) by passing in the node's parent
            return findMinReverse(cursor.getParent());
        // else cursor's parent's right child is cursor
        else
            // return that data
            return cursor.getParent().getData().toString();
    }
    
    /**
    * Find the max value in a node's parent
    * used for find a successor
    * 
    * @param  cursor    current node being searched for
    * @return           String of the min value in a node      
    */
    private String findMaxReverse(BSTNode<E> cursor)
    {
        // if cursor has reached an end, or parent is null
        if (cursor == null || cursor.getParent() == null) 
            // if the parent's right child is not the current node
            return null;
        if (cursor.getParent().getLChild() != cursor)
            // recursive method to find the max value 
            // (the next one down) by passing in the node's parent
            return findMaxReverse(cursor.getParent());
        // else cursor's parent's left child is cursor
        else
            // return that data
            return cursor.getParent().getData().toString();
    }
    
    /**
    * Find the successor of a give value
    * 
    * @param  value     the data being searched for
    * @return           String of the successor of a node
    */
    public String findSuc(E value)
    {
        // call and return the overloaded 
        // version of findSec passing in 
        // tree's root and the value
        // we are searching for
        return findSuc(this.root, value);
    }
    
    /**
    * Find the successor of a give value (overloaded)
    * 
    * @param cursor     current node being searched
    * @param value      the value being searched for
    * @return           String of the successor         
    */
    private String findSuc(BSTNode<E> cursor, E value)
    {
        // if the the cursor contains the value being search for
        if(cursor.getData().compareTo(value) == 0) 
        {
            //if the right child is not null
            if(cursor.getRChild() != null)
                // find the left most data of the rigth child
                // and return it's string value
                return getLeftMostData(cursor.getRChild()).toString(); 
            // else right child is null
            else if(cursor.getRChild() == null)
            {
                // if the node's parent's left child is the node
                if(cursor.getParent().getLChild() == cursor)
                    // return the node's parent's data
                    // this data will be the successor
                    return cursor.getParent().getData().toString();
                // if parent is not the correct successor
                else
                    // call method to find max (or in this case,
                    // the first higher number than this node)
                    // we pass in parent since thier is no reason
                    // to check this same node twice
                    return findMaxReverse(cursor.getParent());
            }
        }
        // if node is not equal, then find it
        // if data is greater than root, compare
        // to the right child
        if(cursor.getData().compareTo(value) < 0) 
            // recursion of method using the right child
            // since the data being looked for is 
            // greater than current node
            return findSuc(cursor.getRChild(), value);
        // if node is not equal, then find it
        // if data is less than root, compare
        // to the left child
        if(cursor.getData().compareTo(value) > 0)
            // recursion of method using the left child
            // since the data being looked for is 
            // less than current node
            return findSuc(cursor.getLChild(), value);  
        // when all else fails, compiler wanted this here
        return null;
    }
    
    /**
    * Find the predecessor of a given value
    * 
    * @param  value     the value being searched for
    * @return           String of the predecessor         
    */
    public String findPre(E value)
    {
        // call and return the overloaded 
        // version of findPre passing in 
        // tree's root and the value
        // we are searching for
        return findPre(this.root, value);
    }
    
    /**
    * Find the predecessor of a given value (overloaded)
    * 
    * @param  cursor    current node being searched
    * @param  value     the value being searched for
    * @return           String of the predecessor          
    */
    private String findPre(BSTNode<E> cursor, E value)
    {
        // if the the cursor contains the value being search for
        if(cursor.getData().compareTo(value) == 0)
        {
            //if the left child is not null
            if(cursor.getLChild() != null)
                // find the right most data of the left child
                // and return it's string value
                return getRightMostData(cursor.getLChild()).toString();
            // else left child is null
            else if(cursor.getLChild() == null)
            {
                // if the node's parent's right child is the node
                if(cursor.getParent().getRChild() == cursor)
                    // return the node's parent's data
                    // this data will be the predecessor
                    return cursor.getParent().data.toString();
                // if parent is not the correct successor
                else
                    // call method to find min (or in this case,
                    // the first lower number than this node)
                    // we pass in parent since thier is no reason
                    // to check this same node twice
                    return findMinReverse(cursor.getParent()); // may need fixing
            }
        }
        // if node is not equal, then find it
        // if data is greaterthan root, compare
        // to the right child
        if(cursor.getData().compareTo(value) < 0) 
            // recursion of method using the right child
            // since the data being looked for is 
            // greater than current node    
            return findPre(cursor.getRChild(), value);
        // if node is not equal, then find it
        // if data is less than root, compare
        // to the left child
        if(cursor.getData().compareTo(value) > 0) 
            // recursion of method using the left child
            // since the data being looked for is 
            // less than current node
            return findPre(cursor.getLChild(), value);
        // when all else fails, compiler wanted this here
        return null;
    }
    
    /**
    * Add a value to the tree
    * 
    * @param  value     the value to be added to tree
    * @return           Boolean: true if value was added false if not
    */
    public boolean addValue(E value)
    {
        // if root has no value
        if(root == null)
        {
            // set the current value to it
            root = new BSTNode<E>(value);
            // set parent to null
            root.setParent(null);
            // return true
            return true;
        }
        // if root already has a value
        else
            // call overloaded method to add a value using that value and tree
            return addValue(value, root);
    }
    
    /**
    * Add a value to the tree (overloaded)
    * 
    * @param  value     the value to be added to tree
    * @param  cursor    cursor which should be the tree
    * @return           boolean: true if value was added false if not
    */
    private boolean addValue(E value, BSTNode<E> cursor)
    {
       // if current value is bigger than root
       if(cursor.getData().compareTo(value) < 0) 
       { 
           // if right child is not null
           if(cursor.getRChild() != null)
               // recursive call to add value but using the
               // right child instead
               return addValue(value, cursor.getRChild());
           // else right child is null
           else
           {
               // set cursor's null right child to a 
               // new node
               cursor.setRChild(new BSTNode<E>(value));
               // set cursor's right child's parent as the current
               // node, the method also returns true so 
               // a boolean is returned
               return cursor.getRChild().setParent(cursor);
           }
       }
       // if current value is less than root
       else if(cursor.getData().compareTo(value) > 0) 
       {
          // if left child is not null
          if(cursor.getLChild() != null)
               // recursive call to add value but using the
               // left child instead              
               return addValue(value, cursor.getLChild());
           // else left child is null
           else
           {
               // set cursor's null left child to a 
               // new node
              cursor.setLChild(new BSTNode<E>(value));
               // set cursor's left child's parent as the current
               // node, the method also returns true so 
               // a boolean is returned
              return cursor.getLChild().setParent(cursor);
           }
       }
       // when all else fails
       else 
           // return false
           // compiler needed this
           // also works for ignore duplicates
           return false; 
    }
   
    /**
    * Delete a value from the tree
    * 
    * @param  value     value to be searched then deleted
    * @return           boolean: true if value was deleted, false if value doesn't exist        
    */
    public boolean deleteValue(E value)
    {
        // if the root has no data 
        if(root == null)
            // return false
            return false;
        // if root has a value
        else
            // call overloaded method to delete value
            // passing in the value and root of the tree
            return deleteValue(value, root, null);
    }
    
    /**
    * Delete a value from the tree (overloaded)
    * 
    * @param  value     value to be searched then deleted
    * @param  cursor    node that is assumed to be the root
    * @param  parent    node that should be a parent of the cursor
    * @return           boolean: true if value was deleted, false if value doesn't exist        
    */
    private boolean deleteValue(E value, BSTNode<E> cursor, BSTNode<E> parent)
    {
        // if cursor is null
        if(cursor == null)
            // return false, no such value exist
            return false;
        // if current node contains the data to be deleted
        if(cursor.getData().equals(value)) 
        {
            // if current node has no left child
            if(cursor.getLChild() == null) 
            { 
                // if current node is the root, and with only a right child
                // if(cursor.getData().equals(this.root.getData()))
                if(cursor == this.root)
                {
                    // set the root to the right child
                    // since this statement block has all codes where
                    // left child is null, that means this value can either be
                    // a leaf or connect to another child
                    // in anycase assign the root to the right child, and
                    // if it is a leaf, root will be null (deleted) 
                    // or if there is an existing child, then root will not be that
                    // this takes care of deleting single child nodes and deleting leafs
                    this.root = cursor.getRChild();
                    // return true
                    return true; 
                }
                // this next ones takes over if cursor is not root
                // get the current node's parent, and connect it to childs, right child
                // because remember, left is still null in these statments, and it right child 
                // is null then the cursor can connect to the right child, or null if child is a leaf
                // this takes care of deleting single child nodes and deleting leafs
                // if parent's right child is current node
                if(cursor == parent.getRChild()) 
                    // if parent's right child is current node
                    // connect parent's right child to child's right
                    // since left is null
                    parent.setRChild(cursor.getRChild());
                // if parent's left child is current node
                else
                    // connect parent's left child to child's right
                    // since left is null
                    parent.setLChild(cursor.getRChild());
                // return true
                return true;
            } 
            // left child is NOT null
            else
            {
                // when a left child exist, find the rightmost of left child
                // you will ignore status of right child since we only care about
                // the left childs right most
                // and due to previous statements, this will solv for nodes with
                // 2 children, the other operations will be ignored since they
                // were taking care of when left was null
                // basically, no point in re writing the same operation
                // lets switch data of the cursor with this child we are looking for
                cursor.setData(getRightMostData(cursor.getLChild()));
                // delete laste node and return refrence to entire branch without
                // the far right node
                cursor.setLChild(removeRightMostNode(cursor.getLChild()));
            }
            // return true
            return true;
        }
        // if node is not equal, then find it
        // if data is greaterthan root, compare
        // to the right child
        else if(cursor.getData().compareTo(value) < 0) 
            // recursion of method using the right child
            // since the data being looked for is 
            // greater than current node    
            return deleteValue(value, cursor.getRChild(), cursor);
        // if node is not equal, then find it
        // if data is less than root, compare
        // to the left child
        else if(cursor.getData().compareTo(value) > 0) 
            // recursion of method using the left child
            // since the data being looked for is 
            // less than current node
            return deleteValue(value, cursor.getLChild(), cursor);
        // when all else fails, compiler wanted this here
        return false;
    }
        
    /**
    * Get the tree as a String in prefix order
    * 
    * @param  
    * @return       String of the binary tree in prefix order         
    */
    public String preFix()
    {
        // call and return overloaded version of 
        // this method using root and an empy string
        return preFix(this.root, new String());
    }
    
    /**
    * Get the tree as a String in infix order
    * 
    * @param  
    * @return       String of the binary tree in infix order         
    */
    public String inFix()
    {
        // call and return overloaded version of 
        // this method using root and an empy string
        return inFix(this.root, new String());
    }
    
    /**
    * Get the tree as a String in postfix order
    * 
    * @param  
    * @return       String of the binary tree in postfix order         
    */
    public String postFix()
    {
        // call and return overloaded version of 
        // this method using root and an empy string
        return postFix(this.root, new String());
    }
    
    /**
    * Get the tree as a String in prefix order (overloaded)
    * 
    * @param  cursor    current node to put in order
    * @param  ret       String to pass recursively that records the order of the data
    * @return           String of the binary tree in prefix order         
    */
    private String preFix(BSTNode<E> cursor, String ret)
    {
        // if is not null
        if(cursor != null)
            // add to string current data with a space
            ret += (cursor.data.toString() + " ");
        // if cursor is null
        else 
            // return empty string
            return "";
        // if left child is not null
        if(cursor.getLChild() != null)
            // recursive call using left child
            // to tranverse down the left child
            // set it to string
           ret = preFix(cursor.lchild, ret); 
        // if right child is not null
        if(cursor.getRChild() != null)
            // recursive call using right child
            // to tranverse down the right child
            // set it to string
           ret = preFix(cursor.rchild, ret);
        // return complete string
        return ret;
    }

    /**
    * Get the tree as a String in infix order (overloaded)
    * 
    * @param  cursor    current node to put in order
    * @param  ret       String to pass recursively that records the order of the data
    * @return           String of the binary tree in infix order         
    */
    private String inFix(BSTNode<E> cursor, String ret)
    {
        // if is not null
        if(cursor == null)
            // return empty string
            return "";
        // if left child is not null
        if(cursor.getLChild() != null)
            // recursive call using left child
            // to tranverse down the left child
            // set it to string
            ret = inFix(cursor.lchild, ret); 
        // add to string current data with a space
        ret += (cursor.data.toString() + " ");
         // if right child is not null
        if(cursor.getRChild() != null)
            // recursive call using right child
            // to tranverse down the right child
            // set it to string
            ret = inFix(cursor.rchild, ret);
        // return complete string      
        return ret;
    }  
    
    /**
    * Get the tree as a String in postfix order (overloaded)
    * 
    * @param  cursor    current node to put in order
    * @param  ret       String to pass recursively that records the order of the data
    * @return           String of the binary tree in postfix order         
    */
    private String postFix(BSTNode<E> cursor, String ret)
    {
        // if is not null
        if(cursor == null)
            // return empty string
            return "";
        // if left child is not null
        if(cursor.getLChild() != null)
            // recursive call suing left child
            // to tranverse down the left child
            // set it to string
            ret = postFix(cursor.lchild, ret); 
        // if right child is not null
        if(cursor.getRChild() != null)
            // recursive call suing right child
            // to tranverse down the right child
            // set it to string
            ret = postFix(cursor.rchild, ret);
        // add to string current data with a space
        ret += (cursor.data.toString() + " ");
        // return complete string
        return ret;
    }  

    /**
    * Find the most left data in the most left root from a chosen node in a tree
    * 
    * @param  cursor    node that is recursively used to travel to the far left of a subtree
    * @return           the data within the furthest left node
    */
    private E getLeftMostData(BSTNode<E> cursor)
    {
        // if left child is null
        if(cursor.getLChild() == null)
            // return current node's data
            return (E) cursor.data;
        // left child is not null
        else
            // recursive call to this method using leftchild
            return (E) getLeftMostData(cursor.getLChild());
    }
    
    /**
    * Find the most right data in the most right root from a chosen node in a tree
    * 
    * @param  cursor    node that is recursively used to travel to the far right of a subtree
    * @return           the data within the furthest right node
    */
    private E getRightMostData(BSTNode<E> cursor)
    {
        // if right child is null
        if(cursor.getRChild() == null)
            // return current node's data
            return (E) cursor.data;
        // right child is not null
        else
            // recursive call to this method using rightchild
            return (E) getRightMostData(cursor.getRChild());
    }
    
    /**
    * Find the most right node from a tree
    * 
    * @param  cursor    node that is recursively used to travel to the far right of a subtree
    * @return           the node within the furthest right of the tree
    */
    private BSTNode<E> removeRightMostNode(BSTNode<E> cursor)
    {
        // if cursor's right child is null
        if(cursor.getRChild() == null)
            // return the cursors left child
            return cursor.getLChild();
        // else right child is not null
        else 
            // recursive call to this method using right child
            return removeRightMostNode(cursor.getRChild());
    }
    
    /**
    * A generic node class that has it's own variables and methods
    */
    public static class BSTNode<E extends Comparable<E>>
    {  
        // node private variables
        // data for node
        private E data;
        // node's left child node
        private BSTNode<E> lchild;
        // node's right child node
        private BSTNode<E> rchild;
        // node's parent node
        private BSTNode<E> parent;

        // constructor
        BSTNode()
        {
            // set everything to null
            this.data = null;
            lchild = null;
            rchild = null;
            parent = null;
        }

        // constructor with new data
        BSTNode(E data)
        {
            // set data variable to data passed in
            // everything else is null
            this.data = data;
            lchild = null;
            rchild = null;
            parent = null;
        }

        /**
        * return the data within the node
        * 
        * @param  
        * @return    return the node's data variable          
        */
        public E getData()
        {
            //return data
            return this.data;
        }

        /**
        * set the data within the node
        * 
        * @param  data      the data to be set into the node's data variable
        * @return             
        */
        public void setData(E data)
        {
            // set node's data to data
            this.data = data;
        }

        /**
        * set the right child of current node
        * 
        * @param  rchild    node to be set as the node's right child
        * @return           boolean: true if child was set, false for anything else
        */
        public boolean setRChild(BSTNode<E> rchild)
        {
            // set nodes right child to rchild
            this.rchild = rchild;
            // return true
            return true;
        }

        /**
        * set the left child of current node
        * 
        * @param  lchild    node to be set as the node's right child
        * @return           boolean: true if child was set, false for anything else
        */
        public boolean setLChild(BSTNode<E> lchild)
        {
            // set nodes left child to lchild
            this.lchild = lchild;
            // return true
            return true;
        }
        
        /**
        * set the parent of current node
        * 
        * @param  parent    node to be set as the node's parent
        * @return           boolean: true if child was set, false for anything else
        */
        public boolean setParent(BSTNode<E> parent)
        {
            // set node's parent to parent
            this.parent = parent;
            // return true
            return true;
        }

        /**
        * return the node that is the node's right child
        * 
        * @param  
        * @return       A reference to the node's right child node      
        */
        public BSTNode<E> getRChild()
        {
            // return the right child of this node
            return this.rchild;
        }

        /**
        * return the node that is the node's left child
        * 
        * @param  
        * @return       A reference to the node's left child node      
        */
        public BSTNode<E> getLChild()
        {
            // return the left child of this node
            return this.lchild;
        }
        
        /**
        * return the node that is the node's parent child
        * 
        * @param  
        * @return       A reference to the node's parent node      
        */
        public BSTNode<E> getParent()
        {
            // return the parent of this node
            return this.parent;
        }
    }
}

