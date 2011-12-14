package org.maxur.taskun.view.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public class ModelListTest {

    private ModelList<Integer> list;

    @Before
    public void setUp() throws Exception {
        final ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(null);
        arrayList.add(1);
        arrayList.add(2);
        list = new ModelList<Integer>(arrayList);
    }

    @Test
    public void testSerializable() throws Exception {
        Assert.assertTrue(list instanceof Serializable);
    }


    @Test
    public void testSubList() throws Exception {
        final List<Integer> sublist = list.subList(1, 1 + 2);
        Assert.assertEquals(new Integer(1), sublist.get(0));
        Assert.assertEquals(new Integer(2), sublist.get(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSet() throws Exception {
        list.set(1, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() throws Exception {
        list.add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddWithIndex() throws Exception {
        list.add(0, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() throws Exception {
        list.remove(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveByIndex() throws Exception {
        list.remove(0);
    }

    @Test
    public void testIndexOf() throws Exception {
        final int i = list.indexOf(1);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        final int i = list.lastIndexOf(1);
        Assert.assertEquals(1, i);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() throws Exception {
        final ArrayList c = new ArrayList();
        c.add(null);
        list.addAll(c);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAllWithIndex() throws Exception {
        final ArrayList c = new ArrayList();
        c.add(null);
        list.addAll(1, c);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIterator() throws Exception {
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next =  iterator.next();
            iterator.remove();
        }

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListIterator() throws Exception {
        final Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer next =  iterator.next();
            iterator.remove();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testListIteratorWithIndex() throws Exception {
        final Iterator<Integer> iterator = list.listIterator(1);
        while (iterator.hasNext()) {
            Integer next =  iterator.next();
            iterator.remove();
        }
    }

}
