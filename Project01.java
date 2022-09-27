package project01;

import java.util.Scanner;
import project01.LinkedList.Node;

public class Project01 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int v = n * n;
        int[] arr = new int[v * 4];
        for (int w = 0; w < v * 4; w++) {
            arr[w] = in.nextInt();
        }
        
        int v2 = ((n - 2) * 4) + 8 + v;
        int[] visited = new int[v2];
        for (int i = 0; i < v2; i++) {
            if (i < v) {
                visited[i] = 0;
            } else {
                visited[i] = 1;
            }
        }
        Node[] arr2 = new Node[v2];
        arr2 = graph(arr, v2, n);
        int x = 1;

        int y = 0;
        int min = 0;
        Node miin = null;
        while (x > 0) {
            for (int o = 0; o < v2; o++) {
                Node p = arr2[o].next;
                if (visited[o] == 1) {
                    
                    while (p != null) {
                        if (visited[p.data - 1] == 0 && miin==null) {
                            
                            min = p.dis;
                            miin = p;

                        } else if (visited[p.data - 1] == 0 && miin != null) {
                            if (p.dis < min) {
                                min = p.dis;
                                miin = p;
                            }
                        }
                        p = p.next;
                    }
                }
            }
            visited[miin.data - 1] = 1;
            y += miin.dis;
            miin = null;
            if (cheak(visited)) {
                x = 0;
                break;
            }

        }
        System.out.println(y);

    }

    public static boolean cheak(int[] a) {
        int b = 0;
        for (int o = 0; o < a.length; o++) {
            if (a[o] == 1) {
                b++;
            }
        }
        if (b == a.length) {
            return true;
        } else {
            return false;
        }
    }

    public static Node[] graph(int[] arr, int v2, int n) {
        Node[] graph = new Node[v2];
        for (int i = 0; i < v2; i++) {
            graph[i] = new Node(i + 1);
        }
        int z = (n * n) + 1;
        for (int i = 0; i < (n * n); i++) {
            Node p = graph[i];
            int a = (i-1) / n;
            int b = i  / n;
            int c = (i+1) / n;
            
            if (i > 0 && a == b) {
                p.next = new Node(i, arr[i * 4]);
                p = p.next;
            } else {
                p.next = new Node(z, arr[i * 4]);
                graph[z - 1].next = new Node(i + 1, arr[i * 4]);
                z++;
                p = p.next;
                
            }
            if (i - n + 1 > 0) {
                p.next = new Node(i - n + 1, arr[(i * 4) + 1]);
                p = p.next;
            } else {
                p.next = new Node(z, arr[(i * 4) + 1]);
                graph[z - 1].next = new Node(i + 1, arr[(i * 4) + 1]);
                z++;
                p = p.next;
                
            }
            if (i + 2 > 0 && c==b) {
                p.next = new Node(i + 2, arr[(i * 4) + 2]);
                p = p.next;
            } else {
                p.next = new Node(z, arr[(i * 4) + 2]);
                graph[z - 1].next = new Node(i + 1, arr[(i * 4) + 2]);
                z++;
                p = p.next;
                
            }
            if (i + n + 1 < (n * n)+1) {
                p.next = new Node(i + n + 1, arr[(i * 4) + 3]);
                p = p.next;
            } else {
                p.next = new Node(z, arr[(i * 4) + 3]);
                graph[z - 1].next = new Node(i + 1, arr[(i * 4) + 3]);
                z++;
                p = p.next;
                
            }

        }

        return graph;
    }

}

class LinkedList {


    static class Node {

        int data;
        int dis;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }

        Node(int x, int y) {
            dis = y;
            data = x;
            next = null;
        }
    }

}
