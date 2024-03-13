package org.jfree.data.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;


public class DataUtilitiesTest extends DataUtilities {

	private Mockery mockingContext;
	private Values2D values;

	@Before
	public void setUp() throws Exception {
		// setup
		this.mockingContext = new Mockery();
		this.values = this.mockingContext.mock(Values2D.class);

	}

	@Test
	public void calculateColumnTotalForTwoValues() {
//		This method takes nominal values for the columns

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(7.5));

				one(values).getValue(1, 0);
				will(returnValue(2.5));

			}
		});

		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(result, 10.0, .000000001d);
	}

	@Test
	public void calculateColumnTotalForOneMaxValue() {
//		This method takes One integer Maxvalue for testing
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(2.0));

			}
		});

		double result = DataUtilities.calculateColumnTotal(values, 0);

		assertEquals(result, Integer.MAX_VALUE + 2.0, .000000001d);
	}

	@Test
	public void calculateColumnTotalForOneMinValue() {
//		This method takes One integer minimum value for testing

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(-Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(10));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(result, (10 - Integer.MAX_VALUE), .000000001d);
	}
	


	@Test
	public void calculateColumnTotalForOneoMinOneMaxValue() {
//		This method takes one integer Max value and one Integer minimum value for testing for testing

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(-Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(Integer.MAX_VALUE));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);

		assertEquals(result, 0, .000000001d);
	}

	@Test
	public void calculateColumnTotalForALB() {
//		This takes One value just above the lower Bound for testing
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(1 - Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(0));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);

		assertEquals(result, 1 - Integer.MAX_VALUE, .000000001d);
	}

	@Test
	public void calculateColumnTotalForBUB() {
//		One value is just below the upper bound Range
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 0);
				will(returnValue(1));

				one(values).getValue(1, 0);
				will(returnValue(Integer.MAX_VALUE - 1));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 0);

		assertEquals(result, Integer.MAX_VALUE, .000000001d);
	}

	@Test
	public void calculateColumnTotalForLastColumn() {
//		Calculating column total for the last column
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 1);
				will(returnValue(1));

				one(values).getValue(1, 1);
				will(returnValue(2));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 1);

		assertEquals(result, 3, .000000001d);
	}

	@Test
	public void calculateColumnTotalForMaxColumn() {
//		Testing using the maximum possible column position 
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, Integer.MAX_VALUE);
				will(returnValue(10));

				one(values).getValue(1, Integer.MAX_VALUE);
				will(returnValue(20));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE);

		assertEquals(result, 30, .000000001d);
	}

	@Test
	public void calculateColumnTotalForMinColumn() {
//		Testing using the minimum possible column position 

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, Integer.MIN_VALUE);
				will(returnValue(10));

				one(values).getValue(1, Integer.MIN_VALUE);
				will(returnValue(20));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, Integer.MIN_VALUE);

		assertEquals(result, 30, .000000001d);
	}

	@Test
	public void calculateColumnTotalForBUPColumn() {
//		Testing using the below the upperBound possible column position 
		
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, Integer.MAX_VALUE - 1);
				will(returnValue(10));

				one(values).getValue(1, Integer.MAX_VALUE - 1);
				will(returnValue(20));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE - 1);

		assertEquals(result, 30, .000000001d);
	}

	@Test
	public void calculateColumnTotalForALBColumn() {
//		Testing using the Above the Lower Bound column position 

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));

				one(values).getValue(0, 1 - Integer.MAX_VALUE);
				will(returnValue(10));

				one(values).getValue(1, 1 - Integer.MAX_VALUE);
				will(returnValue(20));

			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 1 - Integer.MAX_VALUE);

		assertEquals(result, 30, .000000001d);
	}


