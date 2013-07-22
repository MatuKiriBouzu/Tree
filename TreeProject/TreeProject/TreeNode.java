/**
 * 追加削除情報
 * 単語IDを保持するフィールド、またそのgetter等はMapから取り出せるため削除
 * コンストラクタも単語IDを引数から除去、代わりに階層情報を引数とした
 * 単語のピクセル長さを決定する処理はModelに委任、データは当フィールドで保持する
 *
 * 仕様変更により、多くのSetter,getterを削除(親ノード情報、子ノード情報等は必要がなくなった。)
 * 
 * 追記:
 * javadoc対応重視のリファクタリング 小林祐希 13/07/23
 **/

package TreeProject;

import java.awt.Point;

/**
 * 木を構成する要素。枝ではなく節の部分。入力ファイルの文字列部分。
 **/
public class TreeNode{
	
	/**
	 * 単語データ
	 **/
    private String date;
	
	/**
	 * 配置座標
	 **/
    private Point target;
	
	/**
	 * 階層
	 **/
    private int level;
	
	/**
	 * 文字の高さ
	 **/
    private int height;
    
    /**
     * 文字の幅
     **/
	private int width;
    
    /**
     * ベースラインから一番下までの長さ
     **/
    private int descent;
    
	/**
     * TreeBranchのコンストラクタ。
	 * 引数よりTreeNodeを作成する
	 * 虎谷　144858 2013/6/13　動作確認まで
	 * ハッシュ方式採用のため、ナンバー取り消し、階層引数追加 7/14
     **/
	public TreeNode(String aString,int level){
		this.level = level;
		this.date = aString;
	}
	
    /**
     * 高さ、幅、文字下限setter
     * 小林祐希 144524 2013/6/20
     * 7/13 虎谷　直接モデル受け渡しスタイル=>　モデルに一旦格納スタイルへ
     **/
	public void setNodeSize(int width,int height,int descent){
		this.width = width;
		this.height = height;
		this.descent = descent;
		return;
	}
    
    
    /**
     * 単語getter 
     * 6/13 虎谷
     **/
    public String getDate()
    {
        return this.date;
    }
	
	/**
     * 単語幅getter 
     * 7/14 虎谷　
     **/
    public int getWidth()
    {
		return this.width;   
    }
	/**
     * 単語高さgetter 
     * 7/14 虎谷
     **/
    public int getHeight()
    {
		return this.height;   
    }
	/**
	 * 単語下限getter 
     * 7/14 虎谷　
	 **/
    public int getDescent()
    {
		return this.descent;   
    }
	
    /**
     *-位置setter
	 * 6/13　虎谷
     **/
	public void setTarget(Point point)
    {
		this.target = point;
    }
    
    /**
     * 単語位置getter 
     * 6/13 虎谷　
     **/
    public Point getTarget()
    {
		return this.target;   
    }
    
	
    /**
     *-階層getter
     * 7/14 小山
     **/
    public int getLevel()
    {
		return this.level;   
    }

    
	
	
    
}