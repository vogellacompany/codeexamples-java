from random import randrange

map = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, "equal":0}
number =0 
for i in range(100001):
    number +=1
    w1 = randrange(1, 7)
    w2 = randrange(1, 7)
    map[w1]+=1
    map[w2]+=1
    if w1 == w2:
        map["equal"] += 1
    
    
print map
print (map["equal"]/float(number))