//David Ciupei
//CS427 Program #1
//Dr.Mocas

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.io.PrintWriter;


public class WSUCRYPT {

    static int ftable [ ] =
    {0xa3,0xd7,0x09,0x83,0xf8,0x48,0xf6,0xf4,0xb3, 0x21,0x15,0x78,0x99,0xb1,0xaf,0xf9,
            0xe7,0x2d,0x4d,0x8a,0xce,0x4c,0xca,0x2e,0x52,0x95,0xd9,0x1e,0x4e,0x38,0x44,0x28,
            0x0a,0xdf,0x02,0xa0,0x17,0xf1,0x60,0x68,0x12,0xb7,0x7a,0xc3,0xe9,0xfa,0x3d,0x53,
            0x96,0x84,0x6b,0xba,0xf2,0x63,0x9a,0x19,0x7c,0xae,0xe5,0xf5,0xf7,0x16,0x6a,0xa2,
            0x39,0xb6,0x7b,0x0f,0xc1,0x93,0x81,0x1b,0xee,0xb4,0x1a,0xea,0xd0,0x91,0x2f,0xb8,
            0x55,0xb9,0xda,0x85,0x3f,0x41,0xbf,0xe0,0x5a,0x58,0x80,0x5f,0x66,0x0b,0xd8,0x90,
            0x35,0xd5,0xc0,0xa7,0x33,0x06,0x65,0x69,0x45,0x00,0x94,0x56,0x6d,0x98,0x9b,0x76,
            0x97,0xfc,0xb2,0xc2,0xb0,0xfe,0xdb,0x20,0xe1,0xeb,0xd6,0xe4,0xdd,0x47,0x4a,0x1d,
            0x42,0xed,0x9e,0x6e,0x49,0x3c,0xcd,0x43,0x27,0xd2,0x07,0xd4,0xde,0xc7,0x67,0x18,
            0x89,0xcb,0x30,0x1f,0x8d,0xc6,0x8f,0xaa,0xc8,0x74,0xdc,0xc9,0x5d,0x5c,0x31,0xa4,
            0x70,0x88,0x61,0x2c,0x9f,0x0d,0x2b,0x87,0x50,0x82,0x54,0x64,0x26,0x7d,0x03,0x40,
            0x34,0x4b,0x1c,0x73,0xd1,0xc4,0xfd,0x3b,0xcc,0xfb,0x7f,0xab,0xe6,0x3e,0x5b,0xa5,
            0xad,0x04,0x23,0x9c,0x14,0x51,0x22,0xf0,0x29,0x79,0x71,0x7e,0xff,0x8c,0x0e,0xe2,
            0x0c,0xef,0xbc,0x72,0x75,0x6f,0x37,0xa1,0xec,0xd3,0x8e,0x62,0x8b,0x86,0x10,0xe8,
            0x08,0x77,0x11,0xbe,0x92,0x4f,0x24,0xc5,0x32,0x36,0x9d,0xcf,0xf3,0xa6,0xbb,0xac,
            0x5e,0x6c,0xa9,0x13,0x57,0x25,0xb5,0xe3,0xbd,0xa8,0x3a,0x01,0x05,0x59,0x2a,0x46};


