package MaxFrolov_RPIS82;

public class AbstractAccount implements Account {
    private long number;
    private Tariff tariff;
    protected AbstractAccount(long number,Tariff tariff)
    {
        this.number=number;
        this.tariff=tariff;
    }

    public AbstractAccount() {
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
}
