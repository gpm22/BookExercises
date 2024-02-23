from only_children import BinaryTree

import unittest


class TestOnlyChildren(unittest.TestCase):
    def setUp(self):
        self.tree = BinaryTree('a')
        self.tree.insert('a', 'b', right=False)
        self.tree.insert('a', 'c', right=True)
        self.tree.insert('b', 'd', right=False)
        self.tree.insert('c', 'e', right=True)

    def test_only_child_left(self):
        only_children = self.tree.only_children()
        self.assertEqual(len(only_children), 1)
        self.assertEqual(only_children[0].value, 'd')

    def test_only_child_right(self):
        only_children = self.tree.only_children()
        self.assertEqual(len(only_children), 1)
        self.assertEqual(only_children[0].value, 'e')

    def test_no_only_children(self):
        self.tree.insert('d', 'f', right=False)
        self.tree.insert('d', 'g', right=True)
        only_children = self.tree.only_children()
        self.assertEqual(len(only_children), 0)

    def test_multiple_only_children(self):
        self.tree.insert('f', 'h', right=False)
        self.tree.insert('f', 'i', right=True)
        only_children = self.tree.only_children()
        self.assertEqual(len(only_children), 2)
        self.assertEqual(only_children[0].value, 'd')
        self.assertEqual(only_children[1].value, 'e')

if __name__ == '__main__':
    unittest.main()
