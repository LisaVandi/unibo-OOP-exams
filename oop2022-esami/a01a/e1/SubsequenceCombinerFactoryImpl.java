package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            /**
             * @return a SubsequenceCombiner that turns triplets of integers into their sum
             * e.g.: e1,e2,e3,e4,e5,e6,e7,e8 --> (e1+e2+e3),(e4+e5+e6),(e7+e8)
             */
            public List<Integer> combine(List<Integer> list) {
                List<Integer> res = new ArrayList<>();
                int count = 0;
                int sum = 0;
                
                for (Integer integer : list) {
                    sum += integer;
                    count += 1;
                    
                    if (count % 3 == 0) {
                        res.add(sum);
                        sum = 0;
                        count = 0;
                    }
                }
                if (count > 0) {
                    res.add(sum);
                }
                return res;
            }
            
        };
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X,List<X>>() {

            @Override
            public List<List<X>> combine(List<X> list) {
                List<List<X>> res = new ArrayList<>();
                List<X> intList = new ArrayList<>();
                int count = 0;
                
                for (X x : list) {
                    intList.add(x);
                    count += 1;

                    if (count == 3) {
                        res.add(new ArrayList<>(intList));
                        intList.clear();
                        count = 0;
                    }
                }
                if (count > 0) {
                    res.add(new ArrayList<>(intList));
                }
                return res;
            }
            
        };
    }

    @Override
    /**
	 * @return a SubsequenceCombiner that turns subsequences of integers ending with a zero
	 * into their size (zero excluded)
	 * e.g.: e1,e2,0,f1,f2,f3,0,g1,g2,g3,g4,g5 --> 2,3,5
	 */
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                int count = 0;
                List<Integer> res = new ArrayList<>();

                for (Integer integer : list) {
                    count++;
                    if (integer.equals(0)) {
                        res.add(count - 1);
                        count = 0;
                       // res.clear();
                    }
                }
                if (count > 0) {
                    res.add(count);
                }

                return res;
            }
            
        };
    }

    @Override
    /**
	 * @return a generic SubsequenceCombiner that maps one element of the input into one of the ouput
	 * e.g.: e1,e2,e3 --> f(e1),f(e2),f(e3)
	 */
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> res = new ArrayList<>();
                for (X x : list) {
                    res.add(function.apply(x));
                }
                return res;
            }
            
        };
    }

    @Override
    /**
	 * @return a SubsequenceCombiner that turns subsequences of integers as soon as their sum
	 * overcomes @threshold into a list of them
	 * for an example, look at its testcase in class Test
	 */
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer,List<Integer>>() {

            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                List<List<Integer>> res = new ArrayList<>();
                List<Integer> intermedIntegers = new ArrayList<>();
                int sum = 0;

                for (Integer integer : list) {
                    sum += integer;
                    intermedIntegers.add(integer);

                    if (sum >= threshold) {
                        res.add(new ArrayList<>(intermedIntegers));
                        sum = 0;
                        intermedIntegers.clear();
                    }
                }
                if (sum > 0) {
                    res.add(intermedIntegers);
                }
                return res;
            }
            
        };
    }
    
}
