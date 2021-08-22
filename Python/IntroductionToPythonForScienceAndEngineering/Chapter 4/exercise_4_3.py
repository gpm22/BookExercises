#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 26 03:46:18 2019

@author: gabrielpacmil
"""

import numpy as np


f, a, da = np.loadtxt('exercise_4_3.txt', skiprows = 4, unpack = True, )

#np.savetxt('exercise_4_3_2.txt', list(zip(f, a, da)) ,newline=" \n" , header = "Date: 2013-09-16" + "\nData taken by Liam and Selena" + "\nfrequency (Hz) 		amplitude (mm) 		amp error (mm)")
np.savetxt('exercise_4_3_2.csv', list(zip(f, a, da)), delimiter = ",", fmt="%0.16e")

#exercise_4_1.py'
