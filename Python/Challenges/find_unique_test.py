import unittest
from find_unique import find_unique

class TestFindUnique(unittest.TestCase):

    def test_wrong_type(self) -> None:
        with self.assertRaises(TypeError):
            find_unique(12)
        with self.assertRaises(TypeError):
            find_unique(17j)
        with self.assertRaises(TypeError):
            find_unique(11.11)
        with self.assertRaises(TypeError):
            find_unique({12:14})     
            
    def test_single_element(self) -> None:
        self.assertEqual(find_unique([[]]), [])
        self.assertEqual(find_unique("a"), "a")
        
    def test_mid_odd_input(self) -> None:
        self.assertEqual(find_unique("511ss33"), "5")
        self.assertEqual(find_unique(("1", "1", 5, 2.2, 2.2, [], [])), 5)
        self.assertEqual(find_unique([(), (), 2, 2, "a", [1], [1]]), "a")
        self.assertEqual(find_unique([{1:2}, {1:2}, "aaaa", "aaaa", 3, 3, 5j]), 5j)
        
    def test_mid_even_length_inpu(self) -> None:
        self.assertEqual(find_unique([5, "1", "1", 2j, 2j, (3, 3), (3, 3), 4, 4]), 5)
        self.assertEqual(find_unique([1, 1, 5, 2, 2, 3.14, 3.14, 4, 4]), 5)
        self.assertEqual(find_unique("aagg5__44"), "5")
        self.assertEqual(find_unique([1, 1, 2, 2, [3], [3], 5, 4, 4]), 5)
        self.assertEqual(find_unique((1, 1, 2, 2, 3, 3, 4, 4, 5)), 5)
        
    def test_large_list(self) -> None:
        data = [1, 1, 2, 2, 3, 3, 4, 4] * 100 + [5]
        self.assertEqual(find_unique(data), 5)
        data = [1, 1, 5, 5]*100 + [2] + [7, 7, 8, 8]*100
        self.assertEqual(find_unique(data), 2)
        data = [3] + [1, 1, 4, 4]*100 + [12, 12, 18 ,18]*100
        self.assertEqual(find_unique(data), 3)

    def test_large_string(self) -> None:
        data = "11223344" * 100 + "5"
        self.assertEqual(find_unique(data), "5")
        data = "4411"*100 + "2" + "5577"*100
        self.assertEqual(find_unique(data), "2")
        data = "3" + "2277"*100 + "4488"*100
        self.assertEqual(find_unique(data), "3")
        
    def test_large_tuple(self) -> None:
        data = (1, 1, 2, 2, 3, 3, 4, 4) * 100 + (6, 6, 5)
        self.assertEqual(find_unique(data), 5)
        data = (1, 1, 3, 3)*100 + (4, 4, 2, 5, 5) + (3, 3, 7, 7)*100
        self.assertEqual(find_unique(data), 2)
        data = (3, 1, 1) +(2, 2, 5, 5)*100 + (4, 4, 6, 6)*100
        self.assertEqual(find_unique(data), 3)

if __name__ == '__main__':
    unittest.main()
