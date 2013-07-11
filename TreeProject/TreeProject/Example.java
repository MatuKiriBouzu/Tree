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
//↑追記部分 虎谷6/13

public class Example {

	public static void main(String[] args)
	{
		TreeModel aModel = new TreeModel();
		TreeNode aNode = null;
		TreeBranch aBranch = null;
		aModel.inputTree("aaaa");//view展開前に先にデータの読み込み、体裁を整えておく
		
		for(Map.Entry<Integer,TreeNode> e : aModel.nodes.entrySet())//トップノード表示
		{
			TreeNode buf = e.getValue();
			int i  =e.getKey();
			System.out.println("key:getValue"+i+buf.date);
		}
		
		
		//a.inputTree("aaaa");
		//a.calculateTree();
		//a.branchCalc();
		//モデル処理からVIEWに取り入れ→viewに取り入れからモデル処理に変更
		

		
		TreeView aView= new TreeView(aModel);


//		aModel.calculateTree();//アニメーション用に切る
		Dimension aDimension = new Dimension(1250,700);
		Example.open(aView,aDimension);
		//↑追記部分 虎谷6/13
		aModel.animationTree();
		aModel.branchCalc();
		
		aBranch = (TreeBranch)aModel.branchs.get(1);
		System.out.println(aBranch.parent);
	}
	
	private static void open(TreeView aView, Dimension aDimension)
	{
		JFrame aWindow = new JFrame("Forest");
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