def PascalTriangle(n):
    trow = [1]
    y = [0]
    for _ in range(n):
        l = str(trow)
        l = l.replace('[', '{')
        l = l.replace(']', '}')
        l = l.replace(',', '')
        #print(l)
        l+='\n'
        p.write(l)
        trow=[left+right for left,right in zip(trow+y, y+trow)]
    return n>=1


p = open('pascTest.txt', 'w')

PascalTriangle(16)
p.close()