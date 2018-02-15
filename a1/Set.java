package cs445.a1;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Set is an interface that describes the operations of the ADT set. A set is a
 * homogeneous collection of objects. It is unordered, there are no limits on
 * the number of items it can store, and it cannot contain duplicate items.
 */
public class Set<E> implements SetInterface<E> {

  private E[] set;
  private int size;
  private static final int DEFAULT_CAPACITY = 25;

  public Set() {
    this(DEFAULT_CAPACITY);
  }

  public Set(int initialCapacity) {
    //This is to stop any complaints about generics
    @SuppressWarnings("unchecked")
    E[] tempSet = (E[])new Object[initialCapacity];
    set = tempSet;
    size = 0;
  }

  public Set(E[] contents) {
    set = Arrays.copyOf(contents, contents.length);
    size = contents.length;
  }

  /**
   * Determines the current number of entries in this set.
   *
   * @return  The integer number of entries currently in this set
   */
  @Override
  public int getCurrentSize() {
    return size;
  }

  /**
   * Determines whether this set is empty.
   *
   * @return  true if this set is empty; false if not
   */
  @Override
  public boolean isEmpty(){
    return size == 0;
  }

  /**
   * Adds a new entry to this set, avoiding duplicates.
   *
   * <p> If newEntry is not null, this set does not contain newEntry, and this
   * set has available capacity (if fixed), then add modifies the set so that
   * it contains newEntry. All other entries remain unmodified. Duplicates are
   * determined using the .equals() method.
   *
   * <p> If newEntry is null, then add throws IllegalArgumentException without
   * modifying the set. If this set already contains newEntry, then add
   * returns false without modifying the set. If this set has a capacity
   * limit, and does not have available capacity, then add throws
   * SetFullException without modifying the set.
   *
   * @param newEntry  The object to be added as a new entry
   * @return  true if the addition is successful; false if the item already is
   * in this set
   * @throws SetFullException  If this set has a fixed capacity and does not
   * have the capacity to store an additional entry
   * @throws IllegalArgumentException  If newEntry is null
   */
  @Override
  public boolean add(E newEntry) throws SetFullException, IllegalArgumentException {
    if(contains(newEntry)) {
      return false;
    }
    if(size >= set.length) {
      set = resizeArr(set);
    }
    set[size] = newEntry;
    size++;
    return true;

  }

  /**
   * Doubles the array's size and places old elements from array
   * into new array
   *
   * @param arr The array being resized
   * @return The resized array
   */
  private E[] resizeArr(E[] arr) {
    @SuppressWarnings("unchecked")
    E[] tempSet = (E[])new Object[arr.length*2];
    for(int i = 0; i < arr.length; i++) {
      tempSet[i] = arr[i];
    }
    return tempSet;
  }
  /**
   * Removes a specific entry from this set, if possible.
   *
   * <p> If this set contains the entry, remove will modify the set so that it
   * no longer contains entry. All other entries remain unmodified.
   * Identifying this entry is accomplished using the .equals() method.
   *
   * <p> If this set does not contain entry, remove will return false without
   * modifying the set. If entry is null, then remove throws
   * IllegalArgumentException without modifying the set.
   *
   * @param entry  The entry to be removed
   * @return  true if the removal was successful; false if not
   * @throws IllegalArgumentException  If entry is null
   */
  @Override
  public boolean remove(E entry) throws IllegalArgumentException {
    for(int i = 0; i < size; i++) {
      if(set[i].equals(entry)){
        set[i] = set[size - 1];
        set[size - 1] = null;
        size--;
        return true;
      }
    }
    return false;
  }

  /**
   * Removes an arbitrary entry from this set, if possible.
   *
   * <p> If this set contains at least one entry, remove will modify the set
   * so that it no longer contains one of its entries. All other entries
   * remain unmodified. The removed entry will be returned.
   *
   * <p> If this set is empty, remove will return null without modifying the
   * set. Because null cannot be added, a return value of null will never
   * indicate a successful removal.
   *
   * @return  The removed entry if the removal was successful; null otherwise
   */
  @Override
  public E remove() {
    if(isEmpty()) {
      return null;
    }else {
      E temp = set[size - 1];
      set[size - 1] = null;
      size--;
      return temp;
    }
  }

  /**
   * Removes all entries from this set.
   *
   * <p> If this set is already empty, clear will not modify the set.
   * Otherwise, the set will be modified so that it contains no entries.
   */
  @Override
  public void clear() {
    for(int i = 0; i < size; i++) {
      remove();
      size--;
    }

  }

  /**
   * Tests whether this set contains a given entry. Equality is determined
   * using the .equals() method.
   *
   * <p> If this set contains entry, then contains returns true. Otherwise
   * (including if this set is empty), contains returns false. If entry is
   * null, then remove throws IllegalArgumentException. The method never
   * modifies this set.
   *
   * @param entry  The entry to locate
   * @return  true if this set contains entry; false if not
   * @throws IllegalArgumentException  If entry is null
   */
  @Override
  public boolean contains(E entry) throws IllegalArgumentException {
    for(int i = 0; i < size; i++) {
      if(entry.equals(set[i])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves all entries that are in this set.
   *
   * <p> An array is returned that contains a reference to each of the entries
   * in this set. The returned array's length will be equal to the number of
   * elements in this set, and thus the array will contain no null values.
   *
   * <p> If the implementation of set is array-backed, toArray will not return
   * the private backing array. Instead, a new array will be allocated with
   * the appropriate capacity.
   *
   * @return  A newly-allocated array of all the entries in this set
   */
  @Override
  public E[] toArray() {
    @SuppressWarnings("unchecked")
    E[] result = (E[])new Object[size];
    for(int i = 0; i < size; i++) {
      result[i] = set[i];
    }
    return result;
  }


}
