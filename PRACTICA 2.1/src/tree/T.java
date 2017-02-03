package tree;

public class T
{
    private String name;
    private long size;

    public T(String n, long s){this.name=n; this.size=s; }

    public String getName(){ return name; }
    public void setName(String n){ name = n; }

    public long getSize(){ return size; }
    public void setSize(long s){ size = s; }
}