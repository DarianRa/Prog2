import javax.lang.model.util.ElementScanner14;

public class Ringpuffer<T>{

    T[] array;  //Speicher mit den Elementen
    int size;   //Füllstand
    int start;  //Startindex
    int capacity;

    public Ringpuffer(int cap){ //konstruktor, legt das Array mit länge cap an, setzt Füllstand auf cap, setzt start auf 0, setzt Kapazität auf cap
        if(cap == 0)
            throw new RuntimeException("Cannot ");
        array = (T[])new Object[cap];
        size = 0;
        start = 0;
        capacity = cap;
    }

    public int size(){  //gibt die anzahl der Elemente wieder
        return size;
    }

    public T get(int pos){  //gibt den Inhalt des Elements mit position pos aus
        if(pos<0 || pos>size){
            throw new WrongInputException("Index does not exist");
        }
        return array[(start+pos-1)%capacity]; //gibt den Inhalt an der gewünschten Stelle aus
    }

    public T set(int pos,T e){  //ersetzt den Wert an der Stelle pos und gibt den alten wert zurück
        if(pos<0 || pos > size){
            throw new WrongInputException("Index does not exist");
        }
        T ret = array[(start+pos-1)%capacity];    //speichert den alten Wert in der variable ret
        array[(start+pos-1)%capacity] = e;    //ersetzt den alten mit dem neuen Wert
        return ret; //gibt den alten Wert aus
    }

    public void addFirst(T e){  //fügt e vorne ein
        if(size == capacity){ //kontrolliert ob der Ring voll ist
            throw new NoSpaceException("Cannot add an Element since there is no free Space");
        }
        if(start == 0)  //kontrolliert wo der Start ist
            start = capacity-1;   //setzt den Start ans Ende, falls dieser an erster Stelle war
        else
            start--;    //ansonsten setzt er den Start eine Stelle zurück
        array[start] = e;   //fügt das einzufügende Element an den Start
        size++;
    }

    public void addLast(T e){   //fügt e hinten ein
        if(size == capacity) //kontrolliert ob der Ring voll ist
            throw new NoSpaceException("Cannot add an Element since there is no free space");
        array[(start+size++)%capacity] = e;   //fügt das Element an letzter Stelle ein und erhöht size um 1
    }

    public T removeFirst(){ //entfernt das erste Element und returnt es
        T ret = array[start++]; //speichert das Element an erster Stelle und erhöht den Startindex
        start = start%capacity; //setzt start auf das nächste Element
        return ret; //gibt das gespeicherte Element aus
    }

    public T removeLast(){  //entfernt das letzte Element und returnt es
        return array[(start+--size)%capacity];
    }
}