package com.company.Tree;

import com.company.Sort.SelectionSort;
import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class RecursiveTravel extends SelectionSort {


    static TreeNode A = new TreeNode("a");
    static TreeNode B = new TreeNode("b");
    static TreeNode C = new TreeNode("c");
    static TreeNode D = new TreeNode("d");
    static TreeNode E = new TreeNode("e");
    static TreeNode F = new TreeNode("f");
    static TreeNode G = new TreeNode("g");
    static TreeNode H = new TreeNode("h");
    static TreeNode I = new TreeNode("i");


    public static void main(String[] args) {
        RecursiveTravel demo = new RecursiveTravel();
        TreeNode root = demo.createTree();
        System.out.println("pre order1");
        demo.PreOrderTravel(root);
//        System.out.println("preorder2");
//        demo.PreOrderTravel2(root);
//        System.out.println("posorder2");
//        demo.PosOrderTravel2(root);
        System.out.println("intravel");
        InOrderTravel(root);
        System.out.println(preOrderTravel1.equals( preOrderTravel2));
        System.out.println("height  "+demo.getTreeHeight(root));
        TreeNode node = demo.find(root, "c");
        System.out.println("find  "+node);
        TreeNode z = new TreeNode("z");
        System.out.println("================add z===================");
        demo.addTreeNode(root,z);
        demo.PreOrderTravel(root);
        System.out.println("min tree node : " + demo.getmin(root));
        demo.deleteTreeNode(root,z);
        System.out.println("==============delete z===========");
        demo.PreOrderTravel(root);
        System.out.println("==============delete g===========");
        demo.deleteTreeNode(root,G);
        demo.PreOrderTravel(root);
    }
    static TreeNode rememberNode;
    public void addTreeNode(TreeNode root,TreeNode n){
        if (root == null){
            if (less(n.getValue(),rememberNode.getValue())){
                rememberNode.setLeft(n);
            }else rememberNode.setRight(n);
        }else if (less(root.getValue(),n.getValue())){
            rememberNode = root;
            addTreeNode(root.getRight(),n);
        }else if (less(n.getValue(),root.getValue())){
            rememberNode = root;
            addTreeNode(root.getLeft(),n);
        }
    }
    static boolean direction;

    public void deleteTreeNode(TreeNode root,TreeNode n){
        if (root.getValue() == n.getValue()){
            if (root.getLeft() == null && root.getRight() == null){
                if (direction){
                    rememberNode.setLeft(null);
                }else rememberNode.setRight(null);
            }else if (root.getLeft() != null && root.getRight() != null){
                TreeNode rightmin = getmin(root.getRight());
                if (direction){
                    rememberNode.setLeft(rightmin);
                }else {
                    rememberNode.setRight(rightmin);
                }
                deleteTreeNode(root.getRight(),rightmin);
                rightmin.setRight(root.getRight());
                rightmin.setLeft(root.getLeft());
            }else{
                if (direction){
                    rememberNode.setLeft(n);
                }else rememberNode.setRight(n);
            }
        }
        if (less(root.getValue(),n.getValue())){
            direction = false;
            rememberNode = root;
            deleteTreeNode(root.getRight(),n);
        }else if (less(n.getValue(),root.getValue())){
            direction = true;
            rememberNode = root;
            deleteTreeNode(root.getLeft(),n);
        }
    }

    public TreeNode getmin(TreeNode a){
//        if (a.getLeft() == null){
//            return a;
//        }else return getmin(a.getLeft());
        while (a.getLeft() != null){
            a = a.getLeft();
        }
        return a;
    }


    public TreeNode getmax(TreeNode a){
        while (a.getRight() != null){
            a = a.getRight();
        }
        return a;
    }


    public TreeNode find(TreeNode a,String s){
//        if (less(s,a.getValue())){
//            return find(a.getLeft(),s);
//        }
//        if (less(a.getValue(),s)){
//            return find(a.getRight(),s);
//        }
//        if (a.getValue().equals(s)){
//            return a;
//        }
//        return null;
        while (a!=null){
            if (less(s,a.getValue())){
                a = a.getLeft();
            }
            if (less(a.getValue(),s)){
                a = a.getRight();
            }
            if (a.getValue().equals(s)){
                return a;
            }
        }
        return null;
    }


    public int getTreeHeight(TreeNode a){
        if (a!=null){
            int leftheight = getTreeHeight(a.getLeft());
            int rightheight = getTreeHeight(a.getRight());
            if (leftheight>rightheight) return leftheight+1;
            else return rightheight+1;
        }
        return 0;
    }
    static ArrayList<TreeNode> preOrderTravel1 = new ArrayList<>();
    static ArrayList<TreeNode> preOrderTravel2 = new ArrayList<>();
    public void PreOrderTravel(TreeNode a){
        if (a != null){
            preOrderTravel1.add(a);
            PreOrderTravel(a.getLeft());
            System.out.println(a);
            PreOrderTravel(a.getRight());

        }
    }
    public void PreOrderTravel2(TreeNode a){
        Stack<TreeNode> s = new Stack<>();
//        Stack<TreeNode> s1 = new Stack<>();
        s.push(a);
        while(!s.isEmpty()){
            a = s.pop();
            preOrderTravel2.add(a);
            System.out.println(a);
            if(a.getRight()!=null){
                s.push(a.getRight());
            }
            if(a.getLeft()!=null){
                s.push(a.getLeft());
            }
        }
    }

    public static void InOrderTravel(TreeNode head){
        Stack<TreeNode>  s = new Stack<>();
        while(!s.isEmpty() || head!=null){
            if(head!=null){
                s.push(head);
                head = head.getLeft();
            }else{
                head = s.pop();
                System.out.println(head);
                head = head.getRight();
            }
        }
    }

    public void PosOrderTravel2(TreeNode a){
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> s1 = new Stack<>();
        s.push(a);
        while(!s.isEmpty()){
            a = s.pop();
            s1.push(a);
//            preOrderTravel2.add(a);
            if(a.getLeft()!=null){
                s.push(a.getLeft());
            }
            if(a.getRight()!=null){
                s.push(a.getRight());
            }
        }
        while(!s1.isEmpty()){
            System.out.println(s1.pop());
        }
    }


    public TreeNode createTree(){

//        A.setLeft(B);
//        A.setRight(C);
//        B.setLeft(D);
//        B.setRight(F);
//        F.setLeft(E);
//        C.setLeft(G);
//        C.setRight(I);
//        G.setRight(H);


        E.setLeft(C);
        E.setRight(G);
        C.setLeft(A);
        C.setRight(D);
        G.setLeft(F);
        G.setRight(I);
        I.setLeft(H);
        A.setRight(B);
        return E;
    }
}
class TreeNode{
    private String value;
    private TreeNode left;
    private TreeNode right;

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TreeNode(String value) {
        this.value = value;
    }

    public TreeNode() {
    }

    public TreeNode(String value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }



    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
