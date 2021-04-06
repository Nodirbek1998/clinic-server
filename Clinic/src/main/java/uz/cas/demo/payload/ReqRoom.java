package uz.cas.demo.payload;

public class ReqRoom {

    private String floor;

    private String number;

    public ReqRoom() {
    }

    public ReqRoom(String floor, String number) {
        this.floor = floor;
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

