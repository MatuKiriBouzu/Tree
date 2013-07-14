package TreeProject;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Graphics;//文字列幅を取得するために必要
import java.awt.FontMetrics;


//ID抜け問題に対策してプログラム大幅変更
public class TreeModel extends mvc.Model
{
	
	/**
	 * ノード群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 * <TreeNode>とpublicを書き足し　虎谷　6/13
	 * ハッシュマップに切り替え 松きり坊主 144542 2013/7/12 
	 **/
	public HashMap<Integer,TreeNode> nodes = new HashMap<Integer,TreeNode>();
	
	/**
	 * ブランチ群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 * <TreeBranch>を書き足し　虎谷　6/13
	 **/
	public ArrayList<TreeBranch> branchs = new ArrayList<TreeBranch>();
	
	/**
	 * ノード群の最大数を保持するフィールド。
	 * DeuchiShin 144849 6/10 記述
	 **/
	private int nodesMax = 0;
	
	/**
	 * ブランチ群の最大数を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 **/
	private int branchsMax = 10;
	
	/**
	 * BufferedReader自体を保持するフィールド？(誰か分かる人修正たのみます)
	 * DeguchiShin 144849 6/10 仮記述
	 **/
	private BufferedReader in;
	/**
	 * Yの作画位置を次々下げてゆくために使用
	 * 虎谷 6/13
	 **/
	private int countDownY=0;
	
	
	private int width,height,maxPointX=0;
	
	private Dimension aDimension;
	
	/**
	 * font情報を保持
	 * DeguchiShin 144849 6/10 仮記述
	 **/
	private Font aFont;
	
	
	/**
	 * アニメーションのON、OFF管理
	 * 虎谷7/14
	 **/
	private boolean process=false;
	
	/**
	 * コンストラクタ、親から引き継ぎを明確化
	 * 虎谷 6/20
	 **/
	public TreeModel(){
		super();
	}

	/**
	 * getter
	 * 虎谷 7/14
	 **/
	public Font getFont()
	{
		return aFont;
	}
	/**
	 * setter
	 * 虎谷 7/14
	 **/
	public void setFont(Font inFont)
	{
		this.aFont=inFont;
		settingFontMetrics();
		return;
	}
	
	/**
	 * getter
	 * 虎谷 7/14
	 **/
	public Dimension getDimension()
	{
		aDimension = new Dimension(width,height);
		return aDimension;
	}
	
	/**
	 * getter
	 * NodesMaxの値を返す
	 * 松きり坊主 144542 2013/6/3
	 **/
	public int getNodesMax()
	{
		return this.nodesMax;
	}
	/**
	 * setter
	 * NodesMaxを書き換える
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void setNodesMax(int num)
	{
		this.nodesMax = num;
		return;
	}
	/**
 	 * getter
	 * BranchsMaxの値を返す。
	 * 松きり坊主 144542 2013/6/3
	 **/
	public int getBranchsMax()
	{
		return this.branchsMax;
	}
	/**
	 * setter
	 * BranchsMaxの値を書き替える
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void setBranchsMax(int num)
	{
		this.branchsMax = num;
		return;
	}
	/**
	 * getter
	 * Nodesのindex番目のnodeを返す
	 * 松きり坊主 144542 2013/6/3
	 **/
	public TreeNode getNodes(int index)
	{
		return (TreeNode)nodes.get(index);
	}
	/**
	 *setter
	 *Nodesのindex番目のnodeを返す
	 *松きり坊主 144542 2013/6/11
	 **/
	
	/**
	 *getter
	 *Branchsのindex番目のnodeを返す
	 *松きり坊主 144542 2013/6/3
	 **/
	public TreeBranch getBranchs(int index)
	{
		return (TreeBranch)branchs.get(index);
	}
	/**
	 *setter
	 *Branchsのindex番目のnodeを返す
	 *松きり坊主 144542 2013/6/11
	 **/
	
