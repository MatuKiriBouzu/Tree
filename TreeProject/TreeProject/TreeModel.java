package TreeProject;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Point;

public class TreeModel extends mvc.Model
{
	
	/**
	 * ノード群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 * <TreeNode>とpublicを書き足し　虎谷　6/13
	 **/
	public ArrayList<TreeNode> nodes;
	
	/**
	 * ブランチ群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 * <TreeBranch>を書き足し　虎谷　6/13
	 **/
	public ArrayList<TreeBranch> branchs;
	
	/**
	 * ノード群の最大数を保持するフィールド。
	 * DeuchiShin 144849 6/10 記述
	 **/
	private int nodesMax=0;
	
	/**
	 * ブランチ群の最大数を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 **/
	private int branchsMax=10;
	
	/**
	 * BufferedReader自体を保持するフィールド？(誰か分かる人修正たのみます)
	 * DeguchiShin 144849 6/10 仮記述
	 **/
	private BufferedReader in;
	/**
	 * Yの作画位置を次々下げてゆくために使用
	 * 虎谷 6/13
	 **/
	private int countUpY=20;
	
	
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
	
	public void setNodes(int index,TreeNode node)
	{
		nodes.set(index,node);
		
	}
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
		int distanceX = 20;//Node間隔を定義
		int distanceY = 20;//Nodeの縦の並列間隔を定義
		
		
		//====↓ここからトップノード探索
		ArrayList<TreeNode> topNode = new ArrayList(nodes);//トップのみを格納したノードリストを作るため、一旦ノードをコピー
		for(TreeBranch branch : branchs)//すべてのブランチから検索
		{
			 for(TreeNode node : topNode)//トップノードを回す(全てのトップノードから検索)
			 {
				 //System.out.println("=====: "+node.getNumber()+" "+branch.getChild());//確認用
				 if(node.getNumber() == branch.getChild())//トップノードにブランチの子と同じ物があれば削除(トップノードは上にブランチが繋がっていないため、ブランチの子に設定されない)
				{
					topNode.remove(node);//トップリストから取り除く
					//System.out.println("=削除=");
					break;//削除が完了したら抜ける(でないとエラーが生じる)
				}
			 }
		}
		for(TreeNode topnode : topNode)//トップノード表示
		{
			System.out.println("debugM TOP: "+topnode.getNumber());
			calculateTree(topnode,20);
		}
		//====↑ここまで
	
