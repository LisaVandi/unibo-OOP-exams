package a03b.e1;

import java.util.List;
import java.util.ArrayList;

public class InfiniteIteratorHelpersImpl implements InfiniteIteratorsHelpers {

    @Override
    public <X> InfiniteIterator<X> of(X x) {
        return new InfiniteIterator<X>() {

            @Override
            public X nextElement() {
                return x;
            }

        };
    }

    @Override
    /**
	 * @param l
	 * @return an iterator cyclically giving all elements in l (for example, if l is [1,2,3]
	 * it gives the iterator representing [1,2,3,1,2,3,1,2,3,1,2,3,...]
	 */
    public <X> InfiniteIterator<X> cyclic(List<X> l) {
        
        return new InfiniteIterator<X>() {
            private int currentIndex = 0;
            
            @Override
            public X nextElement() {
                X element = l.get(currentIndex);
                currentIndex = (currentIndex + 1) % l.size();
                return element;
            }
            
        };
    }

    @Override
    /**
	 * @param start
	 * @param increment
	 * @return the iterator representing [start, start+increment, start+increment+increment, ...]
	 */
    public InfiniteIterator<Integer> incrementing(int start, int increment) {
        return new InfiniteIterator<Integer>() {
            private int sum = start;

            @Override
            public Integer nextElement() {
                int result = sum;
                sum += increment;
                return result;
            }
            
        };
    }

    @Override
    public <X> InfiniteIterator<X> alternating(InfiniteIterator<X> i, InfiniteIterator<X> j) {
        return new InfiniteIterator<X>() {
            private boolean isFirst = true;

            @Override
            public X nextElement() {
                X next;
                if (isFirst) {
                    next = i.nextElement();
                    isFirst = !isFirst;
                } else {
                    next = j.nextElement();
                    isFirst = !isFirst;
                }
                return next;
            }
            
        };
    }

    @Override
    public <X> InfiniteIterator<List<X>> window(InfiniteIterator<X> i, int n) {
        return new InfiniteIterator<List<X>>() {
            List<X> res = new ArrayList<>();

            @Override
            public List<X> nextElement() {
                while (res.size() < n) {
                    res.add(i.nextElement());
                }
                List<X> l = new ArrayList<>(res);
                res.remove(0);
                return l;
            }
            
        };
    }

}
