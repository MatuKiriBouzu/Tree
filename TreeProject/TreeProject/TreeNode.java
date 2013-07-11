package TreeProject;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;

import java.awt.Point;
import java.awt.Font;
import java.awt.FontMetrics;
<<<<<<< HEAD
import java.awt.Graphics;//文字列幅を取得するために必要
=======
>>>>>>> c842980d5937990779bfe8df3759db4b4dc775cb

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
	TreeNode(String aString,int level){
		this.level = level;
		this.date = aString;
	}
	
    /**
     * getter 虎谷　6/13
     **/
    public int getNumber()
    {
		return number;   
    }
	public void setNumber(int num)
	{
		this.number=num;
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
     *-setter getter 文字列長さ　虎谷　6/18
     **/
    public Point desideWidth(){
		//System.out.println("=確認=");
		
		BufferedImage BI = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = BI.createGraphics();
		Font aFont=new Font("TimesRoman",Font.ITALIC,10);//===============仮作成　虎谷　７/１１
		FontMetrics fm = g2d.getFontMetrics();
		
		Point point = new Point(fm.stringWidth(date),0);//定数8
		if(target!=null){
			//point = new Point((date.length())*8,(int)this.target.getY()-5);//定数8
			point = new Point(fm.stringWidth(date),(int)this.target.getY()-5);//定数8
		}
		
        return point;
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