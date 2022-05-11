
public class Streaming_Service{
    private int numStreams;
    private String streamShortName;
    private String streamLongName;
    private int streamSubscription;
    private int streamCurrentRevenue;
    private int streamPreviousRevenue;
    private int streamTotalRevenue;
    private int streamLicensing;
//    private ArrayList<String> licensedEvent;
    private final int LIMIT_STREAMS = 10;
    public Streaming_Service(String shortName, String longName,int subscriptionPrice){
        streamShortName = shortName;
        streamLongName = longName;
        streamSubscription = subscriptionPrice;
        //licensedEvent = new ArrayList<String>();
    }

    public int getNumStreams() {
        return numStreams;
    }

    public void setNumStreams(int numStreams) {
        this.numStreams = numStreams;
    }

    public String getStreamShortName() {
        return streamShortName;
    }

    public void setStreamShortName(String streamShortName) {
        this.streamShortName = streamShortName;
    }

    public String getStreamLongName() {
        return streamLongName;
    }

    public void setStreamLongName(String streamLongName) {
        this.streamLongName = streamLongName;
    }

    public int getStreamSubscription() {
        return streamSubscription;
    }

    public void setStreamSubscription(int streamSubscription) {
        this.streamSubscription = streamSubscription;
    }

    public int getStreamCurrentRevenue() {
        return streamCurrentRevenue;
    }

    public void setStreamCurrentRevenue(int streamCurrentRevenue) {
        this.streamCurrentRevenue = streamCurrentRevenue;
    }

    public int getStreamPreviousRevenue() {
        return streamPreviousRevenue;
    }

    public void setStreamPreviousRevenue(int streamPreviousRevenue) {
        this.streamPreviousRevenue = streamPreviousRevenue;
    }

    public int getStreamTotalRevenue() {
        return streamTotalRevenue;
    }

    public void setStreamTotalRevenue(int streamTotalRevenue) {
        this.streamTotalRevenue = streamTotalRevenue;
    }

    public int getStreamLicensing() {
        return streamLicensing;
    }

    public void setStreamLicensing(int streamLicensing) {
        this.streamLicensing = streamLicensing;
    }
    public void display_streaming() {
        System.out.println("stream" + "," + streamShortName + "," + streamLongName);
        System.out.println("subscription" + "," +streamSubscription );
        System.out.println("current_period" + "," + streamCurrentRevenue);
        System.out.println("previous_period" + "," + streamPreviousRevenue);
        System.out.println("total" + "," + streamTotalRevenue);
        System.out.println("licensing" + "," +streamLicensing );

    }
    public void updateStreamCurrentRevenue(int subscription){
        streamCurrentRevenue += subscription;
    }
    public void updateStreamLicensing(int payLicenseFee){
        streamLicensing += payLicenseFee;
    }
    public void updateAllRevenue(){
        this.streamTotalRevenue += this.streamCurrentRevenue;
        this.streamPreviousRevenue = this.streamCurrentRevenue;
        this.streamCurrentRevenue = 0;
    }

}
