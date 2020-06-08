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

    @Override
    public String toString() {
        return String.format("Entity account:\nentity:%s\n%s",name,super.toString());
    }

    @Override
    public int hashCode() {
        return super.hashCode()*53*name.hashCode();
    }

    public boolean equals(Object obj) {

        return obj.getClass().equals(EntityAccount.class)&&
                name.equals(((EntityAccount)obj).name)&&
                number==((EntityAccount)obj).getNumber()&&
                tariff.getSize()==((EntityAccount)obj).tariff.getSize();
    }
}
