package com.example.api;

import java.util.HashMap;
import java.util.Map;

public class LRUAlgorithm {
    class DLinkedNode {
        private Integer key;
        private Integer value;
        private DLinkedNode prev;
        private DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(Integer _key, Integer _value) {
            this.key = _key;
            this.value = _value;
        }
    }

    public LRUAlgorithm(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    private Map<Integer, DLinkedNode> cache = new HashMap<>();

    private DLinkedNode head;

    private DLinkedNode tail;

    private int size;

    private int capacity;


    public void put(Integer key, Integer value) {
        DLinkedNode existedNode = cache.get(key);
        // if key not exists, then add to cache and update linkedNodes
        if (null == existedNode) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            addToHead(newNode);
            cache.put(key, newNode);
            size ++;
            //if size is greater than the capacity, move the oldest one (tail.prev)
            if (size > capacity) {
                DLinkedNode toBeMovedNode = tail.prev;
                moveNode(toBeMovedNode);
                size --;
                cache.remove(toBeMovedNode.key);
            }
        } else {
            //if key exists, move the key to the head
            existedNode.value = value;
            moveToHead(existedNode);
        }
    }

    public Integer get(Integer key) {
        DLinkedNode existedNode = cache.get(key);
        if (null != existedNode) {
            moveToHead(existedNode);
            return existedNode.value;
        } else {
            return null;
        }
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        head.next.prev = node;
    }

    private void moveNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        moveNode(node);
        addToHead(node);
    }
}
