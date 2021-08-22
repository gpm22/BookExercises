#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jan 12 04:28:42 2020

@author: gabrielpacmil
"""

#resolver edos do exercício 9.3 pelo método de euler

import numpy as np
import scipy.integrate as sci
import matplotlib.pyplot as plt

def func(x,t,parametros):
    a,b,c = parametros
    x,y,z = x
    derivs = [a*(y-x),(c-a)*x-x*z+c*y, x*y-b*z]
    return derivs


#condições iniciais

x0, y0, z0 = -10, 0, 35
A0 = np.array([x0,y0,z0])
#parâmetros iniciais

a, b, c = 40, 5, 35 #caóticas; 

a_1, b_1, c_1 = 40, 10, 35 #periódicas

parametros = [a, b, c]
parametros_1 = [a_1, b_1, c_1]


dt = 0.01
t = np.arange(0.,10, dt) #funciona com dt = 0.01

x = np.zeros(t.size)
y = np.zeros(t.size)
z = np.zeros(t.size)

x_1 = x
y_1 = y
z_1 = z

x[0]=x0
y[0]=y0
z[0]=z0
x_1[0]=x0
y_1[0]=y0
z_1[0]=z0



# dx/dt = a(y-x)
# dy/dt = (c-a)x-xz + cy
#dz/dt = xy-bz

#método de Euler


[a*(y-x),(c-a)*x-x*z+c*y, x*y-b*z]

f1 = lambda x,y,a:(a*(y-x))
f2 = lambda x,y,z,a,c:((c-a)*x-x*z+c*y)
f3 = lambda x,y,z,b:(x*y-b*z)

for i in np.arange(0,t.size-1):
    
    #método de Euler
    #x[i+1] = x[i] + (dt)*(f1(x[i],y[i],a))
    #y[i+1] = y[i] + (dt)*f2(x[i],y[i],z[i],a,c)
    #z[i+1] = z[i] + (dt)*f3(x[i],y[i],z[i],b)
    #runge-kutta segunda ordem
    #x[i+1] = x[i] + (dt/2)*(f1(x[i],y[i],a)+f1(x[i+1],y[i+1],a))
    #y[i+1] = y[i] + (dt/2)*(f2(x[i],y[i],z[i],a,c)+f2(x[i+1],y[i+1],z[i+1],a,c))
    #z[i+1] = z[i] + (dt/2)*(f3(x[i],y[i],z[i],b)+f3(x[i+1],y[i+1],z[i+1],b))
    
    #runge-kutta quarta ordem
    
    kx_1 = f1(x[i],y[i],a)
    ky_1 = f2(x[i],y[i],z[i],a,c)
    kz_1 = f3(x[i],y[i],z[i],b)


    kx_2 = f1((x[i]+dt*kx_1/2),(y[i]+dt*ky_1/2),a)
    ky_2 = f2((x[i]+dt*kx_1/2),(y[i]+dt*ky_1/2),(z[i]+dt*kz_1/2),a,c)
    kz_2 = f3((x[i]+dt*kx_1/2),(y[i]+dt*ky_1/2),(z[i]+dt*kz_1/2),b)


    kx_3 = f1((x[i]+dt*kx_2/2),(y[i]+dt*ky_2/2),a)
    ky_3 = f2((x[i]+dt*kx_2/2),(y[i]+dt*ky_2/2),(z[i]+dt*kz_2/2),a,c)
    kz_3 = f3((x[i]+dt*kx_2/2),(y[i]+dt*ky_2/2),(z[i]+dt*kz_2/2),b)


    kx_4 = f1((x[i]+dt*kx_3),(y[i]+dt*ky_3),a)
    ky_4 = f2((x[i]+dt*kx_3),(y[i]+dt*ky_3),(z[i]+dt*kz_3),a,c)
    kz_4 = f3((x[i]+dt*kx_3),(y[i]+dt*ky_3),(z[i]+dt*kz_3),b)
    
    x[i+1] = x[i] + (dt/6)*(kx_1 + 2*kx_2 +2*kx_3 + kx_4)
    y[i+1] = y[i] + (dt/6)*(ky_1 + 2*ky_2 +2*ky_3 + ky_4)
    z[i+1] = z[i] + (dt/6)*(kz_1 + 2*kz_2 +2*kz_3 + kz_4)
    
    
    #print(x[i+1])
    if x[i+1] == np.nan or y[i+1] == np.nan or z[i+1] == np.nan:
        print('deu ruim, carai')
        print(i)
        break 

for i in np.arange(0,t.size-1):
    
    #método de Euler
    #x_1[i+1] = x_1[i] + (dt)*f1(x_1[i],y_1[i],a_1)
    #y_1[i+1] = y_1[i] + (dt)*f2(x_1[i],y_1[i],z_1[i],a_1,c_1)
    #z_1[i+1] = z_1[i] + (dt)*f3(x_1[i],y_1[i],z_1[i],b_1)
      #runge-kutta segunda ordem
    #x_1[i+1] = x_1[i] + (dt/2)*(f1(x_1[i],y_1[i],a_1)+f1(x_1[i+1],y_1[i+1],a_1))
    #y_1[i+1] = y_1[i] + (dt/2)*(f2(x_1[i],y_1[i],z_1[i],a_1,c_1)+f2(x_1[i+1],y_1[i+1],z_1[i+1],a_1,c_1))
    #z_1[i+1] = z_1[i] + (dt/2)*(f3(x_1[i],y_1[i],z_1[i],b_1)+f3(x_1[i+1],y_1[i+1],z_1[i+1],b_1))
    
    
      #runge-kutta quarta ordem
    
    kx_1 = f1(x_1[i],y_1[i],a_1)
    ky_1 = f2(x_1[i],y_1[i],z_1[i],a_1,c_1)
    kz_1 = f3(x_1[i],y_1[i],z_1[i],b_1)


    kx_2 = f1((x_1[i]+dt*kx_1/2),(y_1[i]+dt*ky_1/2),a_1)
    ky_2 = f2((x_1[i]+dt*kx_1/2),(y_1[i]+dt*ky_1/2),(z_1[i]+dt*kz_1/2),a_1,c_1)
    kz_2 = f3((x_1[i]+dt*kx_1/2),(y_1[i]+dt*ky_1/2),(z_1[i]+dt*kz_1/2),b_1)


    kx_3 = f1((x_1[i]+dt*kx_2/2),(y_1[i]+dt*ky_2/2),a_1)
    ky_3 = f2((x_1[i]+dt*kx_2/2),(y_1[i]+dt*ky_2/2),(z_1[i]+dt*kz_2/2),a_1,c_1)
    kz_3 = f3((x_1[i]+dt*kx_2/2),(y_1[i]+dt*ky_2/2),(z_1[i]+dt*kz_2/2),b_1)


    kx_4 = f1((x_1[i]+dt*kx_3),(y_1[i]+dt*ky_3),a_1)
    ky_4 = f2((x_1[i]+dt*kx_3),(y_1[i]+dt*ky_3),(z_1[i]+dt*kz_3),a_1,c_1)
    kz_4 = f3((x_1[i]+dt*kx_3),(y_1[i]+dt*ky_3),(z_1[i]+dt*kz_3),b_1)
    
    x_1[i+1] = x_1[i] + (dt/6)*(kx_1 + 2*kx_2 +2*kx_3 + kx_4)
    y_1[i+1] = y_1[i] + (dt/6)*(ky_1 + 2*ky_2 +2*ky_3 + ky_4)
    z_1[i+1] = z_1[i] + (dt/6)*(kz_1 + 2*kz_2 +2*kz_3 + kz_4)
    
    #print(x_1[i+1])
    if x_1[i+1] == np.nan or y_1[i+1] == np.nan or z_1[i+1] == np.nan:
        print('\ndeu ruim, carai 2')
        print(i)
        break 
#utilizando o scipy


psoln = sci.odeint(func, A0, t, args =(parametros,))

psoln_1 = sci.odeint(func, A0, t, args =(parametros_1,))

#comparação dos resultados

#print(x)
#print(psoln[:,0])


psoln_b = psoln

psoln_b[psoln_b == 0 ] = 0.01

erro_x = (psoln[:, 0]-x)/psoln_b[:, 0]
erro_y = (psoln[:, 1]-y)/psoln_b[:, 1]
erro_z = (psoln[:, 2]-z)/psoln_b[:, 1]

psoln_b = psoln_1

psoln_b[psoln_b == 0 ] = 0.01

erro_x_1 = (psoln_1[:, 0]-x_1)/psoln_b[:, 0]
erro_y_1 = (psoln_1[:, 1]-y_1)/psoln_b[:, 1]
erro_z_1 = (psoln_1[:, 2]-z_1)/psoln_b[:, 2]

print(erro_x.mean())
print(erro_y.mean())
print(erro_z.mean())
print(erro_x_1.mean())
print(erro_y_1.mean())
print(erro_z_1.mean())

sol = [x,y,z]
sol_1 = [x_1, y_1, z_1]
erro = [erro_x, erro_y, erro_z]
erro_1 = [erro_x_1, erro_y_1, erro_z_1]

fig_1 = plt.figure()

ax1 = fig_1.add_subplot()
ax1.plot(t,sol[0],'-.r', label = 'minha solução')
ax1.plot(t,psoln[:,0], 'k', label = 'scipy')
#plt.plot(t,erro[0],'.k', label = 'erro')
ax1.legend()
ax1.set_xlabel('tempo')
ax1.set_ylabel('x')
fig_1.show()
fig_1.canvas.manager.window.raise_()

fig_2 = plt.figure()

ax2 = fig_2.add_subplot()
ax2.plot(t,sol[1],'-.r', label = 'minha solução')
ax2.plot(t,psoln[:,1], 'k', label = 'scipy')
#ax2.plot(t,erro[1],'.k', label = 'erro')
ax2.legend()
ax2.set_xlabel('tempo')
ax2.set_ylabel('y')
fig_2.show()
fig_2.canvas.manager.window.raise_()

fig_3 = plt.figure()

ax3 = fig_3.add_subplot()
ax3.plot(t,sol[2],'-.r', label = 'minha solução')
ax3.plot(t,psoln[:,2],'k', label = 'scipy')
#ax3.plot(t,erro[2],'.k', label = 'erro')
ax3.legend()
ax3.set_xlabel('tempo')
ax3.set_ylabel('z')
fig_3.show()
fig_3.canvas.manager.window.raise_()



fig_4 = plt.figure()

ax4 = fig_4.add_subplot()
ax4.plot(t,sol_1[0],'-.r', label = 'minha solução')
ax4.plot(t,psoln_1[:,0], 'k',label = 'scipy')
#ax4.plot(t,erro_1[0],'.k', label = 'erro')
ax4.legend()
ax4.set_xlabel('tempo')
ax4.set_ylabel('x')
fig_4.show()
fig_4.canvas.manager.window.raise_()


fig_5 = plt.figure()

ax5 = fig_5.add_subplot()
ax5.plot(t,sol_1[1],'-.r', label = 'minha solução')
ax5.plot(t,psoln_1[:,1],'k', label = 'scipy')
#ax5.plot(t,erro_1[1],'.k', label = 'erro')
ax5.legend()
ax5.set_xlabel('tempo')
ax5.set_ylabel('y')
fig_5.show()
fig_5.canvas.manager.window.raise_()


fig_6 = plt.figure()

ax6 = fig_6.add_subplot()
ax6 .plot(t,sol_1[2],'-.r', label = 'minha solução')
ax6 .plot(t,psoln_1[:,2], 'k',label = 'scipy')
#ax6 .plot(t,erro_1[2],'.k', label = 'erro')
ax6 .legend()
ax6 .set_xlabel('tempo')
ax6 .set_ylabel('z')
fig_6.show()
fig_6.canvas.manager.window.raise_()





