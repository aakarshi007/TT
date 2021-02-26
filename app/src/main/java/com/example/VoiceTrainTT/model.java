package com.example.VoiceTrainTT;

public class model {
    private String Destination,Source,Time,TrainNo,Type;

    model() {

    }

    public model(String destination, String source, String time, String trainNo, String type) {
        Destination = destination;
        Source = source;
        Time = time;
        TrainNo = trainNo;
        Type = type;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTrainNo() {
        return TrainNo;
    }

    public void setTrainNo(String trainNo) {
        TrainNo = trainNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
