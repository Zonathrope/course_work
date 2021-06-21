package app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tender {
    private String tenderName;
    private String description;
    private int tenderId;
    private ArrayList<String> tenderTag = new ArrayList<>();
    private List<Comment> commentsOfTheTender;
    private User tenderOwner;
    private boolean online = true;

    public Tender(String tenderName, String description) {
        this.tenderName = tenderName;
        this.description = description;
        commentsOfTheTender = new ArrayList<>();
    }
    public boolean getOnline(){ return this.online;}

    public void setOnline() {this.online = true;}

    public void setOffline() {this.online = false;}

    public List<Comment> getTenderComments() {
        return commentsOfTheTender;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderOwner(User owner) {
        this.tenderOwner = owner;
    }

    public User getTenderOwner() {
        return tenderOwner;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "name='" + tenderName + '\'' +
                ", description='" + description + '\'' +
                 commentsOfTheTender + '}' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tender tender = (Tender) o;

        if (!Objects.equals(tenderName, tender.tenderName)) return false;
        return Objects.equals(description, tender.description);

    }

    @Override
    public int hashCode() {
        int result = tenderName != null ? tenderName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public ArrayList<String> getTenderTags() {
        return tenderTag;
    }

    public void addTenderTag(String tenderTag) {
        this.tenderTag.add(tenderTag);
    }
}