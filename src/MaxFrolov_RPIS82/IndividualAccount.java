package MaxFrolov_RPIS82;

public class IndividualAccount extends AbstractAccount{
    private int id;
    private Person holder;
    private IndividualsTariff tariff;

    public IndividualAccount(int id, Person holder) {
        this.id = id;
        this.holder = holder;
        this.tariff = new IndividualsTariff(new Service[]{new Service()});
    }

    public IndividualAccount(int id, Person holder, IndividualsTariff tariff) {
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

    @Override
    public String toString() {
        return String.format("Individual account:\nholder:%s\n%s",holder,super.toString());
    }

    @Override
    public int hashCode() {
        return super.hashCode()*97*holder.hashCode();
    }

    public boolean equals(Object obj) {

        return obj.getClass().equals(IndividualAccount.class) &&
                super.equals(obj) &&
                holder.equals(((IndividualAccount) obj).holder);
    }
}
