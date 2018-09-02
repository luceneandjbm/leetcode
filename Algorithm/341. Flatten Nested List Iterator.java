/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
	
	List<Integer> lst = new ArrayList<>();
	Iterator<Integer> it ;
	public void flatten(List<NestedInteger> nestedList){
		for(NestedInteger nested : nestedList){
			if(nested.isInteger()){
				lst.add(nested.getInteger());
			}else {
				flatten(nested.getList());
			}
		}
	}
	/*
	  初始化的时候，将元素全部放入到list里面，接着放入到迭代器里面
	*/
    public NestedIterator(List<NestedInteger> nestedList) {
		flatten(nestedList);
		it = lst.iterator();
    }

    @Override
    public Integer next() {
		return it.next();
    }

    @Override
    public boolean hasNext() {
		return it.hasNext();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */	
