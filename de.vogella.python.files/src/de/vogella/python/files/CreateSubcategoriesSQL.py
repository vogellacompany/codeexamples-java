

'''
@author: Lars Vogel
'''

a = 10
categoryID = 10
f = open('c:\\temp\\cdp\\cp_input.csv', 'r')
output = open('c:\\temp\\cdp\\cp_output.csv', 'w')
for line in f:
    if (line.startswith('---SKIP')):
        categoryID += 10 
    else:
        output.write('Insert into SIXSIGMA.SUBCATEGORY(ID, NAME, DESCRIPTION, SORT, ACTIVE ) values (' + str(a) + ',\'' + line.rstrip()+ '\',\''  + line.rstrip()+ '\','+ str(a) + ',1'  + ');\n')
        output.write('insert into sixsigma.rel_cat_subc values(' + str(categoryID) +','+ str(a)+ ');\n')
    a = a + 10
f.close()