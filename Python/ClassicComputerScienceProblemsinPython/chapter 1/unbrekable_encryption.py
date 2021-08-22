#encriptamento inquebrável

from secrets import token_bytes
from typing import Tuple
from math import pi

def random_key(length: int) -> int:
	# generate length random bytes
	tb: bytes = token_bytes(length)
	# convert those bytes into a bit string and return it
	return int.from_bytes(tb, "big")
	
def encrypt(original: str) -> Tuple[int, int]:
	original_bytes: bytes = original.encode()
	dummy: int = random_key(len(original_bytes))
	original_key: int = int.from_bytes(original_bytes, "big")
	encrypted: int = original_key ^ dummy # XOR
	return dummy, encrypted
	
def decrypt(key1: int, key2: int) -> str:
	decrypted: int = key1 ^ key2 # XOR
	temp: bytes = decrypted.to_bytes((decrypted.bit_length()+ 7) // 8, "big")
	return temp.decode()
	
if __name__ == "__main__":
	a = str(pi)
	key1, key2 = encrypt(a)
	#print(key1, key2, sep = '\n')
	result: str = decrypt(key1, key2)
	print(result)
