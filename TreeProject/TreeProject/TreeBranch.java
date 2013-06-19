package TreeProject;
import java.awt.Point;

public class TreeBranch{

    /**
     * フィールド
     **/
    
    /**
	 * 親ノードの情報
     **/
    public int parent;
    
    /**
	 * 子ノードの情報
     **/
    private int child;
    
    /**
	 * 親ノードの接続位置情報
	 *
     **/
    private Point parentP;
    
    /**
	 * 子ノードの接続位置情報
	 *
     **/
    private Point childP;
    
    
    
    /**
     * TreeBranchのコンストラクタ。
	 *
     **/
    TreeBranch(int parentNum,int childNum)
    {
		this.parent = parentNum;
		this.child = childNum;
    }
	
    
    
    /**
     * 親ノードの接続地点を決定
	 * 虎谷 6/19
     **/
    public void decideParentP(Point point)
    {
        parentP = point;
		return;
    }
    
	/**
     * 子ノードの接続地点を決定
	 * 虎谷 6/19
     **/
    public void decideChildP(Point point)
    {
		childP = point;
        return;
    }
    
    /**
	 * 親ノードgetter
	 * 虎谷　6/13
     **/
    public int getParent()
    {
        return this.parent;
    }
    
	/**
	 *
	 *
     **/
    public void setParent(int num)
    {
		this.parent = num;
    }
    /**
	 * 子ノードgetter
	 * 虎谷　6/13
     **/
    public int getChild()
    {
        return this.child;
    }
	/**
	 *
	 *
     **/
    public void setChild(int num)
    {
		this.child = num;
    }
	
	//======追加分↓=====虎谷 6/19
	public Point getParentP()
    {
        return this.parentP;
    }
	public Point getChildP()
    {
        return this.childP;
    }
	
    
}