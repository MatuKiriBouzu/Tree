package TreeProject;

import java.awt.Point;

public class TreeNode{
	
	/**
	 *
	 *
	 **/
    String date;
	
	/**
	 *
	 *
	 **/
    int number;
	
	/**
	 *
	 *
	 **/
    Point target;
	
	/**
	 *
	 *
	 **/
    int level;
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    public String getDate()
    {
        return null;
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
     *-説明文、氏名、学生証、日付
     **/
    public int getNumber()
    {
		return 0;   
    }
	
    /**
     *-説明文、氏名、学生証、日付
     **/
    public Point getTarget()
    {
		return null;   
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
    
}