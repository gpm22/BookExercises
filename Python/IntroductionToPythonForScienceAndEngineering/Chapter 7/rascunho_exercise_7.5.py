#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 07:18:51 2020

@author: gabrielpacmil
"""

fim = 5
inicio = 2
N = 100
h = np.zeros([N])
A = np.zeros([N])
h[0]=fim - inicio
#para x²
A[0] = h[0]*(inicio**2+fim**2)/2 #h[0]*(f[0]+f[-1])/2
h[1] = h[0]/2
A[1] = A[0]/2+ h[1]*((inicio+h[1])**2)
i = 2
erro = 1

while erro > 10**-5: # or h[i] > 10**-5:#i==x.size
    B = np.zeros((2**(i-1)))#([x.size])
    k = 0;
    h[i] = h[i-1]/2
    for j in range(1, (2**(i)), 2):
        
        #print(k)
       # print(j)
        B[k] = ((inicio + j*h[i])**2)
        #print(j)
        k = k +1
        #print(k)
    A[i] = A[i-1]/2 + h[i]*(B.sum()+(fim-h[i])**2)
    #print(A[i])
    erro = ((((A[i]-A[i-1])/A[i])**2))**0.5
    #print(erro)
    #print(A[i])
    i = i + 1

print(erro)
print(A[i-1])




#para sin(x)
A[0] = h[0]*(np.sin(inicio)+np.sin(fim))/2 #h[0]*(f[0]+f[-1])/2
h[1] = h[0]/2
A[1] = A[0]/2+ h[1]*(np.sin(inicio+h[1]))
i = 2
erro = 1

while erro > 10**-5: # or h[i] > 10**-5:#i==x.size
    B = np.zeros((2**(i-1)))#([x.size])
    k = 0;
    h[i] = h[i-1]/2
    for j in range(1, (2**(i)), 2):
        
        #print(k)
       # print(j)
        B[k] = (np.sin(inicio + j*h[i]))
        #print(j)
        k = k +1
        #print(k)
    A[i] = A[i-1]/2 + h[i]*(B.sum()+np.sin(fim-h[i]))
    #print(A[i])
    erro = ((((A[i]-A[i-1])/A[i])**2))**0.5
    #print(erro)
    #print(A[i])
    i = i + 1
