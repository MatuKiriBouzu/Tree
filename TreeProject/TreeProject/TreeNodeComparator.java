package TreeProject;
import java.util.Comparator;
public class TreeNodeComparator implements Comparator<TreeNode>{
	public int compare(TreeNode arg0,TreeNode arg1){
		int no0 = arg0.getNumber();
		int no1 = arg1.getNumber();
		if(no0>no1)
		{
			return 1;
		}
		else if(no0 == no1)
		{
			return 0;
		}
		else{
			return -1;
		}
		
	}

}