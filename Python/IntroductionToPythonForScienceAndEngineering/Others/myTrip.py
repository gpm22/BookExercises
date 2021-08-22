#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Dec 18 18:29:03 2019

@author: gabrielpacmil

Calcula o tempo e o gasto com a viagem.

"""

distancia = float(input("Entre com a distância da viagem (Km): "))

velocidade_media = 60.
km_por_l = 30.
preco_combustivel = 2.85

tempo_de_viagem = distancia/velocidade_media

gasto_da_viagem = (distancia/km_por_l)*preco_combustivel

#print(tempo_de_viagem, gasto_da_viagem)

print("\nDuração da viagem = {0:0.1f} horas".format(tempo_de_viagem))
print("Custo do combustível = R$ {0:0.2f}".format(gasto_da_viagem))

