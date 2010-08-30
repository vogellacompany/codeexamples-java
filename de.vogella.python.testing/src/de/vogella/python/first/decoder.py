
def method(loop_count):
    out_str = ''
    for num in xrange(loop_count):
        out_str += num
    return out_str

for a in range (10):
    for b in range (10):
        for c in range (10):
            if (b==c):
                multi = `a`+`b` + `c`
                result = int(multi) * int(multi)
                text = str(result)
            
                if (len(text)==5):
                    #print text, 
                    #print text[1],
                    # print text[4],
                    #print a,
                    #print b
                  
                    firstValue = int(text[1])
                    secondValue = int (text[4])
                    if (firstValue == b and secondValue == a):
                        print ("Hubba Bubba %d%d%d %s " % (a, b, c, text))
               
       
           
