package sample;

public class ArtistInfo extends UserInfo {

    int id, active;
    String name, origin;

    //Definovanie očakávaných dát
    public ArtistInfo(int id, String name, int active, String origin) {
        super();
        this.id = id;
        this.active = active;
        this.name = name;
        this.origin = origin;

    }

    //Nastavenie getterov a setterov
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}


