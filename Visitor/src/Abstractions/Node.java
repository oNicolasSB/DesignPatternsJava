package Abstractions;

public abstract class Node {
    private Double latitude;
    private Double longitude;

    public Node(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public abstract void accept(Visitor visitor);
}
