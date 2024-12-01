package UseCases;

public class House {
    private String walls;
    private String roof;
    private String windows;
    private String door;
    private String pool;

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return "House [walls=" + walls + ", roof=" + roof + ", windows=" + windows +
                ", door=" + door + ", pool=" + (pool != null ? pool : "None") + "]";
    }
}
