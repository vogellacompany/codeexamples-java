'''
Created on 16.03.2010

@author: d034797
'''

f1 = open('c:\\temp\\launchconfig1.txt', 'r')
s= ""
for line in f1:
    s+=line
f1.close()

f2 = open('c:\\temp\\launchconfig2.txt', 'r')
s2= ""
for line in f2:
    s2+=line
f2.close()
list1 = s.split(",")
list2 = s2.split(",");
print(len(list1))
print(len(list2))


difference = list(set(list1).difference(set(list2))) 

print (difference)
