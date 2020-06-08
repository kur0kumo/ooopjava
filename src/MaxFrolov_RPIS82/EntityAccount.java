package MaxFrolov_RPIS82;

public class EntityAccount extends AbstractAccount implements Account{
    long number;
    String name;
    EntityTariff tariff;

    @Override
    public long getNumber() {
        return number;
    }

    @Override
    public EntityTariff getTariff(int pos) {
        return tariff;
    }

    @Override
    public void setTariff(EntityTariff tariff) {
        this.tariff=tariff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
