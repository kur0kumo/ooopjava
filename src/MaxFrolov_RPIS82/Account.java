package MaxFrolov_RPIS82;

import java.time.LocalDate;

public interface Account {
    public long getNumber();
    public EntityTariff getTariff(int pos);
    public void setTariff(EntityTariff tariff);
    public LocalDate getActivationDate();
}