//	TESTING METHOD CalculateColumnTotal(Values2D, int, int[])

	
	@Test
	public void calculateColumnTotalThreeArgsForAllRows() {
//		testing with nominal values as input

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(7.5));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {0,1,2};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, 15.0, .000000001d);
	}

	@Test
	public void calculateColumnTotalThreeArgsLBRow() {
//		Calculating for the Lower Bound Row

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(7.5));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, 7.5, .000000001d);
	}
	@Test
	public void calculateColumnTotalThreeArgsForTwoRows() {
// Calculating for two rows

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(7.5));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {1,2};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, 7.5, .000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeArgsUPBRow() {
// Calculating column total for the Upper Bound Row
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(7.5));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {2};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, 5.0, .000000001d);
	}
	
	
	@Test
	public void calculateColumnTotalThreeArgsRowUBValue() {
//		calculating with one value as upper bound

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, Integer.MAX_VALUE, .000000001d);
	}
	@Test
	public void calculateColumnTotalThreeArgsRowUBValueAndLBValue() {
//	Testing with one UpperBound Value and One Lower bound value

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(-Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(Integer.MAX_VALUE));

			}
		});
		int[] rows = {0,2};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, 0, .000000001d);
	}
	

	
	@Test
	public void calculateColumnTotalThreeArgsRowOneLBValue() {
//		testing with one lower bound value

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(-Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(2.5));
				
				one(values).getValue(2, 0);
				will(returnValue(5.0));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, -Integer.MAX_VALUE , .000000001d);
	}
	

	
	@Test
	public void calculateColumnTotalThreeArgsOneBUBValues() {
// testing with Values just below UpperBound for one row
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(Integer.MAX_VALUE-1));

				one(values).getValue(1, 0);
				will(returnValue(Integer.MAX_VALUE-1));
				
				one(values).getValue(2, 0);
				will(returnValue(Integer.MAX_VALUE -1));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, (Integer.MAX_VALUE-1) , .000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeArgsOneALBValues() {
//		testing for one value Above lower bound

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 0);
				will(returnValue(1-Integer.MAX_VALUE));

				one(values).getValue(1, 0);
				will(returnValue(Integer.MAX_VALUE-1));
				
				one(values).getValue(2, 0);
				will(returnValue(Integer.MAX_VALUE -1));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 0, rows);
		assertEquals(result, (1-Integer.MAX_VALUE) , .000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeArgsColumnTwo() {
// testing with a different column
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 1);
				will(returnValue(1-Integer.MAX_VALUE));

				one(values).getValue(1, 1);
				will(returnValue(Integer.MAX_VALUE-1));
				
				one(values).getValue(2, 1);
				will(returnValue(Integer.MAX_VALUE -1));

			}
		});
		int[] rows = {0};
		double result = DataUtilities.calculateColumnTotal(values, 1, rows);
		assertEquals(result, (1-Integer.MAX_VALUE) , .000000001d);
	}
	
	@Test
	public void calculateColumnTotalThreeArgsAllZeroValues() {
// testing with zero values
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 1);
				will(returnValue(0));

				one(values).getValue(1, 1);
				will(returnValue(0));
				
				one(values).getValue(2, 1);
				will(returnValue(0));

			}
		});
		int[] rows = {0,1,2};
		
		double result = DataUtilities.calculateColumnTotal(values, 1, rows);
		assertEquals(result, 0 , .000000001d);
	}
	
//	This should be false as int[] of rows is Invalid still the test is passing
	@Test
	public void calculateColumnTotalThreeArgsInvalidRowArray() {

		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));

				one(values).getValue(0, 1);
				will(returnValue(7.5));

				one(values).getValue(1, 1);
				will(returnValue(2.5));
				
				one(values).getValue(2, 1);
				will(returnValue(5.0));

			}
		});
		int[] rows = {4};
		
		double result = DataUtilities.calculateColumnTotal(values, 1, rows);
		assertEquals(result, 0 , .000000001d);
	}
	
