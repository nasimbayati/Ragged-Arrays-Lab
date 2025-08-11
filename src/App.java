package src;

import java.util.*;
import src.arrays.RaggedArrays;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) { help(); return; }

        String cmd = args[0].toLowerCase();
        switch (cmd) {
            case "fill":
                // fill <w1> <w2> <w3> ...
                if (args.length < 2) { System.out.println("Usage: fill <w1> <w2> ..."); return; }
                int[] widths = parseWidths(Arrays.copyOfRange(args, 1, args.length));
                runOnce(widths);
                break;

            case "auto":
                // auto <rows> [seed]
                if (args.length < 2) { System.out.println("Usage: auto <rows> [seed]"); return; }
                Integer rows = tryParseInt(args[1]);
                if (rows == null || rows <= 0) { System.out.println("rows must be a positive integer."); return; }
                Long seed = (args.length >= 3) ? tryParseLong(args[2]) : null;
                int[] rnd = RaggedArrays.randomWidths(rows, seed);
                runOnce(rnd);
                break;

            default:
                System.out.println("Unknown command: " + cmd);
                help();
        }
    }

    private static void runOnce(int[] widths) {
        Object[][] grid = RaggedArrays.createFilled(widths);
        RaggedArrays.printGrid(grid);
        System.out.println();
        int[] w = RaggedArrays.rowWidths(grid);
        for (int i = 0; i < w.length; i++) {
            System.out.println("Row " + i + " width = " + w[i]);
        }
        System.out.println("Final value = " + RaggedArrays.finalValue(grid));
    }

    private static int[] parseWidths(String[] ws) {
        int[] out = new int[ws.length];
        for (int i = 0; i < ws.length; i++) {
            Integer v = tryParseInt(ws[i]);
            if (v == null || v <= 0) throw new IllegalArgumentException("Invalid width: " + ws[i]);
            out[i] = v;
        }
        return out;
    }

    private static Integer tryParseInt(String s) { try { return Integer.valueOf(s); } catch (Exception e) { return null; } }
    private static Long tryParseLong(String s) { try { return Long.valueOf(s); } catch (Exception e) { return null; } }

    private static void help() {
        System.out.println("Usage: java -cp bin src.App <command> [args]");
        System.out.println("Commands:");
        System.out.println("  fill <w1> <w2> ...      -> create ragged rows of given widths and fill 1..N");
        System.out.println("  auto <rows> [seed]      -> generate random widths (2..6) and fill 1..N");
    }
}
