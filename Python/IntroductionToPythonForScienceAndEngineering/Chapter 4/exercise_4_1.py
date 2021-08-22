#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 24 16:13:47 2019

@author: gabrielpacmil
"""

dinheiro_disponivel = float(input("Entre com a quantidade de dinheiro disponível: "))
dia_do_mes = float(input("Entre em qual dia do mês você está: "))
#n_dias_do_mes = float(input("Entre com a quantidade de dias que esse mês têm: "))
mes = input("Entre em qual mês você está: ")


meses = {"janeiro":31,"fevereiro":28, "março":31, "abril":30, "maio":31, "junho":30, "julho":31, "agosto":31, "setembro":30, "outubro":31, "novembro":30, "dezembro":31}
n_dias_do_mes = meses[mes]


dinheiro_por_dia = dinheiro_disponivel/(n_dias_do_mes-dia_do_mes+1)

#print(tempo_de_viagem, gasto_da_viagem)

print("\nVocê pode gastar R$ {0:0.2f} por dia".format(dinheiro_por_dia))

