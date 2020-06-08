package MaxFrolov_RPIS82;

import java.time.LocalDate;

public class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;
    LocalDate activationDate;
    protected AbstractAccount(long number,Tariff tariff,LocalDate activationDate)
    {
        if(number<1000000000001L||number>999999999999999L)
            throw new IllegalAccountNumber();
        if(activationDate==null)
            throw new NullPointerException();
        if(activationDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException();
        this.number=number;
        this.tariff=tariff;
        this.activationDate=activationDate;
    }

    @Override
    public LocalDate getActivationDate() {
        return activationDate;
    }

    public AbstractAccount() {
        tariff=new IndividualsTariff();
    }

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public EntityTariff getTariff(int pos) {
        return (EntityTariff) tariff;
    }

    @Override
    public void setTariff(EntityTariff tariff) {
        this.tariff=tariff;
    }

    @Override
    public String toString() {
        return String.format("number: %d\nservices:\n%s",number,tariff.toString());
    }

    @Override
    public int hashCode() {
        return (int) (number*tariff.getSize());
    }

    public boolean equals(Object obj) {

        return obj.getClass().equals(AbstractAccount.class)&&
                number==((AbstractAccount)obj).getNumber()&&
                tariff.getSize()==((AbstractAccount)obj).tariff.getSize();
    }

    public void setNumber(long number) {
        this.number = number;
    }
}