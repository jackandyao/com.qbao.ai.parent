package com.qbao.ai.parse.filter;

public class FilterSet{

	private final long[] elements;
	
	public FilterSet() {
		 elements = new long[1 + (65535 >>> 6)];
	}
	
	public void add(final int no) {
		elements[no >>> 6] |= (1L << (no & 63));
	}
	
	public void add(final int... no) {
		for(int currNo : no)
			elements[currNo >>> 6] |= (1L << (currNo & 63));
	}
	
	public void remove(final int no) {
		elements[no >>> 6] &= ~(1L << (no & 63));
	}
	
	/**
	 * 
	 * @param no
	 * @return true:添加成功	false:原已包含
	 */
	public boolean addAndNotify(final int no) {
		int eWordNum = no >>> 6;
		long oldElements = elements[eWordNum];
		elements[eWordNum] |= (1L << (no & 63));
		boolean result = elements[eWordNum] != oldElements;
//		if (result)
//			size++;
		return result;
	}
	
	/**
	 * 
	 * @param no
	 * @return true:移除成功	false:原本就不包含
	 */
	public boolean removeAndNotify(final int no) {
		int eWordNum = no >>> 6;
		long oldElements = elements[eWordNum];
		elements[eWordNum] &= ~(1L << (no & 63));
		boolean result = elements[eWordNum] != oldElements;
//		if (result)
//			size--;
		return result;
	}
	
	public boolean contains(final int no) {
        return (elements[no >>> 6] & (1L << (no & 63))) != 0;
    }

	public boolean containsAll(final int... no) {
		if(no.length==0)
			return true;
		for(int currNo : no)
			if((elements[currNo >>> 6] & (1L << (currNo & 63))) == 0)
				return false;
		return true;
	}
	
	public boolean containsAll_ueslessWay(final int... no) {
		long[] elements = new long[this.elements.length];
		for(int currNo : no){
			elements[currNo >>> 6] |= (1L << (currNo & 63));
		}
		
		for (int i = 0; i < elements.length; i++)
			if ((elements[i] & ~this.elements[i]) != 0)
				return false;
		return true;
	}
	
	public int size() {
		int size = 0;
		for (long element : elements)
			size += Long.bitCount(element);
		return size;
	}
	
	public static void main(String[] args) {
		FilterSet oi = new FilterSet();
		System.out.println(oi.elements.length);
	}

}
