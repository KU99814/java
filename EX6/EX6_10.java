//找出最小數的陣列索引

public class EX6_10
{
 public static void main(String args[])
  {
   int[] num = {1,2,4,5,10,100,2,-22}; //設陣列
   
   System.out.print("The smallest element's index is "+min(num)); //顯示
  }

 public static int min(int... num) //運算
  {
   int mIndex = 0;
   int m = num[0];
   //比較最小值
   for(int i=1;i<num.length;i++)
    {
     if(num[i]<m)
      {
       m = num[i];
       mIndex = i;
      }
    }

   return mIndex; //回傳最小值的索引
  }
}