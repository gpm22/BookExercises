from typing import Any

class Node:
    def __init__(self, value: Any):
        self.value = value
        self.left = None
        self.right = None

    def __repr__(self):
        return f'{self.value}'

class BinaryTree:

    def __init__(self, root_value: Any = 'root'):
        node_root = Node(root_value)
        self.root = node_root
        self.nodes ={str(root_value): node_root}
        
    def get_node(self, value: Any):
        return self.nodes[str(value)]

    def insert(self, parent_value: Any, value: Any, right: bool = False) -> bool:
        parent = self.nodes[str(parent_value)]
        if parent:
            child = Node(value)
            if right:
                parent.right = child
            else:
                parent.left = child
                
            self.nodes[str(value)] = child
            
            return True
        
        return False

    def only_children(self) -> list[Node]:
        only_children = []
        self.__only_children_helper(self.root, only_children)
        return only_children
        
    def __only_children_helper(self, node: None, only_children: list[Node]) -> None:

        if node is None:
            return
        
        if (node.left is not None) and (node.right is None):
            only_children.append(node.left)

        if (node.right is not None) and (node.left is None):
            only_children.append(node.right)

        self.__only_children_helper(node.left, only_children)
        self.__only_children_helper(node.right, only_children)
        

if __name__ == '__main__':
    tree = BinaryTree(10)
    tree.insert(10, 12, True)
    tree.insert(10, 4)
    tree.insert(4, 6, True)
    tree.insert(4, 3)
    tree.insert(6, 5)
    
    print(tree.only_children())

    
