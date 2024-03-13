package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    @Test
    public void testCentralValue() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Central value of exampleRange", 0, exampleRange.getCentralValue(), 1e-9);
        assertEquals("Central value of positiveRange", 5.5, positiveRange.getCentralValue(), 1e-9);
        assertEquals("Central value of negativeRange", -5.5, negativeRange.getCentralValue(), 1e-9);
        assertEquals("Central value of wideRange", 0, wideRange.getCentralValue(), 1e-9);
    }

    @Test
    public void testLength() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Length of exampleRange", 2, exampleRange.getLength(), 1e-9);
        assertEquals("Length of positiveRange", 9, positiveRange.getLength(), 1e-9);
        assertEquals("Length of negativeRange", 9, negativeRange.getLength(), 1e-9);
        assertEquals("Length of wideRange", 200, wideRange.getLength(), 1e-9);
    }

    @Test
    public void testLowerBound() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);

        assertEquals("Lower bound of exampleRange", -1, exampleRange.getLowerBound(), 1e-9);
        assertEquals("Lower bound of positiveRange", 1, positiveRange.getLowerBound(), 1e-9);
        assertEquals("Lower bound of negativeRange", -10, negativeRange.getLowerBound(), 1e-9);
    }
    
    @Test
    public void testUpperBound() {
        // Create local Range instances for this specific test
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);
        Range singlePointRange = new Range(5, 5); // Test a range where the lower and upper bounds are the same

        // Assert that getUpperBound returns the correct value for each range
        assertEquals("Upper bound of exampleRange", 1, exampleRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of positiveRange", 10, positiveRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of negativeRange", -1, negativeRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of wideRange", 100, wideRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of singlePointRange", 5, singlePointRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testConstrain() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Constraining 0 in exampleRange", 0, exampleRange.constrain(0), 1e-9);
        assertEquals("Constraining 5 in positiveRange", 5, positiveRange.constrain(5), 1e-9);
        assertEquals("Constraining -5 in negativeRange", -5, negativeRange.constrain(-5), 1e-9);
        assertEquals("Constraining 50 in wideRange", 50, wideRange.constrain(50), 1e-9);

        assertEquals("Constraining -2 in exampleRange", -1, exampleRange.constrain(-2), 1e-9);
        assertEquals("Constraining 11 in positiveRange", 10, positiveRange.constrain(11), 1e-9);
    }

    @Test
    public void testContains() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertTrue("exampleRange contains 0", exampleRange.contains(0));
        assertFalse("positiveRange does not contain 0", positiveRange.contains(0));
        assertTrue("negativeRange contains -5", negativeRange.contains(-5));
        assertFalse("wideRange does not contain 150", wideRange.contains(150));
    }

    @Test
    public void testIntersects_overlappingRanges() {
        Range range1 = new Range(-2, 2);
        Range range2 = new Range(-1, 3);
        assertTrue("Ranges overlap", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
        assertTrue("Ranges overlap (reversed order)", range2.intersects(range1.getLowerBound(), range1.getUpperBound()));
    }

    @Test
    public void testIntersects_nonOverlappingRanges() {
        Range range1 = new Range(-5, -2);
        Range range2 = new Range(1, 4);
        assertFalse("Ranges do not overlap", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
    }

    @Test
    public void testIntersects_singlePointRangeIntersecting() {
        Range range1 = new Range(4, 4);
        Range range2 = new Range(3, 5);
        assertTrue("Single-point range intersects", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
    }

    @Test
    public void testIntersects_singlePointRangeNotIntersecting() {
        Range range1 = new Range(4, 4);
        Range range2 = new Range(2, 3);
        assertFalse("Single-point range does not intersect", range1.intersects(range2.getLowerBound(), range2.getUpperBound()));
    }

    @Test
    public void testIntersects_identicalRanges() {
        Range range1 = new Range(1, 5);
        assertTrue("Identical ranges intersect", range1.intersects(range1.getLowerBound(), range1.getUpperBound()));
    }

    @Test
    public void testIntersects_b0WithinRange_b1Overlapping() {
        Range range = new Range(0, 10);
        assertTrue("b0 within range, b1 overlapping", range.intersects(5, 15));
    }

    @Test
    public void testIntersects_b0WithinRange_b1OutsideButAdjacent() {
        Range range = new Range(0, 10);
        assertFalse("b0 within range, b1 outside but adjacent", range.intersects(5, 10.1));
    }

    @Test
    public void testIntersects_b0OverlappingStart_b1Outside() {
        Range range = new Range(0, 10);
        assertTrue("b0 overlapping start, b1 outside", range.intersects(-1, 5));
    }

    @Test
    public void testIntersects_b0OverlappingEnd_b1Outside() {
        Range range = new Range(0, 10);
        assertTrue("b0 overlapping end, b1 outside", range.intersects(8, 15));
    }

    @Test
    public void testIntersects_b0EqualLowerBound_b1Overlapping() {
        Range range = new Range(5, 10);
        assertTrue("b0 equal to lower bound, b1 overlapping", range.intersects(5, 12));
    }

    @Test
    public void testIntersects_b0EqualUpperBound_b1Overlapping() {
      Range range = new Range(5, 10);
      Range overlappingRange = new Range(10, 15);  // Create a Range object for clarity
      assertTrue("b0 equal to upper bound, b1 overlapping", range.intersects(overlappingRange));
    }


    @Test
    public void testIntersects_b0BeforeLowerBound_b1BeforeLowerBound() {
        Range range = new Range(5, 10);
        assertFalse("b0 before lower bound, b1 before lower bound", range.intersects(0, 3));
    }

    @Test
    public void testIntersects_b0AfterUpperBound_b1AfterUpperBound() {
        Range range = new Range(5, 10);
        assertFalse("b0 after upper bound, b1 after upper bound", range.intersects(12, 15));
    }
    @Test
    public void testCombineIgnoringNaN_bothRangesValid() {
      Range range1 = new Range(0, 5);
      Range range2 = new Range(-2, 8);
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNotNull("Combined range should not be null", combinedRange);
      assertEquals("Combined range lower bound", -2, combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range upper bound", 8, combinedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombineIgnoringNaN_oneRangeNull() {
      Range range1 = null;
      Range range2 = new Range(3, 7);
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNotNull("Combined range should not be null (non-null range)", combinedRange);
      assertEquals("Combined range is the non-null range", range2.getLowerBound(), combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range is the non-null range", range2.getUpperBound(), combinedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombineIgnoringNaN_bothRangesNull() {
      Range range1 = null;
      Range range2 = null;
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNull("Combined range should be null (both null)", combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_oneRangeNaN() {
      Range range1 = new Range(Double.NaN, Double.NaN);
      Range range2 = new Range(2, 6);
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNotNull("Combined range should not be null (non-NaN range)", combinedRange);
      assertEquals("Combined range is the non-NaN range", range2.getLowerBound(), combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range is the non-NaN range", range2.getUpperBound(), combinedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombineIgnoringNaN_bothRangesNaN() {
      Range range1 = new Range(Double.NaN, Double.NaN);
      Range range2 = new Range(Double.NaN, Double.NaN);
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNull("Combined range should be null (both NaN)", combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_oneValidBound() {
      Range range1 = new Range(Double.NaN, 4);
      Range range2 = new Range(-1, Double.NaN);
      Range combinedRange = Range.combineIgnoringNaN(range1, range2);
      assertNotNull("Combined range should not be null (valid bounds)", combinedRange);
      assertEquals("Combined range lower bound", -1, combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range upper bound", 4, combinedRange.getUpperBound(), 1e-9);
    }
    @Test
    public void testExpand_standardExpansion() {
        Range range = new Range(0, 10);
        Range expandedRange = Range.expand(range, 0.2, 0.3);
        assertEquals("Expanded range lower bound", -2, expandedRange.getLowerBound(), 1e-9);
        assertEquals("Expanded range upper bound", 13, expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpand_zeroMargins() {
        Range range = new Range(-5, 5);
        Range expandedRange = Range.expand(range, 0, 0);
        assertEquals("Expanded range is the same as original range", range, expandedRange);
    }

    @Test
    public void testExpand_negativeMargins() {
        Range range = new Range(2, 8);
        Range expandedRange = Range.expand(range, -0.1, -0.2);
        assertEquals("Expanded range is smaller than original range", 2.8, expandedRange.getLowerBound(), 1e-9);
        assertEquals("Expanded range is smaller than original range", 7.2, expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpand_largeMargins_overlappingBounds() {
        Range range = new Range(1, 4);
        Range expandedRange = Range.expand(range, 2, 3);
        // Lower bound will reach -5, upper bound will reach 13,
        // but they'll be adjusted to a single-point range at 4
        assertEquals("Expanded range with overlapping bounds", 4, expandedRange.getLowerBound(), 1e-9);
        assertEquals("Expanded range with overlapping bounds", 4, expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpand_veryLargeMargins_singlePointRange() {
        Range singlePointRange = new Range(5, 5);
        Range expandedRange = Range.expand(singlePointRange, 10, 15);
        assertEquals("Expanded single-point range remains single-point", 5, expandedRange.getLowerBound(), 1e-9);
        assertEquals("Expanded single-point range remains single-point", 5, expandedRange.getUpperBound(), 1e-9);
    }
    @Test
    public void testExpandToInclude_nullRange() {
      double value = 7.5;
      Range expandedRange = Range.expandToInclude(null, value);
      assertNotNull("Expanded range from null should not be null", expandedRange);
      assertEquals("Expanded range lower bound (null range)", value, expandedRange.getLowerBound(), 1e-9);
      assertEquals("Expanded range upper bound (null range)", value, expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpandToInclude_valueBelowRange() {
      Range range = new Range(2, 8);
      double value = 1;
      Range expandedRange = Range.expandToInclude(range, value);
      assertEquals("Expanded range lower bound (below range)", value, expandedRange.getLowerBound(), 1e-9);
      assertEquals("Expanded range upper bound (below range)", range.getUpperBound(), expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpandToInclude_valueAboveRange() {
      Range range = new Range(2, 8);
      double value = 10;
      Range expandedRange = Range.expandToInclude(range, value);
      assertEquals("Expanded range lower bound (above range)", range.getLowerBound(), expandedRange.getLowerBound(), 1e-9);
      assertEquals("Expanded range upper bound (above range)", value, expandedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpandToInclude_valueWithinRange() {
      Range range = new Range(2, 8);
      double value = 5; // Within the range
      Range expandedRange = Range.expandToInclude(range, value);
      assertEquals("Expanded range is the same (within range)", range, expandedRange);
    }

    @Test
    public void testExpandToInclude_valueAtLowerBound() {
      Range range = new Range(5, 8);
      double value = 5; // At the lower bound
      Range expandedRange = Range.expandToInclude(range, value);
      assertEquals("Expanded range is the same (at lower bound)", range, expandedRange);
    }

    @Test
    public void testExpandToInclude_valueAtUpperBound() {
      Range range = new Range(5, 8);
      double value = 8; // At the upper bound
      Range expandedRange = Range.expandToInclude(range, value);
      assertEquals("Expanded range is the same (at upper bound)", range, expandedRange);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpandToInclude_NaNValue() {
      Range range = new Range(2, 8);
      double value = Double.NaN;
      Range.expandToInclude(range, value);  // This should throw an IllegalArgumentException
    }
    @Test
    public void testCombine_bothRangesNull() {
      Range combinedRange = Range.combine(null, null);
      assertNull("Combined range with both ranges null should be null", combinedRange);
    }

    @Test
    public void testCombine_range1Null() {
      Range range2 = new Range(5, 10);
      Range combinedRange = Range.combine(null, range2);
      assertEquals("Combined range with range1 null should be range2", range2, combinedRange);
    }

    @Test
    public void testCombine_range2Null() {
      Range range1 = new Range(2, 8);
      Range combinedRange = Range.combine(range1, null);
      assertEquals("Combined range with range2 null should be range1", range1, combinedRange);
    }

    @Test
    public void testCombine_disjointRanges() {
      Range range1 = new Range(1, 4);
      Range range2 = new Range(6, 9);
      Range combinedRange = Range.combine(range1, range2);
      assertEquals("Combined range lower bound (disjoint ranges)", 1, combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range upper bound (disjoint ranges)", 9, combinedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombine_overlappingRanges() {
      Range range1 = new Range(2, 8);
      Range range2 = new Range(5, 11);
      Range combinedRange = Range.combine(range1, range2);
      assertEquals("Combined range lower bound (overlapping ranges)", 2, combinedRange.getLowerBound(), 1e-9);
      assertEquals("Combined range upper bound (overlapping ranges)", 11, combinedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombine_identicalRanges() {
      Range range1 = new Range(5, 10);
      Range range2 = new Range(5, 10);
      Range combinedRange = Range.combine(range1, range2);
      assertEquals("Combined range with identical ranges should be the same", range1, combinedRange);
    }

    @Test
    public void testCombine_range2WithinRange1() {
      Range range1 = new Range(2, 12);
      Range range2 = new Range(5, 8);
      Range combinedRange = Range.combine(range1, range2);
      assertEquals("Combined range with range2 within range1 should be range1", range1, combinedRange);
    }

    @Test
    public void testCombine_range1WithinRange2() {
      Range range1 = new Range(5, 8);
      Range range2 = new Range(2, 12);
      Range combinedRange = Range.combine(range1, range2);
      assertEquals("Combined range with range1 within range2 should be range2", range2, combinedRange);
    }
    @Test
    public void testHashCode_differentRanges() {
      Range range1 = new Range(2.5, 7.8);
      Range range2 = new Range(1.1, 4.2);
      Range range3 = new Range(2.5, 7.8); // Same values as range1

      assertNotEquals("Hash code for different ranges", range1.hashCode(), range2.hashCode());
      assertEquals("Hash code for identical ranges", range1.hashCode(), range3.hashCode());
    }
    @Test
    public void testHashCode_NaNValues() {
      Range range1 = new Range(Double.NaN, Double.NaN);
      Range range2 = new Range(2.5, 7.8); // Non-NaN range

      // Hash code for NaN ranges might not be consistent across implementations, 
      // so avoid strict equality checks.
      assertNotSame("Hash code for NaN range might differ", range1.hashCode(), range2.hashCode());
    }
    @Test
    public void testHashCode_equalHashCodes() {
      Range range1 = new Range(2.5, 7.8);
      Range range2 = new Range(2.500000000000001, 7.8); // Slightly different upper bound

      // Hash codes might collide, so ensure objects are still equal despite potential collisions.
      assertEquals("Hash code collision, but objects should still be equal", range1.hashCode(), range2.hashCode());
      assertTrue("Objects with equal hash codes should be equal", range1.equals(range2));
    }
    @Test
    public void testShift_allowingZeroCrossing() {
      Range baseRange = new Range(2, 8);
      Range shiftedRange = Range.shift(baseRange, -5, true);
      assertEquals("Shifted range lower bound (allowing zero crossing)", -3, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (allowing zero crossing)", 3, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_notAllowingZeroCrossing() {
      Range baseRange = new Range(-1, 4);
      Range shiftedRange = Range.shift(baseRange, -4, false);
      assertEquals("Shifted range lower bound (not allowing zero crossing)", 0, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (not allowing zero crossing)", 0, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_largeDelta() {
      Range baseRange = new Range(5, 10);
      Range shiftedRange = Range.shift(baseRange, 1000, true);
      assertEquals("Shifted range lower bound (large delta)", 1005, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (large delta)", 1010, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_negativeDelta() {
      Range baseRange = new Range(-2, 3);
      Range shiftedRange = Range.shift(baseRange, -5, true);
      assertEquals("Shifted range lower bound (negative delta)", -7, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (negative delta)", -2, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_zeroDelta() {
      Range baseRange = new Range(1, 6);
      Range shiftedRange = Range.shift(baseRange, 0, true);
      assertEquals("Shifted range with zero delta should be the same", baseRange, shiftedRange);
    }

    @Test
    public void testShift_NaNDelta() {
      Range baseRange = new Range(2, 8);
      Range shiftedRange = Range.shift(baseRange, Double.NaN, true);
      assertEquals("Shifting with NaN delta should result in a range with NaN bounds", Double.NaN, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifting with NaN delta should result in a range with NaN bounds", Double.NaN, shiftedRange.getUpperBound(), 1e-9);
    }
    @Test
    public void testScale_positiveFactor() {
        Range baseRange = new Range(2, 8);
        Range scaledRange = Range.scale(baseRange, 3);
        assertEquals("Scaled range lower bound (positive factor)", 6, scaledRange.getLowerBound(), 1e-9);
        assertEquals("Scaled range upper bound (positive factor)", 24, scaledRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testScale_factorOne() {
        Range baseRange = new Range(5, 10);
        Range scaledRange = Range.scale(baseRange, 1);
        assertEquals("Scaled range with factor 1 should be the same", baseRange, scaledRange);
    }

    @Test
    public void testScale_factorZero() {
        Range baseRange = new Range(-2, 3);
        Range scaledRange = Range.scale(baseRange, 0);
        assertEquals("Scaled range with factor 0 should have zero bounds", 0, scaledRange.getLowerBound(), 1e-9);
        assertEquals("Scaled range with factor 0 should have zero bounds", 0, scaledRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testScale_NaNFactor() {
        Range baseRange = new Range(2, 8);
        Range scaledRange = Range.scale(baseRange, Double.NaN);
        assertEquals("Scaling with NaN factor should result in a range with NaN bounds", Double.NaN, scaledRange.getLowerBound(), 1e-9);
        assertEquals("Scaling with NaN factor should result in a range with NaN bounds", Double.NaN, scaledRange.getUpperBound(), 1e-9);
    }


    @Test
    public void testShift_notAllowingZeroCrossing_largeDelta() {
      Range baseRange = new Range(5, 10);
      Range shiftedRange = Range.shift(baseRange, 1000);
      // Bounds should stop at 0 since zero-crossing is not allowed
      assertEquals("Shifted range lower bound (not allowing zero crossing)", 0, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (not allowing zero crossing)", 500, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_notAllowingZeroCrossing_negativeDelta() {
      Range baseRange = new Range(-2, 3);
      Range shiftedRange = Range.shift(baseRange, -10);
      // Bounds should stop at 0 since zero-crossing is not allowed
      assertEquals("Shifted range lower bound (not allowing zero crossing)", -2, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifted range upper bound (not allowing zero crossing)", 0, shiftedRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testShift_notAllowingZeroCrossing_zeroDelta() {
      Range baseRange = new Range(1, 6);
      Range shiftedRange = Range.shift(baseRange, 0);
      assertEquals("Shifted range with zero delta should be the same (not allowing zero crossing)", baseRange, shiftedRange);
    }

    @Test
    public void testShift_notAllowingZeroCrossing_NaNDelta() {
      Range baseRange = new Range(2, 8);
      Range shiftedRange = Range.shift(baseRange, Double.NaN);
      // Assuming shiftWithNoZeroCrossing handles NaN values appropriately
      assertEquals("Shifting with NaN delta should result in a range with NaN bounds", Double.NaN, shiftedRange.getLowerBound(), 1e-9);
      assertEquals("Shifting with NaN delta should result in a range with NaN bounds", Double.NaN, shiftedRange.getUpperBound(), 1e-9);
    }
    
    @Test
    public void testToString() {
        Range range = new Range(1, 5);
        assertEquals("Range[1.0,5.0]", range.toString());
    }
    
    @Test
    public void testExpand_negativeMargin() {
        Range range = new Range(10, 20);
        Range result = Range.expand(range, -0.1, -0.1);
        assertEquals("The range should have been contracted", 11, result.getLowerBound(), 1e-9);
        assertEquals("The range should have been contracted", 19, result.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpand_zeroMargin() {
        Range range = new Range(10, 20);
        Range result = Range.expand(range, 0, 0);
        assertEquals("The range should not have changed", range, result);
    }
    
    @Test
    public void testExpand_positiveMargin() {
        Range range = new Range(10, 20);
        Range result = Range.expand(range, 0.1, 0.1);
        assertEquals("The range should have been expanded", 9, result.getLowerBound(), 1e-9);
        assertEquals("The range should have been expanded", 21, result.getUpperBound(), 1e-9);
    }
    
    @Test
    public void testCombineIgnoringNaN_withNaNValues() {
        Range range1 = new Range(Double.NaN, 1);
        Range range2 = new Range(1, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(result);
        assertEquals("Lower bound should be 1", 1, result.getLowerBound(), 1e-9);
        assertEquals("Upper bound should be 1", 1, result.getUpperBound(), 1e-9);
    }
    
    @Test
    public void testShiftWithNoZeroCrossing_positiveDelta() {
        Range range = new Range(-5, 5);
        Range result = Range.shift(range, 3, false);
        assertEquals("The lower bound should not have crossed zero", 0, result.getLowerBound(), 1e-9);
        assertEquals("The upper bound should have shifted", 8, result.getUpperBound(), 1e-9);
    }
    
    @Test
    public void testShiftWithNoZeroCrossing_negativeDelta() {
        Range range = new Range(2, 12);
        Range result = Range.shift(range, -3, false);
        assertEquals("The lower bound should have shifted", 0, result.getLowerBound(), 1e-9);
        assertEquals("The upper bound should not have crossed zero", 9, result.getUpperBound(), 1e-9);
    }

    @Test
    public void testCombineIgnoringNaN_withNaNBounds() {
        Range rangeWithNaNLower = new Range(Double.NaN, 5);
        Range rangeWithNaNUpper = new Range(1, Double.NaN);
        Range normalRange = new Range(1, 5);
        
        // Case where one range has a NaN lower bound
        Range result = Range.combineIgnoringNaN(rangeWithNaNLower, normalRange);
        assertEquals("Combine should ignore NaN lower bound", 1, result.getLowerBound(), 1e-9);
        assertEquals("Combine should take non-NaN upper bound", 5, result.getUpperBound(), 1e-9);

        // Case where one range has a NaN upper bound
        result = Range.combineIgnoringNaN(normalRange, rangeWithNaNUpper);
        assertEquals("Combine should take non-NaN lower bound", 1, result.getLowerBound(), 1e-9);
        assertEquals("Combine should ignore NaN upper bound", 5, result.getUpperBound(), 1e-9);

        // Case where both ranges have NaN in different bounds
        result = Range.combineIgnoringNaN(rangeWithNaNLower, rangeWithNaNUpper);
        assertEquals("Combine should ignore NaN bounds", 1, result.getLowerBound(), 1e-9);
        assertEquals("Combine should ignore NaN bounds", 5, result.getUpperBound(), 1e-9);

        // Case where both ranges have NaN bounds
        Range rangeWithNaNBounds = new Range(Double.NaN, Double.NaN);
        result = Range.combineIgnoringNaN(rangeWithNaNBounds, rangeWithNaNBounds);
        assertNull("Combine of two NaN ranges should be null", result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testScale_negativeFactor() {
        Range baseRange = new Range(1, 6);
        Range.scale(baseRange, -2); // Should throw IllegalArgumentException
    }
    
    @Test
    public void testRangeConstructor_ValidArguments() {
        Range range = new Range(1.0, 5.0);
        assertEquals(1.0, range.getLowerBound(), 1e-9);
        assertEquals(5.0, range.getUpperBound(), 1e-9);
    }

    @Test
    public void testRangeConstructor_EqualBounds() {
        Range range = new Range(2.0, 2.0);
        assertEquals(2.0, range.getLowerBound(), 1e-9);
        assertEquals(2.0, range.getUpperBound(), 1e-9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeConstructor_InvalidArguments() {
        new Range(5.0, 1.0); // This should throw an IllegalArgumentException
    }

    @Test
    public void testCombineIgnoringNaN_OneRangeHasNaN() {
        Range range1 = new Range(1.0, Double.NaN);
        Range range2 = new Range(Double.NaN, 2.0);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Combined range should ignore NaN in upper bound of range1", 1.0, combined.getLowerBound(), 1e-9);
        assertEquals("Combined range should ignore NaN in lower bound of range2", 2.0, combined.getUpperBound(), 1e-9);
    }

    @Test
    public void testExpand_ResultsInIdenticalBounds() {
        Range range = new Range(2, 2);
        Range result = Range.expand(range, 0.1, 0.1);
        assertEquals("Expanded range with identical bounds should remain the same", 2, result.getLowerBound(), 1e-9);
        assertEquals("Expanded range with identical bounds should remain the same", 2, result.getUpperBound(), 1e-9);
    }

    @Test
    public void testShiftWithNoZeroCrossing_NegativeRangePositiveDelta() {
        Range range = new Range(-5, -3);
        Range result = Range.shift(range, 10, false);
        assertEquals("Shifted range should not cross zero (lower bound)", 0, result.getLowerBound(), 1e-9);
        assertEquals("Shifted range should not cross zero (upper bound)", 7, result.getUpperBound(), 1e-9);
    }

}