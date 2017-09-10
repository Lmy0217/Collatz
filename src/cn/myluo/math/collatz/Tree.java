package cn.myluo.math.collatz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tree {
	
	private int a;
	
	private int b;
	
	private int c;
	
	private List<Node> tree;
	
	private List<Layer> layerList;
	
	private Tree() {
		tree = new ArrayList<Node>();
		tree.add(new Node(-1));
		tree.add(new Node(0));
		tree.get(0).addChildNode(tree.size() - 1);
		layerList = new ArrayList<Layer>();
	}
	
	public Tree(int a, int b, int c, int maxLayer) {
		this();
		this.a = a;
		this.b = b;
		this.c = c;
		create(maxLayer);
	}
	
	private void create(int maxLayer) {
		int nodeIndex = 1;
		while(nodeIndex < tree.size()) {
			Node node = tree.get(nodeIndex);
			if(node.isEnd()) {
				nodeIndex = nodeIndex + 1;
				continue;
			}
			if(isEstablish(node)) {
				node.setEnd(true);
				layersAddNode(nodeIndex, node);
				nodeIndex = nodeIndex + 1;
				continue;
			}
			if(node.getna() % c == 0 && node.getnb() % c == 0) {
				divide(node);
				continue;
			} else if(node.getna() % c == 0 && node.getnb() % c != 0) {
				if(1 + node.getNa() < maxLayer) iterator(nodeIndex);
				layersAddNode(nodeIndex, node);
				nodeIndex = nodeIndex + 1;
				continue;
			} else if (node.getna() % c != 0) {
				split(nodeIndex);
				continue;
			}
		}
	}
	
	private void layersAddNode(int nodeIndex, Node node) {
		if(layerList.size() < 1 + node.getNa()) layerList.add(new Layer());
		layerList.get(node.getNa()).addNode(nodeIndex, node);
	}
	
	private boolean isEstablish(Node node) {
		return node.geta() + node.getb() > node.getna() + node.getnb();
	}
	
	private void divide(Node node) {
		node.setna(node.getna() / c);
		node.setnb(node.getnb() / c);
		node.upNc();
	}
	
	private void iterator(int preNodeIndex) {
		Node preNode = tree.get(preNodeIndex);
		tree.add(new Node(preNode.geta(), preNode.getb(), a * preNode.getna(), b + a * preNode.getnb(), preNode.upNa(), preNode.getNc(), preNodeIndex));
		preNode.addChildNode(tree.size() - 1);
	}
	
	private void split(int nodeIndex) {
		Node node = tree.get(nodeIndex);
		Node preNode = tree.get(node.getPreNodeIndex());
		for(int i = 1; i < Math.abs(c); i++) {
			tree.add(new Node(c * node.geta(), node.getb() - i * node.geta(), c * node.getna(), node.getnb() - i * node.getna(), node.getNa(), node.getNc(), node.getPreNodeIndex()));
			preNode.addChildNode(tree.size() - 1);
		}
		node.seta(c * node.geta());
		node.setna(c * node.getna());
	}
	
	private String showTree(int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; tree.get(index).getPreNodeIndex() != -1 && i <= tree.get(index).getNa(); i++) sb.append("|  ");
		sb.append(tree.get(index).toString() + "\n");
		List<Integer> childNodeList = tree.get(index).getChildNodeList();
		Collections.sort(childNodeList, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return tree.get(o1).getChildNodeList().size() - tree.get(o2).getChildNodeList().size();
			}
		});
		for(int i = 0; i < childNodeList.size(); i++) {
			sb.append(showTree(childNodeList.get(i)));
		}
		return sb.toString();
	}
	
	private String showLayers() {
		StringBuilder sb = new StringBuilder();
		sb.append("Layers {\n");
		for(int i = 0; i < layerList.size(); i++) {
			sb.append("deep " + (1 + i) + " " + layerList.get(i) + "\n");
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String toString() {
		return showTree(0) + showLayers();
	}
	
	public static void main(String[] args) {
		try {
			File file = new File("result.txt");
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.write((new Tree(3, 1, 2, 10)).toString());
			printWriter.flush();
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
