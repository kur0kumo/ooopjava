package MaxFrolov_RPIS82;

public interface Tariff {
    public boolean add(Service service);
    public boolean add(Service service, int pos);
    public Service get(int pos);
    public Service get(String name);
    public boolean isIncluded(String name);
    public Service set(int pos,Service service);
    public Service delete(int pos);
    public Service delete(String name);
    public int getSize();
    public Service[] getServices();
    public Service[] getSortedServices();
    public int getCost();
}