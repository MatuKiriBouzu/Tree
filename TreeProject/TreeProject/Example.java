package TreeProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.util.Map;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
//↑追記部分 虎谷6/13

public class Example {
	
	public static void main(String[] args)
	{
		Font aFont=new Font("Serif",Font.PLAIN,12);//要求仕様
		boolean process = false;//アニメーションのオンオフ
		Dimension aDimension = new Dimension(800,700);//ウインドウサイズ
		
		String date = "./ExampleText/tree.txt";//デフォルトでこのテキストを呼び出し、後でユーザに読み込みをしてもらう
		
		
		if(args.length==1){
			date = args[0];
		}
		String[] aStrings = null;
		aStrings = date.split("/");
		String title = (aStrings[aStrings.length-1]);
			
		TreeModel aModel = new TreeModel();
		aModel.setFont(aFont);
		TreeController aController = new TreeController();
		aModel.inputTree(date);//view展開前に先にデータの読み込み、体裁を整えておく
		
		/*for(Map.Entry<Integer,TreeNode> e : aModel.nodes.entrySet())//トップノード表示
		 {
		 TreeNode buf = e.getValue();
		 int i  =e.getKey();
		 System.out.println("key:"+i+" Value:"+buf.date+" Level:"+buf.level);
		 }*/
		
		TreeView aView= new TreeView(aModel,aController);
		Example.open(aView,aDimension,title);
		aModel.calculateTree(process);
		System.out.println("疑問点：ブランチ間の交差は容認されるものなのか？(semilattice.txtにて発生)");
	}
	
	private static void open(TreeView aView, Dimension aDimension,String title)
	{
		JFrame aWindow = new JFrame(title);
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("ファイル");
		JMenuItem menuItem1 = new JMenuItem("開く");
		JMenuItem menuItem2 = new JMenuItem("終了");
		
		menu.add(menuItem1);
		menu.add(menuItem2);
		menubar.add(menu);
		aWindow.setJMenuBar(menubar);
		Container contentPane = aWindow.getContentPane();
		
		//aWindow.addMouseListener(this);
		
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent anActionEvent)
			{
				System.out.println("開く");//ここにロード時の処理を書く
				
				//試験的に導入(7/15)
				JFileChooser fileChooser = new JFileChooser();
				FileFilter fileFilter = new FileNameExtensionFilter("Text (*.txt)", "txt");
				fileChooser.addChoosableFileFilter(fileFilter);
				int answer = fileChooser.showOpenDialog(null);
				//if(answer != JFileChooser.APPROVE_OPTION) return;
				File aFile = fileChooser.getSelectedFile();
				
				System.out.println(aFile);
				//このファイルを適切な位置に受け渡してくださいな 2013.7.15 出口
				
			}
	    });
		
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent anActionEvent)
			{
				System.exit(0);
			}
	    });
		
		aWindow.getContentPane().add(aView);
		aWindow.setMinimumSize(aDimension);
		aWindow.setMaximumSize(aDimension);
		aWindow.setResizable(false);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(aDimension.width, aDimension.height + titleBarHeight);
		aWindow.setLocation(50, 70);
		aWindow.setVisible(true);
	}
	//↑追記部分 虎谷6/13
}