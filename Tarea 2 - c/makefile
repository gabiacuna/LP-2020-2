objs = main.o heap.o
CFLAGS = -W -Wall
prog = heap
CC = gcc
#Esta regla compila el progra principal
all: heap

heap: $(objs)
	$(CC) $(CFLAGS) -o $(prog) $(objs)

main.o: main.c heap.h

heap.o: heap.c heap.h

valg:
	valgrind ./heap

clean:
	rm -f $(prog) $(objs)