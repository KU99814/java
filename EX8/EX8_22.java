//Guessing the capital

import javax.swing.JOptionPane;

public class EX8_22
{
 public static void main(String args[])
  {
   //題目
   String[] state = {"ALABAMA","ALASKA","ARIZONA","ARKANSAS","CALIFORNIA",
                     "COLORADO","CONNECTICUT","DELAWARE","FLORIDA","GEORGIA",
                     "HAWAII","IDAHO","ILLINOIS","INDIANA","IOWA",
                     "KANSAS","KENTUCKY","LOUISIANA","MAINE","MARYLAND",
                     "MASSACHUSETTS","MICHIGAN","MINNESOTA","MISSISSIPPI","MISSOURI",
                     "MONTANA","NEBRASKA","NEVADA","NEW HAMPSHIRE","NEW JERSEY",
                     "NEW MEXICO","NEW YORK","NORTH CAROLINA","NORTH DAKOTA","NORTH DAKOTA",
                     "OHIO","OKLAHOMA","OREGON","PENNSYLVANIA","RHODE ISLAND",
                     "SOUTH CAROLINA","SOUTH DAKOTA","TENNESSEE","TEXAS","UTAH",
                     "VERMONT","VIRGINIA","WASHINGTON","WEST VIRGINIA","WISCONSIN","WYOMING"};
   //答案
   String[] answer = {"Montgomery","Juneau","Phoenix","Little Rock","Sacramento",
                      "Denver","Hartford","Dover","Tallahassee","Atlanta",
                      "Honolulu","Boise","Springfield","Indianapolis","Des Moines",
                      "Topeka","Frankfort","Baton Rouge","Augusta","Annapolis",
                      "Boston","Lansing","St. Paul","Jackson","Jefferson City",
                      "Helena","Lincoln","Carson City","Concord","Trenton",
                      "Santa Fe","Albany","Raleigh","Bismarck","Bismarck",
                      "Columbus","Oklahoma City","Salem","Harrisburg","Providence",
                      "Columbia","Pierre","Nashville","Austin","Salt Lake City",
                      "Montpelier","Richmond","Olympia","Charleston","Madison","Cheyenne"};

   int count = 0;

   //問答與計分
   for(int i=0;i<state.length;i++)
    {
     String q = JOptionPane.showInputDialog("What is the capital of "+state[i]); //訊息欄顯示
     
     //判斷是否答對顯示訊息
     if(q.equals(answer[i]))
      {
       JOptionPane.showMessageDialog(null,"Your answer is correct",
                               "Message",JOptionPane.INFORMATION_MESSAGE);
       count++;
      }
     else
      JOptionPane.showMessageDialog(null,"The right answer is "+answer[i],
                                   "Message",JOptionPane.INFORMATION_MESSAGE);
     if(count % 10 == 0 && count!=0)//每10題顯示答對數
      JOptionPane.showMessageDialog(null,"The correct count is "+count,
                                      "Message",JOptionPane.INFORMATION_MESSAGE);
    }
  }
}