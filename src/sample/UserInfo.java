package sample;

public class UserInfo {

    int id;
    String fgenre, fartist, fsong;

    public UserInfo(int id, String fgenre, String fartist, String fsong) {
        this.id = id;
        this.fgenre = fgenre;
        this.fartist = fartist;
        this.fsong = fsong;
    }

    public UserInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFgenre() {
        return fgenre;
    }

    public void setFgenre(String fartist) {
        this.fgenre = fgenre;
    }

    public String getFartist() {
        return fartist;
    }

    public void setFartist(String fartist) {
        this.fartist = fartist;
    }

    public String getFsong() {
        return fsong;
    }

    public void setFsong(String fsong) {
        this.fsong = fsong;
    }

}


