/**
 * 2. Implement the parity check code with chunk interleaving and show that it can correct a set of four 16-bit packets suffering from a 4-bit burst error.
 * Created by
 * Peerachai Banyongrakkul Sec.1 5988070
 * Boonyada Lojanarungsiri Sec.1 5988153
 * ParityChunk.java
 */
import java.util.*;
public class ParityChunk 
{
    public static void main(String[] args)
    {
        System.out.println("----------Parity check code with chunk interleaving----------");
        Scanner sc = new Scanner(System.in);
        String code = "";
        int count = 0;
        String[] packet = new String[4];
        while(count < packet.length)
        {
            System.out.print("input "+ (count+1) +" 16-bit packet: ");
            packet[count] = sc.nextLine();
            if(packet[count].length() != 16)
            {
                System.out.println("Wrong Input!!");
                System.exit(0);
            }
            count++;
        }
        
        /*PACKET CREATION AT SENDER*/
        String[][] chunk = new String[4][5];
        char[][] rBit = new char[4][4];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<5;j++)
            {
                int bit=0;
                if(j == 0)
                {
                    bit=0;
                    chunk[i][j] = packet[i].substring(0,4);
                    for(int m=0;m<4;m++)
                    {
                        rBit[bit][j] = chunk[i][j].charAt(m);
                        bit++;
                    }
                }
                else if( j == 1)
                {
                    bit=0;
                    chunk[i][j] = packet[i].substring(4,8);
                    for(int m=0;m<4;m++)
                    {
                        rBit[bit][j] = chunk[i][j].charAt(m);
                        bit++;
                    }
                }
                else if( j == 2 )
                {
                    bit=0;
                    chunk[i][j] = packet[i].substring(8,12);
                    for(int m=0;m<4;m++)
                    {
                        rBit[bit][j] = chunk[i][j].charAt(m);
                        bit++;
                    }
                }
                else if ( j == 3)
                {
                    bit=0;
                    chunk[i][j] = packet[i].substring(12, 16);
                    for(int m=0;m<4;m++)
                    {
                        rBit[bit][j] = chunk[i][j].charAt(m);
                        bit++;
                    }
                    bit++;
                }
                else
                {
                    chunk[i][j] = "";
                    String temp = "";
                    int c = 0;
                    while(c<4)
                    {
                        char rbit = rBit[c][0];
                        for(int l = 1 ; l<4 ; l++)
                        {
                            if(rbit == rBit[c][l])
                            {
                                rbit = '0';
                            }
                            else
                            {
                                rbit = '1';
                            }
                        } 
                        temp += rbit;
                        c++;
                    }
                    chunk[i][j] = temp;
                    temp = "";
                    for(int a =0; a<4;a++)
                    {
                        for(int b=0;b<4;b++)
                        {
                            rBit[a][b]=0;
                        }
                    }
                }
            }
        }
       
        System.out.println();
        System.out.println("PACKET CREATION AT SENDER");
        System.out.println("\t|chunk 1|chunk 2|chunk 3|chunk 4|r-bit|");
        for(int i = 0; i<4 ; i++)
        {
            System.out.print("Packet "+(i+1)+" | ");
            for(int j = 0 ; j<5 ; j++)
            {
                System.out.print(chunk[i][j]+" | ");
            }
            System.out.println();
        }
        /*END PACKET CREATION AT SENDER*/
        
        /*PACKETS SENT*/
        System.out.println();
        System.out.println("PACKETS SENT");
        for(int i = 0; i< 5 ; i++)
        {
            System.out.print("\t Pactket "+(5-i)+"\t\t");
        }
        System.out.println();
        for(int i = 0; i<5 ; i++)
        {
            System.out.print("| ");
            for(int j = 3 ; j>=0 ; j--)
            {
                System.out.print(chunk[j][i]+" | ");
            }
            System.out.print(" ");
        }
        /*END PACKETS SENT*/
        
        /*PACKETS RECEIVED & ERROR CORRECTION*/
        System.out.println();
        System.out.println();
        System.out.println("PACKETS RECEIVED & ERROR CORRECTION (Suppose there is 4-bit burst error in packet 5 chunk 4)");
        
        String newBit ="";
        for(int i = 0 ; i < 4 ; i++)
        {
            System.out.print(chunk[3][0].charAt(i));
            if(chunk[3][0].charAt(i) == '0')
            {
                newBit += '1';
            }
            else
            {
                newBit += '0';
            }
        }
        System.out.print(" is changed to ");
        chunk[3][0] = newBit;
        System.out.print(newBit);
        
        System.out.println();
        for(int i = 0; i< 5 ; i++)
        {
            System.out.print("\t Pactket "+(5-i)+"\t\t");
        }
        System.out.println();
        for(int i = 0; i<5 ; i++)
        {
            System.out.print("| ");
            for(int j = 3 ; j>=0 ; j--)
            {
                System.out.print(chunk[j][i]+" | ");
            }
            System.out.print(" ");
        }
        
        System.out.println();
        int p = 0;
        String correctBit = "";
        while(p<4)
        {
            char tempBit = chunk[3][0].charAt(p);
            for(int i = 1 ; i < 4 ; i++)
            {
                if(tempBit == chunk[3][i].charAt(p))
                {
                    tempBit = 0;
                }
                else
                {
                    tempBit = 1;
                }
            }
            if(tempBit != chunk[3][4].charAt(p))
            {
                if(chunk[3][0].charAt(p) == '0')
                {
                    System.out.println("Correct digit of packet 5 chunk 4 from "+chunk[3][0].charAt(p)+" to 1");
                    correctBit += 1;
                }
                else
                {
                    System.out.println("Correct digit of packet 5 chunk 4 from "+chunk[3][0].charAt(p)+" to 0");
                    correctBit += 0;
                }
            }
            p++;
        }
        System.out.println("Correct packet 5 chunk 4 from " + chunk[3][0] + " to " + correctBit);
        chunk[3][0] = correctBit;
        /*END PACKETS RECEIVED & ERROR CORRECTION*/
        
        /*PACKET RECREATION AT RECEIVER*/
        System.out.println();
        System.out.println("PACKET RECREATION AT RECEIVER");
        System.out.println("\t|chunk 1|chunk 2|chunk 3|chunk 4|r-bit|");
        for(int i = 0; i<4 ; i++)
        {
            System.out.print("Packet "+(i+1)+" | ");
            for(int j = 0 ; j<5 ; j++)
            {
                System.out.print(chunk[i][j]+" | ");
            }
            System.out.println();
        }
        /*END PACKET RECREATION AT RECEIVER*/
    }
}
