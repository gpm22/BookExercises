#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 19 04:19:36 2019

@author: gabrielpacmil
"""
import numpy as np

V0 = 10
a  = 2.5
z  = 13

V = V0*(1-z/np.sqrt(a**2 + z**2))

print(V)