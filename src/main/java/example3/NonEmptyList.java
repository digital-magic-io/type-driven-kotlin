package example3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.jetbrains.annotations.NotNull;

public record NonEmptyList<A>(
    A head,
    List<A> tail
) implements List<A> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<A> iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return null;
    }

    @Override
    public boolean add(A a) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends A> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends A> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public A get(int index) {
        return null;
    }

    @Override
    public A set(int index, A element) {
        return null;
    }

    @Override
    public void add(int index, A element) {

    }

    @Override
    public A remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NotNull
    @Override
    public ListIterator<A> listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator<A> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<A> subList(int fromIndex, int toIndex) {
        return null;
    }
}
