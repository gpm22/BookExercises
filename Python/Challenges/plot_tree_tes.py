import unittest

from plot_tree import format_balanced_tree

class TestPlottree(unittest.TestCase):


    def test_wrong_types(self) -> None:
        with self.assertRaises(TypeError):
            format_balanced_tree([])
            format_balanced_tree((10, 15))
            format_balanced_tree(12)
            format_balanced_tree({"12": [18]})
            format_balanced_tree({12.0: [18]})
            format_balanced_tree({1: [18]})
            format_balanced_tree({1: [18, 20], 0: [19]})
            
    def test_tree_not_balanced(self) -> None:
        with self.assertRaises(Exception):
            format_balanced_tree({0: [18], 1: [19], 2:[24]})
            format_balanced_tree({0: [18], 1: [19, 14], 2:[24, 45, 55, 57, 99]})
            format_balanced_tree({0: [18], 1: [19, 32], 2:[24, 35], 3:[12]})

        
    def test_empty_tree(self) -> None:
        self.assertEqual(format_balanced_tree({}), "")
        
    def test_only_root(self) -> None:
        self.assertEqual(format_balanced_tree({0: [12]}), " 12 ")
        
    def test_complete_tree(self) -> None:
        self.assertEqual(format_balanced_tree({0: [35.0], 1: [12.12, 'A']}), "     35.0     \n   /      \\   \n 12.12    A   ")
        self.assertEqual(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E', 'F', 'G']}),
                         "     A      \n  /     \\   \n  B     C   \n /  \\  /  \\ \n D  E  F  G ")
        self.assertEqual(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E', 4, 7], 3: [10, 12, 14, 15, 18, 19, 11, 87]}),
        '               A                \n       /               \\        \n       B               C        \n   /       \\       /       \\    \n   D       E       4       7    \n /   \\   /   \\   /   \\   /   \\  \n 10  12  14  15  18  19  11  87 '
)
                         
    def test_incomplete_tree(self) -> None:
        self.assertEqual(format_balanced_tree({0: [35.0], 1: [12.12]}), "     35.0     \n 12.12 ")
        self.assertEqual(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E']}),
                         "     A      \n  /     \\   \n  B     C   \n /  \\ \n D  E ")
        self.assertEqual(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E', 4, 7], 3: [10, 12, 14, 15, 18, 19]}),
        '               A                \n       /               \\        \n       B               C        \n   /       \\       /       \\    \n   D       E       4       7    \n /   \\   /   \\   /   \\  \n 10  12  14  15  18  19 '
)
        

if __name__ == '__main__':
    unittest.main()
