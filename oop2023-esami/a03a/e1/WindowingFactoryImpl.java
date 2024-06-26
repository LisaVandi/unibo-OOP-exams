package a03a.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class WindowingFactoryImpl implements WindowingFactory {

    @Override
    public <X> Windowing<X, X> trivial() {
        return new Windowing<X,X>() {

            @Override
            public Optional<X> process(X x) {
                return Optional.of(x);
            }
            
        };
    }

    @Override
    /**
     * @param <X>
     * @return the windowing in which the last two elements of the input become a pair
     */
    public <X> Windowing<X, Pair<X, X>> pairing() {
        return new Windowing<X,Pair<X,X>>() {

            private X last;
            private boolean hasLast = false;

            @Override
            public Optional<Pair<X, X>> process(X x) {
                if (hasLast) {
                    Pair<X, X> pair = new Pair<>(last, x);
                    last = x;
                    return Optional.of(pair);
                } else {
                    last = x;
                    hasLast = true;
                    return Optional.empty();
                }
            }
        };
    }

    @Override
    public Windowing<Integer, Integer> sumLastFour() {
        return new Windowing<Integer,Integer>() {
            Queue<Integer> queue = new LinkedList<>();

            @Override
            public Optional<Integer> process(Integer x) {
                if(queue.size() == 4) {
                    queue.poll();
                } 
                queue.add(x);
                return queue.size() == 4 ? Optional.of(queue.stream().mapToInt(Integer::intValue).sum()) : Optional.empty();
            }
           
        };
    }

    @Override
    public <X> Windowing<X, List<X>> lastN(int n) {
        return new Windowing<X,List<X>>() {

            private final LinkedList<X> res = new LinkedList<>();

            @Override
            public Optional<List<X>> process(X x) {
                if (res.size() == n) {
                res.poll(); // ritorna il numero di elementi nella lista
                } 
                res.add(x);
                return res.size() == n ? Optional.of(new LinkedList<>(res)) : Optional.empty();
            }
            
        };
    }

    @Override
    public Windowing<Integer, List<Integer>> lastWhoseSumIsAtLeast(int n) {
        return new Windowing<Integer,List<Integer>>() {

            private final LinkedList<Integer> res = new LinkedList<>();
            int sum = 0;

            @Override
            public Optional<List<Integer>> process(Integer x) {
                res.add(x);
                sum += x;
                while (sum >= n && !res.isEmpty() && (sum - res.peekFirst()) >= n) {
                    sum -= res.poll();
                }
                return sum >= n ? Optional.of(new LinkedList<>(res)) : Optional.empty();
            
            }
            
        };
    }

}
