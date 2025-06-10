package ait.timer.task;

import javax.sound.midi.Soundbank;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer implements Runnable{
    private int clockPeriod = 3;

    public int getClockPeriod() {
        return clockPeriod;
    }

    public void setClockPeriod(int clockPeriod) {
        this.clockPeriod = clockPeriod;
    }



    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            try {
                Thread.sleep(clockPeriod * 1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.interrupted());
                System.out.println();
                System.out.println(Thread.currentThread().getName() + " (aka Timer) say bye, bye");
                break;
            }
        }
    }
}
