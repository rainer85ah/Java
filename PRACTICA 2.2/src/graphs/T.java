/*
Type of Vertice with name..
*/
package graphs;

public class T implements Position
{//6 info data
    private String name;
    private String LastName;
    private String ID;
    private long phone;
    private String address;
    private String email;

    public T(String name, String LastName, String ID, long phone, String address, String email) 
    {
        this.name = name;
        this.LastName = LastName;
        this.ID = ID;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    public String getName(){ return name; }
    public void setName(String n){ name = n; }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String LastName) 
    {
        this.LastName = LastName;
    }

    public String getID() 
    {
        return ID;
    }

    public void setID(String ID) 
    {
        this.ID = ID;
    }

    public long getPhone() 
    {
        return phone;
    }

    public void setPhone(long phone) 
    {
        this.phone = phone;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
    
    @Override
    public T getValue() 
    {
        return (T) this;
    }
}