package sample;

public class SongInfo extends UserInfo {

    int id, released;
    String name, duration;

    public SongInfo(int id, String name, int released, String duration) {

        super();
        this.id = id;
        this.released = released;
        this.name = name;
        this.duration = duration;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleased() {
        return released;
    }

    public void setOrigin(int released) {
        this.released = released;
    }
}


