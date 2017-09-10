package cn.myluo.math.collatz;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private long a = 1;
	
	private long b = 0;

	private long na;
	
	private long nb;
	
	private int Na;
	
	private int Nc;
	
	private boolean isEnd;
	
	private int preNodeIndex;
	
	private List<Integer> childNodeList;
	
	private Node() {
		na = a;
		nb = b;
		childNodeList = new ArrayList<Integer>();
	}
	
	public Node(int preNodeIndex) {
		this();
		this.preNodeIndex = preNodeIndex;
	}
	
	public Node(long a, long b, long na, long nb, int Na, int Nc, int preNodeIndex) {
		this(preNodeIndex);
		this.a = a;
		this.b = b;
		this.na = na;
		this.nb = nb;
		this.Na = Na;
		this.Nc = Nc;
	}
	
	public long geta() {
		return a;
	}
	
	public void seta(long a) {
		this.a = a;
	}
	
	public long getb() {
		return b;
	}
	
	public void setb(long b) {
		this.b = b;
	}

	public long getna() {
		return na;
	}
	
	public void setna(long na) {
		this.na = na;
	}

	public long getnb() {
		return nb;
	}
	
	public void setnb(long nb) {
		this.nb = nb;
	}

	public int getNa() {
		return Na;
	}

	public int upNa() {
		return 1 + getNa();
	}

	public int getNc() {
		return Nc;
	}

	public void upNc() {
		Nc = 1 + getNc();
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public int getPreNodeIndex() {
		return preNodeIndex;
	}
	
	public List<Integer> getChildNodeList() {
		return childNodeList;
	}

	public void addChildNode(int childNodeIndex) {
		childNodeList.add(childNodeIndex);
	}
	
	public long min() {
		return geta() + getb();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(geta() != 1) sb.append("" + geta());
		sb.append("n");
		if(getb() > 0) sb.append("+" + getb());
		else if(getb() < 0) sb.append("" + getb());
		sb.append(" ==> ");
		if(getna() != 1) sb.append("" + getna());
		sb.append("n");
		if(getnb() > 0) sb.append("+" + getnb());
		else if(getnb() < 0) sb.append("" + getnb());
		sb.append(" Na:" + getNa() + " Nc:" + getNc());
		if(!isEnd()) sb.append(getChildNodeList().size() == 0 ? (" continue " + (geta() + getb())) : " ->");
		return sb.toString();
	}
}
