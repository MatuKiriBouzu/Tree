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
	 **/
	private ArrayList nodes;
	
	/**
	 * ブランチ群自体を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 **/
	public ArrayList branchs;
	
	/**
	 * ノード群の最大数を保持するフィールド。
	 * DeuchiShin 144849 6/10 記述
	 **/
	private int nodesMax;
	
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
	 *TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する。
	 *松きり坊主 144542 2013/6/3
	 **/
	public void calculateTree()
	{
		return;
	}
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
	 **/
	public void inputTree(String fileName)
	{
		
		try
		{
			
			FileReader fr = new FileReader("/Users/koyamatakayuki/SE/treeRepository/Tree/TreeProject/TreeProject/tree.txt");
			BufferedReader br = new BufferedReader(fr);
			String aString = new String();
	
			while(br.ready()) {
				aString = br.readLine();				
				System.out.println(" aString: " + aString);
				
				//if(aString.equals("Nodes:")){}をここに作る
				if(aString.equals("branches:")){
					this.inputBranch(br);
				}				
				
			}		
		} catch (IOException e)
		{
			System.out.println(e);
		}	
		
	}
	/**
	 * Nodeの情報をファイルから読む
	 * 松きり坊主 144542 2013/6/3
	 **/
	public TreeNode inputNode(BufferedReader br)
	{
		return null;
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
				//	debugMessage			System.out.println("debug message Integer value a"+a);
				//	debugMessage			System.out.println("debug message Integer value b"+b);
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