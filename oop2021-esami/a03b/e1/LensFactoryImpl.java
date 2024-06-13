package a03b.e1;

import java.util.ArrayList;
import java.util.List;

public class LensFactoryImpl implements LensFactory {

    @Override
    /**
	 * @param <E>
	 * @param i
	 * @return a lens over the i-th element of a List<E>
	 */
    public <E> Lens<List<E>, E> indexer(int i) {
        return new Lens<List<E>,E>() {

            @Override
            public E get(List<E> s) {
                return s.get(i);
            }

            @Override
            public List<E> set(E a, List<E> s) {
                List<E> result = new ArrayList<>(s);
                result.set(i, a);
                return result;
            }
            
        };    
    }

    @Override
    /**
	 * @param <E>
	 * @param i
	 * @param i
	 * @return a lens of i-th row and j-th column of a List<List<E>>
	 */
    public <E> Lens<List<List<E>>, E> doubleIndexer(int i, int j) {
        return new Lens<List<List<E>>,E>() {

            @Override
            public E get(List<List<E>> s) {
                return s.get(i).get(j);
            }

            @Override
            public List<List<E>> set(E a, List<List<E>> s) {
                List<List<E>> result = new ArrayList<>(s);
                List<E> innerList = new ArrayList<>(s.get(i));
                innerList.set(j, a);
                result.set(i, innerList);
                return result;
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @return a lens over the first component of a pair
	 */
    public <A, B> Lens<Pair<A, B>, A> left() {
        return new Lens<Pair<A,B>,A>() {

            @Override
            public A get(Pair<A, B> s) {
                return s.get1();
            }

            @Override
            public Pair<A, B> set(A a, Pair<A, B> s) {
                return new Pair<A,B>(a, s.get2());
            }
            
        };    
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @return a lens over the second component of a pair
	 */
    public <A, B> Lens<Pair<A, B>, B> right() {
        return new Lens<Pair<A,B>,B>() {

            @Override
            public B get(Pair<A, B> s) {
                return s.get2();
            }

            @Override
            public Pair<A, B> set(B a, Pair<A, B> s) {
                return new Pair<A,B>(s.get1(), a);
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 * @param i
	 * @return a lens over the second component of the second component of the i-th element of a List<Pair<A,Pair<B,C>>> 
	 */
    public <A, B, C> Lens<List<Pair<A, Pair<B, C>>>, C> rightRightAtPos(int i) {
        return new Lens<List<Pair<A,Pair<B,C>>>,C>() {

            @Override
            public C get(List<Pair<A, Pair<B, C>>> s) {
                return s.get(i).get2().get2();
            }

            @Override
            public List<Pair<A, Pair<B, C>>> set(C a, List<Pair<A, Pair<B, C>>> s) {
                List<Pair<A, Pair<B, C>>> result = new ArrayList<>(s);
                result.set(i, new Pair<A,Pair<B,C>>(s.get(i).get1(), new Pair<B, C>(s.get(i).get2().get1(), a)));
                return result;
            }
            
        };
    }

}
