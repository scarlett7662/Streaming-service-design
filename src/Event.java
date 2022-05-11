
public class Event{
    private int numEvents;

    private String eventType;
    private String eventFullName;
    private String eventStudioOwner;
    private int eventYear;
    private int eventDuration;
    private int eventLicenseFee;
    private final int LIMIT_EVENTS = 20;
    public Event(String type,String name,int yearProduced,int duration,String studio, int licenseFee){
        eventType = type;
        eventFullName = name;
        eventYear = yearProduced;
        eventDuration = duration;
        eventStudioOwner = studio;
        eventLicenseFee = licenseFee;
        numGenre = 0;
    }
    public int getNumEvents(){
        return numEvents;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventFullName() {
        return eventFullName;
    }

    public String getEventStudioOwner() {
        return eventStudioOwner;
    }

    public int getEventYear() {
        return eventYear;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public int getEventLicenseFee() {
        return eventLicenseFee;
    }

    public void display_singleEvent() {
        System.out.println(eventType + "," + eventFullName+ "," +eventYear + "," + eventDuration+ "," + eventStudioOwner + "," + eventLicenseFee);
    }
}
