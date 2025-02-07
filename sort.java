import java.util.*;

public class Sort2D{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of columns:");
        int col = sc.nextInt();
        
        int[][] M = new int[row][col];

        System.out.println("Enter elements:");
        for (int i = 0; i < row; i++) 
            for (int j = 0; j < col; j++) 
                M[i][j] = sc.nextInt();
                
        print2D(M);
        
        System.out.println("\n----\n");
        
        System.out.println("Random Matrix:");
        int[][] A = new int[4][5];
        randomize(A);
        print2D(A);
        System.out.println("Sorted Matrix (with Selection Sort):");
        selSort2D(A);
        print2D(A);
        
        System.out.println("\n----\n");
        
        System.out.println("Random Matrix:");
        randomize(A);
        print2D(A);
        System.out.println("Sorted Matrix (with Bubble Sort):");
        bubbleSort2D(A);
        print2D(A);
        
        System.out.println("\n----\n");
        
        System.out.println("Random Matrix:");
        randomize(A);
        print2D(A);
        System.out.println("Sorted Matrix (Row-Wise)");
        rowSort(A);
        print2D(A);
        
        System.out.println("\n----\n");
        
        System.out.println("Sample Matrix (3x3)");
        int[][] B = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        print2D(B);
        saddlePoint(B);
        
        System.out.println("\n----\n");
        
        System.out.println("Reversed Spiral Matrix (5x4)");
        int[][] Spiral = new int[5][4];
        revSpiral(Spiral, 5 * 4, 0, 0, Spiral[0].length - 1, Spiral.length - 1);
        print2D(Spiral);
        
        System.out.println("\n----\n");
        
        System.out.println("Spiral Matrix (3x3)");
        Spiral = new int[3][3];
        spiral(Spiral, 1, 0, 0, Spiral[0].length - 1, Spiral.length - 1, 3 * 3);
        print2D(Spiral);
        
        System.out.println("\n----\n");
        
        int n = 5;
        int[][] magic = magicSquare(n);
        System.out.println("Magic Square " + "(" + n + "x" + n + ")");
        print2D(magic);
    }
    
    public static void print2D(int[][] M){
        for (int[] arr: M){
            for (int elem : arr) System.out.print(elem + "\t");
            System.out.println();
        }
    }
    
    // UTILITY ONLY 
    public static void randomize(int[][] M){
        Random rand = new Random();

        for (int i = 0; i < M.length; i++) 
            for (int j = 0; j < M[0].length; j++) 
                M[i][j] = rand.nextInt(M.length * M[0].length);
    }

    public static void selSort2D(int[][] M) {
        int row = M.length;
        int col = M[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int mR = i, mC = j;
                for (int k = i; k < row; k++) 
                    for (int l = (k == i ? j : 0); l < col; l++) 
                        if (M[k][l] < M[mR][mC]) {
                            mR = k;
                            mC = l;
                        }

                int temp = M[i][j];
                M[i][j] = M[mR][mC];
                M[mR][mC] = temp;
            }
        }
    }

    public static void bubbleSort2D(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        boolean sw;
        do {
            sw = false;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int nR = (c == cols - 1) ? r + 1 : r;
                    int nC = (c == cols - 1) ? 0 : c + 1;

                    if (nR < rows && A[r][c] > A[nR][nC]) {
                        int temp = A[nR][nC];
                        A[nR][nC] = A[r][c];
                        A[r][c] = temp;
                        sw = true;
                    }
                }
            }
        } while (sw);
    }
    
    public static void rowSort(int[][] M){
        for (int i = 0; i < M.length; i++)
            Arrays.sort(M[i]);
    }

    public static void revSpiral(int[][] M, int i, int t, int l, int r, int b){
        if (i <= 0) return;

        for (int j = l; j <= r && i >= 0; j++) M[t][j] = i--;
        t++;
        for (int j = t; j <= b && i >= 0; j++) M[j][r] = i--;
        r--;
        for (int j = r; j >= l && i >= 0; j--) M[b][j] = i--;
        b--;
        for (int j = b; j >= t && i >= 0; j--) M[j][l] = i--;
        l++;
        
        revSpiral(M, i, t, l, r, b);
    }

    public static void spiral(int[][] M, int i, int t, int l, int r, int b, int n){
        if (i > n) return;

        for (int j = l; j <= r && i <= n; j++) M[t][j] = i++;
        t++;
        for (int j = t; j <= b && i <= n; j++) M[j][r] = i++;
        r--;
        for (int j = r; j >= l && i <= n; j--) M[b][j] = i++;
        b--;
        for (int j = b; j >= t && i <= n; j--) M[j][l] = i++;
        l++;
        
        spiral(M, i, t, l, r, b, n);
    }

    public static void saddlePoint(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;
        for (int i = 0; i < rows; i++) {
            int minRow = A[i][0], colIndex = 0;

            for (int j = 1; j < cols; j++) {
                if (A[i][j] < minRow) {
                    minRow = Math.min(minRow, A[i][j]);
                    colIndex = j;
                }
            }

            boolean isSP = true;
            for (int k = 0; k < rows; k++) {
                if (A[k][colIndex] > minRow) {
                    isSP = false;
                    break;
                }
            }
            if (isSP) 
                System.out.println("Saddle Point @ (" + i + ", " + colIndex + "); Value: " + minRow);
        }
    }

    public static int[][] magicSquare(int n) {
        if (n % 2 == 0) 
            throw new IllegalArgumentException("Only odd values of n are supported.");
        
        int[][] M = new int[n][n];
        int num = 1, row = 0, col = n / 2;

        while (num <= n * n) {
            M[row][col] = num++;
            int nRow = (row - 1 + n) % n;
            int nCol = (col + 1) % n;

            if (M[nRow][nCol] != 0) 
                row = (row + 1) % n;
            else {
                row = nRow;
                col = nCol;
            }
        }
        return M;
    }
}

