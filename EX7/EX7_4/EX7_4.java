public class EX7_4
{
 public static void main(String args[])
  {
   Stock Stock1 = new Stock("SUNW","Sun Microsystems Inc");//宣告股票

   //設定收盤價與現在價錢
   Stock1.setPreviousClosingPrice(100);
   Stock1.setCurrentPrice(90);

   //顯示股票狀態
   System.out.print("The "+Stock1.getSymbol()+" "+Stock1.getName());
   System.out.print("The percentage is "+(double)((int)(Stock1.getChangePercent()*100))/100+"%");
  }
}