    public static String Bin_64 = null;
    static String subkeys[][] = new String[16][12];


    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);

    }

    //used for checking values in print statements
    public static String BinaryToHex(String binaryString){
        return Integer.toHexString(Integer.parseInt(binaryString, 2));
    }


    public static String make_64(String s){
        while(s.length() < 64){
            s = s.concat("0");
        }
        return s;
    }

    public static String make_64_2(String s){
        while(s.length() < 64){
            s = "0" + s;
        }
        return s;
    }

    public static String make_16(String s){
        while(s.length() < 16){
            s = "0" + s;
        }
        return s;
    }

    private static String XOR(String w, String k) {

        StringBuilder str = new StringBuilder();

        for(int i = 0; i < w.length(); i++) {
            str.append((w.charAt(i) ^ k.charAt(i)));
        }
        String result = str.toString();

        return result;
    }

    public static String rightBitRotate(String s) {
        s = s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
        return s;

    }

    public static String leftBitRotate(String s) {
        s = s.substring(1, s.length()) + s.charAt(0) ;
        return s;

    }

    private static String output_Whitening (String []y){
        String C[] = new String[4];
        String k[] = new String[4];
        k[0] = Bin_64.substring(0,16);
        k[1] = Bin_64.substring(16,32);
        k[2] = Bin_64.substring(32,48);
        k[3] = Bin_64.substring(48,64);

        for(int i = 0; i < 4; i++){
            C[i]=XOR(y[i],k[i]);
        }
        String result = C[0]+C[1]+C[2]+C[3];

        BigInteger B = new BigInteger(result,2);
        String str = B.toString(16);

        return str;
    }


    private static String K(int x){
        Bin_64 = leftBitRotate(Bin_64);
        String k[] = new String[8];
        k[0] = Bin_64.substring(0,8);
        k[1] = Bin_64.substring(8,16);
        k[2] = Bin_64.substring(16,24);
        k[3] = Bin_64.substring(24,32);
        k[4] = Bin_64.substring(32,40);
        k[5] = Bin_64.substring(40,48);
        k[6] = Bin_64.substring(48,56);
        k[7] = Bin_64.substring(56,64);

        return k[x%8];
    }


    private static String G(String w, int round, String rounds[]){
        String G[] = new String[6];
        String Temp0,Temp1, Temp2, Temp3;


        G[0] = w.substring(0, 8);  //left high 8 bits
        G[1] = w.substring(8, 16);  // right low 8 bits

        Temp0 = XOR(G[1], rounds[0]);
        int F_table1 = ftable[Integer.parseInt(Temp0, 2)];
        String F_Result = String.format("%8s", Integer.toBinaryString(F_table1)).replace(' ', '0');
        G[2] = XOR(G[0], F_Result);

        Temp1 = XOR(G[2], rounds[1]);
        int F_table2 = ftable[Integer.parseInt(Temp1, 2)];
        String F_Result2 = String.format("%8s", Integer.toBinaryString(F_table2)).replace(' ','0');
        G[3] = XOR(G[1], F_Result2);

        Temp2 = XOR(G[3], rounds[2]);
        int F_table3 = ftable[Integer.parseInt(Temp2, 2)];
        String F_Result3 = String.format("%8s", Integer.toBinaryString(F_table3)).replace(' ', '0');
        G[4] = XOR(G[2], F_Result3);

        Temp3 = XOR(G[4], rounds[3]);
        int F_table4 = ftable[Integer.parseInt(Temp3, 2)];
        String F_Result4 = String.format("%8s", Integer.toBinaryString(F_table4)).replace(' ', '0');
        G[5] = XOR(G[3], F_Result4);

        return G[4] + G[5];
    }

    private static String[] F(String R, String R_1, int round){

        String first_Call_toG[] = new String[4];
        String second_Call_toG[] = new String[4];


        subkeys[round][0] = K(4*round);
        subkeys[round][1] = K(4*round+1);
        subkeys[round][2] = K(4*round+2);
        subkeys[round][3] = K(4*round+3);

        first_Call_toG[0] = subkeys[round][0];
        first_Call_toG[1] = subkeys[round][1];
        first_Call_toG[2] = subkeys[round][2];
        first_Call_toG[3] = subkeys[round][3];


        subkeys[round][4] = K(4*round);
        subkeys[round][5] = K(4*round+1);
        subkeys[round][6] = K(4*round+2);
        subkeys[round][7] = K(4*round+3);

        second_Call_toG[0] = subkeys[round][4];
        second_Call_toG[1] = subkeys[round][5];
        second_Call_toG[2] = subkeys[round][6];
        second_Call_toG[3] = subkeys[round][7];

        subkeys[round][8] = K(4*round);
        subkeys[round][9] = K(4*round+1);
        subkeys[round][10] = K(4*round+2);
        subkeys[round][11] = K(4*round+3);

//        for (int i = 0; i < 12; i++) {
//            System.out.print(BinaryToHex(subkeys[round][i]) + " ");
//        }
//        System.out.print("\n");

        String T_0 = G(R,round, first_Call_toG);
        String T_1 = G(R_1, round, second_Call_toG);

        String F[] = new String[2];


        int F_0 = (Integer.parseInt(T_0, 2) + (2 * Integer.parseInt(T_1,2)) + Integer.parseInt((subkeys[round][8] + subkeys[round][9]),2 )) % (int) (Math.pow(2,16));
        F[0] = Integer.toBinaryString(F_0);
        //System.out.print("F[0]" +BinaryToHex(F[0]) + "\n");

        int F_1 = ((Integer.parseInt(T_0, 2) *2) + Integer.parseInt(T_1,2) + Integer.parseInt((subkeys[round][10] + subkeys[round][11]),2 )) % (int) (Math.pow(2,16));
        F[1] = Integer.toBinaryString(F_1);
        //System.out.print("F[1]" + BinaryToHex(F[1]) + "\n");

        return F;
    }



    private static String[] F_decrypt(String R, String R_1, int round){

        String first_Call_toG[] = new String[4];
        String second_Call_toG[] = new String[4];

        first_Call_toG[0] = subkeys[15 - round][0];
        first_Call_toG[1] = subkeys[15 - round][1];
        first_Call_toG[2] = subkeys[15 - round][2];
        first_Call_toG[3] = subkeys[15 - round][3];

        second_Call_toG[0] = subkeys[15 - round][4];
        second_Call_toG[1] = subkeys[15 - round][5];
        second_Call_toG[2] = subkeys[15 - round][6];
        second_Call_toG[3] = subkeys[15 - round][7];


//        for (int i = 0; i < 12; i++) {
//            System.out.print(BinaryToHex(subkeys[15 - round][i]) + " ");
//        }
//        System.out.print("\n");

        String F[] = new String[2];

        String T_0 = G(R,round, first_Call_toG);
        String T_1 = G(R_1, round, second_Call_toG);
        //System.out.print("T0 = " + BinaryToHex(T_0) + "\n");
        //System.out.print("T1 = " + BinaryToHex(T_1) + "\n");


        int F_0 = (Integer.parseInt(T_0, 2) + (2 * Integer.parseInt(T_1,2)) + Integer.parseInt((subkeys[15 - round][8] + subkeys[15 - round][9]),2 )) % (int) (Math.pow(2,16));
        F[0] = Integer.toBinaryString(F_0);
        //System.out.print("F[0] = " +BinaryToHex(F[0]) + "\n");

        int F_1 = ((Integer.parseInt(T_0, 2) *2) + Integer.parseInt(T_1,2) + Integer.parseInt((subkeys[15 - round][10] + subkeys[15 - round][11]),2 )) % (int) (Math.pow(2,16));
        F[1] = Integer.toBinaryString(F_1);
        //System.out.print("F[1] = " + BinaryToHex(F[1]) + "\n");

        return F;
    }


        public static String[] Encryption(String plain) {
        //System.out.println(stringToBin(plain))

            String R[] = new String[4];
            String w[] = new String[4];
            String k[] = new String[4];

            plain = hexToBin(plain);

            plain = make_64_2(plain);
            //System.out.print("plain = " + plain + "\n");
            //System.out.print("Bin_64 = "+ Bin_64);

            /////Whitening//////

            w[0] = plain.substring(0,16);
            w[1] = plain.substring(16,32);
            w[2] = plain.substring(32,48);
            w[3] = plain.substring(48,64);


            k[0] = Bin_64.substring(0,16);
            k[1] = Bin_64.substring(16,32);
            k[2] = Bin_64.substring(32,48);
            k[3] = Bin_64.substring(48,64);

            for(int i=0; i < 4; i++) {
                R[i] = XOR(w[i], k[i]);
                //System.out.print(BinaryToHex(R[i]) + "\n");
            }

            for (int round = 0; round < 16; round++){
                String[] F_result = F(R[0], R[1], round);

                F_result[0] = make_16(F_result[0]);
                F_result[1] = make_16(F_result[1]);

                String right_Rotate = XOR(R[2], F_result[0]);
                right_Rotate = rightBitRotate(right_Rotate);
                String left_Rotate = leftBitRotate(R[3]);
                left_Rotate = XOR(left_Rotate,F_result[1]);

                R[2] = R[0];
                R[3] = R[1];
                R[0] = right_Rotate;
                R[1] = left_Rotate;

            }

                return R;
        }

    public static String[] Decryption(String cypher) {

        String R[] = new String[4];
        String w[] = new String[4];
        String k[] = new String[4];

        cypher = hexToBin(cypher);
        //System.out.print("cypher = " + cypher + "\n");
        //System.out.print("Bin_64 = "+ Bin_64);

        /////Whitening//////
        w[0] = cypher.substring(0,16);
        w[1] = cypher.substring(16,32);
        w[2] = cypher.substring(32,48);
        w[3] = cypher.substring(48,64);


        k[0] = Bin_64.substring(0,16);
        k[1] = Bin_64.substring(16,32);
        k[2] = Bin_64.substring(32,48);
        k[3] = Bin_64.substring(48,64);

        for(int i=0; i < 4; i++) {
            R[i] = XOR(w[i], k[i]);
            //System.out.print(BinaryToHex(R[i]) + "\n");
        }

        for (int round = 0; round < 16; round++) {
            String[] F_result = F_decrypt(R[0], R[1], round);
            F_result[0] = make_16(F_result[0]);
            F_result[1] = make_16(F_result[1]);

            String right_Rotate;
            String left_Rotate;

            left_Rotate = leftBitRotate(R[2]);
            left_Rotate = XOR(left_Rotate, F_result[0]);
            right_Rotate = XOR(R[3],F_result[1]);
            right_Rotate = rightBitRotate(right_Rotate);

            R[2] = R[0];
            R[3] = R[1];
            R[0] = left_Rotate;
            R[1] = right_Rotate;
        }

        return R;
    }


        public static void main(String[] args)  throws IOException {
            Scanner plain_Text = new Scanner(System.in);
            Scanner key_Text = new Scanner(System.in);

            File file = new File("plaintext.txt");
            File file1 = new File("key.txt");
            plain_Text = new Scanner(file);
            key_Text = new Scanner(file1);

            String plainT = null;
            String keyT = null;


            while (plain_Text.hasNextLine()) {
                plainT = plain_Text.nextLine();
            }

            while (key_Text.hasNextLine()) {
                keyT = key_Text.nextLine();
            }

            String bin_Key = hexToBin(keyT);
            Bin_64 = make_64(bin_Key);

            ////////// Encryption //////////

            PrintWriter writer = new PrintWriter("cyphertext.txt", "UTF-8");

            //String plain = make_64(plainT);
            String[] R = Encryption(plainT);

            String y[] = new String[4];
            y[0] = R[2];
            y[1] = R[3];
            y[2] = R[0];
            y[3] = R[1];

            writer.print(output_Whitening(y));

            writer.close();

            //////////Decryption///////////

            Scanner cypher_T = new Scanner(System.in);
            File file2 = new File("cyphertext.txt");
            cypher_T = new Scanner(file2);
            String cypherT = null;

            while (cypher_T.hasNextLine()) {
                cypherT = cypher_T.nextLine();
            }

            PrintWriter writer1 = new PrintWriter("decrypted_M.txt", "UTF-8");

            String[] RD = Decryption(cypherT);

            y[0] = RD[2];
            y[1] = RD[3];
            y[2] = RD[0];
            y[3] = RD[1];

            writer1.print(output_Whitening(y));

            writer1.close();


        }
}
