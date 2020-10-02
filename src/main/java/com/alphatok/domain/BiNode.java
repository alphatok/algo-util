package com.alphatok.domain;


public class BiNode {
    private String data;
    private BiNode left;
    private BiNode right;

    public BiNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BiNode getLeft() {
        return left;
    }

    public void setLeft(BiNode left) {
        this.left = left;
    }

    public BiNode getRight() {
        return right;
    }

    public void setRight(BiNode right) {
        this.right = right;
    }
}
