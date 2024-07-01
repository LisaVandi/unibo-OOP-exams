package a02a.e1;

import java.util.List;

public class ListBuilderFactoryImpl implements ListBuilderFactory {

    @Override
    public <T> ListBuilder<T> empty() {
        return new ListBuilder<T>() {

            @Override
            public ListBuilder<T> add(List<T> list) {
                return null;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromElement'");
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

}
