package MaxFrolov_RPIS82;

public class AccountManager {
    private Account[] accounts;
    private int capacity;
    private int size;

    public AccountManager(int initialCapacity) {
        this.accounts = new Account[this.capacity];
        this.capacity = initialCapacity;
    }

    public AccountManager(Account[] accounts) {
        Account[] newAccounts = new Account[accounts.length];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.capacity = this.size = accounts.length;
        this.accounts = newAccounts;
    }

    public boolean add(Account account) {
        if (this.size == this.capacity) {
            Account[] newAccounts = new Account[this.capacity *= 2];
            System.arraycopy(this.accounts, 0, newAccounts, 0, this.accounts.length);
            this.accounts = newAccounts;
        }

        for(int i = 0; i < this.accounts.length; ++i) {
            if (this.accounts[i] == null) {
                this.accounts[i] = account;
                ++this.size;
                return true;
            }
        }

        return false;
    }

    public boolean add(Account account, int position) {
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

    public Account rewrite(Account account, int position) {
        Account oldAccount = this.accounts[position];
        this.accounts[position] = account;
        return oldAccount;
    }

    public Account delete(int position) {
        Account deleted = this.accounts[position];
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
            if (this.accounts[i].getId() == id) {
                return this.accounts[i].getTariff();
            }
        }

        return null;
    }

    public IndividualsTariff changeTariff(int id, IndividualsTariff tariff) {
        for(int i = 0; i < this.accounts.length; ++i) {
            if (this.accounts[i].getId() == id) {
                IndividualsTariff oldTariff = this.accounts[i].getTariff();
                this.accounts[i].setTariff(tariff);
                return oldTariff;
            }
        }

        return null;
    }
}
