package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> result = new ArrayList<>();
                int count = 0;
                int sum = 0;
                for (Integer integer : list) {
                    sum += integer; 
                    count++; 
                    if (count == 3) {
                        result.add(sum);
                        count = 0;
                        sum = 0;
                    }
                }
                // considero elementi rimasti che non formano una tripletta
                if (count > 0) {
                    result.add(sum);
                }
                return result;
            }
            
        };    
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X,List<X>>() {

            @Override
            public List<List<X>> combine(List<X> list) {
                List<List<X>> result = new ArrayList<>();
                List<X> currentTriplet = new ArrayList<>();
                
                for (X elem : list) {
                    currentTriplet.add(elem); 
                    if(currentTriplet.size() == 3){
                        // result.add(currentTriplet); NON FUNZIONA
                        result.add(new ArrayList<>(currentTriplet));
                        currentTriplet.clear();
                    }
                }
                if (!currentTriplet.isEmpty()) {
                    result.add(currentTriplet);
                }
                return result;
                
            }
            
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
       return new SubsequenceCombiner<Integer,Integer>() {

        @Override
        public List<Integer> combine(List<Integer> list) {
            int count = 0;
            List<Integer> result = new ArrayList<>();

            for (Integer elem : list) {
                if (elem != 0) {
                    count++;
                } else {
                    if (count > 0) {
                        result.add(count);
                        count = 0;
                    }
                }
            }
            if (count > 0) {
                result.add(count);
            }
            return result;
        }
        
       };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> result = new ArrayList<>();
                for (X x: list) {
                    result.add(function.apply(x));
                }
                return result;
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
                List<Integer> tmp = new ArrayList<>();
                List<List<Integer>> result = new ArrayList<>();
                int sum = 0;

                for (Integer elem : list) {
                    sum += elem; 
                    tmp.add(elem);
                    if (sum >= threshold) {
                        sum = 0;
                        result.add(new ArrayList<>(tmp));
                        tmp.clear();
                    }
                }
                result.add(new ArrayList<>(tmp));
                
                return result; 
            }
            
        };
    }

}
