package TreeProject;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TreeModel
{
	
	
	
	
	/**
	 *getter
	 *NodesMaxの値を返す
	 *松きり坊主 144542 2013/6/3
	 **/
	public int getNodesMax()
	{
		return 0;
	}
	/**
	 *setter
	 *NodesMaxを書き換える
	 *松きり坊主 144542 2013/6/3
	 **/
	public void setNodesMax(int x)
	{
		return;
	}
	/**
 	 *getter
	 *BranchsMaxの値を返す。
	 *松きり坊主 144542 2013/6/3
	 *
	 **/
	public int getBranchsMax()
	{
		return 0;
	}
	/**
	 *setter
	 *BranchsMaxの値を書き替える
	 *松きり坊主 144542 2013/6/3
	 **/
	public void setBranchsMax()
	{
		return;
	}
	/**
	 *getter
	 *Nodesのindex番目のnodeを返す
	 *松きり坊主 144542 2013/6/3
	 **/
	public TreeNode getNodes(int index)
	{
		return null;
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
	 *TreeNode.TreeBranchのPoint情報からそれぞれの場所を計算する様子を
	 *アニメーションにする
	 *松きり坊主 144542 2013/6/3
	 **/
	public void animationTree()
	{
		return;
	}
	/**
	 *ファイルからそれぞれの情報を読み取る
	 *松きり坊主 144542 2013/6/3
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
	 *Nodeの情報をファイルから読む
	 *松きり坊主 144542 2013/6/3
	 **/
	public TreeNode inputNode()
	{
		return null;
	}
	/**
	 *Branchの情報をファイルから読む
	 *松きり坊主 144542 2013/6/3
	 **/
	public TreeBranch inputBranch()
	{
		return null;
	}
	/**
	 *View,Controllerに報告する.
	 *松きり坊主 144542 2013/6/3
	 **/
	public void change()
	{
		return;
	}
	/**
	 *描画する。
	 *松きり坊主 144542 2013/6/3
	 **/
	public void picture()
	{
		return;
	}
}