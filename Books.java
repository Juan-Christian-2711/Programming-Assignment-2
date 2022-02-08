public class Books {
    private int BookID;
    private String Title;
    private int pages;
    private int Genre;
    private boolean Non-Fiction;
    public int getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public int getPages() {
        return pages;
    }

    public int getGenre() {
        return Genre;
    }

    public boolean getIsNon() {
        return Non;
    }

    public void setBookID(int bookID) {
        this.BookID = bookID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setGenre(int genre) {
        Genre = genre;
    }

    public void setNon(boolean non) {
        Non = non;
    }
}