	public void setBranchs(int index,TreeBranch branch)
	{
		branchs.set(index,branch);
	}
	

	/**
	 * ブランチの接続位置計算　7/14完成
	 * 虎谷
	 **/
	
	public void branchCalc(){
		
		for(TreeBranch branch : branchs)
		{			
			int parentX = (int)(nodes.get(branch.getParent()).getTarget().getX());
			int parentWidth = (int)(nodes.get(branch.getParent()).getWidth());
			int parentY= (int)(nodes.get(branch.getParent()).getTarget().getY()
						   -(nodes.get(branch.getParent()).getHeight()/2));//ノードの高さの中央に接続するため、Y座標から文字の高さ/2を引いている
			int childX = (int)(nodes.get(branch.getChild()).getTarget().getX());
			int childY = (int)(nodes.get(branch.getChild()).getTarget().getY()
						   -(nodes.get(branch.getChild()).getHeight()/2));
			branch.decideParentP(new Point(parentX + parentWidth , parentY)); //ブランチ前接続の座標
			branch.decideChildP(new Point(childX , childY)); //ブランチ後接続の座標
		}
	}
	
	int fix = 2;//誤差修正用
	int distanceX = 25  +fix;//Node間隔を定義//階層間距離←→ 　ここで設定
	int distanceY = 2   +fix;//Node間隔を定義//階層間距離↑↓	  　ここで設定
	int InitX = 20;//初期x位置
	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする. トップノードをはじめに処理に放り込むメソッド
	 * 松きり坊主 144542 2013/6/3
	 * 削除予定。トップノード探索の後、アニメーションツリーに引き継ぎ 6/20　虎谷
	 * 松きり坊主 144542 2013/7/11　移行準備完了、レベルによる親探索に変更済み
	 **/
	public void calculateTree(boolean flag)
	{
		process = flag;
		for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
		{
			TreeNode topNode = e.getValue();
			if(topNode.getLevel()==0){
				int number = e.getKey();
				//System.out.println("=確認="+topNode.getDate()); //最上位親確認
				calculateTree(number,InitX);
			}
		}
		if(process == false){
			try{
				super.changed();
				Thread.sleep(5);//作画が追いつかないため
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		height = countDownY;
		width = maxPointX;
		return;
	}	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする
	 * 松きり坊主 144542 2013/6/3
	 * 定義する。間々に内部変更通知を出すようにして、viewに伝え、0.5秒待つ処理を組み込み済み 6/20 虎谷
	 * ハッシュマップ対応可 7/12 虎谷
	 * semi~に対応、２回めの計算を行わない
	 * 虎谷 7/14 コードクローンが多すぎるため、アニメーションと統合
	 **/
	public void calculateTree(int number,int pointX)//ID番号と現在のX座標を受け取る
	{
		TreeNode node = nodes.get(number);
		int nextPointX = pointX + node.getWidth() + distanceX;
		if(nextPointX>maxPointX){maxPointX = nextPointX;} //最も右に作画される位置を探す
		
		if((node.getTarget().getX()==InitX) && (nextPointX > node.getTarget().getX())){
			//計算するノードが移動済み、または作画予定位置より深い場合　当処理を行わない
			
			int childCount = 0;
			int sumY=0;//子ノードのY座標を貯める
			int pointY = countDownY;//最終Y座標 (初期値カウントダウンYの数値)
			node.setTarget(new Point(pointX,pointY));//暫定配置、子ノードがあれば後に再配置する
			//==========↓作画処理一度目↓===========
			branchCalc();
			if(process == true){
				try{
					super.changed();
					Thread.sleep(100);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			//==========↑作画処理一度目↑============================
			//==========↓子ノードの計算↓============================
			for(TreeBranch branch : branchs)
			{
				if(branch.getParent()==number)
				{
					childCount++;
					calculateTree(branch.getChild(),nextPointX);//ブランチから子ノードの要素番号を取り、それを次のcalcに回す
					sumY += (int)nodes.get(branch.getChild()).getTarget().getY();//子ノードを調べ終えたらそのY座標を自ノードのために足し込む
				}
			}
			//==========↑子ノードの計算↑============================
			//========↓Y座標決定処理(必要があれば作画処理２度目)↓=========
			if(childCount==0)//子ノードがなければ
			{
				countDownY += node.getHeight()+node.getDescent()+distanceY;
				node.setTarget(new Point(pointX,pointY));
			}
			else//子ノードがあればそれらの座標から自ノードの座標を決定する
			{
				pointY =(int)(sumY/childCount);
				node.setTarget(new Point(pointX,pointY));
				
				branchCalc();
				if(process == true){
					try{
						super.changed();
						Thread.sleep(100);
					} catch (InterruptedException e) {e.printStackTrace();}
				}
				
			}
			//========↑===========↑========
		}
		return;
	}
	
	/**
	 * 標準出力に単語を出力するため、クリック座標を受け取って適合があるか調べる 7/14　虎谷
	 **/
	public void outTextSearch(Point aPoint)
	{
		int pointX = (int)aPoint.getX();
		int pointY = (int)aPoint.getY();
		for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
		{
			TreeNode node = e.getValue();
			
			int nodeX = (int)node.getTarget().getX();
			int nodeY = (int)(node.getTarget().getY());
			
			if((nodeX-1 < pointX) && (pointX < (nodeX + node.getWidth()+2)) &&
			   ((nodeY - node.getHeight()) < pointY) && (pointY < nodeY+node.getDescent()))
				//各定数は誤差修正用
			{
				int number = e.getKey();
				//System.out.println(aPoint);
				System.out.println(node.getDate());
				break;
			}
		}
		return;
	}
	
	
	
	
	/**
	 * ファイルからそれぞれの情報を読み取る
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 144858 2013/6/13node部　動作確認まで
	 * 松きり坊主 144542 2013/7/9
	 * 追記　虎谷 6/20 ノード初期位置を定義（左に立て一列に配置）
	 * 虎谷 7/14 文字幅エラー原因修正、ブランチの計算を当メソッドの最後で行う
	 **/
	/*
	 inputTree作り直し
	 */
	public void inputTree(String fileName)	
	{
		int maxLevel=0,maxWidth=0;
		try
		{
			ArrayList<TreeNode> bufNodes = new ArrayList<TreeNode>();
			
			FileReader fr = new FileReader(fileName);
			
			
			BufferedReader br = new BufferedReader(fr);
			String aString = br.readLine();
			
			TreeNode abuffer= null;
			TreeBranch abuffer1 = null;
			
			while(aString != null) {
				System.out.println(aString);
				
				if(aString.matches("[a-zA-Z]*") || aString.matches(".*--.*"))
				{
					int levelCount;
					String bufString = null;
					levelCount = inputNodeLevel(aString);
					bufString = inputNodeString(aString);
					int nodeWidth = desideWidthSize(bufString);
					int nodeHeight = desideHeightSize();
					int nodeDescent = desideDescentSize();
					abuffer = new TreeNode(bufString,levelCount);
					abuffer.setNodeSize(nodeWidth,nodeHeight,nodeDescent);
					bufNodes.add(abuffer);					
					//System.out.println("おっぱい"+ans+"おっぱい"+bufString);
					
					//Treeサイズ決定処理　最も長い単語＊最も大きい階層の数により、当Treeの理論最大サイズが求められる
					if(abuffer.getWidth()>maxWidth){maxWidth = abuffer.getWidth();}
					System.out.println("====max:"+maxWidth);
					if(levelCount>maxLevel){maxLevel = levelCount;}
				}
				else if(aString.matches("[0-9]*, [a-zA-Z]*"))
				{
					//	System.out.println("[1-9]*,[a-zA-Z]*"+aString);
					//	abuffer = this.inputNode(aString);
					//	this.nodes.add(abuffer);
					inputNodeNumber(aString,bufNodes);
				}
				else if(aString.matches("[0-9]*, [0-9]*"))
				{
					//	System.out.println("[1-9]*, [1-9]*"+aString);
					abuffer1 = this.inputBranch(aString);
					this.branchs.add(abuffer1);
				}
				aString = br.readLine();
				
				
			}		
		} catch (IOException e)
		{
			System.out.println(e);
		}
		branchCalc();//View生成時に作画を行なっている？ため、先にブランチの位置を決定しておく
		height = nodesMax;
		width = (maxWidth + distanceX)* maxLevel;
		aGraphics.dispose();//文字幅取得のため用いたグラフィックスを閉じる
	}
	
	public int inputNodeLevel(String aString){
		int LevelCount = 0;
		int index = 4;
		while(aString.startsWith("|-- ",index*LevelCount))LevelCount++;
		return LevelCount;
	}
	public String inputNodeString(String aString){
		String[] aStrings = null;
		aStrings = aString.split(" ");
		System.out.println(aStrings[aStrings.length-1]);
		return  aStrings[aStrings.length-1];
	}
	public TreeBranch inputBranch(String aString){
		int parentNum,childNum;
		TreeBranch branch = null;
		String[] aStrings = null;
		aStrings = aString.split(", ");
		parentNum = Integer.parseInt(aStrings[0]);
		childNum = Integer.parseInt(aStrings[1]);
		branch = new TreeBranch(parentNum,childNum);
		return branch;
		
	}
	public void inputNodeNumber(String aString,ArrayList<TreeNode> bufNodes){
		int number;
		String word;
		TreeNode node = null;
		String[] aStrings = null;
		aStrings = aString.split(", ");
		number = Integer.parseInt(aStrings[0]);
		word = (aStrings[1]);
		for(TreeNode i:bufNodes){
			//int count= -1;
			//count++;
			if(i.getDate().equals(word)){//レベル探索で用いたbufNodesのリストより、wordが同じ物にレベルを入れる
				//if(i.getDate()!n=null)
				if(countDownY==0){countDownY=i.getHeight()+distanceY;}
				i.setTarget(new Point(InitX,nodesMax));//初期位置の決定
				this.nodes.put(number,i);
				//i.setNumber(number);
				nodesMax += i.getHeight()+i.getDescent()+distanceY;
				
				bufNodes.remove(i);//同一単語重複処理回避
				//bufNodes.remove(count);//こっちのほうが早い？
				break;
			}
		}		
	}
	
	BufferedImage BI = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	Graphics aGraphics = BI.createGraphics();
	FontMetrics fm;
	private void settingFontMetrics(){fm = aGraphics.getFontMetrics(aFont);}//7/14 虎谷FontMetrics生成
	private int desideWidthSize(String aStrings){return fm.stringWidth(aStrings);}
	private int desideHeightSize(){return fm.getHeight();}
	private int desideDescentSize(){return fm.getDescent() ;}

	
	
	/*======元トップノード探索=========*
	 public ArrayList<int> topNodeSearch()
	 {
	 		ArrayList<int> topNode = new ArrayList<int>();//トップのみを格納したノードリストを作るため、一旦ノードをコピー
	 		for(TreeBranch branch : branchs)//すべてのブランチから検索
	 		{
				for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
				{
					int key = e.getKey();
					if(key == branch.getChild())//トップノードにブランチの子と同じ物があれば削除(トップノードは上にブランチが繋がっていないため、ブランチの子に設定されない)
	 //				{
	 //					topNode.remove(node);//トップリストから取り除く
	 //					//System.out.println("=削除=");
	 //					break;//削除が完了したら抜ける(でないとエラーが生じる)
	 //				}
	 //			}
	 //		}
	 //====↑ここまで
	 
	 return ArrayList;
	 }	
	 */
}