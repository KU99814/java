//Binary tree insert animation

import javax.swing.*;

public class EX25_14 extends JFrame
{
 public EX25_14()
  {
   BinaryTree<Integer> tree = new BinaryTree<Integer>();//創建binary tree

   //放入element
   tree.insert(50);
   tree.insert(25);
   tree.insert(75);
   tree.insert(12);
   tree.insert(30);
   tree.insert(70);
   tree.insert(80);
   tree.insert(10);
   tree.insert(110);

   add(new BinaryTreeView(tree));//將panel加入frame
  }

 public static void main(String args[])
  {
   EX25_14 frame = new EX25_14();//宣告frame
   
   frame.setSize(500,500);//大小
   frame.setTitle("EX25_14");//標題
   frame.setLocationRelativeTo(null);//相對位置
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//關閉動作
   frame.setVisible(true);//顯示
  }
}