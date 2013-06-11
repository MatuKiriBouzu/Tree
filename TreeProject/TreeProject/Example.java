package TreeProject;

public class Example {

	public static void main(String[] args)
	{
		TreeModel a = new TreeModel();
		TreeBranch k = null;
		a.inputTree("aaaa");
		k = (TreeBranch)a.branchs.get(1);
		System.out.println(k.parent);
	}
}