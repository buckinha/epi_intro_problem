import ComplexityFunctions as CF
import ComplexityFunctionFitting as CFF
import csv
import matplotlib.pyplot as plt

csvfile = open("IntroProblemProfiler_Output.csv", "rb")
csvreader = csv.reader(csvfile,delimiter=",")
rows = []
for row in csvreader:
    rows.append(row)

n_values = []
bf_times = []
dnc_times = []
ln_times = []

for i in range(len(rows)-1):
    n_values.append(float(rows[i+1][0]))
    bf_times.append(float(rows[i+1][1]))
    dnc_times.append(float(rows[i+1][2]))
    ln_times.append(float(rows[i+1][3]))

function_bf, p_bf   = CFF.get_best_fit(n_values, bf_times)
function_dnc, p_dnc = CFF.get_best_fit(n_values, dnc_times)
function_ln, p_ln   = CFF.get_best_fit(n_values, ln_times)

print("Brute Force:")
print("  " + function_bf + ",   Params: " + str(p_bf))
print("")
print("Divide and Conquer")
print("  " + function_dnc + ",   Params: " + str(p_dnc))
print("")
print("Linear Time")
print("  " + function_ln + ",   Params: " + str(p_ln))

plt.figure()
plt.plot(n_values, bf_times, label="Brute Force")
plt.plot(n_values, dnc_times, label="Divide and Conquer")
plt.plot(n_values, ln_times, label="Linear Time")
plt.legend()
plt.ylabel("Algorithm Running time (ns)")
plt.xlabel("Array Size (n)")
plt.title("EPI Intro Problem: Array size vs Algorithm Running Times")
plt.show()

