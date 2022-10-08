package com.home.java.letcode.dp;

import java.util.List;

public class ReverseList {

    public static void main(String[] args) {


        ListNode listNode5 = new ListNode(5);
        //  ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode5);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        new ReverseList().reverseBetween(listNode3, 1, 2);

    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode firstNode = head;
        ListNode listNodeLeft = null;
        ListNode listNodeLeftPrev = null;
        ListNode previousNode = null;
        boolean foundLeft = false;
        boolean foundRight = false;
        int postion = 1;
        while (head != null) {
            if (!foundLeft) {
                if (postion == left) {
                    listNodeLeft = head;
                    foundLeft = true;
                }

            } else if (!foundRight) {

                if (postion == right) {
                    ListNode nextNode = head.next;
                    ListNode currentNode = head;
                    currentNode.next = previousNode;

                    if (listNodeLeftPrev != null)
                        listNodeLeftPrev.next = currentNode;
                    listNodeLeft.next = nextNode;
                    if (left == 1)
                        firstNode = head;

                        head = nextNode;
                    foundRight = true;
                    postion++;

                    continue;

                } else {
                    ListNode currentNode = head;
                    head = head.next;
                    currentNode.next = previousNode;
                    previousNode = currentNode;
                    postion++;
                    continue;

                }


            }


            if (!foundLeft)
                listNodeLeftPrev = head;
            previousNode = head;
            head = head.next;
            postion++;


        }
        return firstNode;


    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
