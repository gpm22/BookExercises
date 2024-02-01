from typing import Any, Tuple

def find_unique(arr: list[Any] | Tuple[Any, ...] | str) -> Any:

  if not (isinstance(arr, list) or isinstance(arr, Tuple) or isinstance(arr, str)):
      raise TypeError("not a list, tuple or string")

  n = len(arr)

  if n == 1:
      return arr[0]
  
  mid = n // 2
  
  half_equal = arr[mid] == arr[mid+1]

  if half_equal:
    if mid % 2 == 0:
      return find_unique(arr[(mid+2):])
    else:
      return find_unique(arr[:mid])  
  else:
    if arr[mid] != arr[mid-1]:
      return arr[mid]
    if mid % 2 == 0:
      return find_unique(arr[:(mid-1)]) 
    else:
      return find_unique(arr[(mid+1):])
