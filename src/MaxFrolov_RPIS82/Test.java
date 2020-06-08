package MaxFrolov_RPIS82;


public class Test {
    public static void main(String[] args) {

        AccountManager manager=new AccountManager(0);
        System.out.println(manager.hashCode());
        EntityAccount entityAccount=new EntityAccount();
        entityAccount.setName("bd");
        entityAccount.setNumber(456);
        Service[] services=new Service[2];
        services[0]=new Service();
        services[1]=new Service();
        EntityTariff entityTariff=new EntityTariff(services);
        System.out.println(services[0].hashCode());
        System.out.println(entityTariff.hashCode());
        entityAccount.setTariff(entityTariff);
        System.out.println(entityAccount.hashCode());
    }
}
