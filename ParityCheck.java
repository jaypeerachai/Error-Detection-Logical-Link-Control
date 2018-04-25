import java.util.Scanner;
public class ParityCheck 
{
    public static void main(String[] args)
    {
        System.out.println("---------- Parity Check ----------");
        System.out.println("ENCODE");
        Scanner sc = new Scanner(System.in);
        System.out.print("Dataword: ");
        String data = sc.nextLine();
        DataWord dataWord = new DataWord(data);
        String codeWord = encode(dataWord);
        System.out.println();
        
        System.out.println("DECODE");
        System.out.print("Decode: ");
        String code = sc.nextLine();
        decode(code,codeWord);
    }
    
    public static String encode(DataWord d)
    {
        String codeword = d.getData();
        char rBit = d.getData().charAt(0);
        for(int i = 1 ; i < d.getLength(); i++)
        {
            //System.out.print(d.getData().charAt(i));
            if(rBit == d.getData().charAt(i))
            {
                rBit = '0';
            }
            else
            {
                rBit = '1';
            }
        }
        codeword += rBit;
        System.out.println("r-bit: "+ rBit);
        System.out.println("Codeword: "+codeword);
        return codeword;
    }
    
    public static void decode(String code, String codeWord)
    {
        if(code.equals(codeWord))
        {
            System.out.println("The codeword isn't in error");
        }
        else 
        {
            System.out.println("The codeword is in error");
        }
    }
}
