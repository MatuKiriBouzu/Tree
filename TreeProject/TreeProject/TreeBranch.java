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
	 *
	 *
     **/
    private Point parentPx;
    
    /**
	 *
	 *
     **/
    private Point childPx;
    
    
    
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
     * 
	 *
     **/
    public void decideParentPx()
    {
        return;
    }
    
    /**
	 *
	 *
     **/
    public void decideChildPx()
    {
        return;
    }
    
    /**
	 * getter 虎谷　6/13
	 *
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
	 * getter 虎谷　6/13
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
	
    
}