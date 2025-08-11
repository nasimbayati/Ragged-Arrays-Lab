package src.arrays;

import java.util.*;

public class RaggedArrays {

    /** Create a ragged 2D array with given row widths and fill with 1..N (as Integer). */
    public static Object[][] createFilled(int... widths) {
        if (widths == null || widths.length == 0) throw new IllegalArgumentException("widths required");
        int total = 0;
        for (int w : widths) {
            if (w <= 0) throw new IllegalArgumentException("row widths must be > 0");
            total += w;
        }
        Object[][] grid = new Object[widths.length][];
        int counter = 1;
        for (int r = 0; r < widths.length; r++) {
            grid[r] = new Object[widths[r]];
            for (int c = 0; c < widths[r]; c++) {
                grid[r][c] = Integer.valueOf(counter++);
            }
        }
        return grid;
    }

    /** Print the ragged array as rows of space-separated values. */
    public static void printGrid(Object[][] grid) {
        for (Object[] row : grid) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                if (i > 0) sb.append(' ');
                sb.append(row[i]);
            }
            System.out.println(sb.toString());
        }
    }

    /** Return the widths of each row. */
    public static int[] rowWidths(Object[][] grid) {
        int[] w = new int[grid.length];
        for (int i = 0; i < grid.length; i++) w[i] = grid[i].length;
        return w;
    }

    /** Final value equals the sum of row widths (since we count 1..N). */
    public static int finalValue(Object[][] grid) {
        int sum = 0;
        for (Object[] row : grid) sum += row.length;
        return sum;
    }

    /** Generate random widths in [2..6] for rows, optionally deterministic with seed. */
    public static int[] randomWidths(int rows, Long seed) {
        if (rows <= 0) throw new IllegalArgumentException("rows must be > 0");
        Random rnd = (seed == null) ? new Random() : new Random(seed);
        int[] widths = new int[rows];
        for (int i = 0; i < rows; i++) widths[i] = 2 + rnd.nextInt(5); // 2..6
        return widths;
    }

    /** Join ints for display. */
    public static String join(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
