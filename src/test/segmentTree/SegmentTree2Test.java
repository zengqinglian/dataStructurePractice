package segmentTree;

import static org.junit.Assert.*;

import org.junit.Test;

public class SegmentTree2Test {
	@Test
    public void testUpdateSumRange_WithEmpty() throws Exception {
		SegmentTree2 tree = new SegmentTree2(new int[]{});
        assertEquals(0, tree.sumRange(0, 0));
    }

    @Test
    public void testUpdateSumRange_WithSingleton() throws Exception {
    	SegmentTree2 tree = new SegmentTree2(new int[]{1});
        assertEquals(1, tree.sumRange(0, 0));
        tree.update(0, 2);
        assertEquals(2, tree.sumRange(0, 0));
    }

    @Test
    public void testUpdateSumRange_WithPairElements() throws Exception {
    	SegmentTree2 tree = new SegmentTree2(new int[]{1,2,3,4,5,6});
        assertEquals(12, tree.sumRange(2, 4));
        tree.update(3, 2);
        assertEquals(10, tree.sumRange(2, 4));
    }

    @Test
    public void testUpdateSumRange_WithInPairElements() throws Exception {
    	SegmentTree2 tree = new SegmentTree2(new int[]{1,2,3,4,5,6,7});
        assertEquals(12, tree.sumRange(2, 4));
        tree.update(3, 2);
        assertEquals(10, tree.sumRange(2, 4));
    }
}
