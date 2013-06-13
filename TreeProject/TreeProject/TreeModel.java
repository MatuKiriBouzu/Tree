package TreeProject;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TreeModel
{
	/**
	 * ノード群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 * <TreeNode>を書き足し　虎谷　6/13
	 **/
	private ArrayList<TreeNode> nodes;
	
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
		int temp = 20;//初回Y座標を定義
		
		
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
		}
		//====↑ここまで
		
		
		return;
	}
	
	/**
	 ***************虎谷　2回め以降用
	 *TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する。
	 *松きり坊主 144542 2013/6/3
	 **/
	//public void calculateTree()
	//{
	//	int distanceX = 20;//Node間隔を定義
	//	int distanceY = 20;//Nodeの縦の並列間隔を定義
	//	int temp = 20;//初回Y座標を定義
	//	
	//	
	//	
	//	return;
	//}
	
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 * アニメーションにする
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void animationTree()
	{
		return;
	}
	/**
	 * ファイルからそれぞれの情報を読み取る
	 * 松きり坊主 144542 2013/6/3
	 * 虎谷 144858 2013/6/13node部　動作確認まで
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
				//	debugMessage			
				System.out.println("debug message String value number: "+number);
				//	debugMessage			
				System.out.println("debug message Integer value word: "+word);
				nodedate.add(new TreeNode(number,word));
				nodesMax++;
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}	
		this.nodes = nodedate;
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
				//	debugMessage			
				System.out.println("debug message Integer value a:"+parentNum+" b:"+childNum);
				//	debugMessage	System.out.println("debug message Integer value b:"+childNum);
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
	 **/
	public void change()
	{
		return;
	}
	/**
	 * 描画する。
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void picture()
	{
		return;
	}
}