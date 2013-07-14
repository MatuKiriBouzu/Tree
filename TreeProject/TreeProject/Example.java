package TreeProject;

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
		String date = "./TreeProject/tree.txt";
		
		//String date = "./TreeProject/tree.txt";
		//String date = "./TreeProject/forest.txt";
		
		
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