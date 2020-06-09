package MaxFrolov_RPIS82;


public class Test {
    public static void main(String[] args) {

        //lab4test();
        lab5test();
    }

    static void lab4test(){
        AccountManager manager=new AccountManager(0);
        System.out.println(Math.abs( manager.hashCode()));
        EntityAccount entityAccount=new EntityAccount();
        entityAccount.setName("bd");
        entityAccount.setNumber(456);
        Service[] services=new Service[2];
        services[0]=new Service();
        services[1]=new Service();
        EntityTariff entityTariff=new EntityTariff(services);
        System.out.println(Math.abs(services[0].hashCode()));
        System.out.println(Math.abs(entityTariff.hashCode()));
        entityAccount.setTariff(entityTariff);
        System.out.println(Math.abs(entityAccount.hashCode()));
    }

    static void lab5test(){
        IndividualAccount[] accounts=null;
        try {
            AccountManager manager=new AccountManager(accounts);
        }
        catch (NullPointerException e){
            System.out.println(String.format("Перехвачено исключение:\n %s",e.toString()));
        }

        try {

            Person holder=new Person("efs","ef");
            accounts=
                    new IndividualAccount[2];
            accounts[0]=new IndividualAccount(0,holder);
            accounts[1]=new IndividualAccount(0,holder);
            accounts[0].setNumber(999999999999099L);
            accounts[1].setNumber(999999999999092L);
            AccountManager manager1=new AccountManager(accounts);
            manager1.add(accounts[0]);

        }
        catch (DublicateAccountNumberException e){
            System.out.println(String.format("Перехвачено исключение:\n %s",e.toString()));
        }

        try {
            AccountManager manager1=new AccountManager(0);
            Person holder=new Person("efs","ef");
            IndividualAccount account=new IndividualAccount(0,holder);
            account.setNumber(999999999999099L);
            manager1.add(account);
            manager1.getAccount(2);
        }
        catch (DublicateAccountNumberException e){
            System.out.println(String.format("Перехвачено исключение:\n %s",e.toString()));
        }
        catch (IllegalAccountNumber e){
            System.out.println(String.format("Перехвачено исключение:\n %s",e.toString()));
        }
    }
}
