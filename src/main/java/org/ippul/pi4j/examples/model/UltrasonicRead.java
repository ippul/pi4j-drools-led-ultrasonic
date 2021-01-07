package org.ippul.pi4j.examples.model;

public class UltrasonicRead {

    private long startTime;

    private long endTime;

    public UltrasonicRead(long startTime) {
        this.startTime = startTime;
    }

    public UltrasonicRead(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
