package TreeProject;

import java.awt.Point;

public class TreeNode{
	
	/**
	 * 単語データ
	 **/
    String date;
	
	/**
	 * 単語ナンバー
	 **/
    int number;
	
	/**
	 * 配置座標
	 **/
    Point target;
	
	/**
	 * 階層
	 **/
    int level;
	
    /**
     *-getter 虎谷　6/13
     **/
    public String getDate()
    {
        return date;
    }
	
	/**
     * TreeBranchのコンストラクタ。
	 *　引数よりTreeNodeを作成する
	 * 虎谷　144858 2013/6/13　動作確認まで
     **/
    TreeNode(int number,String word)
    {
		this.number = number;
		this.date = word;
    }
	
    /**
     * getter 虎谷　6/13
     **/
    public int getNumber()
    {
		return number;   
    }
	
    /**
     * getter 虎谷　6/13
     **/
    public Point getTarget()
    {
		return target;   
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    public int getLevel()
    {
		return 0;   
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    public Point getRJoint(){
        return null;
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    public Point getLJoint(){
        return null;
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    private Point desidePoint(){
        return null;
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    private Point desideWidth(){
        return null;
    }
	/**
     *-setter
	 * 虎谷　6/13　追加
     **/
	public void setTarget(Point point)
    {
		this.target = point;
    }
    
}