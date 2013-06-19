package TreeProject;

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
		TreeModel a = new TreeModel();
		TreeBranch k = null;
		a.inputTree("aaaa");
		a.calculateTree();
		a.branchCalc();
		
		TreeView aView= new TreeView(a);
		Dimension aDimension = new Dimension(1250,700);
		Example.open(aView,aDimension);
		//↑追記部分 虎谷6/13
		
		k = (TreeBranch)a.branchs.get(1);
		System.out.println(k.parent);
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