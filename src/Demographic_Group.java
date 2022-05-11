
public class Demographic_Group {
    private String demoShortName;
    private String demoLongName;
    private int demoAccounts;
    private int demoCurrentSpending;
    private int demoPreviousSpending;
    private int demoTotalSpending;


    public String getDemoShortName() {
        return demoShortName;
    }

    public String getDemoLongName() {
        return demoLongName;
    }

    public int getDemoAccounts() {
        return demoAccounts;
    }

    public int getDemoCurrentSpending() {
        return demoCurrentSpending;
    }

    public int getDemoPreviousSpending() {
        return demoPreviousSpending;
    }

    public int getDemoTotalSpending() {
        return demoTotalSpending;
    }

    public void setDemoShortName(String demoShortName) {
        this.demoShortName = demoShortName;
    }

    public void setDemoLongName(String demoLongName) {
        this.demoLongName = demoLongName;
    }

    public void setDemoAccounts(int demoAccounts) {
        this.demoAccounts = demoAccounts;
    }

    public void setDemoCurrentSpending(int demoCurrentSpending) {
        this.demoCurrentSpending = demoCurrentSpending;
    }

    public void setDemoPreviousSpending(int demoPreviousSpending) {
        this.demoPreviousSpending = demoPreviousSpending;
    }

    public void setDemoTotalSpending(int demoTotalSpending) {
        this.demoTotalSpending = demoTotalSpending;
    }

    public Demographic_Group(String shortName, String longName, int numOfAccount){
        demoShortName = shortName;
        demoLongName = longName;
        demoAccounts = numOfAccount;
        }

        public void display_demo(){
        System.out.println("demo," + demoShortName+","+ demoLongName);
        System.out.println("size,"+demoAccounts);
        System.out.println("current_period,"+demoCurrentSpending);
        System.out.println("previous_period,"+demoPreviousSpending);
        System.out.println("total,"+demoTotalSpending);
        }
        public void updateDemoCurrentSpending(int watchViewingCost){
            demoCurrentSpending += watchViewingCost;
        }

        public void updateAllSpending(){
            demoTotalSpending += demoCurrentSpending;
            demoPreviousSpending = demoCurrentSpending;
            demoCurrentSpending = 0;
        }

}