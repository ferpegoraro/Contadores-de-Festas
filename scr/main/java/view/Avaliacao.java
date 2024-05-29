package entity;

public class Avaliacao {
    private String buffetName;
    private String clientName;
    private int rating;

    public String getBuffetName() {
        return buffetName;
    }

    public void setBuffetName(String buffetName) {
        this.buffetName = buffetName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "buffetName='" + buffetName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
