package ait.hw.elevator;

import ait.hw.elevator.model.Elevator;
import ait.hw.elevator.task.Truck;

import java.time.LocalTime;

public class ElevatorAppl {
    private static final int N_TRUCK = 10_000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        Elevator elevator = new Elevator("V. I. Lenin");
        Elevator elevator2 = new Elevator("V. I. Lenin 2");
        Truck[] trucks = new Truck[N_TRUCK];
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, elevator, elevator2);
        }
        System.out.println(LocalTime.now());
        Thread[] threads = new Thread[trucks.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(LocalTime.now());
        System.out.println("Elevator " + elevator.getName() + " has " + elevator.getCurrentVolume());
        System.out.println("Elevator " + elevator2.getName() + " has " + elevator2.getCurrentVolume());
    }
}
