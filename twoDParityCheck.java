/**
 * 1. Implement a two dimensional parity check code and show that it can detect an even number of errors.
 * Created by
 * Peerachai Banyongrakkul Sec.1 5988070
 * Boonyada Lojanarungsiri Sec.1 5988153
 * twoDParityCheck.java
 */
import java.util.*;
public class twoDParityCheck 
{
    public static void main(String[]args)
    {
        System.out.println("---------- 2D Parity Check----------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input number of block: ");
        int n = sc.nextInt();
        String temp = sc.nextLine();
        System.out.print("Input length of each block: ");
        int length = sc.nextInt();
        temp = sc.nextLine();
        String[] data = new String[n];
        int count = 0;
        while(count < n)
        {
            System.out.print("Block "+(count+1)+": ");
            data[count] = sc.nextLine();
            if(data[count].length() != length)
            {
               System.out.println("Wrong input!! you must input "+length+" bits");
               System.exit(0);
            }
            count++;
        }
        String codeWord = encode(data);
        
        System.out.println();
        System.out.print("Received codeword: ");
        String ReCodeWord = sc.nextLine();
        checkError(codeWord,ReCodeWord);
    }
    
    public static String encode(String[] dataWord)
    {
        int count = 0;
        char rRowBit[] = new char[dataWord.length];
        while(count<dataWord.length)
        {
            rRowBit[count] = dataWord[count].charAt(0);
            for(int i = 1 ; i < dataWord[count].length() ; i++)
            {
                if(rRowBit[count] == dataWord[count].charAt(i))
                {
                    rRowBit[count] = '0';
                }
                else
                {
                    rRowBit[count] = '1';
                }
            }
            dataWord[count] += rRowBit[count];
            count++;
        }
        System.out.print("Row parities: ");
        for(int i = 0 ; i<rRowBit.length ; i++)
        {
            System.out.print(rRowBit[i]);
        }
        System.out.println();
        
        count = 0;
        char rColBit[] = new char[dataWord[0].length()];
        String ColBit = "";
        while(count<dataWord[0].length())
        {
            rColBit[count] = dataWord[0].charAt(count);
            for(int i = 1 ; i < dataWord.length ; i++)
            {
                //System.out.println(dataWord[i].charAt(count));
                if(rColBit[count] == dataWord[i].charAt(count))
                {
                    rColBit[count] = '0';
                }
                else
                {
                    rColBit[count] = '1';
                }
            }
            count++;
        }
        System.out.print("Column parities: ");
        for(int i = 0 ; i<rColBit.length ; i++)
        {
            ColBit += rColBit[i];
            System.out.print(rColBit[i]);
        }
        System.out.println();
        
        String code[] = new String[dataWord.length+1];
        String codeWord = "";
        
        for(int i = 0 ; i<code.length ; i++)
        {
            if( i == dataWord.length)
            {
                code[i] = ColBit;
            }
            else
            {
                code[i] = dataWord[i]; 
            }
        }
        System.out.print("Codeword: ");
        for(int i = 0 ; i<code.length ; i++)
        {
            System.out.print(code[i] + " ");
            codeWord += code[i];
        }
        return codeWord;
    }
    
    public static boolean checkError(String codeWord, String ReCodeWord)
    {
        return true;
    }
}
