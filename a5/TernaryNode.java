package cs445.a5;

class TernaryNode<T> {
  private T data;
  private TernaryNode<T>[] children;

  public TernaryNode() {
    this(null);
  }

  public TernaryNode(T dataPortion) {
    this(dataPortion, null, null, null);
  }

  public TernaryNode(T dataPortion, TernaryNode<T> newLeftChild,
                     TernaryNode<T> newMiddleChild,
                     TernaryNode<T> newRightChild) {
    data = dataPortion;
    @SuppressWarnings("unchecked")
    TernaryNode<T>[] temp = (TernaryNode<T>[])new TernaryNode<?>[3];
    children = temp;
    children[0] = newLeftChild;
    children[1] = newMiddleChild;
    children[2] = newRightChild;
  }

  public T getData() {
    return data;
  }

  public void setData(T newData) {
    data = newData;
  }

  public TernaryNode<T> getLeftChild() {
    return children[0];
  }

  public void setLeftChild(TernaryNode<T> newLeftChild) {
    children[0] = newLeftChild;
  }

  public boolean hasLeftChild() {
    return children[0] != null;
  }

  public TernaryNode<T> getMiddleChild() {
    return children[1];
  }

  public void setMiddleChild(TernaryNode<T> newMiddleChild) {
    children[1] = newMiddleChild;
  }

  public boolean hasMiddleChild() {
    return children[1] != null;
  }

  public TernaryNode<T> getRightChild() {
    return children[2];
  }

  public void setRightChild(TernaryNode<T> newRightChild) {
    children[2] = newRightChild;
  }

  public boolean hasRightChild() {
    return children[2] != null;
  }

  public boolean isLeaf() {
    return (children[0] == null) && (children[1] == null) && (children[2] == null);
  }

  public int getNumberOfNodes() {
    int leftNumber  = 0;
    int middleNumber = 0;
    int rightNumber = 0;
    if(children[0] != null) {
      leftNumber = children[0].getNumberOfNodes();
    }

    if(children[1] != null) {
      middleNumber = children[1].getNumberOfNodes();
    }

    if(children[2] != null) {
      rightNumber = children[2].getNumberOfNodes();
    }

    return 1 + leftNumber + middleNumber + rightNumber;
  }

  public int getHeight() {
    return getHeight(this);
  }
  private int getHeight(TernaryNode<T> node) {
    int height = 0;

    if (node != null)
      height = 1 + getMax(getHeight(node.getLeftChild()),
                            getHeight(node.getMiddleChild()),
                            getHeight(node.getRightChild()));
    return height;
  }

  private int getMax(int a, int b, int c) {
    if (a > b && a > c) {
      return a;
    } else if (b >= a && b > c) {
      return b;
    } else {
      return c;
    }
  }

  public TernaryNode<T> copy() {
    TernaryNode<T> newRoot = new TernaryNode<>(data);

    if (children[0] != null) {
      newRoot.setLeftChild(children[0].copy());
    }

    if(children[1] != null) {
      newRoot.setMiddleChild(children[1].copy());
    }

    if (children[2] != null) {
      newRoot.setRightChild(children[2].copy());
    }

    return newRoot;
  }

}
