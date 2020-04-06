package apch.marvelbasket.bean;

public class Event {
    int eventId;
    String eventName;
    String eventDate;
    int userId;

    public Event() {
        //Do nothing
    }

    public Event(int eventId, String eventName, String eventDate, int userId) {
        super();
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
