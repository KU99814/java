//Finding numbers divisible by 5 or 7 ,but not both

public class EX4_11
{
 public static void main(String args[])
  {
   int count = 0; //�p���X�Ӽ� ���ƥ�
   for(int i = 100;i<1001;i++)//�q100��1000
    {
     if(i%5==0 ^ i%7==0) //�Q5��7�����Q��̾㰣
      {
       if(count > 9)
        {
         System.out.print("\n");
         count = 0;
        }
       System.out.print(i+" ");
       count++;
      }
    }
  }
}