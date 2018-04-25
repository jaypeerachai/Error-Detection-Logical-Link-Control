/**
 * Dataword Class
 * Created by Peerachai Banyongrakkul Sec.1 5988070
 * DataWord.java
 */
public class DataWord 
{
    private final String data;
    private final int length;
    public DataWord(String d)
    {
        this.data = d;
        this.length = d.length();
    }
    
    public int getLength()
    {
        return this.length;
    }
    
    public String getData()
    {
        return this.data;
    }
}
