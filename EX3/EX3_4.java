//learning addition

import java.util.Scanner;

public class EX3_4
{
 public static void main(String args[])
  {
   Scanner input = new Scanner(System.in); //宣告輸入
   
   //以現在時間產生兩個小於100得數
   int number1 = (int)(System.currentTimeMillis()%100);
   int number2 = (int)(System.currentTimeMillis() *7 % 100);

   System.out.print("What is " + number1+" + "+number2 +"? ");
   int answer = input.nextInt(); //輸入答案

   //顯示結果
   System.out.println(number1 + " + "+ number2 + " = "+ answer + " is "+(number1+number2==answer));
  }
}