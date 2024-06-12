package a01a.e1;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import java.util.ArrayList;
import java.util.List;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    /**
	 * @return an acceptor of a sequence of strings such that:
	 * A) any element is accepted
	 * B) any sequence is accepted
	 * C) the result is the number of empty strings (strings with size 0)
	 */
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        final List<String> strings = new ArrayList<>();

        return new Acceptor<String,Integer>() {

            @Override
            public boolean accept(String e) {
                strings.add(e);
                return true; 
            }

            @Override
            public Optional<Integer> end() {
                int count = 0; 
                for (String s: strings) {
                    if (s.length() == 0) {
                        count++;
                    }
                }
                return Optional.of(count);
            }
            
        };
    }

    @Override
    /**
	 * @return an acceptor of a sequence of int such that:
	 * A) any element is accepted, provided it is greater than previous one
	 * B) any increasing sequence is overall accepted
	 * C) the result is the string "e1:e2:...:en"
	 */
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer, String>() {
            private final List<Integer> sequence = new ArrayList<>();
            private boolean isValid = true;
    
            @Override
            public boolean accept(Integer e) {
                if (!sequence.isEmpty() && e <= sequence.get(sequence.size() - 1)) {
                    isValid = false;
                }
                sequence.add(e);
                return isValid;
            }
    
            @Override
            public Optional<String> end() {
                if (!isValid) {
                    return Optional.empty();
                }
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < sequence.size(); i++) {
                    if (i > 0) {
                        result.append(":");
                    }
                    result.append(sequence.get(i));
                }
                return Optional.of(result.toString());
            }
        };
    }

    @Override
    /**
	 * @return an acceptor of a sequence of int such that:
	 * A) any element is accepted
	 * B) any sequence of 3 elements is overall accepted
	 * C) the result is the sum
	 */
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer,Integer>() {

            private final List<Integer> ints = new ArrayList<>();

            @Override
            public boolean accept(Integer e) {
                if(ints.size() > 3) {
                    return false;
                }
                ints.add(e);
                return ints.size() <= 3;
            }

            @Override
            public Optional<Integer> end() {
                if (ints.size() == 3) {
                    int sum = 0;
                    for (Integer integer : ints) {
                        sum += integer;
                    }
                    return Optional.of(sum);
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    /**
	 * @param <E> is the type of input
	 * @param <O1> is the type of output of a1
	 * @param <O2> is the type of output of a2
	 * @param a1 is the first acceptor
	 * @param a2 is the second acceptor
	 * @return an acceptor returning a pair with the result that a1 would give and the result a2 would give
	 */
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E,Pair<O1,O2>>() {

            @Override
            public boolean accept(E e) {
                return a1.accept(e) && a2.accept(e);    
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                var o1 = a1.end();
                var o2 = a2.end();
                if (o1.isPresent() && o2.isPresent()) {
                    return Optional.of(new Pair<>(o1.get(), o2.get()));
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    /**
	 * A general way to build an acceptor, which proceeds step by step maintaining an internal state
	 * of type S.
	 * @param initial, is the initial state
	 * @param stateFun, is a function from input and state to new state (empty if input is not accepted)
	 * @param outputFun, is a function from state to output (empty if output should not be provided)
	 * @return the acceptor
	 */
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
                return new Acceptor<E,O>() {
                    
                    private S state = initial;
        
                    @Override
                    public boolean accept(E e) {
                        var newState = stateFun.apply(e, state); // stato intermedio
                        if (newState.isPresent()) {
                            state = newState.get();
                            return true;
                        }
                        return false;
                    }
        
                    @Override
                    public Optional<O> end() {
                        return outputFun.apply(state);
                    }
                    };
        }

}
