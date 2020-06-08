package MaxFrolov_RPIS82;

public class Person {
    private String sName;
    private String fName;

    public Person(String sName, String fName) {
        this.sName = sName;
        this.fName = fName;
    }

    public String getsName() {
        return this.sName;
    }

    public String getfName() {
        return this.fName;
    }

    @Override
    public String toString() {
        return String.format("%s %s",fName,sName);
    }

    @Override
    public int hashCode() {
        return sName.hashCode()*fName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(Person.class))
        {
            Person person= (Person) obj;
            return sName.equals(person.sName)&&fName.equals(person.fName);
        }else return false;
    }

}
