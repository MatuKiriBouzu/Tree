package TreeProject;



import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;


public class TreeView extends mvc.View
{
	protected TreeModel model;//modelからTreeModelに変更、間違っているかも6/13虎谷
	//protected TreeController controller;//

	
	public TreeView(TreeModel aModel)//
	{
		super(aModel);
		model = aModel;//親で代入しているが、描画できなくなるのでもう一度上書き　間違っているかも6/13虎谷
	}
	
	public void paintComponent(Graphics aGraphics)//ほぼ流用6/13虎谷
	{
		int width = this.getWidth();
		int height = this.getHeight();
		aGraphics.setColor(Color.WHITE);
		aGraphics.fillRect(0, 0, width, height);
		if (model == null) { return; }
		BufferedImage picture = model.picture();
		aGraphics.setColor(Color.BLACK);
		
		//==============仮作成部=====================
		for(TreeNode node : model.nodes)//オリジナル　//nodeを作画6/13虎谷 ※仮作成
		{
			int X = (int)node.getTarget().getX();
			int Y = (int)node.getTarget().getY();
			aGraphics.drawString(node.getDate(),X,Y);
			aGraphics.drawRect(X,Y-12, (int)node.desideWidth().getX(), 14);
		}
		for(TreeBranch branch : model.branchs)
		{
			int X1 = (int)branch.getParentP().getX();
			int Y1 = (int)branch.getParentP().getY();
			int X2 = (int)branch.getChildP().getX();
			int Y2 = (int)branch.getChildP().getY();
			aGraphics.drawLine(X1,Y1,X2,Y2);
		}
		//==========================================
		
		return;
	}


}