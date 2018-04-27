/**
 * 
 * Created by Peerachai Banyongrakkul Sec.1 5988070
 * HammingCode.java
 */
import java.util.*;
public class HammingCode 
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of bits: ");
        int n = sc.nextInt();
        String temp = sc.nextLine();
        System.out.print("Dataword: ");
        String data = sc.nextLine();
        if(data.length() != n)
        {
            System.out.println("Wrong input!! you must input "+n+" bits");
            System.exit(0);
        }
        DataWord dataWord = new DataWord(data);
        
        int a[] = encode(dataWord,n);    
        System.out.print("Codeword: ");
        for(int i=0 ; i < a.length ; i++) 
        {
            System.out.print(a[a.length-i-1]);
	}
        System.out.println();
        
        System.out.print("Received codeword: ");
        for(int i = 0; i< a.length ; i++)
        {
            //System.out.println("");
        }
    }
    
    public static int[] encode(DataWord dataWord, int n)
    {
        int dataword[] = new int[n];
        for(int i=0 ; i < n ; i++) 
        {
            dataword[n-i-1] = Character.getNumericValue(dataWord.getData().charAt(i));
        }
        
        int codeword[];
        int i=0, parityCount=0,j=0,k=0;
        while(i<dataword.length)
        {
            if(Math.pow(2, parityCount) == i+parityCount + 1)
            {
                parityCount++;
            }
            else
            {
                i++;
            }
        }
        
        codeword = new int[dataword.length + parityCount];
        i = 1;
        while(i<=codeword.length)
        {
            if(Math.pow(2, j) == i)
            {
                codeword[i-1] = 2;
                j++;
            }
            else
            {
                codeword[k+j] = dataword[k++];
            }
            i++;
        }
        for(i = 0; i<parityCount ; i++)
        {
            codeword[((int) Math.pow(2,i)) - 1] = getParity(codeword,i);
        }
        return codeword;
    }
    
    public static int getParity(int b[], int power)
    {
        int parity = 0;
        for(int i = 0 ; i<b.length ;i++)
        {
            if(b[i] != 2)
            {
                int k = i+1;
                String s = Integer.toBinaryString(k);
                int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(x == 1) 
                {
                    if(b[i] == 1) 
                    {
                        parity = (parity+1)%2;
                    }
                }
            }
        }
        return parity;
    }
}
