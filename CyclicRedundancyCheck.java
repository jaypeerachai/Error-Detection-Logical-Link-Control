/**
 * 5. Write a computer program that encode a dataword into a codeword and decode a codeword to determine whether the codeword is in error for cyclic redundancy check code.
 * Created by Peerachai Banyongrakkul Sec.1 5988070
 * CyclicRedundancyCheck.java
 */
import java.util.Scanner;
public class CyclicRedundancyCheck 
{
    public static void main(String[] args)
    {
        System.out.println("---------- Cyclic Redundancy Check ----------");
        
        System.out.println("ENCODE");
        Scanner sc = new Scanner(System.in);
        System.out.print("Dataword: ");
        String data = sc.nextLine();
        DataWord dataWord = new DataWord(data);
        System.out.print("Divisor: ");
        String divisor = sc.nextLine();
        encode(dataWord,divisor,0);
        System.out.println();
        
        System.out.println("DECODE");
        System.out.print("Received Codeword: ");
        data = sc.nextLine();
        DataWord code = new DataWord(data);
        decode(code,divisor);
    }
    
    
    public static String encode(DataWord dataWord, String divisor, int check)
    {
        String code = dataWord.getData();
        // check whether it is encoding or decoding
        if(check == 0)
        {
            while(code.length() < (dataWord.getLength() + divisor.length() - 1))
            {
                code = code + '0';
            }
        }
        int temp = divisor.length();
        String result = code.substring(0, temp);
        String remain = "";
        for(int i = 0; i<temp; i++)
        {
            if(result.charAt(i) == divisor.charAt(i))
            {
                remain += '0';
            }
            else
            {
                remain += '1';
            }
        }
        //System.out.println(remain);
        while(temp < code.length())
        {
            String dvs = divisor;
            if(remain.charAt(1) == '1')
            {
                remain = remain.substring(1, remain.length());
                remain = remain + String.valueOf(code.charAt(temp));
                temp++;
            }
            else
            {
                remain = remain.substring(1, remain.length());
                remain = remain + String.valueOf(code.charAt(temp));
                dvs = "";
                for(int i = 0 ; i< divisor.length();i++)
                {
                    dvs += 0;
                }
                temp++;
            }
            //System.out.println(remain);
            //System.out.println(dvs);
            result = remain;
            remain = "";
            for(int i = 0 ; i < divisor.length() ; i++)
            {
                if(result.charAt(i) == dvs.charAt(i))
                {
                    remain += '0';
                }
                else
                {
                    remain += '1';
                }
            }
        }
        String codeWord;
        String remainder = remain.substring(1, remain.length());
        codeWord = dataWord.getData() + remainder;
        if(check == 0)
        {
            System.out.println("Remainder: " + remainder);
            System.out.println("Codeword: "+codeWord);
        }
        else
        {
            System.out.println("Remainder: " + remainder);
        }
        return remainder;
    }
    
    
    public static void decode(DataWord dataWord, String divisor)
    {
        String remainder = encode(dataWord,divisor,1);
        //System.out.println(remainder);
        boolean syndrome = Integer.parseInt(remainder) == 0;
        if(syndrome == true)
        {
            System.out.println("Syndrome: 0");
            System.out.println();
            System.out.println("***The codeword isn't in error");
        }
        else 
        {
            System.out.println("Symdrome: non-zero");
            System.out.println();
            System.out.println("***The codeword is in error");
        }
    }
}
