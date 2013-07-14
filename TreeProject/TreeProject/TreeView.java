/*
 クラス図に関する重要メモ:
 
 小林祐希 144524 2013/06/20
 フィールド二つ(treeImage,viewport)、メソッド一つ(paintTree)増やしました。再検討必須。
 */

package TreeProject;



import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Font;
import java.awt.Dimension;

public class TreeView extends mvc.View
{
    /**
     * 情報(Node,Blanch等)を格納するモデル体を管理する変数。
     * modelからTreeModelに変更、間違っているかも6/13虎谷
	 * getModel()に役割移転  7/14 虎谷
     **/
	//protected TreeModel aModel;
    
    /**
     * マウスカーソルによるスクロール機能等を担当するコントローラー体を管理する変数。
     **/
	//protected TreeController controller;//
	
    
    /*ここ三変数付け足しました。*/
    /**
     * このクラスではモデル体の情報を利用して画像を構成する処理を行うのだが、その処理結果の格納先。
     * 小林祐希 144524 2013/6/20
     **/
    private BufferedImage treeImage;
    
    /**
     * paintComponentメソッドでtreeImageを描画するが、その表示位置を管理する変数。
     * 小林祐希 144524 2013/6/20
     **/
    private Point viewport=new Point(0,0);
    
    /**
     * paintTreeメソッドで使う筆
     * 小林祐希 144524 2013/6/20
     **/
    private Graphics2D aGraphics2D;
	
	/**
     * 課題のフォント指定事項
     * 虎谷　7/14
     **/
	private Font aFont;
	
    
    
    /**
     * コンストラクタ
     * 親で代入しているが、描画できなくなるのでもう一度上書き　間違っているかも6/13虎谷
     * コンス"トラ"クタ……"虎"谷……深夜テンション小林
	 * 修正完了。親から帰ってきたものを型変換の上格納　7/5　虎谷
     **/
	public TreeView(TreeModel aModel,TreeController aController)//
	{
		super(aModel,aController);
		aFont = getModel().getFont();
	}
	
	/**
	 * 自分のモデルを応答する。
	 */
	public TreeModel getModel()
	{
		return (TreeModel)(this.model);
		
	}
	
	/**
	 * 自分のモデルを応答する。
	 */
	
    /**
     * 描画メソッド(継承関係により、この場においては自動実行型の描画用メソッドとの認識で良い？)
     * ほぼ流用6/13虎谷
     * BufferedImageへの描画にしてみた。:144524 小林 2013/6/20
	 * 最適化を実施　元のMVCを最大限活用 虎谷　7/13
     **/
    public void paintComponent(Graphics aGraphics)
    {
		this.paintTree();
        super.paintComponent(aGraphics);
        //treeImage=this.paintTree(aGraphics2D);
		//viewport=this.scrollAmount();
		//aGraphics.drawImage(treeImage , -viewport.x , -viewport.y ,this);
    }
    
    /**
     * モデル体の情報を利用して画像を構成する描画メソッド。
     * 小林祐希 144524 2013/6/20
     * 虎谷　7/13 直接モデル受け渡しスタイル=>　モデルに一旦格納スタイルへ
     * 
     **/
	private void paintTree()
	//private BufferedImage paintTree(Graphics2D aGraphics2D)
	{
		TreeModel aModel=getModel();
        int width =(int) aModel.getDimension().getWidth();
		int height =(int) aModel.getDimension().getHeight();
        BufferedImage picture=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g=picture.createGraphics();
        //aGraphics2D.setBackground(Color.WHITE);
        //aGraphics2D.clearRect(0,0,width,height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
		//g.setFont(aFont);
		
		//==============作画部=====================
		for(Map.Entry<Integer,TreeNode> e : aModel.nodes.entrySet())//トップノード表示
		{
			TreeNode node = e.getValue();
			int X = (int)node.getTarget().getX();
			int Y = (int)node.getTarget().getY();
			g.setFont(aFont);
			g.drawString(node.getDate(),X,Y);
			g.drawRect(X-1,Y - node.getHeight(), node.getWidth()+2, node.getHeight()+node.getDescent());
			//各定数は誤差修正用
		}
		for(TreeBranch branch : aModel.branchs)
		{
			int X1 = (int)branch.getParentP().getX();
			int Y1 = (int)branch.getParentP().getY();
			int X2 = (int)branch.getChildP().getX();
			int Y2 = (int)branch.getChildP().getY();
			g.drawLine(X1+1,Y1,X2-1,Y2);
			//各定数は誤差修正用
		}
		//==========================================
		
        g.dispose();
		aModel.picture(picture);
		return;
	}
	
	
	
	
}