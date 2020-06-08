package MaxFrolov_RPIS82;
import java.util.Arrays;

public class IndividualsTariff {
    private Service[] services;
    private int capacity;
    private int size;
    private final int DEFAULT_CAPACITY = 8;
    private final int MAINTENANCE_COST = 50;

    public IndividualsTariff() {
        this.services = new Service[8];
        this.capacity = 8;
    }

    public IndividualsTariff(int initialCapacity) {
        this.services = new Service[initialCapacity];
        this.capacity = initialCapacity;
    }

    public IndividualsTariff(Service[] services) {
        Service[] newServices = new Service[services.length];
        System.arraycopy(services, 0, newServices, 0, services.length);
        this.capacity = this.size = services.length;
        this.services = newServices;
    }

    public boolean add(Service service) {
        if (this.size == this.capacity) {
            Service[] newServices = new Service[this.capacity *= 2];
            System.arraycopy(this.services, 0, newServices, 0, this.services.length);
            this.services = newServices;
        }

        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i] == null) {
                this.services[i] = service;
                ++this.size;
                return true;
            }
        }

        return false;
    }

    public boolean add(Service service, int position) {
        if (position < 0 | position > this.capacity) {
            return false;
        } else if (this.services[position] == null) {
            this.services[position] = service;
            ++this.size;
            return true;
        } else {
            return false;
        }
    }

    public Service get(int position) {
        return this.services[position];
    }

    public Service get(String name) {
        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i].getName().equalsIgnoreCase(name)) {
                return this.services[i];
            }
        }

        return null;
    }

    public boolean exists(String serviceName) {
        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i].getName().equalsIgnoreCase(serviceName)) {
                return true;
            }
        }

        return false;
    }

    public Service rewrite(Service newService, int position) {
        Service oldService = this.services[position];
        this.services[position] = newService;
        return oldService;
    }

    public Service delete(int position) {
        Service deleted = this.services[position];
        System.arraycopy(this.services, position + 1, this.services, position, this.size - position);
        this.services[this.size--] = null;
        return deleted;
    }

    public Service delete(String name) {
        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i].getName().equalsIgnoreCase(name)) {
                Service deleted = this.services[i];
                System.arraycopy(this.services, i + 1, this.services, i, this.size - i);
                this.services[this.size] = null;
                --this.size;
                return deleted;
            }
        }

        return null;
    }

    public int getSize() {
        return this.size;
    }

    public Service[] getServices() {
        Service[] out = new Service[this.size];
        System.arraycopy(this.services, 0, out, 0, this.size);
        return out;
    }

    public Service[] getSortedServices() {
        Service[] out = this.getServices();

        for (int k = out.length / 2; k > 0; k /= 2) {
            for (int i = k; i < out.length; ++i) {
                Service temp = out[i];

                int j;
                for (j = i; j >= k && temp.getCost() < out[j - k].getCost(); j -= k) {
                    out[j] = out[j - k];
                }

                out[j] = temp;
            }
        }

        return out;
    }

    public int getCost() {
        int totalCost = 50;

        for (int i = 0; i < this.size; ++i) {
            totalCost += this.services[i].getCost();
        }

        return totalCost;
    }
}
