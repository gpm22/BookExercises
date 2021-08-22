#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Dec 31 17:59:20 2019

@author: gabrielpacmil
"""

import numpy as np
import matplotlib.pyplot as plt

theta = np.arange(0.01, 8., 0.04)
y = np.sqrt((8./theta)**2-1.)
ytan = np.tan(theta)
ytan = np.ma.masked_where(np.abs(ytan) > 20., ytan)
ycot = 1./np.tan(theta)
ycot = np.ma.masked_where(np.abs(ycot) > 20., ycot)

fig, ax = plt.subplots(2,1, sharex = True, figsize=(8.5, 6)) # plt.figure(figsize=(8.5, 6))


ax[0].plot(theta, y, linestyle=':')
ax[0].plot(theta, ytan)
ax[0].axvline(x=np.pi/2., color="gray", linestyle='--', zorder=-1)
ax[0].axvline(x=3.*np.pi/2., color="gray", linestyle='--', zorder=-1)
ax[0].axvline(x=5.*np.pi/2., color="gray", linestyle='--', zorder=-1)
ax[0].set_ylabel(r"tan$\,\theta$")

ax[1].plot(theta, -y, linestyle=':')
ax[1].plot(theta, ycot)
ax[1].axvline(x=np.pi, color="gray", linestyle='--', zorder=-1)
ax[1].axvline(x=2.*np.pi, color="gray", linestyle='--', zorder=-1)
ax[1].set_ylabel(r"cot$\,\theta$")
ax[1].set_xlabel(r"$\theta$") #

for i in [0,1]:
    
    ax[i].set_xlim(0, 8) #
    ax[i].set_ylim(-8, 8) #
    ax[i].axhline(color="gray", zorder=-1) #
   



#ax[1].set_xlim(0, 8) #
#ax[1].set_ylim(-8, 8) #
#ax[1].axhline(color="gray", zorder=-1) #

#ax[1].set_xlabel("theta") #


#plt.savefig('figures/subplotDemo.pdf')
fig.show()
fig.canvas.manager.window.raise_()