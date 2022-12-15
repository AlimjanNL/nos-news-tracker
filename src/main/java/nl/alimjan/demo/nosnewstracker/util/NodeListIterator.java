package nl.alimjan.demo.nosnewstracker.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeListIterator implements Iterator<Node> {
    private final NodeList nodeList;
    private int index = 0;

    public NodeListIterator(final NodeList _nodeList) {
        nodeList = _nodeList;
    }

    @Override
    public boolean hasNext() {
        return index < nodeList.getLength();
    }

    @Override
    public Node next() {
        if (!hasNext()) throw new NoSuchElementException();
        return nodeList.item(index++);
    }
}
