#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 05:04:11 2020

@author: gabrielpacmil
"""
import numpy as np

def f1(x, a, p):
	return a*x**p

def deriv(f, x, h=1.e-9, *params):
	return (f(x + h, *params) - f(x - h, *params))/(2.*h)

def int_num_trap_simple(f,inicio,fim ):
    return (fim-inicio)*(f(inicio)+f(fim))/2

def int_num_trap(f, x):
    #a = np.linspace(inicio, fim, N)
    #h = (fim-inicio)/N
    x = np.array(x)
    f = np.array(f)
    inicio = x[0]
    fim = x[-1]
    A=np.zeros(x.size)
    h=np.zeros(x.size)
    h[0] = fim - inicio
    A[0] = h[0]*(f[0]+f[-1])/2
    i = 1
    erro = 1
    while erro > 10**-8:
        B = np.array([])
        k = 0;
        h[i] = h[i-1]/2
        for j in range(1, (2**(i-1)), 2):
        
            B[k] = f[x == (inicio + j*h[i])]
            k = k +1
        
        A[i] = A[i-1]/2 + h[i]*(B.sum())
        erro = (A[i]-A[i-1])/A[i]
        i = i + 1
    
    return A[i]

#x = np.linspace(2,5)
#print(int_num_trap(x**2,x))
#x = np.linspace(0,np.pi),
#print(int_num_trap(np.sin(x),x))
#x = np.linspace(0,3.5),
#print(int_num_trap(np.exp(x)**(-x**2)),x)


#x = np.linspace(0,10,10)
#f = x**2
#A=np.zeros(x.size)
#h=np.ones(x.size)*(x[-1]-x[0])/x.size
    
#h[0] = x[-1] - x[0]
    
fim = 3.5
inicio = 0
N = 100
h = np.zeros([N])
A = np.zeros([N])
h[0]=fim - inicio
#para np.exp(-x**2)
A[0] = h[0]*(np.exp(-inicio**2)+np.exp(-fim**2))/2 #h[0]*(f[0]+f[-1])/2
h[1] = h[0]/2
A[1] = A[0]/2+ h[1]*(np.exp(-(inicio+h[1])**2))
i = 2
erro = 1

while erro > 10**-8: # or h[i] > 10**-5:#i==x.size
    B = np.zeros((2**(i-1)))#([x.size])
    k = 0;
    h[i] = h[i-1]/2
    for j in range(1, (2**(i)), 2):
        
        #print(k)
       # print(j)
        B[k] = (np.exp(-(inicio + j*h[i])**2))
        #print(j)
        k = k +1
        #print(k)
    A[i] = A[i-1]/2 + h[i]*(B.sum()+np.exp(-(fim-h[i])**2))
    #print(A[i])
    erro = ((((A[i]-A[i-1])/A[i])**2))**0.5
    #print(erro)
    #print(A[i])
    i = i + 1

print(erro)
print(A[i-1])

