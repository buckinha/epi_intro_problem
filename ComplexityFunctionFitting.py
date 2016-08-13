from scipy.optimize import curve_fit
import ComplexityFunctions as CFunc
import numpy as np

def fit_quadratic(X,y):
    """Fits a quadratic function to the data
    Args:
        X: vector of x values (1-dimensional)
        y: vector of y values (1-dimensional)
    Returns:
        popt, pcov: from scipy.optimize.curve_fit
    """
    #Function signature for scipy.optimize.curve_fit:
    # scipy.optimize.curve_fit(f, xdata, ydata, p0=None, sigma=None, absolute_sigma=False, check_finite=True, bounds=(-inf, inf), method=None, jac=None, **kwargs)
    popt, pcov = curve_fit(f=CFunc.f_quadratic, xdata=X, ydata=y)

    #hand-calculating the sum-of-squared errors on the fitted function
    y_hat = [CFunc.f_quadratic(x, popt[0], popt[1], popt[2]) for x in X]
    SS = 0
    for actual, predicted in zip(y, y_hat):
        SS += (actual - predicted)**2

    return popt, pcov, SS

def fit_linear(X,y):
    """Fits a linear function to the data
    Args:
        X: vector of x values (1-dimensional)
        y: vector of y values (1-dimensional)
    Returns:
        popt, pcov: from scipy.optimize.curve_fit
    """
    #Function signature for scipy.optimize.curve_fit:
    # scipy.optimize.curve_fit(f, xdata, ydata, p0=None, sigma=None, absolute_sigma=False, check_finite=True, bounds=(-inf, inf), method=None, jac=None, **kwargs)
    popt, pcov = curve_fit(f=CFunc.f_linear, xdata=X, ydata=y)

    #hand-calculating the sum-of-squared errors on the fitted function
    y_hat = [CFunc.f_linear(x, popt[0], popt[1], popt[2]) for x in X]
    SS = 0
    for actual, predicted in zip(y, y_hat):
        SS += (actual - predicted)**2

    return popt, pcov, SS


def fit_nlogn(X,y):
    """Fits a log-linear function to the data
    Args:
        X: vector of x values (1-dimensional)
        y: vector of y values (1-dimensional)
    Returns:
        popt, pcov: from scipy.optimize.curve_fit
    """
    #Function signature for scipy.optimize.curve_fit:
    # scipy.optimize.curve_fit(f, xdata, ydata, p0=None, sigma=None, absolute_sigma=False, check_finite=True, bounds=(-inf, inf), method=None, jac=None, **kwargs)
    popt, pcov = curve_fit(f=CFunc.f_nlogn, xdata=X, ydata=y, p0=(1,1,0))
    #hand-calculating the sum-of-squared errors on the fitted function
    y_hat = [CFunc.f_nlogn(x, popt[0], popt[1], popt[2]) for x in X]
    SS = 0
    for actual, predicted in zip(y, y_hat):
        SS += (actual - predicted)**2

    return popt, pcov, SS

def get_best_fit(X,y,DEBUG=False):
    """Gets the best-fitting function for the data
    Fits a quadratic, log-linear, and linear function to the data, and chooses the best.

    Args:
        X: vector of x values (1-dimensional)
        y: vector of y values (1-dimensional)
    Retuns:
        function type (string): values can be "quadratic", "n*log(n)" and "linear"
        parameters: vector of parameters (length = 3) for the chosen function.
    """

    #get fits for the functions
    ln_p, ln_cov, ln_SS = fit_linear(X,y)
    nlogn_p, nlogn_cov, nlogn_SS  = fit_nlogn(X,y)
    #nlogn_p, nlogn_cov, nlogn_SS = [1,1,1], [[0,0,0],[0,0,0],[0,0,0]], np.inf
    quad_p, quad_cov, quad_SS  = fit_quadratic(X,y)

    #choose the model with the lowest sum-of-squared errors
    if DEBUG:
        print("Linear:")
        print("  " + str(ln_SS))
        print("nlog(n):")
        print("  " + str(nlogn_SS))
        print("Quadratic:")
        print("  " + str(quad_SS))
    if (ln_SS < nlogn_SS) and (ln_SS < quad_SS):
        return "linear", ln_p
    elif (nlogn_SS < quad_SS):
        return "n*log(n)", nlogn_p
    else:
        return "quadratic", quad_p