package Objects;

public class book {
    private double price;
    private String name="";
    private double rating;
    private String writer="";
    private boolean isAudioBook;
    private boolean isRead;
    private int numberOfPages;
    private boolean like;
    private boolean buy;
    private int sells;
    private String image;
    private int numberoflikes;
    private boolean newrelease;

    public boolean isNewrelease() {
        return newrelease;
    }

    public void setNewrelease(boolean newrelease) {
        this.newrelease = newrelease;
    }

    public int getNumberoflikes() {
        return numberoflikes;
    }

    public void setNumberoflikes(int numberoflikes) {
        this.numberoflikes = numberoflikes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSells() {
        return sells;
    }

    public void setSells(int sells) {
        this.sells = sells;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public book() {

    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getWriter() {
        return writer;
    }

    public boolean isAudioBook() {
        return isAudioBook;
    }

    public boolean isRead() {
        return isRead;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public boolean isLike() {
        return like;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAudioBook(boolean audioBook) {
        isAudioBook = audioBook;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}
