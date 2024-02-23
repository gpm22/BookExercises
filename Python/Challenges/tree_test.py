import unittest
from Tree import Tree

class TestTreeEquals(unittest.TestCase):

    def test_wrong_type_inputs(self):
        tree1 = Tree("Directory")
        
        self.assertFalse(tree1.equals(""))
            
        self.assertFalse(tree1.equals([1, 3, 4]))
            
        self.assertFalse(tree1.equals((1, 2, 4)))
        
        self.assertFalse(tree1.equals({"a":1, "b":2, "d":4}))
        
        self.assertFalse(tree1.equals(1))
        
        self.assertFalse(tree1.equals(1.0))

    def test_equals_basic(self):
        tree1 = Tree("Projects")
        tree2 = Tree("Projects")
        self.assertTrue(tree1.equals(tree2))

    def test_equals_name_differs(self):
        tree1 = Tree("Projects")
        tree2 = Tree("Work")
        self.assertFalse(tree1.equals(tree2))
        
    def test_equals_one_text_none(self):
        tree1 = Tree("About", text="Hello")
        tree2 = Tree("About")
        self.assertFalse(tree1.equals(tree2))
        
    def test_equals_text_differs(self):
        tree1 = Tree("About", text="Hello")
        tree2 = Tree("About", text="Bye")
        self.assertFalse(tree1.equals(tree2))
    
    def test_equal_text(self):
        tree1 = Tree("DocumentID", "1234")
        tree2 = Tree("DocumentID", "1234")
        self.assertTrue(tree1.equals(tree2)) 

    def test_equals_children_length_differs(self):
        tree1 = Tree("Projects")
        tree1.add_child(Tree("Python"))
        tree2 = Tree("Projects")
        tree2.add_child(Tree("Python"))
        tree2.add_child(Tree("Java"))
        self.assertFalse(tree1.equals(tree2))
        
    def test_equals_children_same_order(self):
        tree1 = Tree("Projects")
        tree1.add_child(Tree("Python"))
        tree1.add_child(Tree("Java"))
        tree2 = Tree("Projects")
        tree2.add_child(Tree("Python"))
        tree2.add_child(Tree("Java"))
        
        self.assertTrue(tree1.equals(tree2))
        
    def test_equals_children_different_order(self):
        tree1 = Tree("Projects")
        tree1.add_child(Tree("Python"))
        tree1.add_child(Tree("Java"))
        tree2 = Tree("Projects")
        tree2.add_child(Tree("Java"))
        tree2.add_child(Tree("Python"))
        self.assertTrue(tree1.equals(tree2))

    def test_equals_children_differ(self):
        tree1 = Tree("Projects")
        tree1.add_child(Tree("Python"))
        tree2 = Tree("Projects")
        tree2.add_child(Tree("Java"))
        self.assertFalse(tree1.equals(tree2))
        
    def test_equals_children_with_different_contents(self):
        id1 = Tree("ID")
        id2 = Tree("ID", "1456")
        
        tree1 = Tree("User")
        tree1.add_child(id1)
        
        tree2 = Tree("User")
        tree2.add_child(id2)
        
        self.assertFalse(tree1.equals(tree2))

    def test_equals_deep_compare_different(self):
        tree1 = Tree("Projects")
        python1 = Tree("Python")
        java1 = Tree("Java")
        tree1.add_child(python1)
        tree1.add_child(java1)
        python1.add_child(Tree("Flask"))
        java1.add_child(Tree("Spring"))
        
        tree2 = Tree("Projects")
        python2 = Tree("Python")
        java2 = Tree("Java")
        tree2.add_child(java2)
        tree2.add_child(python2)
        python2.add_child(Tree("Django"))
        java2.add_child(Tree("Spring"))
        
        self.assertFalse(tree1.equals(tree2))
        
    def test_equals_deep_compare_equal(self):
        tree1 = Tree("Projects")
        python1 = Tree("Python")
        java1 = Tree("Java")
        tree1.add_child(python1)
        tree1.add_child(java1)
        python1.add_child(Tree("Django"))
        java1.add_child(Tree("Spring"))
        
        tree2 = Tree("Projects")
        python2 = Tree("Python")
        java2 = Tree("Java")
        tree2.add_child(java2)
        tree2.add_child(python2)
        python2.add_child(Tree("Django"))
        java2.add_child(Tree("Spring"))
        
        self.assertTrue(tree1.equals(tree2))
        

if __name__ == '__main__':
    unittest.main()
