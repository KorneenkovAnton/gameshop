package com.epam.gameshop.entity;



import java.util.Date;
import java.util.List;

public class Game {
    private long id;
    private int cost;
    private Date releaseDate;
    private String description;
    private String name;
    private String developer;
   // private String posterLink;
    private Poster poster;
    private SystemRequirements minimalSystemRequirements;
    private SystemRequirements recommendedSystemRequirements;
    private List<Comment> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SystemRequirements getMinimalSystemRequirements() {
        return minimalSystemRequirements;
    }

    public void setMinimalSystemRequirements(SystemRequirements minimalSystemRequirements) {
        this.minimalSystemRequirements = minimalSystemRequirements;
    }

    public SystemRequirements getRecommendedSystemRequirements() {
        return recommendedSystemRequirements;
    }

    public void setRecommendedSystemRequirements(SystemRequirements recommendedSystemRequirements) {
        this.recommendedSystemRequirements = recommendedSystemRequirements;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Game(long id, String name, int cost, Date releaseDate, String description, SystemRequirements minimalSystemRequirements,
                SystemRequirements recommendedSystemRequirements, String developer) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.releaseDate = releaseDate;
        this.description = description;
        this.minimalSystemRequirements = minimalSystemRequirements;
        this.recommendedSystemRequirements = recommendedSystemRequirements;
        this.developer = developer;
    }

    public Game(long id) {
        this.id = id;
    }

    public Game() {
    }

    @Override
    public boolean equals(Object obj) {
        boolean answer = false;
        Game game = (Game) obj;
        if(this.name.equalsIgnoreCase(game.getName()))answer = true;
        return answer;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", cost=" + cost +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", minimalSystemRequirements=" + minimalSystemRequirements +
                ", recommendedSystemRequirements=" + recommendedSystemRequirements +
                '}';
    }
}