		return;
	}
	int count=0;
	/**
	 ***************虎谷　2回め以降用
	 *TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する。
	 *松きり坊主 144542 2013/6/3
	 **/
	int distanceX = 25;//Node間隔を定義//階層間距離←→
	public void calculateTree(TreeNode node,int pointX)//(自ノードの)最終X座標
	{
		int nextPointX = pointX + (int)node.desideWidth().getX() + distanceX;
		int childCount = 0;
		int sumY=0;//子ノードのY座標を貯める
		int pointY = countUpY;//最終Y座標 (初期値カウントアップYの数値)
		//paint(pointX,pointY)
		ArrayList<TreeBranch> afterBranchs = new ArrayList<TreeBranch>();//自ノードのY座標が後で求まるので、一旦格納する
		for(TreeBranch branch : branchs)
		{
			
			if(branch.getParent()==node.getNumber())
			{
				childCount++;
				TreeNode childNode = nodes.get(branch.getChild()-1);//ブランチから子ノードの要素番号を取り、ノードリストから子ノードを取って、次のcalcに回す
				
				calculateTree(childNode,nextPointX);
				sumY += (int)childNode.getTarget().getY();//子ノードを調べ終えたらそのY座標を自ノードのために足し込む
				
				afterBranchs.add(branch);
				

			}
		}
		//========↓Y座標決定処理↓=========
		if(childCount==0)//子ノードがなければ
		{
			countUpY += 16;//Nodeの縦の並列間隔を定義,定数化するべき
		}
		else//子ノードがあればそれらの座標から自ノードの座標を決定する
		{
			pointY =(int)sumY/childCount;
			//paint(pointX,pointY)
		}
		//========↑===========↑========
			
		node.setTarget(new Point(pointX,pointY));
		
		return;
	}
	
	
	public void branchCalc(){
		for(TreeBranch branch : branchs)
		{
			int p1 = (int)(nodes.get(branch.getParent()-1).getTarget().getX());
			int p2 = (int)(nodes.get(branch.getParent()-1).desideWidth().getX());
			int p3 = (int)(nodes.get(branch.getParent()-1).desideWidth().getY());
			int c1 = (int)(nodes.get(branch.getChild()-1).getTarget().getX());
			int c2 = (int)(nodes.get(branch.getChild()-1).desideWidth().getY());
			branch.decideParentP(new Point(p1 + p2 , p3));
			branch.decideChildP(new Point(c1 , c2));
		}
	}
	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする
	 * 松きり坊主 144542 2013/6/3
	 * 削除予定。トップノード探索の後、アニメーションツリーに引き継ぎ 6/20　虎谷
	 **/
	public void animationTree()
	{
		int distanceX = 20;//Node間隔を定義
		int distanceY = 20;//Nodeの縦の並列間隔を定義
		
		
		//====↓ここからトップノード探索
		ArrayList<TreeNode> topNode = new ArrayList(nodes);//トップのみを格納したノードリストを作るため、一旦ノードをコピー
		for(TreeBranch branch : branchs)//すべてのブランチから検索
		{
			for(TreeNode node : topNode)//トップノードを回す(全てのトップノードから検索)
			{
				//System.out.println("=====: "+node.getNumber()+" "+branch.getChild());//確認用
				if(node.getNumber() == branch.getChild())//トップノードにブランチの子と同じ物があれば削除(トップノードは上にブランチが繋がっていないため、ブランチの子に設定されない)
				{
					topNode.remove(node);//トップリストから取り除く
					//System.out.println("=削除=");
					break;//削除が完了したら抜ける(でないとエラーが生じる)
				}
			}
		}
		for(TreeNode topnode : topNode)//トップノード表示
		{
			System.out.println("debugM TOP: "+topnode.getNumber());
			animationTree(topnode,20);
		}
		//====↑ここまで
		
		return;
	}	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする
	 * 松きり坊主 144542 2013/6/3
	 * 定義する。間々に内部変更通知を出すようにして、viewに伝え、0.5秒待つ処理を組み込み済み 6/20 虎谷
	 **/
	public void animationTree(TreeNode node,int pointX)//(自ノードの)最終X座標
	{
		int nextPointX = pointX + (int)node.desideWidth().getX() + distanceX;
		int childCount = 0;
		int sumY=0;//子ノードのY座標を貯める
		int pointY = countUpY;//最終Y座標 (初期値カウントアップYの数値)
		
		node.setTarget(new Point(pointX,pointY));//暫定配置、子ノードがあれば後に再配置する
		//paint(pointX,pointY)
		//==========↓作画処理一度目↓===========
		try{
			branchCalc();
			changed();
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=確認1S=");
		//==========↑作画処理一度目↑===========
		
		
		ArrayList<TreeBranch> afterBranchs = new ArrayList<TreeBranch>();//自ノードのY座標が後で求まるので、一旦格納する
		for(TreeBranch branch : branchs)
		{
			
			if(branch.getParent()==node.getNumber())
			{
				childCount++;
				TreeNode childNode = nodes.get(branch.getChild()-1);//ブランチから子ノードの要素番号を取り、ノードリストから子ノードを取って、次のcalcに回す
				
				animationTree(childNode,nextPointX);
				sumY += (int)childNode.getTarget().getY();//子ノードを調べ終えたらそのY座標を自ノードのために足し込む
				
				afterBranchs.add(branch);
				
				
			}
		}
		//========↓Y座標決定処理↓=========
		if(childCount==0)//子ノードがなければ
		{
			countUpY += 16;//Nodeの縦の並列間隔を定義,定数化するべき
			node.setTarget(new Point(pointX,pointY));
		}
		else//子ノードがあればそれらの座標から自ノードの座標を決定する
		{
			pointY =(int)sumY/childCount;
			node.setTarget(new Point(pointX,pointY));
			
			try{
				branchCalc();
				changed();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=確認2S="+node.getDate());
		}
		//========↑===========↑========
		
		
		
		return;
	}

	
	/**
	 * ファイルからそれぞれの情報を読み取る
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 144858 2013/6/13node部　動作確認まで
	 * 追記　虎谷 6/20 ノード初期位置を定義（左に立て一列に配置）
	 **/
	public void inputTree(String fileName)
	{
		
		try
		{
			
			//FileReader fr = new FileReader("/Users/torataniakira/SE/treeRepository/Tree/TreeProject/TreeProject/tree.txt");
			FileReader fr = new FileReader("./TreeProject/tree.txt");
			//相対パスに設定
			
			BufferedReader br = new BufferedReader(fr);
			String aString = new String();
	
			while(br.ready()) {
				aString = br.readLine();				
				System.out.println(" aString: " + aString);
				
				if(aString.equals("nodes:"))
				{
					this.inputNode(br);
					this.inputBranch(br);
					//↑おかしいのはわかってるけど、Nodeでbranches:を読み取ったから↓の条件に引っかからないため
					//暫定的にこのように記述
				}
				
				//if(aString.equals("branches:"))
				//{
				//	this.inputBranch(br);
				//}				
				
			}		
		} catch (IOException e)
		{
			System.out.println(e);
		}	
		
	}
	/**
	 * Nodeの情報をファイルから読む
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 144858 2013/6/13動作確認まで
	 **/
	public void inputNode(BufferedReader br)
	//返り値をTreeNodeからvoidに変更6/13
	{
		int number;
		String word;
		TreeNode node = null;
		String aString = null;
		String[] aStrings = null;
		ArrayList<TreeNode> nodedate = new ArrayList<TreeNode>();
		try
		{
			while(br.ready())
			{
				aString = br.readLine(); 
				
				if(aString.equals("branches:"))break;
				
				aStrings = aString.split(", ");
				number = Integer.parseInt(aStrings[0]);
				word = (aStrings[1]);
				//	debugMessage			System.out.println("debug message String value number: "+number);
				//	debugMessage			System.out.println("debug message Integer value word: "+word);
				nodedate.add(new TreeNode(number,word));
				nodedate.get(nodesMax).setTarget(new Point(20,nodesMax*20+10));
				nodesMax++;
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
		this.nodes = nodedate;
		//nodesMax = nodes.size();
	}
	
	/**
	 * Branchの情報をファイルから読む
	 * 松きり坊主 144542 2013/6/3
	 **/
	private void inputBranch(BufferedReader br)
	{
		int parentNum,childNum;
		TreeBranch branch = null;
		String aString = null;
		String[] aStrings = null;
		ArrayList<TreeBranch> branchdate = new ArrayList<TreeBranch>();
		try
		{

			while(br.ready())
			{
				aString = br.readLine(); 
				aStrings = aString.split(", ");
				parentNum = Integer.parseInt(aStrings[0]);
				childNum = Integer.parseInt(aStrings[1]);
				//	debugMessage			System.out.println("debug message Integer value a:"+parentNum+" b:"+childNum);
				branchdate.add(new TreeBranch(parentNum,childNum));
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
		this.branchs = branchdate;
	}
	/**
	 * View,Controllerに報告する.
	 * 松きり坊主 144542 2013/6/3
	 * オーバーライドを取り消し　6/20 虎谷
	 **/
	/*public void changed()
	{
		Iterator anIterator = dependents.iterator();
		while (anIterator.hasNext())
		{
			TreeView aView = (TreeView)anIterator.next();
			aView.update();
		}
		return;
	}
	
	/**
	 * 描画する。
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 6/13 暫定削除
	 **/
	//public void picture()
	//{
	//	return;
	//}
	
}