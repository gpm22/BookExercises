#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 27 04:20:21 2019

@author: gabrielpacmil
"""

#programa que calcula o valor de um investimento após alguns anos

valor_inicial = float(input("Entre com o valor do investimento inicial: "))

rendimento = float(input("Entre com o valor do rendimento (em %): "))

tempo = int(input("Entre com a quantidade do tempo que se passou (anos): "))


#a) calculo se o rendimento é anual

valor_final = valor_inicial

for i in range(tempo):
    
    valor_final = valor_final*(1+rendimento/100)

print("\no valor final para rendimento anual será: {}".format(valor_final))

#b) calculo se o rendimento é mensal


tempo2 = tempo * 12
valor_final = valor_inicial
rendimento2 = rendimento/12

for i in range(tempo2):
    
    valor_final = valor_final*(1+rendimento2/100)

print("\no valor final para rendimento mensal será: {}".format(valor_final))

#c) calculo se o rendimento é diário


tempo3 = tempo * 365
valor_final = valor_inicial
rendimento3 = rendimento/365

for i in range(tempo3):
    
    valor_final = valor_final*(1+rendimento3/100)

print("\no valor final para rendimento diário será: {}".format(valor_final))