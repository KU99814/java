import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class EX30_7_Client extends JFrame
{
 //常數 代表剪刀石頭布
 static final int ROCK = 1;
 static final int PAPER = 2;
 static final int SCISSORS = 3;

 JLabel listenLabel = new JLabel("\t\tlisten\t\t",JLabel.CENTER);//顯示現在誰出

 private int yourScore = 0;//自己得分
 private int othersScore = 0;//對手得分

 private JLabel myScore = new JLabel("  0  ",JLabel.CENTER);//自己得分顯示
 private JLabel otherScore = new JLabel("  0  ",JLabel.CENTER);//對手得分顯示
  
 private JTextArea jtaResult = new JTextArea("",8,25);//顯示結果

 private int nowNum = 1;//現在出的拳
 private boolean isChosen = false;//判斷是否允許傳送資料
 private boolean canDo = false;//判斷現在是否動作
 
 //radio button
 private JRadioButton jrbRock = new JRadioButton("Rock");//石頭
 private JRadioButton jrbPaper = new JRadioButton("Paper");//布
 private JRadioButton jrbScissors = new JRadioButton("Scissors");//剪刀
 private JButton btnPlay = new JButton("Play");

 private JLabel otherPlay = new JLabel();//顯示對手玩家出的拳

 Socket socket;

 public EX30_7_Client()
  {
   JPanel showPanel = new JPanel(new BorderLayout());//得分資訊panel

   JPanel listenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//顯示誰出的panel

   //設定排版與加入   
   listenLabel.setFont(new Font("TimesRoman",Font.BOLD,15));
   listenLabel.setBorder(new LineBorder(Color.red,1));
   listenPanel.add(listenLabel);

   showPanel.add(listenPanel,BorderLayout.NORTH);

   //顯示遊玩得分資訊
   //Score Label
   JPanel resultPanel = new JPanel(new BorderLayout());
   JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   JLabel scoreLabel = new JLabel("Score",JLabel.CENTER);
   scorePanel.add(scoreLabel);

   //顯示得分
   JPanel playerScorePanel = new JPanel(new GridLayout(2,2,5,5));
   playerScorePanel.add(new JLabel("This Player",JLabel.CENTER));
   playerScorePanel.add(new JLabel("other Player",JLabel.CENTER));
   playerScorePanel.add(myScore);//自己得分
   playerScorePanel.add(otherScore);//他人得分
   
   //放入結果資訊欄
   resultPanel.add(scorePanel,BorderLayout.NORTH);
   resultPanel.add(playerScorePanel,BorderLayout.CENTER);

   //放入顯示panel
   showPanel.add(resultPanel,BorderLayout.CENTER);

   JPanel playsLabelPanel = new JPanel(new BorderLayout());//play文字的panel
   JPanel boardPanel = new JPanel(new BorderLayout());//放置textarea
   
   playsLabelPanel.add(new JLabel("   Plays"),BorderLayout.NORTH);

   //加入textarea
   JScrollPane scrollPane = new JScrollPane(jtaResult);
   JPanel jtaPanel = new JPanel(new FlowLayout());  
   jtaPanel.add(scrollPane);

   //按鈕panel
   JPanel buttonPanel = new JPanel(new FlowLayout());
   JPanel radioPanel = new JPanel(new FlowLayout());//radio panel
   radioPanel.add(jrbRock);
   radioPanel.add(jrbPaper);
   radioPanel.add(jrbScissors);

   radioPanel.setBorder(new LineBorder(Color.black,1));

   //放入按鈕
   buttonPanel.add(radioPanel);
   buttonPanel.add(btnPlay);

   //按鈕分群
   ButtonGroup group = new ButtonGroup();
   group.add(jrbRock);
   group.add(jrbPaper);
   group.add(jrbScissors);

   //顯示其他玩家資訊
   JPanel otherPlayLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
   otherPlayLabel.add(otherPlay);

   //排版
   JPanel bottomPanel = new JPanel(new GridLayout(2,1));
   bottomPanel.add(buttonPanel);
   bottomPanel.add(otherPlayLabel);

   JPanel playPanel = new JPanel(new GridLayout(2,1));
   playPanel.add(jtaPanel);
   playPanel.add(bottomPanel);

   //遊玩過程panel排版
   boardPanel.add(playsLabelPanel,BorderLayout.WEST);
   boardPanel.add(playPanel,BorderLayout.CENTER);

   //frame排版
   setLayout(new BorderLayout());
   add(showPanel,BorderLayout.NORTH);
   add(boardPanel,BorderLayout.CENTER);

   jtaResult.setText("You player\tThey played\tWinner\t\t");

   //選擇石頭
   jrbRock.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     nowNum = 1;
    }
   });

   //選擇布
   jrbPaper.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     nowNum = 2;
    }
   });

   //選擇剪刀
   jrbScissors.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     nowNum = 3;
    }
   });

   //出拳
   btnPlay.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
     //判斷是否可以動作
     if(canDo)
      {
       isChosen = true;
       canDo = false;
      }
    }
   });

   new Thread(new HandleTask()).start();//執行task
  }

 //猜拳task
 public class HandleTask implements Runnable
  {
   //傳送資料
   DataOutputStream toServer;
   DataInputStream fromServer;

   public HandleTask()
    {}

   public void run()
    {
     try{
      socket = new Socket("localhost",8000);//接受連線  
      toServer = new DataOutputStream(socket.getOutputStream());
      fromServer = new DataInputStream(socket.getInputStream());
     
      while(true)
       {
        //接收對方資料
        int otherNum = fromServer.readInt();
        listenLabel.setText("\t\tcall\t\t");
        
        canDo = true;//設為可動

        while(!isChosen)
         {}

        //傳送資料
        isChosen = false;
        toServer.writeInt(nowNum);
        listenLabel.setText("\t\tlisten\t\t");

        compareRPS(nowNum,otherNum);//比較
        otherPlay.setText("other play "+playValue(otherNum));
        canDo = false;
       }
     }
     catch(IOException ex){
      System.err.println(ex);
     }
    }
  }

 //比較勝負
 public void compareRPS(int player,int opponent)
  {
   //結果文字
   String text = "\n"+playValue(player)+"\t"+playValue(opponent)+"\t";

   if(player==ROCK)
    {
     switch(opponent)
      {
       case ROCK:     text+="no winner\t";
                      break;
       case PAPER:    text+="they won\t";
                      othersScore++;
                      break;
       case SCISSORS: text+="you won\t";
                      yourScore++;
                      break;
      }
    }
   else if(player==PAPER)
    {
     switch(opponent)
      {
       case ROCK:     text+="you won\t";
                      yourScore++;
                      break;
       case PAPER:    text+="no winner\t";
                      break;
       case SCISSORS: text+="they won\t";
                      othersScore++;
                      break;
      }
    }
    else
    {
     switch(opponent)
      {
       case ROCK:     text+="they won\t";
                      othersScore++;
                      break;
       case PAPER:    text+="you won\t";
                      yourScore++;
                      break;
       case SCISSORS: text+="no winner\t";
                      break;
      }     
    }

   //顯示得分
   myScore.setText("  "+yourScore+"  ");
   otherScore.setText("  "+othersScore+"  ");

   jtaResult.setText(jtaResult.getText()+text);//顯示結果
  }

 //顯示猜拳文字
 public String playValue(int num)
  {
   String ret;
   switch(num)
    {
     case ROCK:ret = "Rock";break;
     case PAPER:ret = "Paper";break;
     case SCISSORS: ret = "Scissors";break;
     default:ret = "";
    }

   return ret;
  }

 public static void main(String args[])
  {
   EX30_7_Client frame = new EX30_7_Client();

   frame.setTitle("EX30_7_Client");//名稱
   frame.setSize(500,500);//大小
   frame.setLocationRelativeTo(null);//相對位置
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//關閉動作
   frame.setVisible(true);//顯示
  }
}