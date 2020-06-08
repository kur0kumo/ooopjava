package MaxFrolov_RPIS82;

import java.util.ArrayList;

public class AccountManager {
    private IndividualAccount[] accounts;
    private int capacity;
    private int size;

    public AccountManager(int initialCapacity) {
        this.accounts = new IndividualAccount[this.capacity];
        this.capacity = initialCapacity;
    }

    public AccountManager(IndividualAccount[] accounts) {
        IndividualAccount[] newAccounts = new IndividualAccount[accounts.length];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.capacity = this.size = accounts.length;
        this.accounts = newAccounts;
    }

    public boolean add(IndividualAccount account){
        doubleCapacity();
        for(int i = 0; i < this.accounts.length; ++i) {
            if (this.accounts[i] == null) {
                this.accounts[i] = account;
                ++this.size;
                return true;
            }
        }

        return false;
    }

    private void doubleCapacity(){
        if (this.size == this.capacity) {
            IndividualAccount[] newAccounts = new IndividualAccount[this.capacity *= 2];
            System.arraycopy(this.accounts, 0, newAccounts, 0, this.accounts.length);
            this.accounts = newAccounts;
        }
    }

    public boolean add(IndividualAccount account, int position) {
        if (position < 0 | position > this.capacity) {
            return false;
        } else if (this.accounts[position] == null) {
            this.accounts[position] = account;
            ++this.size;
            return true;
        } else {
            return false;
        }
    }

    public Account get(int position) {
        return this.accounts[position];
    }

    public Account rewrite(IndividualAccount account, int position) {
        IndividualAccount oldAccount = this.accounts[position];
        this.accounts[position] = account;
        return oldAccount;
    }

    public IndividualAccount delete(int position) {
        IndividualAccount deleted = this.accounts[position];
        System.arraycopy(this.accounts, position + 1, this.accounts, position, this.size - position);
        this.accounts[this.size--] = null;
        return deleted;
    }

    public int getSize() {
        return this.size;
    }

    public Account[] getAccounts() {
        return this.accounts;
    }

    public IndividualsTariff getTariff(int id) {
        for(int i = 0; i < this.accounts.length; ++i) {
            if (((IndividualAccount)this.accounts[i]).getId() == id) {
                return ((IndividualAccount)this.accounts[i]).getTariff();
            }
        }

        return null;
    }

    public IndividualsTariff changeTariff(int id, IndividualsTariff tariff) {
        for(int i = 0; i < this.accounts.length; ++i) {
            if (((IndividualAccount)this.accounts[i]).getId() == id) {
                IndividualsTariff oldTariff = ((IndividualAccount)this.accounts[i]).getTariff();
                ((IndividualAccount)this.accounts[i]).setTariff(tariff);
                return oldTariff;
            }
        }

        return null;
    }

    public Service[] getSeervices(ServiceTypes serviceType)
    {
        ArrayList<Service> s=new ArrayList<>();
        for (IndividualAccount acc: accounts) {
            for(Service serv:acc.getTariff().getServices())
                if(serv.getServiceType().equals(serviceType))s.add(serv);
        }
        return (Service[]) s.toArray();
    }

    @Override
    public String toString() {
        StringBuilder str= new StringBuilder();
        for (IndividualAccount acc:accounts) {
            str.append(acc.toString());
        }
        return str.toString();
    }
}
