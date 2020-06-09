package MaxFrolov_RPIS82;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndividualsTariff implements Tariff {
    private Service[] services;
    private int capacity;
    private int size;

    public IndividualsTariff() {
        this.services = new Service[8];
        this.capacity = 8;
    }

    public IndividualsTariff(int initialCapacity) {

        this.services = new Service[initialCapacity];
        this.capacity = initialCapacity;
    }

    public IndividualsTariff(Service[] services) {
        if(services==null)
            throw new NullPointerException();
        Service[] newServices = new Service[services.length];
        System.arraycopy(services, 0, newServices, 0, services.length);
        this.capacity = this.size = services.length;
        this.services = newServices;
    }

    public boolean add(Service service) {
        if(service==null)
            throw new NullPointerException();
        if (this.size == this.capacity) {
            DoubleCapacity();
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

    protected void DoubleCapacity()
    {
        Service[] newServices = new Service[this.capacity *= 2];
        System.arraycopy(this.services, 0, newServices, 0, this.services.length);
        this.services = newServices;
    }

    public boolean add(Service service, int position) {
        if(service==null)
            throw new NullPointerException();
        if (position < 0 | position > this.capacity) {
            throw new IndexOutOfBoundsException();
        } else if (this.services[position] == null) {
            this.services[position] = service;
            ++this.size;
            return true;
        } else {
            return false;
        }
    }

    public Service get(int position) {
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
        return this.services[position];
    }

    public Service get(String name) {
        if(name==null)
            throw new NullPointerException();
        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i].getName().equalsIgnoreCase(name)) {
                return this.services[i];
            }
        }

        return null;
    }

    @Override
    public boolean isIncluded(String name) {
        if(name==null)
            throw new NullPointerException();
        return get(name)==null;
    }

    @Override
    public Service set(int pos, Service service) {
        if(service==null)
            throw new NullPointerException();
        if(pos<0||pos>=getSize())
            throw new IndexOutOfBoundsException();
        if(pos<services.length) {
            Service s = services[pos];
            services[pos] = service;
            return s;
        }else
        {
            add(service,pos);
            return null;
        }
    }

    public boolean exists(String serviceName) {
        if(serviceName==null)
            throw new NullPointerException();
        for (int i = 0; i < this.services.length; ++i) {
            if (this.services[i].getName().equalsIgnoreCase(serviceName)) {
                return true;
            }
        }

        return false;
    }

    public Service rewrite(Service newService, int position) {
        if(newService==null)
            throw new NullPointerException();
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
        Service oldService = this.services[position];
        this.services[position] = newService;
        return oldService;
    }

    public Service delete(int position) {
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
        Service deleted = this.services[position];
        System.arraycopy(this.services, position + 1, this.services, position, this.size - position);
        this.services[this.size--] = null;
        return deleted;
    }

    public Service delete(String name) {
        if(name==null)
            throw new NullPointerException();
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

        Arrays.sort(out);

        return out;
    }



    public int getCost() {
        int totalCost = 0;

        for (int i = 0; i < this.size; ++i) {
            totalCost += this.services[i].getCost();
        }

        return totalCost;
    }

    @Override
    public Service delete(Service service) {
        if(service==null)
            throw new NullPointerException();
        int index=firstIndex(service);
        if(index>0)
            return delete(index);
        else throw new NoSuchElementException();
    }

    @Override
    public int firstIndex(Service service) {
        if(service==null)
            throw new NullPointerException();
        for (int i=0;i<getSize();i++)
            if(services[i].equals(service))return i;
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("services:\n");
        for (Service serv:services) {
            stringBuilder.append(serv.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        int hash=31;
        for (Service serv:services) hash*=serv.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res=false;
        if(obj.getClass().equals(IndividualsTariff.class))
        {
            IndividualsTariff tariff= (IndividualsTariff) obj;
            res=(tariff.getSize()==getSize());
            Service[] services=tariff.getServices();
            for (int i=0;i<getSize();i++) {
                res= services[i].equals(this.services[i]);
                if(!res)return res;
            }
            return res;
        }
        return false;
    }

    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    class ServiceIterator implements  Iterator<Service>{
        int index=0;


        @Override
        public boolean hasNext() {
            return index<getSize();
        }

        @Override
        public Service next() {
            if(hasNext())
                return get(index++);
            else throw new NoSuchElementException();
        }
    }
}

