
public class Studio{
    private String studioShortName;
    private String studioLongName;
    private int studioCurrentRevenue;
    private int studioPreviousRevenue;
    private int studioTotalRevenue;
    private final int LIMIT_STUDIOS = 10;
    public Studio(String shortName, String longName){
        studioShortName = shortName;
        studioLongName = longName;
    }

    public String getStudioShortName() {
        return studioShortName;
    }

    public void setStudioShortName(String studioShortName) {
        this.studioShortName = studioShortName;
    }

    public String getStudioLongName() {
        return studioLongName;
    }

    public void setStudioLongName(String studioLongName) {
        this.studioLongName = studioLongName;
    }

    public int getStudioCurrentRevenue() {
        return studioCurrentRevenue;
    }

    public void setStudioCurrentRevenue(int studioCurrentRevenue) {
        this.studioCurrentRevenue = studioCurrentRevenue;
    }

    public int getStudioPreviousRevenue() {
        return studioPreviousRevenue;
    }

    public void setStudioPreviousRevenue(int studioPreviousRevenue) {
        this.studioPreviousRevenue = studioPreviousRevenue;
    }

    public int getStudioTotalRevenue() {
        return studioTotalRevenue;
    }

    public void setStudioTotalRevenue(int studioTotalRevenue) {
        this.studioTotalRevenue = studioTotalRevenue;
    }

    public void display_studio() {
        System.out.println("studio" + "," + studioShortName + "," + studioLongName);
        System.out.println("current_period" + "," + studioCurrentRevenue);
        System.out.println("previous_period" + "," + studioPreviousRevenue);
        System.out.println("total" + "," + studioTotalRevenue);

    }
    public void updateStudioCurrentRevenue(int payLicenseFee){
        studioCurrentRevenue += payLicenseFee;
    }
    public void updateAllRevenue(){
        this.studioTotalRevenue += this.studioCurrentRevenue;
        this.studioPreviousRevenue = this.studioCurrentRevenue;
        this.studioCurrentRevenue = 0;
    }


}