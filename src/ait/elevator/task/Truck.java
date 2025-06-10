package ait.elevator.task;

import ait.elevator.model.Elevator;

public class Truck implements Runnable{
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
            synchronized (elevator) {
                elevator.add(capacity);
            }
        }
    }
}
