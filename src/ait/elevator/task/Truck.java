package ait.elevator.task;

import ait.elevator.model.Elevator;

import java.util.Objects;

public class Truck implements Runnable{
    private static final Object monitor = new Object();
    private int nRaces;
    private int capacity;
    private Elevator elevator;

    public Truck(int nRaces, int capacity, Elevator elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
    }

    @Override
    public void run() {
//        synchronized (elevator) {} - так будет работать, но убивает многопоточку. Все ждут пока один тред выполнит работу.
        for (int i = 0; i < nRaces; i++) {
//            synchronized (this) - водитель спрашивает сам себя - НЕ работает,
//            synchronized ("hello") - мы даем любому объекту ключ мутекс - и спрашиваем - она мутекс или нет? - РАБОТАЕТ
//            synchronized (new String("hello")) - НЕ работает, каждый раз создается новый объект
//            synchronized (Truck.class) - рефлексия. РАБОТАЕТ как охранник.
//            synchronized ("mutex") - НЕЛЬЗЯ создавать одинаковые мутекс-ключи. Здесь он отвечает за поезда, там за корабли. Поэтому лучше ссылаться на конкретный объект
//            synchronized (elevator) - ПРОБЛЕМА: геттеры и сеттеры.
            synchronized (monitor) {
                elevator.add(capacity);
            }
        }
    }
}
