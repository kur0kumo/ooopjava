package MaxFrolov_RPIS82;

public class Service {
    private String name;
    private int cost;

    public Service() {
        this.name = "интернет 100мб\\сек";
        this.cost = 300;
    }

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
