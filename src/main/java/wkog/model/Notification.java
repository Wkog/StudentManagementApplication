package wkog.model;

import java.util.Date;

public class Notification {
    private String content;
    private Date initializationDate;
    private Date expirationDate;
    private String author;
    private Date ModifiedDate;

    public Notification() {
    }

    public Notification(String content, Date initializationDate, Date expirationDate, String author, Date modifiedDate) {
        this.content = content;
        this.initializationDate = initializationDate;
        this.expirationDate = expirationDate;
        this.author = author;
        ModifiedDate = modifiedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

    public void setInitializationDate(Date initializationDate) {
        this.initializationDate = initializationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        ModifiedDate = modifiedDate;
    }
}
