#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 27 04:35:47 2019

@author: gabrielpacmil
"""

#programa que determina o dia da semana baseado na data
#començando em 1 de janeiro de 1900 (quarta)

dia = int(input("entre com o dia: "))

mes = input("entre com o mes: ")

ano = int(input("entre com o ano: "))

meses = {"janeiro":0,"fevereiro":31, "março":59, "abril":90, "maio":120, "junho":151, "julho":181, "agosto":212, "setembro":243, "outubro":273, "novembro":304, "dezembro":334}

n_total_dias_do_ano = meses[mes] + dia
#print(n_total_dias_do_ano)

n_total_dias_ate_ano_anterior = ((ano-1900)*365)
#n_total_dias_ate_ano_anterior = n_total_dias_ate_ano_anteriora//1

#verificar os dias que faltam devido aos anos bissextos
#dias_adicionais = ((ano-1900)/4)//1

a = 0

for i in range(1900,ano):
    
    if i%4==0 and i%100!=0:
        a = a + 1
    elif i%400==0:
        a = a + 1
        
dias_adicionais  = a
#print(n_total_dias_ate_ano_anteriora)
#print(n_total_dias_ate_ano_anterior)
#print(dias_adicionais)

if (ano%4 == 0 and ano%100 != 0) or ano%400==0:
    if mes != "janeiro" and mes != "fevereiro":# and mes != "março":
        print("bissexto, carai")
        n_total_dias_do_ano = n_total_dias_do_ano + 1
    
    elif dia == 29:
        n_total_dias_do_ano = n_total_dias_do_ano - 1
        
    elif dia == 1: #mes== "março":
          n_total_dias_do_ano = n_total_dias_do_ano  
     
        
n_total_dias = n_total_dias_do_ano + n_total_dias_ate_ano_anterior + dias_adicionais

resto = n_total_dias%7

#print(n_total_dias_do_ano)
if resto == 0:
    print("domingo ou sunday")

elif resto == 1:
    print("segunda-feira ou monday")

elif resto == 2:
    print("terça-feira ou tuesday")

elif resto == 3:
    print("quarta-feira ou wednesday")

elif resto == 4:
    print("quinta-feira ou thursday")

elif resto == 5:
    print("sexta-feira ou friday")

elif resto == 6:
    print("sábado ou saturday")

     