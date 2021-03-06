//GCD use commeand-Line

public class EX8_13
{
 public static void main(String args[])
  {
   int[] arg = new int[args.length]; //將文字換成數字

   for(int i=0;i<args.length;i++)
    arg[i] = Integer.parseInt(args[i]);//從命令列文字轉為數字

   int min = arg[0];

   for(int i=1;i<arg.length;i++) //找出最小值
   {
    if(arg[i] < min)
      min = arg[i];
   }

   int gcd = 1;

   for(int i=1;i<=min;i++) //找出GCD
    {
     boolean ok = true;

     for(int j=0;j<arg.length;j++)
      {
       if(arg[j]%i != 0)
        {
         ok = false;
         break;
        }
      }

     if(ok)
      gcd = i;
    }
   System.out.print("The GCD is "+gcd); //顯示
  }
}       