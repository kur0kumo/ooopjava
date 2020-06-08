package MaxFrolov_RPIS82;

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

    public IndividualAccount get(int position) {
        return this.accounts[position];
    }

    public IndividualAccount rewrite(IndividualAccount account, int position) {
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

    public IndividualAccount[] getAccounts() {
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
}
