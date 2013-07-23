package TreeProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * ファイル入力選択をラジオボタンにて促す
 * 7/23 虎谷
 */

class radio extends Thread implements ActionListener{
    /**
     * ラジオボタンをactionPerformedとコンストラクタが共有するため
     * 7/23 虎谷
     */
    private JRadioButton[] radio;
    /**
     * 決定したファイルのパス
     * 7/23 虎谷
     */
    private String returnFilePath;
    
    
    /**
     * ファイル入力受け付けラジオボタンウインドウの作成を行う
     * 7/23 虎谷
     */
    
    public radio(){
        JFrame aWindow = new JFrame("ファイル選択");
        aWindow.setBounds(100, 100, 180, 180);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel aPanel = new JPanel();
        
        radio = new JRadioButton[4];
        radio[0] = new JRadioButton("tree.txt",true);
        radio[0].setPreferredSize(new Dimension(180, 20));
        radio[1] = new JRadioButton("forest.txt");
        radio[1].setPreferredSize(new Dimension(180, 20));
        radio[2] = new JRadioButton("semilattice.txt");
        radio[2].setPreferredSize(new Dimension(180, 20));
        radio[3] = new JRadioButton("FileSelect");
        radio[3].setPreferredSize(new Dimension(180, 20));
        
        ButtonGroup group = new ButtonGroup();
        group.add(radio[0]);
        group.add(radio[1]);
        group.add(radio[2]);
        group.add(radio[3]);
        
        aPanel.add(radio[0]);
        aPanel.add(radio[1]);
        aPanel.add(radio[2]);
        aPanel.add(radio[3]);
        
        JPanel buttonPanel = new JPanel();
        
        JButton button = new JButton("決定");
        button.addActionListener(this);
        
        buttonPanel.add(button);
        
        Container contentPane = aWindow.getContentPane();
        contentPane.add(aPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        aWindow.setVisible(true);
    }
    
    /**
     * ラジオボタンの情報を返す。ただしその入力があるまで処理を停止する
     * 7/23 虎谷
     */
    public synchronized String selectFile(){
        try {
            // writeメソッド内でnotifyが呼ばれるまで待機します。
            wait();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return returnFilePath;
    }
        
    /**
     * どのボタンが押されたかを検出、検出後selectFile()を再会する
     * 7/23 虎谷
     */
    public synchronized void actionPerformed(ActionEvent e){

        File[] filePath = new File[4];
        filePath[0] = new File("./ExampleText/tree.txt");
        filePath[1] = new File("./ExampleText/forest.txt");
        filePath[2] = new File("./ExampleText/semilattice.txt");
        filePath[3] = null;
        for (int i = 0 ; i < radio.length; i++){
            if (radio[i].isSelected()){
                if(filePath[i]==null)
                {
                    returnFilePath = null;
                }
                else
                {
                    if(filePath[i].exists())
                    {
                        returnFilePath = filePath[i].toString();//make test時の場合
                    }
                    else
                    {
                        String aString = filePath[i].toString();//app実行時の場合
                        String[] aStringArray = aString.split("/");
                        returnFilePath = "./" + aStringArray[aStringArray.length-1];
                    }
                }

            }
        }
        notifyAll();//wait解除
        System.out.println("ファイルパス:"+returnFilePath);
    }
}