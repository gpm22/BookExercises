def roots(func_str):

    if not sympify(func_str).is_polynomial():
    	raise TypeError(f"{func_str} is not a polynomial")

    x = Symbol('x')
    # Calculate roots
    roots = solve(func_str)
    roots = [complex(root.evalf()) for root in roots] 
    roots = [round(root.real, 2) + round(root.imag, 2) * 1j for root in roots]
    return roots



