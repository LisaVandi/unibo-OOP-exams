package a02a.e1;

import java.util.List;
import java.util.ArrayList;

public class ListBuilderFactoryImpl implements ListBuilderFactory {

    @Override
    public <T> ListBuilder<T> empty() {
        return new ListBuilder<T>() {

            @Override
            public ListBuilder<T> add(List<T> list) {
                return 
            }

            @Override
            public ListBuilder<T> concat(ListBuilder<T> lb) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'concat'");
            }

            @Override
            public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'replaceAll'");
            }

            @Override
            public ListBuilder<T> reverse() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'reverse'");
            }

            @Override
            public List<T> build() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'build'");
            }
            
        };
    }

    @Override
    public <T> ListBuilder<T> fromElement(T t) {
        return new ListBuilder<T>() {

            @Override
            public ListBuilder<T> add(List<T> list) {
                
            }

            @Override
            public ListBuilder<T> concat(ListBuilder<T> lb) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'concat'");
            }

            @Override
            public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'replaceAll'");
            }

            @Override
            public ListBuilder<T> reverse() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'reverse'");
            }

            @Override
            public List<T> build() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'build'");
            }
            
        };
    }

    @Override
    public <T> ListBuilder<T> fromList(List<T> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromList'");
    }

    @Override
    public <T> ListBuilder<T> join(T start, T stop, List<ListBuilder<T>> builderList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }

    // per ritornare ogni volta negli altri metodi ListBuilder
    private static class ListBuilderImpl<T> implements ListBuilder<T> {
        private final List<T> list;

        ListBuilderImpl(List<T> list) {
            this.list = list;
        }

        @Override
        public ListBuilder<T> add(List<T> otherList) {
            List<T> newList = new ArrayList<>(this.list);
            newList.addAll(otherList);
            return new ListBuilderImpl<>(newList);
        }

        @Override
        public ListBuilder<T> concat(ListBuilder<T> lb) {
            List<T> newList = new ArrayList<>(this.list);
            newList.addAll(lb.build());
            return new ListBuilderImpl<>(newList);
        }

        @Override
        public ListBuilder<T> replaceAll(T t, ListBuilder<T> lb) {
            List<T> newList = this.list.stream()
                    .flatMap(element -> element.equals(t) ? lb.build().stream() : List.of(element).stream())
                    .collect(Collectors.toList());
            return new ListBuilderImpl<>(newList);
        }

        @Override
        public ListBuilder<T> reverse() {
            List<T> newList = new ArrayList<>(this.list);
            Collections.reverse(newList);
            return new ListBuilderImpl<>(newList);
        }

        @Override
        public List<T> build() {
            return new ArrayList<>(this.list);
        }
    }

}
