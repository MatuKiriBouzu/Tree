package TreeProject;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class TreeController extends mvc.Controller{
	
	/**
	 * TreeProjectのコントローラ。
	 * DeguchiShin 144849 6/3 スタブ作成
	 */
	public TreeController() 
	{
		super();
	}
	/**
	 * 自分のモデルを応答する。
	 */
	public TreeModel getModel()
	{
		return (TreeModel)(this.model);
	}
	
	/**
	 * 指定されたマウスイベントからマウスカーサの位置を獲得して、モデル座標系でのクリック位置を割り出して標準出力に出力する。
	 * 良好（2010年7月25日）
	 * 7/14　樹状整列ようにオーバーライドを実施、
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		getModel().outTextSearch(aPoint);
		return;
	}
}