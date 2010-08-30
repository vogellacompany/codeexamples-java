

'''
@author: Lars Vogel
'''

f = open('c:\\temp\\wave1_new.csv', 'r')
output = open('c:\\temp\\sql_script.text', 'w')
for line in f:
    output.write('Insert into SIXSIGMA.REL_REQ_USER(REQ_ID, USER_ID) values (' + line.rstrip() + ', 29);\n')
f.close()