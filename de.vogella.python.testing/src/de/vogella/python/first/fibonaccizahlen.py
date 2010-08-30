a,b=1,1
counter = 0
sum = 0
while counter < 40:
    print a
    sum += a
    a,b = b,a+b
    counter +=1
print ("Summe %d" % sum)  
average = sum / counter;
print ("Durchschnitt %d" % average) 
    