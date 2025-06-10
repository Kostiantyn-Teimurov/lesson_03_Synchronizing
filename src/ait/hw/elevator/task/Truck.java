package ait.hw.elevator.task;

import ait.hw.elevator.model.Elevator;

public class Truck implements Runnable {
    private static final Object monitor = new Object();
    private static final Object monitor2 = new Object();
    private int nRaces;
    private int capacity;
    private Elevator elevator;
    private Elevator elevator2;

    public Truck(int nRaces, int capacity, Elevator elevator, Elevator elevator2) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
        this.elevator2 = elevator2;
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
                elevator.add(capacity / 2);
            }

            synchronized (monitor2) {
                elevator2.add(capacity / 2);
            }
        }
    }
}
