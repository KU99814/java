//Binary tree insert animation

import javax.swing.*;

public class EX25_14 extends JFrame
{
 public EX25_14()
  {
   BinaryTree<Integer> tree = new BinaryTree<Integer>();//�Ы�binary tree

   //��Jelement
   tree.insert(50);
   tree.insert(25);
   tree.insert(75);
   tree.insert(12);
   tree.insert(30);
   tree.insert(70);
   tree.insert(80);
   tree.insert(10);
   tree.insert(110);

   add(new BinaryTreeView(tree));//�Npanel�[�Jframe
  }

 public static void main(String args[])
  {
   EX25_14 frame = new EX25_14();//�ŧiframe
   
   frame.setSize(500,500);//�j�p
   frame.setTitle("EX25_14");//���D
   frame.setLocationRelativeTo(null);//�۹��m
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����ʧ@
   frame.setVisible(true);//���
  }
}