package MaxFrolov_RPIS82;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AccountManager implements Iterable<IndividualAccount> {
    private IndividualAccount[] accounts;
    private int capacity;
    private int size;

    public AccountManager(int initialCapacity) {
        this.accounts = new IndividualAccount[this.capacity];
        this.capacity = initialCapacity;
    }

    public AccountManager(IndividualAccount[] accounts) {
        if(accounts==null)
            throw new NullPointerException();
        IndividualAccount[] newAccounts = new IndividualAccount[accounts.length];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.capacity = this.size = accounts.length;
        this.accounts = newAccounts;
    }

    public boolean add(IndividualAccount account) throws DublicateAccountNumberException {
        if(account==null)
            throw new NullPointerException();
        for(int i = 0; i < this.accounts.length; ++i)
            if(accounts[i].getNumber()==account.getNumber())
                throw new DublicateAccountNumberException();
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

    public boolean add(IndividualAccount account, int position) throws DublicateAccountNumberException {
        if(account==null)
            throw new NullPointerException();
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
        for(int i = 0; i < this.accounts.length; ++i)
            if(accounts[i].getNumber()==account.getNumber())
                throw new DublicateAccountNumberException();
        if (this.accounts[position] == null) {
            this.accounts[position] = account;
            ++this.size;
            return true;
        } else {
            return false;
        }
    }

    public Account get(int position)
    {if(position<0||position>=getSize())
        throw new IndexOutOfBoundsException();
        return this.accounts[position];
    }

    public Account rewrite(IndividualAccount account, int position) throws DublicateAccountNumberException {
        if(account==null)
            throw new NullPointerException();
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
        for(int i = 0; i < this.accounts.length; ++i)
            if(accounts[i].getNumber()==account.getNumber())
                throw new DublicateAccountNumberException();
        IndividualAccount oldAccount = this.accounts[position];
        this.accounts[position] = account;
        return oldAccount;
    }

    public IndividualAccount delete(int position) {
        if(position<0||position>=getSize())
            throw new IndexOutOfBoundsException();
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
        if(tariff==null)
            throw new NullPointerException();
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
        if(serviceType==null)
            throw new NullPointerException();
        ArrayList<Service> s=new ArrayList<>();
        for (IndividualAccount acc: accounts) {
            for(Service serv:acc.getTariff().getServices())
                if(serv.getServiceType().equals(serviceType))s.add(serv);
        }
        return (Service[]) s.toArray();
    }

    public IndividualAccount getAccount(long number)
    {
        if(number<1000000000001L||number>999999999999999L)
            throw new IllegalAccountNumber();
        for(IndividualAccount account: accounts)
            if( account.getNumber()==number)
                return account;
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder str= new StringBuilder();
        for (IndividualAccount acc:accounts) {
            str.append(acc.toString());
        }
        return str.toString();
    }

    @Override
    public Iterator<IndividualAccount> iterator() {
        return new ServiceIterator();
    }

    class ServiceIterator implements Iterator<IndividualAccount> {
        int index=0;


        @Override
        public boolean hasNext() {
            return index<getSize();
        }

        @Override
        public IndividualAccount next() {
            if(hasNext())
                return accounts[index++];
            else throw new NoSuchElementException();
        }
    }
}
