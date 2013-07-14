package TreeProject;

import java.awt.Point;

public class TreeNode{
	
	/**
	 * 単語データ
	 **/
    String date;
	
	/**
	 * 単語ナンバー
	 *
	 **/
    //int number;
	//ハッシュ方式採用のため、ナンバー取り消し 7/14
	/**
	 * 配置座標
	 **/
    Point target;
	
	/**
	 * 階層
	 **/
    int level;
	
	/**
	 * 文字の高さ、幅、ベースラインから一番下までの長さ
	 **/
    int height,width,descent;
	
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
	 * ハッシュ方式採用のため、ナンバー取り消し 7/14
     **/
    //TreeNode(int number,String word)
    //{
	//	this.number = number;
	//	this.date = word;
    //}
	TreeNode(String aString,int level){
		this.level = level;
		this.date = aString;
		//desideSize();
	}
	
	public void setNodeSize(int width,int height,int descent){
		this.width = width;
		this.height = height;
		this.descent = descent;
		return;
	}
	
    /**
     * getter 虎谷　6/13
	 * ハッシュマップ方式採用のため、ナンバー取り消し 7/14
     **/
	/*
	 public int getNumber()
	 {
	 return this.number;   
	 }
	 public void setNumber(int num)
	 {
	 this.number=num;
	 }
	 */
	
	/**
     * getter 虎谷　7/14
     **/
    public int getWidth()
    {
		return width;   
    }
	/**
	  * getter 虎谷　7/14
	  **/
    public int getHeight()
    {
		return height;   
    }
	/**
	 * getter 虎谷　7/14
	 **/
    public int getDescent()
    {
		return descent;   
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
		return this.level;   
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
     *-setter getter 文字列長さ,高さ　虎谷　6/18
	 * 虎谷　7/14 定義変更、関数名より変更
	 * モデルへ機能移転 7/14
     **/
    /*public void desideSize(){
		
		BufferedImage BI = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics aGraphics = BI.createGraphics();
		Font aFont=new Font("Serif",Font.PLAIN,12);//===============課題条件　虎谷　７/14
		FontMetrics fm = aGraphics.getFontMetrics(aFont);
		
		width = fm.stringWidth(date);
		height = fm.getHeight();
		
		aGraphics.dispose();
        return;
    }*/
	/**
     *-setter
	 * 虎谷　6/13　追加
     **/
	public void setTarget(Point point)
    {
		this.target = point;
    }
    
}