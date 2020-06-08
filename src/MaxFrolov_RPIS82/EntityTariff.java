package MaxFrolov_RPIS82;

public class EntityTariff implements Tariff {
    Node head;
    Node tail;
    int size;

    public EntityTariff()
    {
        head=tail=null;
    }

    public EntityTariff(Service[] services)
    {
        head=tail=new Node();
        head.service=services[0];
        for(int i=1;i<services.length;i++)
        {
            tail.next=new Node(tail);
            tail=tail.next;
            tail.service=services[i];
        }
    }

    @Override
    public boolean add(Service service) {
        if(head!=null){
            tail.next=new Node(tail);
            tail=tail.next;
            tail.service=service;}
        else {
            head=tail=new Node();
            head.service=service;
        }
        return false;
    }

    @Override
    public boolean add(Service service, int pos) {
        Node current= head;
        for(int i=0;i<pos;i++)
            current=current.next;
        current.service=service;
        return false;
    }

    @Override
    public Service get(int pos) {
        Node current= head;
        for(int i=0;i<pos;i++)
            current=current.next;
        return current.service;
    }

    @Override
    public Service get(String name) {
        Node current= head;
        do {
            if(current.service.getName().equals(name))
                return current.service;
            else current=current.next;
        }while (current!=null);
        return null;
    }

    @Override
    public boolean isIncluded(String name) {
        return get(name)!=null;
    }

    @Override
    public Service set(int pos, Service service) {
        Node current= head;
        for(int i=0;i<pos;i++)
            current=current.next;
        Service s=current.service;
        current.service=service;
        return s;
    }

    @Override
    public Service delete(int pos) {
        Node current= head;
        for(int i=0;i<pos;i++)
            current=current.next;
        Service s=current.service;
        current.previous.next=current.next;
        return s;
    }

    @Override
    public Service delete(String name) {
        Node current= head;
        do {
            if(current.service.getName().equals(name))
            {
                Service s=current.service;
                current.previous.next=current.next;
                return s;
            }
            else current=current.next;
        }while (current!=null);
        return null;
    }

    @Override
    public int getSize() {
        Node current= head;
        int n=0;
        do {
            n++;
            current=current.next;
        }while (current!=null);
        return n;
    }

    @Override
    public Service[] getServices() {
        int n=getSize();
        Node current= head;
        Service[] services=new Service[n];
        for (int i=0;i<n;i++)
        {
            services[i]=current.service;
            current=current.next;
        }
        return services;
    }

    @Override
    public Service[] getSortedServices() {
        Service[] services=getServices();
        for(int i=0;i<services.length;i++)
            for(int j=1;j<services.length-1;j++)
            {
                if(services[i].getCost()>services[j].getCost()) {
                    Service service =services[i];
                    services[i]=services[j];
                    services[j]=service;
                }
            }
        return services;
    }

    @Override
    public int getCost() {
        return 0;
    }
}
