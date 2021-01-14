JFLAGS = -g -cp .:./classes -d ./classes
#JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Juego.java \
		Cartas/Carta.java \
		Cartas/Estudio.java \
		Cartas/Ramo.java \
		Cartas/Evento.java \
		Juego/Tablero.java \
		Juego/Mazo.java \
		Juego/Mano.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) classes/*.class
		$(RM) classes/Cartas/*.class
		$(RM) classes/Juego/*.class
#$(RM) *.class
#$(RM) Cartas/*.class
#$(RM) Jurgo/*.class