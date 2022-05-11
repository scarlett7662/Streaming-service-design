import java.util.Scanner;

public class TestCaseReader {

    private int numDemos;
    private Demographic_Group demoList[];
    private final int LIMIT_DEMOS = 10;

    private int numStudios;
    private Studio studioList[];
    private final int LIMIT_STUDIOS = 10;

    private int numEvents;
    private Event eventList[];
    private final int LIMIT_EVENTS = 20;

    private int numStreams;
    private Streaming_Service streamingList[];
    private final int LIMIT_STREAMS = 10;

    private int numOffers;
    private String offerStream[];
    private String offerType[];
    private String offerEventName[];
    private int offerEventYear[];
    private int offerEventPrice[];
    private final int LIMIT_OFFERS = LIMIT_EVENTS * LIMIT_STREAMS;

    private int watchGroupStreams[][];

    private int monthTimeStamp;
    private int yearTimeStamp;

    public TestCaseReader() {
        numDemos = 0;
        demoList = new Demographic_Group[LIMIT_DEMOS];

        numStudios = 0;
        studioList = new Studio[LIMIT_STUDIOS];

        numEvents = 0;
        eventList = new Event[LIMIT_EVENTS];

        numStreams = 0;
        streamingList = new Streaming_Service[LIMIT_STREAMS];

        numOffers = 0;
        offerStream = new String[LIMIT_OFFERS];
        offerType = new String[LIMIT_OFFERS];
        offerEventName = new String[LIMIT_OFFERS];
        offerEventYear = new int[LIMIT_OFFERS];
        offerEventPrice = new int[LIMIT_OFFERS];

        watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];

