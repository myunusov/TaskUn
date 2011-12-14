package org.maxur.taskun.view.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * This is unmodifiable and serializable list of bean.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public class ModelList<E> extends ArrayList<E> {

    private static final long serialVersionUID = -6276465960710839071L;

    private final List<? extends E> list;

    public ModelList(List<? extends E> list) {
        super(list);
        this.list = list;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new ModelList<E>(list.subList(fromIndex, toIndex));
    }

    /**
     * Allows instances to be deserialized in pre-1.4 JREs (
     * a readResolve method that inverts this transformation upon
     * deserialization.
     *
     * @return List of Bean.
     */
    private Object writeReplace() {
        return new ModelList<E>(list);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || list.equals(o);
    }

    public int hashCode() {
        return list.hashCode();
    }

    public E get(int index) {
        return list.get(index);
    }

    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public boolean add(E element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(final int index) {
        return new ListIterator<E>() {
            private final ListIterator<? extends E> i = list.listIterator(index);

            public boolean hasNext() {
                return i.hasNext();
            }

            public E next() {
                return i.next();
            }

            public boolean hasPrevious() {
                return i.hasPrevious();
            }

            public E previous() {
                return i.previous();
            }

            public int nextIndex() {
                return i.nextIndex();
            }

            public int previousIndex() {
                return i.previousIndex();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
