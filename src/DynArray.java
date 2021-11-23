public class DynArray<T> {
    
    private T[] array;  //Array mit den gespeicherten Werten
    private int size;   //Anzahl der Elemente die gerade im Array gespeichert werden
    

    public DynArray(){
        array = (T[]) new Object[1];
        size = 0;
    }

    public int size(){  //gibt den aktuellen Füllstand aus
        return size;    
    }

    public int capacity(){  //gibt die aktuelle maximalgröße aus
        return array.length;
    }

    public T get(int pos){  //Inhalt an der Stelle pos soll ausgegeben werden
        if(pos> size || pos < 0){   //falls an der Stelle pos kein Element ist wird eine Exception geworfen
            throw new WrongInputException("Index incorrect");
        }
        return array[pos];  //gibt den Wert an der Stelle pos aus
    }

    public T set(int pos, T e){ //Element an der Stelle pos soll durch e ersetzt werden und der alte Wert soll ausgegeben werden
        if(pos> size || pos <0){    //falls an der Stelle pos kein Element ist wird eine Exception geworfen
            throw new WrongInputException("Index incorrect");
        }
        T ret = array[pos]; //das auszugebene Element wird in der variable ret zwischengespeichert
        array[pos] = e; //das neue Element e ersetzt das alte an der Stelle pos
        return ret; //das auszugebene Element wird ausgegeben
    }

    public void addFirst(T e){  //verschiebt alle Elemente um  1 nach hinten und fügt e vorne ein. Vergrößert evtl. das Array
        refresh();  //kontrolliert ob das Array groß genug ist um ein Element einzufügen
        for(int i=0;i<size;i++){    //verschiebt alle Element eins nach hinten von hinten ausgehend
            array[size-i] = array[size-i-1];
        }
        size++; //erhöht aktuelle Füllstand um 1
        array[0] = e;   //setzt e vorne ein
    }

    public void addLast(T e){   //Fügt Inhalt an letzter Stelle ein. vergrößert evtl. das Array
        refresh();  //kontrolliert ob das Array groß genug ist um eine Element einzufügen
        array[size++] = e;  //fügt das Element ein und erhöht den aktuellen Füllstand
    }

    public T removeFirst(){ //entfernt das Element an erster Stelle und gibt seinen Inhalt aus
        T ret = array[0];   //speichert das auszugeben Element
        for(int i=0;i<size-1;i++){  //verschiebt alle Elemente um eins nach unten. Notiz: das Element an letzter Stelle existiert nun doppelt
            array[i] = array[i+1];
        }
        array[size-1] = null;   //entfernt die Kopie des letzten Elements
        size--; //reduziert den Füllstand um 1
        refresh();  //verkleinert ggf. das Array
        return ret; //gibt das auszugebene Element aus
    }

    public T removeLast(){  //Löscht das letzte Element und gibt seinen Inhalt aus
        T ret = array[--size];  //speichert das auszugebene Element in die variable ret
        array[size] = null; //entfernt das Element an letzter Stelle
        return ret;   //gibt das gespeichert Element aus
    }

    private void refresh(){ //Hilfsmethode: kontrolliert ob das Array zu voll oder zu leer ist und vergroeßert oder verkleinert es entsprechend
        if(size == capacity()){ //1. Fall Array ist zu voll
            T[] array2 = (T[]) new Object[capacity()*2];    //erstellt ein Hilfsarray mit doppelter Größe
            for(int i=0;i<size;i++){    //füllt die Werte in das neue Array
                array2[i] = array[i];
            }
            array = array2; //Hilfsarray ersetzt altes Array
        }
        if(size*4 <= capacity() ){ //2.Fall Array ist zu leer
            T[] array2 = (T[]) new Object[capacity()*2];    //erstellt ein Hilfsarray mit doppelter Größe
            for(int i=0;i<size;i++){    //füllt die Wert in das Hilfsarray
                array2[i] = array[i];
            }
            array = array2; //Hilfsarray ersetzt altes Array
        }
    }



}