// old code starts here
	
	 @Test
	    public void testCreateNumberArray() {
	        double[] inputData = {2.0, 3.5, 4.7};
	        Number[] expectedOutput = {2.0, 3.5, 4.7};

	        Number[] actualOutput = DataUtilities.createNumberArray(inputData);

	        assertArrayEquals(expectedOutput, actualOutput);
	    }
	    
	    @Test
	    public void testCreateNumberArray2D() {
	        double[][] inputData = {{2.0, 3.5}, {4.7, 5.2}};
	        Number[][] expectedOutput = {{2.0, 3.5}, {4.7, 5.2}};

	        Number[][] actualOutput = DataUtilities.createNumberArray2D(inputData);

	        assertArrayEquals(expectedOutput, actualOutput);
	    }

	    @Test
	    public void testCreateNumberArray2DWithNull() {
	        double[][] inputData = null;

	        try {
	            Number[][] result = DataUtilities.createNumberArray2D(inputData);
	            fail("Expected an IllegalArgumentException to be thrown");
	        } catch (IllegalArgumentException e) {
	            // Test passed!
	        }
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testCreateNumberArray2DWithEmptyArray() {
	        double[][] inputData = {};
	        DataUtilities.createNumberArray2D(inputData);
	    }
	    
	    @Test
	    public void testCalculateColumnTotal() {
	        mockingContext.checking(new Expectations() {{
	            allowing(values).getValue(with(any(Integer.class)), with(equal(0)));
	            will(onConsecutiveCalls(
	                returnValue(2.0), 
	                returnValue(3.5), 
	                returnValue(4.7),
	                returnValue(null) 
	            ));
	            allowing(values).getRowCount();
	            will(returnValue(3));  
	        }});
	        double total = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(10.2, total, 0.0001);
	    }


	    @Test
	    public void testCalculateColumnTotalWithInvalidColumn() {
	       mockingContext.checking(new Expectations() {{
	            allowing(values).getValue(with(any(Integer.class)), with(equal(1)));
	            will(returnValue(null));
	            allowing(values).getRowCount();
	            will(returnValue(3));
	        }});
	        double total = DataUtilities.calculateColumnTotal(values, 1);
	        assertEquals(0.0, total, 0.0001);
	    }

	    
	    @Test
	    public void testCalculateRowTotal() {
	        mockingContext.checking(new Expectations() {{
	            allowing(values).getValue(with(equal(0)), with(any(Integer.class)));
	            will(onConsecutiveCalls(
	                returnValue(2.0), 
	                returnValue(3.5), 
	                returnValue(4.7),
	                returnValue(null)  // default value for out-of-range columns
	            ));
	            allowing(values).getColumnCount();
	            will(returnValue(3));  // adjust this to match your actual number of columns
	        }});
	        double total = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals(10.2, total, 0.0001);
	    }


	    @Test
	    public void testCalculateRowTotalWithInvalidRow() {
	        mockingContext.checking(new Expectations() {{
	            allowing(values).getValue(with(equal(1)), with(any(Integer.class)));
	            will(returnValue(null));
	            allowing(values).getColumnCount();
	            will(returnValue(3));  
	        }});
	        double total = DataUtilities.calculateRowTotal(values, 1);
	        assertEquals(0.0, total, 0.0001);
	    }
	    
	    @Test
	    public void testGetCumulativePercentages() {
	        // Create input KeyedValues
	        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
	        keyedValues.addValue("0", 5.0);
	        keyedValues.addValue("1", 9.0);
	        keyedValues.addValue("2", 2.0);

	        // Expected output KeyedValues
	        DefaultKeyedValues expectedOutput = new DefaultKeyedValues();
	        expectedOutput.addValue("0", 0.3125);
	        expectedOutput.addValue("1", 0.875);
	        expectedOutput.addValue("2", 1.0);

	        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

	     // Assert each value in the output matches the expected output
	        for (int i = 0; i < expectedOutput.getItemCount(); i++) {
	            assertEquals(expectedOutput.getValue(i).doubleValue(), result.getValue(i).doubleValue(), 0.0001);
	        }

	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testGetCumulativePercentagesWithNullData() {
	        DataUtilities.getCumulativePercentages(null);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testGetCumulativePercentagesWithEmptyData() {
	        DefaultKeyedValues keyedValues = new DefaultKeyedValues(); // Create an empty KeyedValues object

	        DataUtilities.getCumulativePercentages(keyedValues);
	    }

	    // testing array equality
	    
	    @Test
	    public void testBothArraysNull() {
	        assertTrue(DataUtilities.equal(null, null));
	    }

	    @Test
	    public void testFirstArrayNullSecondNotNull() {
	        double[][] b = {{1.0, 2.0}};
	        assertFalse(DataUtilities.equal(null, b));
	    }

	    @Test
	    public void testFirstArrayNotNullSecondNull() {
	        double[][] a = {{1.0, 2.0}};
	        assertFalse(DataUtilities.equal(a, null));
	    }

	    @Test
	    public void testArraysOfDifferentLengths() {
	        double[][] a = {{1.0, 2.0}};
	        double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
	        assertFalse(DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testArraysWithDifferentValues() {
	        double[][] a = {{1.0, 2.0}};
	        double[][] b = {{2.0, 3.0}};
	        assertFalse(DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testArraysWithSameValues() {
	        double[][] a = {{1.0, 2.0}};
	        double[][] b = {{1.0, 2.0}};
	        assertTrue(DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testArraysWithNaNValues() {
	        double[][] a = {{Double.NaN, 2.0}};
	        double[][] b = {{Double.NaN, 2.0}};
	        assertTrue(DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testArraysWithINFValues() {
	        double[][] a = {{Double.POSITIVE_INFINITY, 2.0}};
	        double[][] b = {{Double.POSITIVE_INFINITY, 2.0}};
	        assertTrue(DataUtilities.equal(a, b));
	    }
	    
	    // testing clone function
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testCloneWithNullSource() {
	        double[][] source = null;
	        DataUtilities.clone(source);
	    }

	    @Test
	    public void testCloneWithNonEmptyArray() {
	        double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] cloned = DataUtilities.clone(source);
	        assertNotSame(source, cloned);
	        assertArrayEquals(source, cloned);
	    }

	    @Test
	    public void testCloneWithEmptyArray() {
	        double[][] source = new double[0][];
	        double[][] cloned = DataUtilities.clone(source);
	        assertNotSame(source, cloned);
	        assertEquals(0, cloned.length);
	    }

	    @Test
	    public void testCloneWithNullRows() {
	        double[][] source = {{1.0, 2.0}, null, {3.0, 4.0}};
	        double[][] cloned = DataUtilities.clone(source);
	        assertNotSame(source, cloned);
	        assertNull(cloned[1]);
	        assertArrayEquals(new double[]{1.0, 2.0}, cloned[0], 0.0);
	        assertArrayEquals(new double[]{3.0, 4.0}, cloned[2], 0.0);
	    }

	    @Test
	    public void testModificationAfterCloning() {
	        double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] cloned = DataUtilities.clone(source);
	        source[0][0] = 10.0; // Modify the original array
	        assertThat(source[0][0], not(equalTo(cloned[0][0])));
	    }
	    
	    // Values 2D Test
	    @Test
	    public void testCalculateRowTotalWithValidDataAndColumns() {
	        //final Values2D data = mockingContext.mock(Values2D.class);
	        
	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount(); will(returnValue(5));
	            oneOf(values).getValue(0, 1); will(returnValue(10));
	            oneOf(values).getValue(0, 3); will(returnValue(20));
	        }});
	        
	        double total = DataUtilities.calculateRowTotal(values, 0, new int[]{1, 3});
	        assertEquals(30.0, total, 0.0);
	    }

	    @Test
	    public void testCalculateRowTotalWithInvalidColumns() {
	       // final Values2D data = mockingContext.mock(Values2D.class);
	        
	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount(); will(returnValue(2));
	            oneOf(values).getValue(0, 0); will(returnValue(10));
	            // Column 3 is invalid, so it will not be called
	        }});
	        
	        double total = DataUtilities.calculateRowTotal(values, 0, new int[]{0, 3});
	        assertEquals(10.0, total, 0.0);
	    }



}
