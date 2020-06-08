package MaxFrolov_RPIS82;

import java.time.LocalDate;

public class IndividualAccount extends AbstractAccount{
    private int id;
    private Person holder;
    private IndividualsTariff tariff;

    public IndividualAccount(int id, Person holder) {
        if(holder==null)
            throw new NullPointerException();
        this.id = id;
        this.holder = holder;
        this.tariff = new IndividualsTariff(new Service[]{new Service()});
        this.activationDate= LocalDate.now();
    }

    public IndividualAccount(int id, Person holder, IndividualsTariff tariff,LocalDate activationDate) {
        if(activationDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException();
        if(holder==null)
            throw new NullPointerException();
        this.id = id;
        this.holder = holder;
        this.tariff = tariff;
        this.activationDate=activationDate;
    }

    public Person getHolder() {
        return this.holder;
    }

    public void setHolder(Person holder) {
        if(holder==null)
            throw new NullPointerException();
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
