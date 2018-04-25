/**
 * 
 * 
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
        
        System.out.print("Recieved codeword: ");
        for(int i = 0; i< a.length ; i++)
        {
            System.out.println("");
        }
    }
    
    public static int[] encode(DataWord dataWord, int n)
    {
        int dataword[] = new int[n];
        for(int i=0 ; i < n ; i++) 
        {
            dataword[n-i-1] = Character.getNumericValue(dataWord.getData().charAt(i));
        }
        
        int a[];
        int i=0, parity_count=0,j=0,k=0;
        while(i<dataword.length)
        {
            if(Math.pow(2, parity_count) == i+parity_count + 1)
            {
                parity_count++;
            }
            else
            {
                i++;
            }
        }
        
        a = new int[dataword.length + parity_count];
        for(i = 1 ; i<=a.length ; i++)
        {
            if(Math.pow(2, j) == i)
            {
                a[i-1] = 2;
                j++;
            }
            else
            {
                a[k+j] = dataword[k++];
            }
        }
        for(i = 0; i<parity_count ; i++)
        {
            a[((int) Math.pow(2,i)) - 1] = getParity(a,i);
        }
        return a;
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
