import re

mails = '''
trivialito@ejemplo.correcto.com
un.nombre+y+algunos+tags@uwu-correcto.net
ejemplo.malo.com
otro@ejemplo@malo.cl
aqui_si@aqui_no.malo.com
'''

patMail = re.compile(r'([\w!#$!+-/’&?‘^{}|_=%]|\.?)+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+')

matches = patMail.finditer(mails)

for m in matches:
    print(m)


print('~'*90, '\n')

colores = '''
#C4F3AA
#F1F0F3
hsl(361 , 55%, 60%)
#F30
{hsl(4 , 10% , 5%)}
hsl(15 , 10% , 12%)
'''

patCol = re.compile(r'(#[0-9A-F]{6}|hsl\((360|3[0-5][0-9]|[0-2]{1}[0-9]?[0-9]?)\s*,\s*([0-9]?[0-9]?|100)%\s*,\s*([0-9]?[0-9]?|100)%\))')

mat = patCol.finditer(colores)

for m in mat:
    print(m)

print('~'*90, '\n')

direct = r'''
E:\Carpeta\archivo.txt
.\Desktop\USM\LP\Tarea 1\programa.py
..\..\..\Minecraft.exe
uwu\    esto no se puede\.py
'''

#patDir = re.compile(r'([A-Z]:|∼|\.{0,2})(\\[A-Za-z0-9\.]+[\s]?[A-Za-z0-9\.]|\.\.)+\b')
#patDir = re.compile(r'\b(([A-Z]:|∼|\.{0,2})+(\\[A-Za-z0-9]+\s?[A-Za-z0-9]*|\\\.\.)+)\b')
#patDir = re.compile(r'([A-Z]:|∼|\.)+(\\[A-Za-z0-9]+\s?[A-Za-z0-9]*|\\\.\.)+\.[a-z]+')

patDir = re.compile(r'([A-Z]:|∼|\.)+(\\\w+\s?\w*|\\\.\.)+\.[a-z]+')

mmm = patDir.finditer(direct)

for m in mmm:
    if m:
        print('true')
    print(m)

print('~'*90, '\n')

#[x.group() for x in re.finditer( r'([A-Z]:|∼|\.)+(\\\w+\s?\w*|\\\.\.)+\.[a-z]+', r'.\Desktop\USM\LP\Tarea 1\programa.py')]

fechas = '''
2020-02-29
2019-02-29
2020-2-2
2020-12-02
0001-12-3
1-12-3
'''

patDate = re.compile(r'((\d{4}-(0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|(\d{4}-(0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(\d{4}-(0?2)-(0?[1-9]|1[0-9]|2[0-8])\b)|(((\d{2})(04|08|00|[2468][048]|[13579][26]))-(0?2)-29))')

#patDate = re.compile(r'((\d{4}-(0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|(\d{4}-(0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(\d{4}-(0?2)-(0?[1-9]|1[0-9]|2[0-8]))|((((\d{2})(04|08|[2468][048]|[13579][26]))|2000)-(0?2)-29))')

# (cualquier año- meses c/ 31 días)|(cualquier año- meses c/ 30 días)|(cualquier año- febreri c/ 28 días)|(años biciestos - febrero - 29 )

matchDate = patDate.finditer(fechas)

for m in matchDate:
    print(m)

print('~'*90, '\n')

xdson ='''
< hola = 1 , joaoaoa = "XDDDDDDDD", tomc = 432, xdson = < holaestoydentro = 1 , yo = “igual”>, M = [1,2,3,4]>
< holasoyelhector = 10 , holasoyelaxel = 11, holasoylaana = 12, holaestoymal : 13>
<jeje = [”xd”, 3]>
<jeje = [”:(”, 3]>
<jeje = [”xd”, 3, [2, 3]]>
< esto = ["deberia" , 12 , "bien"]>
'''

#(<\s?)([a-zA-Z]+\s=\s(\d+|"([a-zA-Z0-9]+)"|“\4”)(\s?,\s?|>))+

patXdson = re.compile(r'(<\s?)([a-zA-Z]+\s=\s(\d+|"[a-zA-Z0-9]+"|“[a-zA-Z0-9]+”|\[((\d|"[a-zA-Z0-9]+"|“[a-zA-Z0-9]+”)\s?,?\s?)+\])(\s?,\s?|\s?>)?)+')

mXd = patXdson.finditer(xdson)

for m in mXd:
    print(m)
