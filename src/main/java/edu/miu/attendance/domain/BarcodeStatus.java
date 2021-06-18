package edu.miu.attendance.domain;

public enum BarcodeStatus {
    ABSCENT("Abscent"), PRESENT("Present");

    private String status;


    BarcodeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
