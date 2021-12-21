# Only Children of a Binary Tree

## Description

Program to find only children in a binary tree, that is, children that have no siblings, whereas the root is not an only child, as it has no parents.

## How to use

First you have to run the class **Main** using a console, after it, the follow message will appear:

```bash
Enter the values to create the binary tree:
```

Then you enter in the follow way the values which will be used to create the binary tree:

```
0 1 2 null 4 5 6 7 8 9 10 11 null 13 14
```

Any string that cannot be converted to an integer, like the string "null" in the previous example , will be **null**, so it will not be a node and the "siblings" of these "nulled nodes" will be the **only children**.

The output of this example is :

```bash
Binary Tree Created...
Traversing the tree with different methods:

DFS recursive traverse:

0 - left: 1- right: 2
1 - left: null- right: 4
4 - left: 7- right: 8
7 - left: 13- right: 14
13 - left: null- right: null
14 - left: null- right: null
8 - left: null- right: null
2 - left: 5- right: 6
5 - left: 9- right: 10
9 - left: null- right: null
10 - left: null- right: null
6 - left: 11- right: null
11 - left: null- right: null

DFS iterative traverse:

0 - left: 1- right: 2
1 - left: null- right: 4
4 - left: 7- right: 8
7 - left: 13- right: 14
13 - left: null- right: null
14 - left: null- right: null
8 - left: null- right: null
2 - left: 5- right: 6
5 - left: 9- right: 10
9 - left: null- right: null
10 - left: null- right: null
6 - left: 11- right: null
11 - left: null- right: null

BFS iterative traverse:

0 - left: 1- right: 2
1 - left: null- right: 4
2 - left: 5- right: 6
4 - left: 7- right: 8
5 - left: 9- right: 10
6 - left: 11- right: null
7 - left: 13- right: 14
8 - left: null- right: null
9 - left: null- right: null
10 - left: null- right: null
11 - left: null- right: null
13 - left: null- right: null
14 - left: null- right: null

BFS recursive traverse:

0 - left: 1- right: 2
1 - left: null- right: 4
2 - left: 5- right: 6
4 - left: 7- right: 8
5 - left: 9- right: 10
6 - left: 11- right: null
7 - left: 13- right: 14
8 - left: null- right: null
9 - left: null- right: null
10 - left: null- right: null
11 - left: null- right: null
13 - left: null- right: null
14 - left: null- right: null

Only Children:
[4, 11]
```



