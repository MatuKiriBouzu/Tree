/**
 * 7/19 虎谷 リファクタリングを実施
 * 6/20作成のプログラムは、ペイントコンポーネントが呼ばれた時にTreePaintを呼び、その返り値
 * のバッファイメージをペイントコンポーネントないでdrowImageするものだった。また、offsetが
 * 使えなかったので、scrollAmountから値を無理に取り出し、フィールドviewportで保持していた。
 * 現在は、ペイントコンポーネント呼び出し時、TreePaintを呼び、その中で成果物をmodelに格納し、
 * MVC本来の機能で親クラスのペイントコンポーネントでモデルからイメージを取り出し作画している
 */

package TreeProject;



import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Font;
import java.awt.Dimension;

public class TreeView extends mvc.View
{
    /**
     * フォント指定事項を保持
     * 虎谷　7/14
     **/
	private Font aFont;
	
    /**
     * コンストラクタ
     * 親で代入しているが、描画できなくなるのでもう一度上書き　間違っているかも6/13虎谷
     * コンス"トラ"クタ……"虎"谷……深夜テンション小林
	 * 修正完了。親から帰ってきたものを型変換の上格納　7/5　虎谷
     * 7/19 親クラスのコンストラクタを実行した上で、モデルからフォント情報を取り出し保持する。
     **/
	public TreeView(TreeModel aModel,TreeController aController)//
	{
		super(aModel,aController);
		aFont = getModel().getFont();
	}
	
	/**
	 * 自分のモデルをTreeModelとして応答する。
     * 7/13 虎谷
	 */
	public TreeModel getModel()
	{
		return (TreeModel)(this.model);
		
	}
	
    /**
     * 描画メソッド(継承関係により、この場においては自動実行型の描画用メソッドとの認識で良い？)
     * ほぼ流用6/13虎谷
     * BufferedImageへの描画にしてみた。:144524 小林 2013/6/20
	 * 7/13 虎谷 最適化を実施　元のMVCを最大限活用 
     * 7/19 虎谷 モデルに格納されているイメージを作画する。
     **/
    public void paintComponent(Graphics aGraphics)
    {
		this.paintTree();
        super.paintComponent(aGraphics);
    }
    
    /**
     * モデル体の情報を利用して画像を構成する描画メソッド。
     * 小林祐希 144524 2013/6/20
     * 虎谷　7/13 直接モデル受け渡しスタイル=>　モデルに一旦格納スタイルへ
     * 7/19 モデル情報をバッファに作画し、そのイメージをモデルに格納する。
     **/
	private void paintTree()
	{
		TreeModel aModel=getModel();
        int width =(int) aModel.getDimension().getWidth();
		int height =(int) aModel.getDimension().getHeight();
        BufferedImage picture=new BufferedImage( width, height,BufferedImage.TYPE_INT_BGR);
        Graphics g=picture.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
		
		//==============作画部=====================
		for(Map.Entry<Integer,TreeNode> e : aModel.getNodes().entrySet())//トップノード表示
		{
			TreeNode node = e.getValue();
			int X = (int)node.getTarget().getX();
			int Y = (int)node.getTarget().getY();
			g.setFont(aFont);
			g.drawString(node.getDate(),X,Y);
			g.drawRect(X-1,Y - node.getHeight(), node.getWidth()+2, node.getHeight()+node.getDescent());
			//各定数は誤差修正用
		}
		for(TreeBranch branch : aModel.getBranchs())
		{
			int X1 = (int)branch.getParentPoint().getX();
			int Y1 = (int)branch.getParentPoint().getY();
			int X2 = (int)branch.getChildPoint().getX();
			int Y2 = (int)branch.getChildPoint().getY();
			g.drawLine(X1+1,Y1,X2-1,Y2);
			//各定数は誤差修正用
		}
		//==========================================
		
        g.dispose();
		aModel.picture(picture);
		return;
	}
	
	
	
	
}