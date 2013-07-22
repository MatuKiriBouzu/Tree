/**
 * 
 *ノードIDのセッターがあったが、コンストラクタで定義する上、その情報が変わることは無いので削除
 **/

package TreeProject;
import java.awt.Point;
/**
 樹状構造の枝のクラス
 **/

public class TreeBranch{
    
    /**
	 * 親ノードの情報(ID)
     **/
    private int parent;
    
    /**
	 * 子ノードの情報(ID)
     **/
    private int child;
    
    /**
	 * 親ノードの接続位置情報
	 *
     **/
    private Point parentPoint;
    
    /**
	 * 子ノードの接続位置情報
	 *
     **/
    private Point childPoint;
    
    
    
    /**
     * TreeBranchのコンストラクタ。親ID,子IDsetter
	 * 7/19 虎谷
     **/
    TreeBranch(int parentNum,int childNum)
    {
		this.parent = parentNum;
		this.child = childNum;
    }
	
    
    
    /**
     * 親ノードの接続地点をセットする
	 * 虎谷 6/19
     **/
    public void setParentPoint(Point point)
    {
        parentPoint = point;
		return;
    }
    
	/**
     * 子ノードの接続地点をセットする
	 * 虎谷 6/19
     **/
    public void setChildPoint(Point point)
    {
		this.childPoint = point;
        return;
    }
    
	/**
     * 親ノードの接続地点を返す
	 * 虎谷 6/19
     **/
	public Point getParentPoint()
    {
        return this.parentPoint;
    }
    
    /**
     * 子ノードの接続地点を返す
	 * 虎谷 6/19
     **/
	public Point getChildPoint()
    {
        return this.childPoint;
    }
	
    /**
	 * 親ノードIDgetter
	 * 虎谷　6/13
     **/
    public int getParent()
    {
        return this.parent;
    }
    
    /**
	 * 子ノードIDgetter
	 * 虎谷　6/13
     **/
    public int getChild()
    {
        return this.child;
    }
}