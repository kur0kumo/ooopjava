package MaxFrolov_RPIS82;

public class Account {
    private int id;
    private Person holder;
    private IndividualsTariff tariff;

    public Account(int id, Person holder) {
        this.id = id;
        this.holder = holder;
        this.tariff = new IndividualsTariff(new Service[]{new Service()});
    }

    public Account(int id, Person holder, IndividualsTariff tariff) {
        this.id = id;
        this.holder = holder;
        this.tariff = tariff;
    }

    public Person getHolder() {
        return this.holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }

    public IndividualsTariff getTariff() {
        return this.tariff;
    }

    public void setTariff(IndividualsTariff tariff) {
        this.tariff = tariff;
    }

    public int getId() {
        return this.id;
    }
}
