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
}
