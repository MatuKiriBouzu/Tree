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
/**
 実行本体(main) 
**/
public class Example {
	/**
	 仕様書の文字:Serif系の標準体(plain)で12pt
	 **/
	static final Font aFont=new Font("Serif",Font.PLAIN,12);//要求仕様
	/**
	 ウィンドウのサイズを固定する定数800*700とする。
	 **/
	static Dimension aDimension = new Dimension(800,700);//ウインドウサイズ
	/**
	 main
	 **/
	public static void main(String[] args)
	{
        //==================================変更点7/23====↓
        Radio aRadio = new Radio();
        aRadio.waitEvent();//==================================変更点7/25====
        String filePath = aRadio.selectFile();
        if(filePath == null)
        {
            filePath = fileOpen();
        }
        String[] fileName = filePath.split("/");
        //==================================変更点7/23====↑
        boolean isViewProcess = aRadio.selectAnimetion();//==================================変更点7/25====
		
		//デフォルトでこのテキストを呼び出し、後でユーザに読み込みをしてもらう
		TreeModel aModel = new TreeModel();
		aModel.setFont(aFont);
		TreeController aController = new TreeController();
		aModel.inputTree(filePath);//view展開前に先にデータの読み込み、体裁を整えておく
		
		TreeView aView= new TreeView(aModel,aController);
		Example.open(aView,aDimension,fileName[fileName.length-1]);//==================================変更点7/23====
		aModel.calculateTree(isViewProcess);
		System.out.println("疑問点：ブランチ間の交差は容認されるものなのか？(semilattice.txtにて発生)");
	}
	/**
	 樹状整列のBufferedImageを置くJPanelを開く
	 **/
	private static void open(TreeView aView, Dimension aDimension,String title)
	{
		JFrame aWindow = new JFrame(title);
		Container contentPane = aWindow.getContentPane();
	
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
	
	/**
	 * 整列するテキストファイルのファイルパスを応答するメソッド。
	 * 出口 テスト結果:良好(7/22)
	 */
	private static String fileOpen()
	{
		JFileChooser fileChooser = new JFileChooser();
		FileFilter fileFilter = new FileNameExtensionFilter("Text (*.txt)", "txt");
		fileChooser.addChoosableFileFilter(fileFilter);
		int answer = fileChooser.showOpenDialog(null);
		if(answer != JFileChooser.APPROVE_OPTION)
		{
            System.exit(0);
			return null;
		}else
		{
            File aFile = fileChooser.getSelectedFile();
            return aFile.getPath();
        }		
	}
}