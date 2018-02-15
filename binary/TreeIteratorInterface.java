package cs445.binary;

import java.util.Iterator;

/**
 * An interface of iterators for the ADT tree. Note that these iterators are
 * required to be efficient. That is, they may not traverse the entire tree in
 * advance and cache the order, nor may they restart the iteration at each call
 * to next(). They are not required to support remove().
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author William C. Garrison
 * @version 4.5
 */
public interface TreeIteratorInterface<T> {
   /** Creates an iterator to traverse the tree in preorder fashion
    *  @return  the iterator */
    public Iterator<T> getPreorderIterator();

   /** Creates an iterator to traverse the tree in postorder fashion
    *  @return  the iterator */
    public Iterator<T> getPostorderIterator();

   /** Creates an iterator to traverse the tree in inorder fashion
    *  @return  the iterator */
    public Iterator<T> getInorderIterator();

   /** Creates an iterator to traverse the tree in level-order fashion
    *  @return  the iterator */
    public Iterator<T> getLevelOrderIterator();
}

