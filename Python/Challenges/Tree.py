from typing import Any

class Tree:
    def __init__(self, name :str, text: str = ""):
        self.name = name
        self.children = []
        self.text = text

    def add_child(self, child: 'Tree') -> None:
        if not isinstance(child, Tree):
            raise TypeError(f"A Tree can only be parent of another Tree, and not of a {type(child)}")
            
        if self.text:
            raise ValueError("A node with text cannot have children.")
            
        self.children.append(child)
        
    def set_text(self, text: str) -> None:
    
        if not isinstance(text, str):
            raise TypeError(f"The attribute text must be a string and not a {type(text)}")
    
        if self.children:
            raise ValueError("A node with children cannot have text.")
        
        self.text = text
        
    def equals(self, other: Any) -> bool:
        if not isinstance(other, Tree):
            return False
            
        if self.name != other.name: 
            return False
            
        if self.text != other.text:
            return False
            
        if not(self.children and other.children):
            return not(self.children or other.children)

        if len(self.children) != len(other.children):
            return False
            
        self_children = sorted(self.children, key=lambda c: c.name) 
        other_children = sorted(other.children, key=lambda c: c.name)
        
        for self_child, other_child in zip(self_children, other_children):
            if not self_child.equals(other_child):
                return False
            
        return True
    

    def __repr__(self) -> str:
        if self.text:
            return f"<Tree {self.name}: {self.text}>"
        else:
            return f"<Tree {self.name} - Children: {str(self.children)}>"
            
if __name__ == '__main__':
   # Tree with just a name
   tree = Tree("Projects") 

   # Tree with text 
   about_me = Tree("About Me", "I like coding in Python!") 

   # Adding children to a tree
   tree.add_child(Tree("Python"))
   tree.add_child(Tree("Java"))

   print(about_me)
   print(tree)

   # Can't add children to a tree with text
   try:
      about_me.add_child(Tree("My History"))
   except ValueError as e:
      print(e) # A node with text cannot have children.
      
   # Can't add text to a tree with children
   try:
      tree.set_text("My History")
   except ValueError as e:
      print(e) # A node with children cannot have text.
      
   tree1 = Tree("Projects")
   tree1.add_child(Tree("Python"))
   tree1.add_child(Tree("Java"))
    
   # Same as tree1 but children reversed
   tree2 = Tree("Projects") 
   tree2.add_child(Tree("Java"))
   tree2.add_child(Tree("Python"))
    
   print(tree1.equals(tree2)) # True
    
    # Different name 
   tree3 = Tree("Hobbies")
   tree3.add_child(Tree("Python"))
   tree3.add_child(Tree("Java"))
   print(tree1.equals(tree3)) # False
