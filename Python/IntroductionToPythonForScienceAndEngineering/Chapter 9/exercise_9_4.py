#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 12 07:04:06 2020

@author: gabrielpacmil
"""

#exercicio 9.4

import numpy as np
from scipy import fftpack
import matplotlib.pyplot as plt

#para criar um ruído com forma gaussiana

def gaussNoisy(x, noiseAmp):
    noise = noiseAmp*(np.random.randn(len(x)))
    return np.exp(-0.5*x*x) * (1.0+noise)
N = 256
x = np.linspace(-4.0, 4.0, N)
y = gaussNoisy(x, 0.1)


t = np.linspace(-4, 4, 512)
g = gaussNoisy(t,0.1)
dt = t[1]-t[0] # increment between times in time array

G = fftpack.fft(g) # FFT of g
f = fftpack.fftfreq(g.size, d=dt) # FFT frequenies
f = fftpack.fftshift(f) # shift freqs from min to max
G = fftpack.fftshift(G) # shift G order to match f

c = np.exp(-0.5*t*t)

C = fftpack.fft(c) # FFT of g
fc = fftpack.fftfreq(c.size, d=dt) # FFT frequenies
fc = fftpack.fftshift(fc) # shift freqs from min to max
C = fftpack.fftshift(C) # shift G order to match f

G_f = G
G_f[np.abs(f)>np.pi/4] = 0
#G_f = G[np.sqrt(f**2)<2]

A = np.real(fftpack.ifft(G_f))
A[A<0] = -A[A<0] #deixando tudo positivo



fig, (ax1, ax2, ax3) = plt.subplots(3, 1, figsize=(9, 6))
ax1.plot(t, g)
ax1.plot(t, c, 'r')
ax1.set_xlabel(r'$t$')
ax1.set_ylabel(r'$g(t)$')
#ax1.set_ylim(-2, 2)
#ax2.plot(f, np.real(G), color='dodgerblue', label='real part G')
#ax2.plot(f, np.imag(G), color='coral', label='imaginary part G')
ax2.plot(fc, np.real(C), color='black', label='real part C')
ax2.plot(fc, np.imag(C), color='yellow', label='imaginary part C')
ax2.plot(f, np.real(G_f), color='green', label='real part G_F')
ax2.plot(f, np.imag(G_f), color='brown', label='imaginary part G_F')
ax2.legend()
ax2.set_xlabel(r'$f$')
ax2.set_ylabel(r'$G(f)$')

ax3.plot(t,g,'r',label = 'original')
ax3.plot(t,A,'k', label = 'filtrado')
ax3.plot(t,c,'g', label = 'teorico')
ax3.legend()
ax3.set_xlabel(r'$t$')
ax3.set_ylabel(r'$g(t)$')
plt.tight_layout()
#plt.savefig('figures/fftExample.pdf')
plt.show()

print((A-c).mean())