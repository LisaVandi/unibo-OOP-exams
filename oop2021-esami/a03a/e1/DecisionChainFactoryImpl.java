package a03a.e1;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DecisionChainFactoryImpl implements DecisionChainFactory {

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param b the output to provide
	 * @return a simple decider that directly returns 'b' whatever input receives
	 */
    public <A, B> DecisionChain<A, B> oneResult(B b) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.ofNullable(b);
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return null;
            }
            
        };    
    }

    @Override
    	/**
	 * @param <A>
	 * @param <B>
	 * @param predicate
	 * @param positive
	 * @param negative
	 * @return a decider that if predicate is passed by the input then it delegates to
	 * a decider always returning 'positive', otherwise it delegates to a decider always 
	 * returning 'negative'  
	 */
    public <A, B> DecisionChain<A, B> simpleTwoWay(Predicate<A> predicate, B positive, B negative) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return predicate.test(a) ? oneResult(positive) : oneResult(negative);
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param mapList, e.g. list (a1,b1),...,(an,bn)
	 * @param defaultReply is returned if the input is neither a1, nor a2, ..., nor an
	 * @return a chain of deciders, the first returns 'b1' if it receives 'a1', otherwise 
	 * it delegates to one that returns 'b2' if it receives 'a2', and so on. If latter
	 * decider does not receive 'an', then 'defaultReply' is returned.
	 */
    public <A, B> DecisionChain<A, B> enumerationLike(List<Pair<A, B>> mapList, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                for (Pair<A,B> pair : mapList) {
                    if (pair.get1().equals(a)) {
                        return Optional.of(pair.get2());
                    }
                }
                // return Optional.of(defaultReply);
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return oneResult(defaultReply);
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param predicate
	 * @param positive
	 * @param negative
	 * @return a decider that, if predicate is passed by the input, it delegates to 'positive' decider, 
	 * otherwise to 'negative' decider 
	 */
    public <A, B> DecisionChain<A, B> twoWay(Predicate<A> predicate, DecisionChain<A, B> positive,
            DecisionChain<A, B> negative) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                return predicate.test(a) ? positive : negative;
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param cases, e.g. list (p1,b1),...,(pn,bn)
	 * @param defaultReply
	 * @return a chain of deciders, the first returns 'b1' if its input passes predicate 'p1', otherwise 
	 * it delegates to one that returns 'b2' if its input passes 'p2', and so on. If latter
	 * decider does not receive an input that passes 'pn', then 'deafultReply' is returned.
	 */
    public <A, B> DecisionChain<A, B> switchChain(List<Pair<Predicate<A>, B>> cases, B defaultReply) {
        return new DecisionChain<A,B>() {

            @Override
            public Optional<B> result(A a) {
                return Optional.empty();
            }

            @Override
            public DecisionChain<A, B> next(A a) {
                for (Pair<Predicate<A>,B> pair : cases) {
                    if (pair.get1().test(a)) {
                        return oneResult(pair.get2());
                    } 
                    }    
                return oneResult(defaultReply);
            }
            
        };    
    }

}
