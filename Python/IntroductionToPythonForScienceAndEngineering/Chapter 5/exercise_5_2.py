#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 27 03:45:53 2019

@author: gabrielpacmil
"""
#verifica se o número é primo, se não for mostra o menor primo o qual esse número é divisível
n = int(input("Input an integer > 1: "))
i = 2



while (n % i) != 0:
    i += 1

if i == n:
    print("o número {0:d} é primo".format(n))
else:
    print("The smallest factor of {0:d} is {1:d}".format(n, i))