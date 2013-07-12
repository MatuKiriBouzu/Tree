package TreeProject;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Point;
import java.util.Collections;
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
	private int countUpY = 20;
	
	/**
	 * コンストラクタ、親から引き継ぎを明確化
	 * 虎谷 6/20
	 **/
	public TreeModel(){
		super();
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
	 ***************虎谷　1回め用
	 *トップのブランチを見つけ出す。
	 *虎谷　6/13
	 **/
	public void calculateTree()
	{
		{
			for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
			{
				TreeNode topNode = e.getValue();
				//System.out.println("=確認="+topNode.getDate()+" =階層="+topNode.getLevel());
				if(topNode.getLevel()==0){
					int number = e.getKey();
					System.out.println("=確認="+topNode.getDate());
					calculateTree(number,20);//20はx座標の初期位置、個々から子ノードまで足しあわせ
				}
			}
			//==========↓作画処理↓===========
			branchCalc();
			super.changed();
			//==========↑作画処理↑===========
			
			return;
		}	
	}
	
	int distanceX = 25;//Node間隔を定義//階層間距離←→
	int distanceY = 16;//Node間隔を定義//階層間距離↑↓
	
	/**
	 ***************虎谷　2回め以降用
	 *TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する。
	 *松きり坊主 144542 2013/6/3
	 **/
	
	public void calculateTree(int number,int pointX)//(自ノードの)最終X座標
	{
		TreeNode node = nodes.get(number);
		int nextPointX = pointX + (int)node.desideWidth().getX() + distanceX;
		int childCount = 0;
		int sumY=0;//子ノードのY座標を貯める
		int pointY = countUpY;//最終Y座標 (初期値カウントアップYの数値)
		node.setTarget(new Point(pointX,pointY));//暫定配置、子ノードがあれば後に再配置する
		
		for(TreeBranch branch : branchs)
		{
			if(branch.getParent()==number)
			{
				childCount++;
				TreeNode childNode = nodes.get(branch.getChild());//ブランチから子ノードの要素番号を取り、ノードリストから子ノードを取って、次のcalcに回す
				calculateTree(branch.getChild(),nextPointX);
				sumY += (int)childNode.getTarget().getY();//子ノードを調べ終えたらそのY座標を自ノードのために足し込む
				//System.out.println("=確認2S="+childNode.getDate());
			}
		}
		//========↓Y座標決定処理↓=========
		if(childCount==0)//子ノードがなければ
		{
			countUpY += 16;//Nodeの縦の並列間隔
		}
		else//子ノードがあればそれらの座標から自ノードの座標を決定する
		{
			pointY =(int)(sumY/childCount);
		}
		node.setTarget(new Point(pointX,pointY));
		//========↑===========↑========
		return;
	}
	
	
	
	public void branchCalc(){
		
		for(TreeBranch branch : branchs)
		{			
			int p1 = (int)(nodes.get(branch.getParent()).getTarget().getX());
			int p2 = (int)(nodes.get(branch.getParent()).desideWidth().getX());
			int p3 = (int)(nodes.get(branch.getParent()).desideWidth().getY());
			int c1 = (int)(nodes.get(branch.getChild()).getTarget().getX());
			int c2 = (int)(nodes.get(branch.getChild()).desideWidth().getY());
			branch.decideParentP(new Point(p1 + p2 , p3));
			branch.decideChildP(new Point(c1 , c2));
		}
	}
	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする. トップノードをはじめに処理に放り込むメソッド
	 * 松きり坊主 144542 2013/6/3
	 * 削除予定。トップノード探索の後、アニメーションツリーに引き継ぎ 6/20　虎谷
	 * 松きり坊主 144542 2013/7/11　移行準備完了、レベルによる親探索に変更済み
	 **/
	public void animationTree()
	{
		for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
		{
			TreeNode topNode = e.getValue();
			//System.out.println("=確認="+topNode.getDate()+" =階層="+topNode.getLevel());
			if(topNode.getLevel()==0){
				int number = e.getKey();
				System.out.println("=確認="+topNode.getDate());
				animationTree(number,20);//20はx座標の初期位置、個々から子ノードまで足しあわせ
			}
		}
		
		return;
	}	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする
	 * 松きり坊主 144542 2013/6/3
	 * 定義する。間々に内部変更通知を出すようにして、viewに伝え、0.5秒待つ処理を組み込み済み 6/20 虎谷
	 * ハッシュマップ対応可 7/12 虎谷
	 **/
	public void animationTree(int number,int pointX)//(自ノードの)最終X座標
	{
		TreeNode node = nodes.get(number);
		int nextPointX = pointX + (int)node.desideWidth().getX() + distanceX;
		int childCount = 0;
		int sumY=0;//子ノードのY座標を貯める
		int pointY = countUpY;//最終Y座標 (初期値カウントアップYの数値)
		node.setTarget(new Point(pointX,pointY));//暫定配置、子ノードがあれば後に再配置する
		//==========↓作画処理一度目↓===========
		try{
			branchCalc();
			super.changed();
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		//System.out.println("=確認1S="+node.getDate());
		//==========↑作画処理一度目↑===========
		
		for(TreeBranch branch : branchs)
		{
			if(branch.getParent()==number)
			{
				childCount++;
				TreeNode childNode = nodes.get(branch.getChild());//ブランチから子ノードの要素番号を取り、ノードリストから子ノードを取って、次のcalcに回す
				animationTree(branch.getChild(),nextPointX);
				sumY += (int)childNode.getTarget().getY();//子ノードを調べ終えたらそのY座標を自ノードのために足し込む
				//System.out.println("=確認2S="+childNode.getDate());
			}
		}
		//========↓Y座標決定処理(必要があれば作画処理２度目)↓=========
		if(childCount==0)//子ノードがなければ
		{
			countUpY += 16;//Nodeの縦の並列間隔
			node.setTarget(new Point(pointX,pointY));
		}
		else//子ノードがあればそれらの座標から自ノードの座標を決定する
		{
			pointY =(int)(sumY/childCount);
			node.setTarget(new Point(pointX,pointY));
			try{
				branchCalc();
				super.changed();
				Thread.sleep(100);
			} catch (InterruptedException e) {e.printStackTrace();}
			//System.out.println("=確認2S="+node.getDate());
		}
		//========↑===========↑========
		return;
	}
	
	
	/**
	 * ファイルからそれぞれの情報を読み取る
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 144858 2013/6/13node部　動作確認まで
	 *松きり坊主 144542 2013/7/9
	 * 追記　虎谷 6/20 ノード初期位置を定義（左に立て一列に配置）
	 **/
	/*
	 inputTree作り直し
	 */
	public void inputTree(String fileName)	
	{
		try
		{
			ArrayList<TreeNode> bufNodes = new ArrayList<TreeNode>();
			
			FileReader fr = new FileReader("./TreeProject/tree.txt");
			//FileReader fr = new FileReader("./TreeProject/forest.txt");
			//FileReader fr = new FileReader("./TreeProject/semilattice.txt");
			
			
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
					abuffer = new TreeNode(bufString,levelCount);
					bufNodes.add(abuffer);					
					//System.out.println("おっぱい"+ans+"おっぱい"+bufString);
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
			if(i.getDate().equals(word)){//レベル探索で用いたbufNodesのリストより、wordが同じ物にレベルを入れる
				this.nodes.put(number,i);
				i.setTarget(new Point(20,(nodesMax*20+10)));//初期位置の決定
				i.setNumber(number);
				nodesMax++;//ハッシュマップ用ではなく、初期位置決定用、IDから位置を求める場合ID歯抜けがある場合初期位置も歯抜けになるため
			}
		}		
	}
	/**
	 * View,Controllerに報告する.
	 * 松きり坊主 144542 2013/6/3
	 * オーバーライドを取り消し　6/20 虎谷
	 **/
	//public void changed()
	//	 {
	//	 Iterator anIterator = dependents.iterator();
	//	 while (anIterator.hasNext())
	//	 {
	//	 TreeView aView = (TreeView)anIterator.next();
	//	 aView.update();
	//	 }
	//	 return;
	//	 }
	//	 
	/**
	 * 描画する。
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 6/13 暫定削除
	 **/
	//public void picture()
	//{
	//	return;
	//}
	
	
	/*======元トップノード探索=========*
	public void animationTree()
	{
		//	int distanceX = 20;//Node間隔を定義
		//		int distanceY = 20;//Nodeの縦の並列間隔を定義
		//		
		//		
		//		//====↓ここからトップノード探索
		//		ArrayList<TreeNode> topNode = new ArrayList<TreeNode>(nodes);//トップのみを格納したノードリストを作るため、一旦ノードをコピー
		//		for(TreeBranch branch : branchs)//すべてのブランチから検索
		//		{
		//			for(TreeNode node : topNode)//トップノードを回す(全てのトップノードから検索)
		//			{
		//				//System.out.println("=====: "+node.getNumber()+" "+branch.getChild());//確認用
		//				if(node.getNumber() == branch.getChild())//トップノードにブランチの子と同じ物があれば削除(トップノードは上にブランチが繋がっていないため、ブランチの子に設定されない)
		//				{
		//					topNode.remove(node);//トップリストから取り除く
		//					//System.out.println("=削除=");
		//					break;//削除が完了したら抜ける(でないとエラーが生じる)
		//				}
		//			}
		//		}
		
		for(Map.Entry<Integer,TreeNode> e : this.nodes.entrySet())//トップノード表示
		{
			TreeNode buf = e.getValue();
			if(buf.getLevel()==0){
				animationTree(buf,20);
			}
		}
		//====↑ここまで
		
		return;
	}	
	 */
	
}