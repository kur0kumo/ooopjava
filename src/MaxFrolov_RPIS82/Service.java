package MaxFrolov_RPIS82;

import java.time.LocalDate;
import java.util.Date;

public final class Service implements Comparable<Service>{
    private final String name;
    private final int cost;
    ServiceTypes serviceType;
    LocalDate activationDate;

    public Service() {
        this.name = "интернет 100мб\\сек";
        this.cost = 300;
        serviceType=ServiceTypes.INTERNET;
        activationDate=LocalDate.now();
    }

    public Service(String name, int cost, ServiceTypes serviceType, LocalDate activationDate) {
        if(name==null||serviceType==null||activationDate==null)
            throw new NullPointerException();
        if(cost<0||activationDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException();
        this.name = name;
        this.cost = cost;
        this.serviceType=serviceType;
        this.activationDate=activationDate;

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
                &&service.serviceType.equals(serviceType)
                && activationDate.equals(service.activationDate);
    }

    @Override
    protected Service clone() throws CloneNotSupportedException {
        return (Service) super.clone();
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    @Override
    public int compareTo(Service o) {
        if(getCost()==o.getCost())return 0;
        return getCost()-o.getCost();
    }
}
