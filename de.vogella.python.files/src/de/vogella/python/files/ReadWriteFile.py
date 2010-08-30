
'''
@author: Lars Vogel
'''

f = open('c:\\temp\\wave1_new.csv', 'r')
output = open('c:\\temp\\sql_script.text', 'w')
for line in f:
    output.write(line.rstrip() + '\n')
f.close()