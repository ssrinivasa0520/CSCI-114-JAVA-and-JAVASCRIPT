import java.util.*;
//bar code class
public class barcode {
static String weight[]={"||:::",":::||","::|:|","::||:",":|::|",":|:|:",":||::","|:::|","|::|:","|:|::"};//storing encodings in an array
public static void zipToBar(String zip){
       String bars="|";//surrounding full bars
       int sum=0;
       for(int i=0;i<zip.length();i++){
    	   //using parseInt for conversion of the String to numbers, covered in Chapter 11. I don't want to over complicate the program, it's why I am using this.
           sum+=Integer.parseInt(""+zip.charAt(i));//for calculation of check bit
           bars+=weight[Integer.parseInt(""+zip.charAt(i))];//adding encoding of each digit of zip
       }
       bars+=weight[10-sum%10]+"|";//adding check encoding and final complete bar frame
       System.out.println("Translating "+zip+" to bar we get: ");
       System.out.println("\t"+bars);//outputting encoded bar
      
   }
public static void barToZip(String bars){
       String zip="";
       if(bars.length()!=32){//checking on input length
           System.out.println("INPUT ERROR: The bar code must be 32 bars long.");
           return;
       }
       if((bars.charAt(0)!='|')||(bars.charAt(bars.length()-1)!='|')){//checking on input format
           System.out.println("INPUT FORMAT ERROR: The bar code must have full height frame bars on each side.");
           return;
       }
       int check = 0,sum=0;
       for(int i=1;i<bars.length()-1;i+=5){
           //processing each encoding
           int weightSeven = ((bars.charAt(i)==':')?0:1);
           int weightFour = ((bars.charAt(i+1)==':')?0:1);
           int weightTwo = ((bars.charAt(i+2)==':')?0:1);
           int weightOne = ((bars.charAt(i+3)==':')?0:1);
           int weightZero = ((bars.charAt(i+4)==':')?0:1);
           if(weightSeven+weightFour+weightTwo+weightOne+weightZero!=2){//checking if encoding is invalid
               System.out.println("INPUT ERROR: There can only be exact 2 full bars in each bar code encoding.");
               return;
           }
           if(i!=bars.length()-6){
               sum+=((7*weightSeven+4*weightFour+2*weightTwo+1*weightOne+0*weightZero)%11);//calculating check for decoded zip
           zip+=((7*weightSeven+4*weightFour+2*weightTwo+1*weightOne+0*weightZero)%11);//adding decoded number to zip (11 for 0 encoding)
           }
           else
               check = ((7*weightSeven+4*weightFour+2*weightTwo+1*weightOne+0*weightZero)%11);//decoding check
       }
       System.out.println("Translating "+bars+" to back to zip code we get: ");//outputting
       System.out.println("\tZip : "+zip);
       System.out.println("\tCheck : "+check);
       if(check!=10-sum%10)//if decoded check does not match calculated check
           System.out.println("CHECK ERROR: There is some mismatch of digits since the check is wrong.");
      
   }
  
public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
       while(true){
           System.out.println("\nM E N U");
           System.out.println("Press 1 to convert zip to bar");
           System.out.println("Press 2 to convert bar to zip");
           System.out.println("Press 0 to exit");
           System.out.print("\n Your choice: ");
          
           int ch = sc.nextInt();
           String zip,bars;
           if(ch==0)
               break;
           else if(ch==1){
               System.out.print("\nEnter a zip code: ");
               zip = sc.next();
               zipToBar(zip);
           }
           else if(ch==2){
               System.out.print("\nEnter a bar code: ");
               bars = sc.next();
               barToZip(bars);
           }
           else
               System.out.print("\nInvalid input!!");
       }

   }

}