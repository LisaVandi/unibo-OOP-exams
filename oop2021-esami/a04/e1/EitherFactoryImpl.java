package a04.e1;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.ArrayList;

public class EitherFactoryImpl implements EitherFactory {

    @Override
    public <A, B> Either<A, B> success(B b) {

        return new Either<A,B>() {

            @Override
            public boolean isFailure() {
                return false;    
            }

            @Override
            public boolean isSuccess() {
                return true;
            }

            @Override
            public Optional<A> getFailure() {
                return Optional.empty();
            }

            @Override
            public Optional<B> getSuccess() {
                return Optional.of(b);
            }

            @Override
            public B orElse(B other) {
                return b; // caso del successo => ritorno b
            }

            @Override
            public <B1> Either<A, B1> map(Function<B, B1> function) {
               return success(function.apply(b));
            }

            @Override
            public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
                return function.apply(b);
            }

            @Override
            public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
                if(predicate.test(b)) {
                    return success(b);
                } 
                return failure(failure);
            }

            @Override
            public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
                // funFailure.apply(null); sono nel caso del successo => non la applico
                return funSuccess.apply(b);
            }
            
        };
    }

    @Override
    public <A, B> Either<A, B> failure(A a) {
        return new Either<A,B>() {

            @Override
            public boolean isFailure() {
                return true;
            }

            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public Optional<A> getFailure() {
                return Optional.of(a);
            }

            @Override
            public Optional<B> getSuccess() {
                return Optional.empty();
            }

            @Override
            public B orElse(B other) {
                return other; // fallimento => ritorno other
            }

            @Override
            public <B1> Either<A, B1> map(Function<B, B1> function) {
                return failure(a);
            }

            @Override
            public <B1> Either<A, B1> flatMap(Function<B, Either<A, B1>> function) {
                return failure(a);
            }

            @Override
            public <A1> Either<A1, B> filterOrElse(Predicate<B> predicate, A1 failure) {
                return failure(failure);
            }

            @Override
            public <C> C fold(Function<A, C> funFailure, Function<B, C> funSuccess) {
                return funFailure.apply(a);
            }
            
        };
    }

    @Override
    /**
	 * @param <A>
	 * @param computation
	 * @return either a success (if computation correctly produces a value) 
	 * or a failure (if computation raises an exception)
	 */
    public <A> Either<Exception, A> of(Supplier<A> computation) {
       try {
            return success(computation.get());
        } catch (Exception e) {
            return failure(e);       
       }
    }

    @Override
    /**
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 * @param list
	 * @param function
	 * @return an either: it applies function to all elements of lists; if all results are success it returns a 
	 * success with the list of results; if one fails, it returns that failure.
	 */
    public <A, B, C> Either<A, List<C>> traverse(List<B> list, Function<B, Either<A, C>> function) {
        List<C> result = new ArrayList<>();
        for (B b : list) {
            Either<A, C> either = function.apply(b);
            if (either.isFailure()) {
                return failure(either.getFailure().get());
            }
            result.add(either.getSuccess().get());
        }
        return success(result);
    }

}
