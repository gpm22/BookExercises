import matplotlib.pyplot as plt
import numpy as np
from sympy import Symbol, lambdify, diff, integrate, solve, re

def plot_function_details(func_str, start=-10, end=10):
    """
    Plots the given function, its derivative, integral, and roots.

    Args:
        func_str (str): The function as a string, using 'x' as the variable.
        start (float, optional): Start value for the plot domain. Defaults to -10.
        end (float, optional): End value for the plot domain. Defaults to 10.
    """

    x = Symbol('x')


    # Convert string to sympy expression and then to numerical function
    f = lambdify(x, func_str, modules=['numpy'])
    f_derivative = lambdify(x, diff(func_str), modules=['numpy'])
    f_integral = lambdify(x, integrate(func_str), modules=['numpy'])

    # Calculate roots
    roots = solve(func_str)
    
    roots = [re(root.evalf()) for root in roots if start <= re(root) <= end]
    print(roots)
    # Generate x values for plotting
    x_vals = np.linspace(start, end, 200)
    y_vals = f(x_vals)
    y_derivative = f_derivative(x_vals)
    y_integral = f_integral(x_vals)

    # Create the plot
    plt.figure(figsize=(10, 6))

    plt.plot(x_vals, y_vals, label='Function')
    plt.plot(x_vals, y_derivative, label='Derivative')
    plt.plot(x_vals, y_integral, label='Integral')

    # Mark roots
    for root in roots:
        plt.axvline(root, color='gray', linestyle='--')
        plt.scatter(root, 0, marker='o', color='red', label='Root')

    plt.xlabel('x')
    plt.ylabel('y')
    plt.title(f'Function: {func_str}')
    plt.grid(True)
    plt.legend()
    plt.show()


# Examples
# plot_function_details("x**2 - 4")
# plot_function_details("sin(x)", start=-2*np.pi, end=2*np.pi)
plot_function_details("x**3 - 3*x + 1", -3, 3)
# plot_function_details("log(x)") 
