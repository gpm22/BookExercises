#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jan  6 18:36:02 2020

@author: gabrielpacmil
"""

#código exemplo do cap 7 do livro 
#[David_J._Pine]_Introduction_to_Python_for_Science(z-lib.org) 
#com o objetivo de fazer um linear least squares fitting

#S = somatório de i até n de (yi - f(xi; a, b, c, ...))²

#dS/da = 0, dS/db = 0, ...

#na regressão linear -> f = a + bxi²

def lineFit(x, y): #regressão linear boladaça
    ''' Returns slope and y-intercept of linear fit to (x,y) data set'''
    xavg = x.mean()
    slope = (y * (x-xavg)).sum()/(x * (x-xavg)).sum() #b
    yint = y.mean() - slope*xavg #a
    return slope, yint

#o caso anterior é ótimo para quando as incertezas são iguais para todos os pontos, 
#onde a incerteza varia, é necessário utiliar um sistema de pesos.
#esse sistema de pesos é o chi-squared method, 
#que valoriza mais os dados com menor incerteza

#chi² = somatatório de i até n de ((yi-f(xi))/incerteza,i)²

#na regressão linear -> f = a + bxi²  
    
#para medir a qualidade da regressão -> chi,r² = chi²/(n-2)
#quanto mais próximo de 1, melhor.
#se muito maior que 1, indica um péssimo fitting
#se muito menor que 1, as incertezas foram superestimadas
