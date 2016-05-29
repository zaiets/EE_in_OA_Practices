package Practice01.model;

public class Audio {
    private int id;
    private String title;
    private int year;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audio audio = (Audio) o;

        if (getId() != audio.getId()) return false;
        if (getYear() != audio.getYear()) return false;
        if (getDuration() != audio.getDuration()) return false;
        return getTitle() != null ? getTitle().equals(audio.getTitle()) : audio.getTitle() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + getYear();
        result = 31 * result + getDuration();
        return result;
    }
}
