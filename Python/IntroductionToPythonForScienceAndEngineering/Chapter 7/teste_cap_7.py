#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 05:27:00 2020

@author: gabrielpacmil
"""

#programa teste baseado no cap 7 do livro [David_J._Pine]_Introduction_to_Python_for_Science(z-lib.org)

def f1(x, a, p):
	return a*x**p

def deriv(f, x, h=1.e-9, *params):
	return (f(x + h, *params) - f(x - h, *params))/(2.*h)

print(deriv(f1, 3, 1.e-9, *(4,5)))
