import unittest
from XMLTree import Tree, Node

class TestFromXML(unittest.TestCase):

    def test_raises_type_error(self) -> None:
        with self.assertRaises(TypeError):
            tree = Tree.from_xml(12)  
        
        with self.assertRaises(TypeError):
            tree = Tree.from_xml(10j)

        with self.assertRaises(TypeError):
            tree = Tree.from_xml([12.5])
            
        with self.assertRaises(TypeError):
            tree = Tree.from_xml((-12, "tag"))
        
    def test_raises_value_error(self) -> None:
        with self.assertRaises(ValueError):
            xml = "<a>text<b></b>text</a>"
            tree = Tree.from_xml(xml)  
            
        with self.assertRaises(ValueError):
            xml = "<a>text1<b>text2<c></c></b>text3<d></d>text4</a>"
            tree = Tree.from_xml(xml)
            
        with self.assertRaises(ValueError):
            xml = ""
            tree = Tree.from_xml(xml)
            
        with self.assertRaises(ValueError):
            xml = "tag"
            tree = Tree.from_xml(xml)
            
    def test_empty_xml(self) -> None:
        xml = "<root/>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("root"))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)
    
    def test_from_xml_with_text_1(self) -> None:
        xml = "<a>text</a>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("a", "text"))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)
        
    def test_from_xml_with_text_2(self) -> None:
        xml = "<root>Hello</root>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("root", "Hello"))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)
        
    def test_from_xml_with_children_without_text(self) -> None:
        xml = "<a><b></b><c></c></a>"
        expected_str = "<a><b/><c/></a>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("a", children=[Node("b"), Node("c")])) 
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), expected_str)
            
    def test_from_xml_nested_children_without_text(self) -> None:
        xml = "<a><b><c/></b></a>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("a", children=[Node("b", children=[Node("c")])]))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)

    def test_node_with_children_with_text(self) -> None:
        xml = "<root><child>Text</child></root>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("root", children=[Node("child", "Text")]))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)
        
    def test_really_nested_tree_without_text(self) -> None:
        xml = "<a><b><c><d><e><f><g></g></f></e></d></c></b></a>"
        expected_str = "<a><b><c><d><e><f><g/></f></e></d></c></b></a>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("a", children=[Node("b", children=[Node("c", children=[Node("d", children=[Node("e", children=[Node("f", children=[Node("g")])])])])])]))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), expected_str)
        
        
    def test_really_nested_tree_with_text(self) -> None:
        xml = "<a><b><c><d><e><f><g>nested</g></f></e></d></c></b></a>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("a", children=[Node("b", children=[Node("c", children=[Node("d", children=[Node("e", children=[Node("f", children=[Node("g", "nested")])])])])])]))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), xml)

    def test_complex_tree(self) -> None:
        xml = "<root><child1></child1><child2>Text</child2><child3><grandchild>Nested</grandchild></child3></root>"
        expected_str = "<root><child1/><child2>Text</child2><child3><grandchild>Nested</grandchild></child3></root>"
        tree = Tree.from_xml(xml)
        expected = Tree(Node("root", children=[Node("child1"), Node("child2", "Text"), Node("child3", children = [Node("grandchild", "Nested")])]))
        self.assertEqual(tree, expected)
        self.assertEqual(tree.to_xml(), expected_str)
        
if __name__ == "__main__":
    unittest.main()
