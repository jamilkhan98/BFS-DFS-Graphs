package bfs_dfs;

public class Node {
	private int value;
	private int distance;
	private Integer parent = null;
	Integer start_time, end_time;
	
	/**
	 * overloaded constructor to set value of node
	 * @param number - value of node
	 */
	Node(int number){
		value = number;
	}
	
	/**
	 * gets value of node
	 * @return - value of node
	 */
	int getValue() {
		return value;
	}
	
	/**
	 * gets parent of node
	 * @return - parent of node
	 */
	Integer getParent() {
		return parent;
	}
	
	/**
	 * gets distance of node
	 * @return - distance traveled to get to node
	 */
	int getDistance() {
		return distance;
	}
	
	/**
	 * new value of node
	 * @param newValue - new value of node to set
	 */
	void setValue(int newValue) {
		this.value = newValue;
	}
	
	/**
	 * sets parent of node
	 * @param newParent - new parent of node
	 */
	void setParent(Integer newParent) {
		parent = newParent;
	}
	
	/**
	 * sets distance traveled to get to node
	 * @param newDist - distance traveled to get to node
	 */
	void setDist(int newDist) {
		distance = newDist;
	}
	
	/**
	 * toString method for Node
	 */
	@Override
	public String toString() {
        return "" + value;
    }
}