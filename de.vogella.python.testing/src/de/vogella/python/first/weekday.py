# Calculate the weekday of a date
year = 2009
month = 1
day = 30

if month == 1 or month == 2:
    year = year-1
    month= month +12
    
k = day + 2* month + (3* month +3 ) / 5 +year + year/4 - year / 100 + year / 400
rest = k % 7 +1 
print 28 % 7
myMap = {1:"Monday", 2:"Tuesday", 3:"Wednesday", 4:"Tuesday", 5:"Friday", 6:"Saturday", 7:"Sunday"}
print myMap.__getitem__(rest)
print myMap[rest]

list1 = (1,2,3)
for e in list1: print ("Testing")
# range(4) results in (0,1,2,3)
for e in range (4):
    print e

list1 = (1, "Moin")
for e in list1: print e,
sum = 0 
for e in range(200, 300+1):
    if e % 2 == 0 : 
        sum += e
    print e
print sum