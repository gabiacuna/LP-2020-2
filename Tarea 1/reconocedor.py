import re

"""
isColor
-----------------------
line : str
patRgb : Pattern
patHsl : Pattern
------------------------
revisa si alguno de los patrones de regex entregados como patRgb y patHsl
generan match con el str entregado en line.
"""
def isColor(line, patRgb, patHsl):
    if re.search(patRgb, line):
        print('RGB')
        return True
    elif re.search(patHsl, line):
        print('HSL')
        return True
    return False

"""
isDir
-----------------------
line : str
pat : Pattern
------------------------
Busca match para el patrón pat (patDir) dentro de line.
"""
def isDir(line, pat):
    if re.search(pat, line):
        print('Archivo en Directorio')
        return True
    return False

"""
isMail
-----------------------
line : str
pat : Pattern
------------------------
Busca match para pat (patMail) en line.
"""
def isMail(line, pat):
    if re.search(pat, line):
        print('Correo')
        return True
    return False

"""
isDate
-----------------------
line : str
pat : Pattern
------------------------
Busca match para pat (patDate) en line. (reconoce desde el año 0000 al 9999)
"""
def isDate(line, pat):
    if re.search(pat, line):
        print('Fecha')
        return True
    return False

"""
isPascal
-----------------------
line : str
------------------------
En base a los caracteres presentes en line, genera el patrón regex correcto
para la línea del triangulo de pascal
"""
def isPascal(line):
    if line[:2] != '{1':
        return False
    if re.search(r'\{1\}' , line):
        print('Pascal')
        return True

    cont = 1

    i = list(map(int, re.findall(r'\d+', line)))    #Se pasa la linea a list(ints) para saber que patron generar

    patPasc = "{"   #Se inicia el str que luego sera el pattern

    for k in range(0, i[1]+1):  #Se genera la linea correcta del triangulo de pascal y se guarda como str en patPasc
        patPasc += str(cont) + r'\s'
        cont = int(cont * (i[1] - k) / (k + 1))

    patPasc = patPasc[:-2] + '}'    #Por los pasos previos, el patron termina con un \s asi que se saca eso y se cambia por }

    if re.search(patPasc , line):   #En este caso no compile previamente el patron por que se usará solo una vez
        print('Pascal')
        return True

"""
isXdson
-----------------------
line : str
pat1 : Pattern
pat2 : Pattern
------------------------
Busca match para pat1 (patXdson) en line. impo, esta funcion cambia la linea que analiza,
si encuentra una linea(match para pat1) dentro de un valor de variable, lo reemplaza pot un str '"reemplazo"' hasta
que la linea no tenga lineas validas dentro de si.

pat 1 -> linea de XDSON sin recursividad aka los valores que pueden tomar las variables son int, str o arreglo. (sin límites de str)
pat 2 -> linea de XDSON con límites de str (^ y $)
"""
def isXdson(line, pat1, pat2):
    if re.search(pat2, line):
        print('XDSON')
        return True

    while re.search(pat1, line): 
        line = re.sub(pat1, '"reemplazo"' , line)
        if re.search(pat2, line):
            print('XDSON')
            return True

    return False

# Patterns RegEx <3 :

patRgb = re.compile(r'^#[0-9A-F]{6}$')
patHsl = re.compile(r'^hsl\((360|3[0-5][0-9]|[0-2][0-9]?[0-9]?)\s*,\s*([0-9]?[0-9]?|100)%\s*,\s*([0-9][0-9]?|100)%\)$')
patDir = re.compile(r'^([A-Z]:|∼|\.|\.\.)?(\\(?!\\)[a-zA-Z0-9]+[^\/:*?\".<>|]*\s?[^\/:.*?\"<>|]*|\\\.{2})+(?<!\\)\\([a-zA-Z0-9]+\.[a-zA-Z0-9.]+)$')
patMail = re.compile(r'^[\w!#$*!\+\-/’‘&?^{}|=%~_](\.(?!\.)|[\w!#$*!\+\-/’‘&?^{}|=%~])+[\w!#$*!\+\-/’‘&?^{}|=%~]@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$')
patDate = re.compile(r'^((\d{4}-(0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|(\d{4}-(0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(\d{4}-(0?2)-(0?[1-9]|1[0-9]|2[0-8])\b)|(((\d{2})(04|08|00|[2468][048]|[13579][26]))-(0?2)-29))$')
patXdson = re.compile(r'<\s*([a-zA-Z]+\s*=\s*(\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\'|\[((\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\')\s*,(?!\])\s*)*(\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\')?\s*\])(\s*,(?!\s*>)\s*)?)+\s*>')
patXdson2 = re.compile(r'^<\s*([a-zA-Z]+\s*=\s*(\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\'|\[((\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\')\s*,(?!\])\s*)*(\-?[1-9]\d*|\"[a-zA-Z0-9]*\"|\'[a-zA-Z0-9]*\')?\s*\])(\s*,(?!\s*>)\s*)?)+\s*>$')

t = open('palabras.txt')

for line in t:  #Se recorre el txt linea a linea buscando pertenencias al lenguaje
    if isColor(line, patRgb, patHsl):  
        pass
    elif isDir(line, patDir):
        pass
    elif isMail(line, patMail):
        pass
    elif isDate(line, patDate):
        pass
    elif isPascal(line):
        pass
    elif isXdson(line, patXdson, patXdson2):
        pass
    else:
        print('No pertenece al lenguaje')

t.close()