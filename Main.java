//Encryption Project By Daniel C & Jie R//
class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){  
// Encoding the plaintext:
    String file = Input.readFile("Original.txt");
   
    // Random cipher shift array
int[] randCipher = new int[file.length()];
for (int x=0; x<=file.length()-1; x++)
randCipher[x] = randInt(1,5);
//Testing Array
//for (int x=0; x<=file.length()-1; x++)
// System.out.print(randCipher[x]);

//Testing for decimal to binary
    //int decimal = 50; // Example decimal number  
    //System.out.println(decimalToBinary(decimal));

//Testing binary to decimal
//int binary = Integer.parseInt(decimalToBinary(decimal));
//System.out.println(getDecimal(binary));

//testing decode2
//String testingBinary = "00100001001000010010001000100011";
//System.out.println(decode2(testingBinary));


    // Encode level 1 (Random Cipher Shift)
    String encodedMsg1 = encode1(file,randCipher);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (Decimal to Binary)
    String encodedMsg2 = encode2(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // // Encode level 3 (1s to 0s & 0s to 1)
    String encodedMsg3 = encode3(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);
// // Encode level 4 (Ascii to char)
    String encodedMsg4 = encode4(encodedMsg3);
    Input.writeFile("Encode4.txt", encodedMsg4);

   
    // Decoding the ciphertext:
    String file2 = Input.readFile("Encode4.txt");
// Decode level 2  (Char to ascii)
    String decodedMsg1 = decode4(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2  (1s to 0s & 0s to 1)
    String decodedMsg2 = decode3(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (Binary to decimal)
    String decodedMsg3 = decode2(encodedMsg2);
    Input.writeFile("Decode3.txt", decodedMsg3);
    // Decode level 4 (Random Cipher Shift)
    String decodedMsg4 = decode1(decodedMsg3,randCipher);
    Input.writeFile("Decode4.txt", decodedMsg4);
   
  }
 
  // Random Cipher Shift ++
  String encode1(String txt,int[] randCipher){
    String build = "";
    int ascii = 0;
    char ch = '\0';
    for(int x=0; x<=txt.length()-1; x++){
      ch = txt.charAt(x);
      ascii = (int)ch;
      ascii += randCipher[x];
      build += (char)ascii;
    }    
    return build;
  }
 
  // Random Cipher Shift --
  String decode1(String txt,int[] randCipher){
    String build="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=txt.length()-1; x++){
      ch=txt.charAt(x);
      ascii = (int)ch;
      ascii -= randCipher[x];
        build += (char)ascii;
    }
    return build;
  }
 
  // Decimal to Binary
  public static String decimalToBinary(int decimal) {  
        StringBuilder binary = new StringBuilder();        
        // Continue dividing the decimal number by 2 until it becomes 0  
        while (decimal > 0) {  
            // Get the remainder when dividing by 2  
            int remainder = decimal % 2;  
            // Prepend the remainder of the binary representation  
            binary.insert(0, remainder);  
            // Update the quotient for the next iteration  
            decimal = decimal / 2;  
        }          
        // If the input decimal was 0, return "0"  
        if (binary.length() == 0) {  
            binary.append("0");  
        }          

//makes the binary an 8bit
        String binary1 = binary.toString();  
if(binary1.length() == 0)
return "00000000" + binary1;
else if(binary1.length() == 1)
return "0000000" + binary1;
else if(binary1.length() == 2)
return "000000" + binary1;
else if(binary1.length() == 3)
return "00000" + binary1;
else if(binary1.length() == 4)
return "0000" + binary1;
else if(binary1.length() == 5)
return "000" + binary1;
else if(binary1.length() == 6)
return "00" + binary1;
else if(binary1.length() == 7)
return "0" + binary1;
else
return binary1;

    }  

//
String encode2(String txt){
char ch = '\0';
int ascii = 0;
String build = "";
for(int x=0;x < txt.length();x++){
ch = txt.charAt(x);
ascii = (int)ch;
build += decimalToBinary(ascii);
}
return build;
}

// Binary to Decimal
public static int getDecimal(int binary){  
int decimal = 0;  
int n = 0;  
while(true){  
if(binary == 0){  
break;  
} else {  
int temp = binary%10;  
decimal += temp*Math.pow(2, n);  
binary = binary/10;  
n++;  
}  
}
return decimal;  
}  

String decode2(String txt){
int count = 8;
int binary = 0;
int ascii = 0;
char ch = '\0';
String build = "";
for(int x=0;x<txt.length();x+=8){
binary = Integer.parseInt(txt.substring(x,count));
//System.out.println(binary);
ascii = getDecimal(binary);
//System.out.println(ascii);
ch = (char)ascii;
build += ch;
count += 8;
}
return build;
}

//1s to 0s and 0s to 1s
String encode3(String txt){
int count = 1;
int num = 0;
String build = "";
for(int x = 0 ; x < txt.length() ; x++){
num = Integer.parseInt(txt.substring(x,count));
if(num==1)
build += "0";
else
build += "1";
count++;
}
return build;
}

//0s to 1s and 1s to 0s
String decode3(String txt){
int count = 1;
int num = 0;
String build = "";
for(int x = 0 ; x < txt.length() ; x++){
num = Integer.parseInt(txt.substring(x,count));
if(num==1)
build += "0";
else
build += "1";
count++;
}
return build;
}

//Binary to char
String encode4(String txt){
int count = 8;
int binary = 0;
int ascii = 0;
char ch = '\0';
String build = "";
for(int x=0;x<txt.length();x+=8){
binary = Integer.parseInt(txt.substring(x,count));
//System.out.println(binary);
ascii = getDecimal(binary);
//System.out.println(ascii);
ch = (char)ascii;
build += ch;
count += 8;
}
return build;
}

//Char to Binary
String decode4(String txt){
char ch = '\0';
int ascii = 0;
String build = "";
for(int x=0;x < txt.length();x++){
ch = txt.charAt(x);
ascii = (int)ch;
build += decimalToBinary(ascii);
}
return build;
}

  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }

}