        monthTimeStamp = 10;
        yearTimeStamp = 2020;
    }

    public void processInstructions(Boolean verboseMode) {
        int selectDemo, selectEvent, selectStudio, selectStream, selectOffer;
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("create_demo")) {
                    if (verboseMode) { System.out.println("create_demo_acknowledged"); }
                    if (numDemos >= LIMIT_DEMOS) { continue; }
                    Demographic_Group demo = new Demographic_Group(tokens[1],tokens[2],Integer.parseInt(tokens[3]));
                    demoList[numDemos] =  demo;
                    numDemos++;

                } else if (tokens[0].equals("create_studio")) {
                    if (verboseMode) { System.out.println("create_studio_acknowledged"); }
                    if (numStudios >= LIMIT_STUDIOS) { continue; }

                    Studio studio = new Studio(tokens[1],tokens[2]);
                    studioList[numStudios] = studio;
                    numStudios++;

                } else if (tokens[0].equals("create_event")) {
                    if (verboseMode) { System.out.println("create_event_acknowledged"); }
                    if (numEvents >= LIMIT_EVENTS) { continue; }
                    Event event = new Event(tokens[1],tokens[2], Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]), tokens[5],Integer.parseInt(tokens[6]));
                    eventList[numEvents] = event;
                    numEvents++;

                } else if (tokens[0].equals("create_stream")) {
                    if (verboseMode) { System.out.println("create_stream_acknowledged"); }
                    if (numStreams >= LIMIT_STREAMS) { continue; }
                    Streaming_Service ss = new Streaming_Service(tokens[1],tokens[2], Integer.parseInt(tokens[3]));
                    streamingList[numStreams] = ss;
                    numStreams++;

                } else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
                    if (verboseMode) { System.out.println(tokens[0] + "_acknowledged"); }
                    if (numOffers >= LIMIT_OFFERS) { continue; }
                    offerType[numOffers] = tokens[0].substring(6);
                    
                    offerStream[numOffers] = tokens[1];
                    offerEventName[numOffers] = tokens[2];
                    offerEventYear[numOffers] = Integer.parseInt(tokens[3]);
                    if (offerType[numOffers].equals("ppv")) {
                        offerEventPrice[numOffers] = Integer.parseInt(tokens[4]);
                    }
                    numOffers++;

                    // The streaming service must license the event from the studio
                    String payStudio = "";
                    int payLicenseFee = 0;
                    for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
                        if (eventList[selectEvent].getEventFullName().equals(tokens[2]) && eventList[selectEvent].getEventYear() == Integer.parseInt(tokens[3])) {
                            payStudio = eventList[selectEvent].getEventStudioOwner();//which studio is ss paying
                            payLicenseFee = eventList[selectEvent].getEventLicenseFee();//how much
                        }
                    }

                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        if (streamingList[selectStream].getStreamShortName().equals(tokens[1])) {
                            streamingList[selectStream].updateStreamLicensing(payLicenseFee);
                        }
                    }

                    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
                        if (studioList[selectStudio].getStudioShortName().equals(payStudio)) {
                            studioList[selectStudio].updateStudioCurrentRevenue(payLicenseFee);
                        }
                    }

                } else if (tokens[0].equals("watch_event")) {
                    if (verboseMode) { System.out.println("watch_event_acknowledged"); }
                    String watchDemoGroup = tokens[1];
                    int watchPercentage = Integer.parseInt(tokens[2]);
                    String watchStream = tokens[3];
                    String watchEventName = tokens[4];
                    int watchEventYear = Integer.parseInt(tokens[5]);

                    // Identify the demo group & the number of viewers affected
                    int watchViewerCount = 0;
                    int demoIndex = -1;
                    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
                        if (demoList[selectDemo].getDemoShortName().equals(watchDemoGroup)) {
                            watchViewerCount = (demoList[selectDemo].getDemoAccounts()) * watchPercentage / 100;
                            demoIndex = selectDemo;
                        }
                    }
                    // Identify the streaming service & the subscription fee
                    int watchSubscriptionFee = 0;
                    int streamIndex = -1;
                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        if (streamingList[selectStream].getStreamShortName().equals(watchStream)) {
                            watchSubscriptionFee = streamingList[selectStream].getStreamSubscription();
                            streamIndex = selectStream;
                        }
                    }
                    // Identify the event selected & the Pay-Per-View price
                    // For all events: determine event type
                    String watchType = "";
                    Event e = null;
                    int watchPayPerViewPrice = 0;
                    int offerIndex = -1;
                  for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                        if (offerStream[selectOffer].equals(watchStream) && offerEventName[selectOffer].equals(watchEventName) && offerEventYear[selectOffer] == watchEventYear) {
                            watchType = offerType[selectOffer];
                            watchPayPerViewPrice = offerEventPrice[selectOffer];
                            offerIndex = selectOffer;
                        }
                    }
                    int watchViewingCost = 0;
                    if (watchType.equals("movie")) {
                        // For movies: identify the increased percentage of new customers and subscription fee
                        if (watchViewerCount > watchGroupStreams[demoIndex][streamIndex]) {
                            watchViewingCost = (watchViewerCount - watchGroupStreams[demoIndex][streamIndex]) * watchSubscriptionFee;
                            watchGroupStreams[demoIndex][streamIndex] = watchViewerCount;
                        }
                    } else if (watchType.equals("ppv")) {
                        // For Pay-Per-Views: identify the demo viewers affected and event price
                        watchViewingCost = watchViewerCount * watchPayPerViewPrice;
                    }
                    // Adjust funds for watching events
                    demoList[demoIndex].updateDemoCurrentSpending(watchViewingCost);
                    streamingList[streamIndex].updateStreamCurrentRevenue(watchViewingCost);

                } else if (tokens[0].equals("next_month")) {
                    if (verboseMode) { System.out.println("next_month_acknowledged"); }

                    // Update the current timestamp
                    if (monthTimeStamp == 12) { yearTimeStamp++; }
                    monthTimeStamp = (monthTimeStamp % 12) + 1;

                    // Update current, previous and total dollar amounts
                    for (selectDemo = 0; selectDemo < numDemos; selectDemo++) {
                        demoList[selectDemo].updateAllSpending();
                    }
                    for (selectStream = 0; selectStream < numStreams; selectStream++) {
                        streamingList[selectStream].updateAllRevenue();
                    }
                    for (selectStudio = 0; selectStudio < numStudios; selectStudio++) {
                        studioList[selectStudio].updateAllRevenue();
                    }
                    // Remove all movie and Pay-Per-View offerings
                    numOffers = 0;
                    offerStream = new String[LIMIT_OFFERS];
                    offerType = new String[LIMIT_OFFERS];
                    offerEventName = new String[LIMIT_OFFERS];
                    offerEventYear = new int[LIMIT_OFFERS];
                    offerEventPrice = new int[LIMIT_OFFERS];

                    // Reset the subscription counts for the month
                    watchGroupStreams = new int[LIMIT_DEMOS][LIMIT_STREAMS];

                } else if (tokens[0].equals("display_demo")) {
                    if (verboseMode) { System.out.println("display_demo_acknowledged"); }
                    selectDemo = -1;
                    for (int findItem = 0; findItem < numDemos; findItem++) {
                        if (demoList[findItem].getDemoShortName().equals(tokens[1])) {
                            selectDemo = findItem;
                        }
                    }
                    if (selectDemo < 0) { continue; }
                    demoList[selectDemo].display_demo();


                } else if (tokens[0].equals("display_events")) {
                    if (verboseMode) { System.out.println("display_events_acknowledged"); }
                    for (selectEvent = 0; selectEvent < numEvents; selectEvent++) {
                        eventList[selectEvent].display_singleEvent();
                    }

                } else if (tokens[0].equals("display_stream")) {
                    if (verboseMode) { System.out.println("display_stream_acknowledged"); }
                    selectStream = -1;
                    for (int findItem = 0; findItem < numStreams; findItem++) {
                        if (streamingList[findItem].getStreamShortName().equals(tokens[1])) {
                            selectStream = findItem;
                        }
                    }
                    if (selectStream < 0) { continue; }
                    streamingList[selectStream].display_streaming();
                    //System.out.println("!!!!STREAMING:"+ streamingList[selectStream].getStreamShortName());

                } else if (tokens[0].equals("display_studio")) {
                    if (verboseMode) { System.out.println("display_studio_acknowledged"); }
                    selectStudio = -1;
                    for (int findItem = 0; findItem < numStudios; findItem++) {
                        if (studioList[findItem].getStudioShortName().equals(tokens[1])) {
                            selectStudio = findItem;
                        }
                    }
                    if (selectStudio < 0) { continue; }
                        studioList[selectStudio].display_studio();

                } else if (tokens[0].equals("display_offers")) {
                    if (verboseMode) { System.out.println("display_offers_acknowledged"); }
                    for (selectOffer = 0; selectOffer < numOffers; selectOffer++) {
                        System.out.print(offerStream[selectOffer] + "," + offerType[selectOffer] + "," + offerEventName[selectOffer] + "," + offerEventYear[selectOffer]);
                        if (offerType[selectOffer].equals("ppv")) {
                            System.out.print("," + offerEventPrice[selectOffer]);
                        }
                        System.out.println();
                    }

                } else if (tokens[0].equals("display_time")) {
                    if (verboseMode) { System.out.println("display_time_acknowledged"); }
                    System.out.println("time," + monthTimeStamp + "," + yearTimeStamp);

                } else if (tokens[0].equals("stop")) {
                    break;
                } else {
                    if (verboseMode) { System.out.println("command_" + tokens[0] + "_NOT_acknowledged"); }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        if (verboseMode) { System.out.println("stop_acknowledged"); }
        commandLineInput.close();
    }
}
