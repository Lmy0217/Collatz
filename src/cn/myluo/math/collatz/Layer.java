package cn.myluo.math.collatz;

import java.util.HashMap;
import java.util.Map;

public class Layer {
	
	private Map<Integer, Node> nodeMap;
	
	private long min;
	
	public Layer() {
		nodeMap = new HashMap<Integer, Node>();
	}
	
	public Map<Integer, Node> getNodeMap() {
		return nodeMap;
	}
	
	public long getMin() {
		return min;
	}
	
	public void addNode(int index, Node node) {
		if(!node.isEnd() && getMin() > node.min() && node.min() != 1 || getMin() == 0) min = node.min();
		getNodeMap().put(index, node);
	}
	
	public int size() {
		return getNodeMap().size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Layer size:" + size() + " min:" + getMin() + " { ");
		if (getNodeMap().size() > 0)
			for (Map.Entry<Integer, Node> entry : getNodeMap().entrySet())
				sb.append(entry.getKey() + " : {" + entry.getValue() + "}, ");
		sb.delete(sb.length() - 2, sb.length());
		sb.append(" }");
		return sb.toString();
	}
}
