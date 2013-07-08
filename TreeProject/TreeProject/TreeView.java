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


public class TreeView extends mvc.View
{
    /**
     * 情報(Node,Blanch等)を格納するモデル体を管理する変数。
     * modelからTreeModelに変更、間違っているかも6/13虎谷
     **/
	protected TreeModel aModel;
    
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
     * コンストラクタ
     * 親で代入しているが、描画できなくなるのでもう一度上書き　間違っているかも6/13虎谷
     * コンス"トラ"クタ……"虎"谷……深夜テンション小林
	 * 修正完了。親から帰ってきたものを型変換の上格納　7/5　虎谷
     **/
	public TreeView(TreeModel aModel)//
	{
		super(aModel);
		this.aModel=(TreeModel)(model);
		
	}
	
    /**
     * 描画メソッド(継承関係により、この場においては自動実行型の描画用メソッドとの認識で良い？)
     * ほぼ流用6/13虎谷
     * BufferedImageへの描画にしてみた。:144524 小林 2013/6/20
     **/
    public void paintComponent(Graphics aGraphics)
    {
        super.paintComponent(aGraphics);
        treeImage=this.paintTree(aGraphics2D);
        //aGraphics.drawImage(treeImage , offset.x , offset.y ,this);
		viewport=this.scrollAmount();
		aGraphics.drawImage(treeImage , -viewport.x , -viewport.y ,this);
    }
    
    /**
     * モデル体の情報を利用して画像を構成する描画メソッド。
     * 小林祐希 144524 2013/6/20
     * 
     * 
     **/
	private BufferedImage paintTree(Graphics2D aGraphics2D)
	{
        int width = this.getWidth();
		int height = this.getHeight();
            //BufferedImage picture = model.picture();//何か元から置いてあった。ここのModelのフィールドpictureって、どこで代入処理(設定処理)されてるの？
        BufferedImage picture=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        if (model == null)
        {
            return picture;//何も描画されてないものを返す。
        }
        aGraphics2D=picture.createGraphics();
		
		
        
        aGraphics2D.setBackground(Color.WHITE);
        aGraphics2D.clearRect(0,0,width,height);
        /*  aGraphics2D.setColor(Color.WHITE);//背景色設定の代わり？
		    aGraphics2D.fillRect(0, 0, width, height);    */
        aGraphics2D.setColor(Color.BLACK);
		
		//==============仮作成部=====================
		for(TreeNode node : aModel.nodes)//オリジナル　//nodeを作画6/13虎谷 ※仮作成
		{
			int X = (int)node.getTarget().getX();
			int Y = (int)node.getTarget().getY();
			aGraphics2D.drawString(node.getDate(),X,Y);
			aGraphics2D.drawRect(X,Y-12, (int)node.desideWidth().getX(), 14);
		}
		for(TreeBranch branch : aModel.branchs)
		{
			int X1 = (int)branch.getParentP().getX();
			int Y1 = (int)branch.getParentP().getY();
			int X2 = (int)branch.getChildP().getX();
			int Y2 = (int)branch.getChildP().getY();
			aGraphics2D.drawLine(X1,Y1,X2,Y2);
		}
		//==========================================
		
        aGraphics2D.dispose();
		return picture;
	}


}