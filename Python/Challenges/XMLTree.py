import xml.etree.ElementTree as ET
from typing import Optional, Union

class Node:
    def __init__(self, tag:str, text:str ='', children:list['Node'] =[]):
        self.tag = tag
        self.text = text
        self.children = children
        
    def __eq__(self, other):
        if not isinstance(other, Node):
            return False
            
        if self.tag != other.tag: 
            return False
            
        if self.text != other.text:
            return False
            
        if not(self.children and other.children):
            return not(self.children or other.children)

        if len(self.children) != len(other.children):
            return False
            
        self_children = sorted(self.children, key=lambda c: c.tag) 
        other_children = sorted(other.children, key=lambda c: c.tag)
        
        for self_child, other_child in zip(self_children, other_children):
            if not self_child == other_child:
                return False
            
        return True

    def __ne__(self, other):
        return not self.__eq__(other)
        
    def __repr__(self) -> str:
        if self.text:
            return f"Node(tag = {self.tag}, text= {self.text})"
        
        if self.children:
            children_str = ", ".join([str(c) for c in self.children])
            return f"Node(tag = {self.tag}, children= {children_str})"
        
        return f"Node(tag = {self.tag})"
        
    def to_xml(self) -> str:
        if self.text:
            return f"<{self.tag}>{self.text}</{self.tag}>"
        
        if self.children:
            children_str = "".join([c.to_xml() for c in self.children])
            return f"<{self.tag}>{children_str}</{self.tag}>"
        
        return f"<{self.tag}/>"

class Tree:

    def __init__(self, root: Optional['None']=None):
    	self.root = root

    @staticmethod
    def from_xml(xml: str) -> 'Tree':
    
        if not(isinstance(xml, str)):
            raise TypeError
    
        try:
            root = ET.fromstring(xml)
            return Tree(Tree._parse_node(root))
        except Exception as e:
            raise ValueError(str(e))
        
    @staticmethod
    def _parse_node(node: Union['Node', ET.Element]) -> 'Node':
        children = [Tree._parse_node(c) for c in node]
        text = node.text or ''
        if children and text:
            raise ValueError("A Node cannot have both text and children")
        return Node(node.tag, text, children)
        
    def __eq__(self, other):
        if isinstance(other, Tree):
            return self.root == other.root
        return False
        
    def __ne__(self, other):
        return not self.__eq__(other)
        
        
    def __repr__(self) -> str:
        return str(self.root)
        
    def to_xml(self) -> str:
        return self.root.to_xml()
        
if __name__ == '__main__':
    xml_string = "<root><child1>text</child1><child2><grandchild2>text</grandchild2></child2></root>"
    tree = Tree.from_xml(xml_string)
    print(tree.to_xml())
    print(tree)
    
    #xml = '<root><child>Text</child></root>'
    #tree = Tree.from_xml(xml)
    #print(tree.tag) # 'root'
    #print(tree.children[0].tag) # 'child'
    #print(tree.children[0].text) # 'Text'

