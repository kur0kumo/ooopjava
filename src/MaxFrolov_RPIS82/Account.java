package MaxFrolov_RPIS82;

public interface Account {
    public long getNumber();
    public EntityTariff getTariff(int pos);
    public void setTariff(EntityTariff tariff);
}
