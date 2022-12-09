package com.twitterapp.Models;

public class Heap {
    public User[] heap;
    private int size;
    private int MAX;

    public Heap(int MAX)
    {
        this.size = 0;
        this.MAX = MAX;
        heap = new User[this.MAX];
    }
    private int parent(int pos) {return (pos - 1) / 2;}
 
    private int leftChild(int pos) {return (2 * pos) + 1;}
 
    private int rightChild(int pos){return (2 * pos) + 2;}

    private boolean isLeaf(int pos)
    {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int a, int b)
    {
        User temp;
        temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
 

    private void Heapify(int pos)
    {
        if (isLeaf(pos))
            return;
 
        if (heap[pos].get_followers() < heap[leftChild(pos)].get_followers() || heap[pos].get_followers() < heap[rightChild(pos)].get_followers()) {
 
            if (heap[leftChild(pos)].get_followers() > heap[rightChild(pos)].get_followers()) {
                swap(pos, leftChild(pos));
                Heapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                Heapify(rightChild(pos));
            }
        }
    }
    public void insert(User element)
    {
        heap[size] = element;
        int current = size;
        while (heap[current].get_followers() > heap[parent(current)].get_followers()) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public User popMax()
    {
        User popped = heap[0];
        heap[0] = heap[--size];
        Heapify(0);
        return popped;
    }
}
