package pl.edu.agh.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String basicInformation;
    private String url;
    private String readBooksUrl;
    private String currentlyReadingBooksUrl;
    private String wantToReadBooksUrl;
    private Integer numOfReadBooks;
    private Integer numOfCurrentlyReadingBooks;
    private Integer numOfWantToReadBooks;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "read",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "bookId")})
    private Set<Book> readBooks = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "reading",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "bookId")})
    private Set<Book> currentlyReadingBooks = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "wantToRead",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "bookId")})
    private Set<Book> wantToReadBooks = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "friendId")})
    private Set<User> friends = new HashSet<>();

    @ManyToMany(mappedBy = "friends")
    private Set<User> mappedFriends = new HashSet<>();

    public User() {
    }

    public User(String name, String description, String basicInformation, String url, String readBooksUrl, String currentlyReadingBooksUrl, String wantToReadBooksUrl, Integer numOfReadBooks, Integer numOfCurrentlyReadingBooks, Integer numOfWantToReadBooks) {
        this.name = name;
        this.description = description;
        this.basicInformation = basicInformation;
        this.url = url;
        this.readBooksUrl = readBooksUrl;
        this.currentlyReadingBooksUrl = currentlyReadingBooksUrl;
        this.wantToReadBooksUrl = wantToReadBooksUrl;
        this.numOfReadBooks = numOfReadBooks;
        this.numOfCurrentlyReadingBooks = numOfCurrentlyReadingBooks;
        this.numOfWantToReadBooks = numOfWantToReadBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(String basicInformation) {
        this.basicInformation = basicInformation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReadBooksUrl() {
        return readBooksUrl;
    }

    public void setReadBooksUrl(String readBooksUrl) {
        this.readBooksUrl = readBooksUrl;
    }

    public String getCurrentlyReadingBooksUrl() {
        return currentlyReadingBooksUrl;
    }

    public void setCurrentlyReadingBooksUrl(String currentlyReadingBooksUrl) {
        this.currentlyReadingBooksUrl = currentlyReadingBooksUrl;
    }

    public String getWantToReadBooksUrl() {
        return wantToReadBooksUrl;
    }

    public void setWantToReadBooksUrl(String wantToReadBooksUrl) {
        this.wantToReadBooksUrl = wantToReadBooksUrl;
    }

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public Set<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public void setCurrentlyReadingBooks(Set<Book> currentlyReadingBooks) {
        this.currentlyReadingBooks = currentlyReadingBooks;
    }

    public Set<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public void setWantToReadBooks(Set<Book> wantToReadBooks) {
        this.wantToReadBooks = wantToReadBooks;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getMappedFriends() {
        return mappedFriends;
    }

    public void setMappedFriends(Set<User> mappedFriends) {
        this.mappedFriends = mappedFriends;
    }

    public Integer getNumOfReadBooks() {
        return numOfReadBooks;
    }

    public void setNumOfReadBooks(Integer numOfReadBooks) {
        this.numOfReadBooks = numOfReadBooks;
    }

    public Integer getNumOfCurrentlyReadingBooks() {
        return numOfCurrentlyReadingBooks;
    }

    public void setNumOfCurrentlyReadingBooks(Integer numOfCurrentlyReadingBooks) {
        this.numOfCurrentlyReadingBooks = numOfCurrentlyReadingBooks;
    }

    public Integer getNumOfWantToReadBooks() {
        return numOfWantToReadBooks;
    }

    public void setNumOfWantToReadBooks(Integer numOfWantToReadBooks) {
        this.numOfWantToReadBooks = numOfWantToReadBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basicInformation='" + basicInformation + '\'' +
                ", url='" + url + '\'' +
                ", readBooksUrl='" + readBooksUrl + '\'' +
                ", currentlyReadingBooksUrl='" + currentlyReadingBooksUrl + '\'' +
                ", wantToReadBooksUrl='" + wantToReadBooksUrl + '\'' +
                ", numOfReadBooks=" + numOfReadBooks +
                ", numOfCurrentlyReadingBooks=" + numOfCurrentlyReadingBooks +
                ", numOfWantToReadBooks=" + numOfWantToReadBooks +
                ", readBooks=" + readBooks +
                ", currentlyReadingBooks=" + currentlyReadingBooks +
                ", wantToReadBooks=" + wantToReadBooks +
                '}';
    }
}
