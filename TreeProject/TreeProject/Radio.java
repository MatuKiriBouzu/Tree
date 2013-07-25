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

class Radio extends Thread implements ActionListener{
    /**
     * ファイル選択のラジオボタンをactionPerformedとコンストラクタが共有するため
     * 7/23 虎谷
     */
    private JRadioButton[] fileRadio;
    /**
     * アニメーション選択のラジオボタンをactionPerformedとコンストラクタが共有するため
     * 7/25 虎谷
     */
    private JRadioButton[] animetionRadio;
    /**
     * 決定したファイルのパス
     * 7/23 虎谷
     */
    private String returnFilePath;
    /**
     * 決定したファイルのパス
     * 7/25 虎谷
     */
    private boolean returnAnimetion = false;
    /**
     * ウインドウ
     * 7/25 虎谷
     */
    private JFrame aWindow;
    
    
    /**
     * ファイル入力受け付けラジオボタンウインドウの作成を行う
     * 7/23 虎谷
     */
    
    public Radio(){
        aWindow = new JFrame("ファイル選択");
        aWindow.setBounds(100, 100, 180, 300);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel FilePanel = new JPanel();
        FilePanel.setPreferredSize(new Dimension(180, 140));
        fileRadio = new JRadioButton[4];
        fileRadio[0] = new JRadioButton("tree.txt",true);
        fileRadio[0].setPreferredSize(new Dimension(180, 20));
        fileRadio[1] = new JRadioButton("forest.txt");
        fileRadio[1].setPreferredSize(new Dimension(180, 20));
        fileRadio[2] = new JRadioButton("semilattice.txt");
        fileRadio[2].setPreferredSize(new Dimension(180, 20));
        fileRadio[3] = new JRadioButton("UserFile Open");
        fileRadio[3].setPreferredSize(new Dimension(180, 20));
        ButtonGroup group0 = new ButtonGroup();
        group0.add(fileRadio[0]);
        group0.add(fileRadio[1]);
        group0.add(fileRadio[2]);
        group0.add(fileRadio[3]);
        JLabel fileLabel = new JLabel("FILE SELECT");
        fileLabel.setPreferredSize(new Dimension(180, 30));
        FilePanel.add(fileLabel);
        FilePanel.add(fileRadio[0]);
        FilePanel.add(fileRadio[1]);
        FilePanel.add(fileRadio[2]);
        FilePanel.add(fileRadio[3]);
        
        
        JPanel animetionPanel = new JPanel();
        animetionPanel.setPreferredSize(new Dimension(180, 50));
        animetionRadio = new JRadioButton[2];
        animetionRadio[0] = new JRadioButton("ON",true);
        animetionRadio[0].setPreferredSize(new Dimension(180, 20));
        animetionRadio[1] = new JRadioButton("OFF");
        animetionRadio[1].setPreferredSize(new Dimension(180, 20));
        ButtonGroup group1 = new ButtonGroup();
        group1.add(animetionRadio[0]);
        group1.add(animetionRadio[1]);
        JLabel animetionLabel = new JLabel("ANIMETION SELECT");
        animetionLabel.setPreferredSize(new Dimension(180, 30));
        animetionPanel.add(animetionLabel);
        animetionPanel.add(animetionRadio[0]);
        animetionPanel.add(animetionRadio[1]);
        
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("決定");
        button.setPreferredSize(new Dimension(180, 20));
        button.addActionListener(this);
        
        buttonPanel.add(button);
        
        Container contentPane = aWindow.getContentPane();
        contentPane.add(FilePanel, BorderLayout.NORTH);
        contentPane.add(animetionPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        aWindow.setVisible(true);
    }
    
    /**
     * 当インスタンスでの入力があるまで処理を停止する。その後当ウインドウを消す
     * 7/25 虎谷
     */
    public synchronized void waitEvent(){
        try {
            // writeメソッド内でnotifyが呼ばれるまで待機します。
            wait();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        aWindow.setVisible(false);
    }
    
    /**
     * ファイル選択のラジオボタンの情報を返す。ただしその入力があるまで処理を停止する
     * 7/25 虎谷
     */
    public String selectFile(){
        return returnFilePath;
    }
    
    /**
     * アニメーション選択のラジオボタンの情報を返す。
     * 7/25 虎谷
     */
    public boolean selectAnimetion(){
        return returnAnimetion;
    }
    
    /**
     * どのボタンが押されたかを検出、検出後ctFile()を再会する
     * 7/23 虎谷
     */
    public synchronized void actionPerformed(ActionEvent e){
        
        File[] filePath = new File[4];
        filePath[0] = new File("./ExampleText/tree.txt");
        filePath[1] = new File("./ExampleText/forest.txt");
        filePath[2] = new File("./ExampleText/semilattice.txt");
        filePath[3] = null;
        for (int i = 0 ; i < fileRadio.length; i++){
            if (fileRadio[i].isSelected()){
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
            
            if (animetionRadio[0].isSelected()){
                returnAnimetion = true;
            }
            
        }
        notifyAll();//wait解除
        System.out.println("ファイルパス:"+returnFilePath);
    }
}