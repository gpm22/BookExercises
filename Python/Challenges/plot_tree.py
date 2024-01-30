from typing import Any 

def format_balanced_tree(tree : dict[int, Any]) -> str:
    """
    Generates a string representation of a balanced binary tree represented as a dictionary.

    Args:
        tree: A dictionary where keys represent node levels and values are lists of node 
              labels at that level.

    Returns:
        A string representation of the balanced binary tree, level by level, bottom-up.
    """
    
    
    if not isinstance(tree, dict):
        raise TypeError(f"tree must be a dictionary and not {type(tree)}")
        
    if tree == {}:
        return ""
    
    for key in tree.keys():
        if not isinstance(key, int):
            raise TypeError(f"The keys must be int, but key: {str(key)} is {type(key)}")
    
    max_level = max(tree.keys())
    previous_key = 0
    for key in range(max_level+1):
        if not key in tree:
            raise TypeError(f"They key {key} should be present")
        
        if key != 0 and key != (previous_key+1):
            raise TypeError(f"The keys are not coutinously ordered")
            
        previous_key = key
    
    
    for index in range(max_level):
        if len(tree[index]) != int(2**index):
            raise Exception("The tree is not balanced")
    
    if len(tree[max_level]) > (int(2**max_level)):
        raise Exception("The tree is not balanced")
    
    
    def len_of_biggest_element():
        result = 0
        for level in tree.values():
            for node in level:
                current_node = len(str(node))
                if current_node > result:
                    result = current_node
                
        return result

    spacing_size = len_of_biggest_element() + 2
    
    level_strings = []
    for nodes in reversed(tree.values()):
 
        formatted_nodes = [str(node).center(spacing_size) for node in nodes]
        level_string = ''.join(formatted_nodes)
        level_strings.append(level_string)
        
        # the root does not have connectors
        if(len(nodes) > 1):
            formatted_connectors = [("/" if idx % 2 == 0 else "\\" ).center(spacing_size) for idx in range(len(nodes))]
            level_connectors = ''.join(formatted_connectors)
            level_strings.append(level_connectors)
            
        spacing_size *= 2

    return "\n".join(reversed(level_strings))

if __name__ == '__main__':
    # Example usage
    print(repr(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E', 4, 7], 3: [10, 12, 14, 15, 18, 19]})))
    print(repr(format_balanced_tree({0: ['A'], 1: ['B', 'C'], 2: ['D', 'E', 4, 7], 3: [10, 12, 14, 15, 18, 19, 11, 87]})))
