package MaxFrolov_RPIS82;

public final class Service {
    private final String name;
    private final int cost;
    ServiceTypes serviceType;

    public Service() {
        this.name = "интернет 100мб\\сек";
        this.cost = 300;
        serviceType=ServiceTypes.INTERNET;
    }

    public Service(String name, int cost, ServiceTypes serviceType) {
        this.name = name;
        this.cost = cost;
        this.serviceType=serviceType;
    }

    public String getName() {
        return this.name;
    }


    public int getCost() {
        return this.cost;
    }

    public ServiceTypes getServiceType() {
        return serviceType;
    }

    @Override
    public String toString() {
        return String.format("%-40s\\\\%dp.",name,cost);
    }

    @Override
    public int hashCode() {
        return cost*name.hashCode()*serviceType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Service service= (Service) obj;
        return cost==service.cost&&service.name.equals(name)
                &&service.serviceType.equals(serviceType);
    }

    @Override
    protected Service clone() throws CloneNotSupportedException {
        return (Service) super.clone();
    }
}
