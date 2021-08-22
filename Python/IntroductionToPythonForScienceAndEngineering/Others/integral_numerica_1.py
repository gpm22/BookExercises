#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jan  7 06:17:25 2020

@author: gabrielpacmil
"""

#teste integral bolada

import numpy as np

x = np.linspace(2,5)

y = x**2

integral = y.sum()

print(integral)