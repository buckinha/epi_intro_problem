"""Complexity functions for curve fitting.
Three standard complexity functions for fitting purposes. When you have data on set size ("n") and and running times ("x"), these functions can be used to fit a linear-, quadratic-, and n*log(n)-time function to the data. 

"""
import math
import numpy as np

def f_quadratic(x, a, b, c):
    """Standard quadratic function: a*(x**2) + b*x + c
    Args:
        x: the independent variable. Any numeric type.
        a: paramater for the quatdratic term
        b: parameter for the linear term
        c: constant term
    Returns:
        The value of the function.
    """
    return a*(x**2) + b*x + c

def f_linear(x, a, b, c):
    """Standard linear function: a*x + b + 0*c  (c is unused)
    Args:
        x: the independent variable. Any numeric type.
        a: paramater for the linear term
        b: constant term
        c: unused, but included to match the function signatiure of the other functions in this file.
    Returns:
        The value of the function.
    """
    return a*x + b

def f_nlogn(x, a, b, c):
    """A logarithmic function: a * x*log(x) + b*x + c
    Args:
        x: the independent variable. Any numeric type.
        a: paramater for the log-linear term
        b: parameter for the linear term
        c: constant term
    Returns:
        The value of the function.
    """

    return a * x * np.log(x) + b*x + c
 