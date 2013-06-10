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
	private ArrayList branchs;
	
	/**
	 * ノード群の最大数を保持するフィールド。
	 * DeuchiShin 144849 6/10 記述
	 **/
	private int nodesMax;
	
	/**
	 * ブランチ群の最大数を保持するフィールド。
	 * DeguchiShin 144849 6/10 記述
	 **/
	private int branchsMax;
	
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
		return 0;
	}
	/**
	 * setter
	 * NodesMaxを書き換える
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void setNodesMax(int x)
	{
		return;
	}
	/**
 	 * getter
	 * BranchsMaxの値を返す。
	 * 松きり坊主 144542 2013/6/3
	 **/
	public int getBranchsMax()
	{
		return 0;
	}
	/**
	 * setter
	 * BranchsMaxの値を書き替える
	 * 松きり坊主 144542 2013/6/3
	 **/
	public void setBranchsMax()
	{
		return;
	}
	/**
	 * getter
	 * Nodesのindex番目のnodeを返す
	 * 松きり坊主 144542 2013/6/3
	 **/
	public TreeNode getNodes(int index)
	{
		return null;
	}
	/**
	 * TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する。
	 * 松きり坊主 144542 2013/6/3
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
			
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			String aBuffer;
			String aString = br.readLine();
			
			while(aString != null) {
				aBuffer = aString;
				aString = br.readLine();
				
				System.out.println("aBuffer: " + aBuffer + " aString: " + aString);
			}		
			
		} catch (IOException e)
		{
			System.out.println(e);
		}
		return;
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
	public ArrayList<TreeBranch> inputBranch(BufferedReader br)
	{
		int a,b;
		br.reset();
		ArrayList<TreeBranch> branchdate = new ArrayList<TreeBranch>(this.branchsMax);

		for(TreeBranch i: branchdate){
			String aString = br.readLine(); 
			Stiring aStrings[] = aString.split(", ");
			a = Integer.parseInt(aString[0]);
			b = Integer.parseInt(aString[1]);
			i.setParent(a);
			i.setChild(b);
		}
		return branchdate;
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