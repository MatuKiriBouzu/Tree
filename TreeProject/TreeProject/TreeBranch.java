package TreeProject;
import java.awt.Point;

public class TreeBranch{

    /**
     * フィールド
     **/
    
    /**
	 *
	 *
     **/
    public int parent;
    
    /**
	 *
	 *
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
	 *
	 *
     **/
    public int getParent()
    {
        return 0;
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
	 *
	 *
     **/
    public int getChild()
    {
        return 0;
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