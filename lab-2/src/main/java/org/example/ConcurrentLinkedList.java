package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class ConcurrentLinkedList<T> {
    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    public ConcurrentLinkedList() {
        Node<T> dummyHead = new Node<>(null);
        Node<T> dummyTail = new Node<>(null);
        dummyHead.next.set(dummyTail);
        dummyTail.prev.set(dummyHead);

        this.head = new AtomicReference<>(dummyHead);
        this.tail = new AtomicReference<>(dummyTail);
    }

    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        Node<T> headNode = head.get();
        Node<T> tailNode = tail.get();
        if (tailNode.prev.compareAndSet(headNode, newNode)) {
            newNode.next.set(tailNode);
            newNode.prev.set(headNode);
            headNode.next.compareAndSet(tailNode, newNode);
        } else {
            Node<T> prevNode = tailNode.prev.get();
            if (prevNode.next.compareAndSet(tailNode, newNode)) {
                newNode.next.set(tailNode);
                newNode.prev.set(prevNode);
                tailNode.prev.compareAndSet(prevNode, newNode);
            }
        }
    }

    public boolean remove(T item) {
        Node<T> current = firstAfterHead().get();
        while (current != tail.get()) {

            if (current.item.equals(item)) {
                AtomicReference<Node<T>> next = current.next;
                AtomicReference<Node<T>> prev = current.prev;
                prev.get().next.set(next.get());
                next.get().prev.set(prev.get());
                return true;
            }
            current = current.next.get();
        }
        return false;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }

        Node<T> current = firstAfterHead().get();
        int currentIndex = 0;

        while (current != tail.get()) {
            if (currentIndex == index) {
                return current.item;
            }

            current = current.next.get();
            currentIndex++;
        }

        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
    }

    public int size() {
        Node<T> current = firstAfterHead().get();
        int count = 0;

        while (current != tail.get()) {
            current = current.next.get();
            count++;
        }

        return count;
    }


    public String toString() {
        StringBuilder arrayString = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            T item = get(i);
            arrayString.append(" ").append(item);
            if (i < size() - 1) {
                arrayString.append(",");
            }
        }
        arrayString.append(" ]");
        return arrayString.toString();
    }

    public T first() {
        return firstAfterHead().get().item;
    }

    public T last() {
        return lastBeforeTail().get().item;
    }


    private AtomicReference<Node<T>> firstAfterHead() {
        return head.get().next;
    }

    private AtomicReference<Node<T>> lastBeforeTail() {
        return tail.get().prev;
    }

    @EqualsAndHashCode(of = "id")
    private static class Node<T> {
        UUID id;
        T item;
        AtomicReference<Node<T>> next;
        AtomicReference<Node<T>> prev;

        public Node(T item) {
            this.id = UUID.randomUUID();
            this.item = item;
            this.next = new AtomicReference<>(null);
            this.prev = new AtomicReference<>(null);
        }
    }
}
