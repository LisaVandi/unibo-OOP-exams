package a03b.e1;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

    @Override
    public <X> LazyTree<X> constantInfinite(X value) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return value;
            }

            @Override
            public LazyTree<X> left() {
                return constantInfinite(value);
            }

            @Override
            public LazyTree<X> right() {
                return constantInfinite(value);
            }
            
        };
    }

    @Override
      /**
     * @param <X>
     * @param root
     * @param map
     * @return a tree created recursively, with given root, and with left and right children
     * obtained by getting a pair from the map.
     * E.g. with root 10 and Map [10->(0,20), 20->(15,25)], we model a tree with 10 as root, left child
     * 0, right child 20, left child of the right child 15, and so on.
     */
    public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return root;
            }

            @Override
            public LazyTree<X> left() {
                if (!map.containsKey(root)) {
                        return new LazyTree<X>() {
                            @Override
                            public boolean hasRoot() {
                                return false; // Il nodo sinistro non ha una radice
                            }
        
                            @Override
                            public X root() {
                                throw new UnsupportedOperationException("Node does not have a left child");
                            }
        
                            @Override
                            public LazyTree<X> left() {
                                return null; // Il nodo sinistro non ha figli
                            }
        
                            @Override
                            public LazyTree<X> right() {
                                return null; // Il nodo sinistro non ha figli
                            }
                        };
                }
                X leftRoot = map.get(root).getX(); 
                return fromMap(leftRoot, map);
            }

            @Override
            public LazyTree<X> right() {
                if (!map.containsKey(root)) {
                    return new LazyTree<X>() {

                        @Override
                        public boolean hasRoot() {
                            return false;
                        }

                        @Override
                        public X root() {
                            throw new UnsupportedOperationException("Node does not have a rigth child");
                        }

                        @Override
                        public LazyTree<X> left() {
                            return null;
                        }

                        @Override
                        public LazyTree<X> right() {
                            return null;
                        }
                        
                    };
                }
                X rightRoot = map.get(root).getY(); 
                return fromMap(rightRoot, map);
            }
            
        };
    }

    @Override
    public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return root.isPresent();
            }

            @Override
            public X root() {
                return root.get();
            }

            @Override
            public LazyTree<X> left() {
                return leftSupp.get();
            }

            @Override
            public LazyTree<X> right() {
                return rightSupp.get();    
            }
            
        };
    }

    @Override
    /**
     * @param <X>
     * @param root
     * @param leftOp
     * @param rightOp
     * @return a tree created recursively, with given root, and with left and right children
     * obtained by applying the unary operators to the root.
     */
    public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
        return new LazyTree<X>() {

            @Override
            public boolean hasRoot() {
                return true;
            }

            @Override
            public X root() {
                return root;
            }

            @Override
            public LazyTree<X> left() {
                return fromTwoIterations(leftOp.apply(root), leftOp, rightOp);
            }

            @Override
            public LazyTree<X> right() {
                return fromTwoIterations(rightOp.apply(root), leftOp, rightOp);
            }
            
        };
    }

    @Override
     /**
     * @param <X>
     * @param tree
     * @param bound
     * @return a version of the input tree that is cut so that the tree depth is at most bound,
     * namely, no node has depth greater than bound. For instance, cutting with bound 0 gives an empty
     * tree, cutting with bound 1 gives a tree with (at most) just the root, cutting with bound 2 gives 
     * a tree with (at most) just the root and two children, and so on.
     */
    public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
        return tree;
    }

}
