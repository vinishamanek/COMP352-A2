import java.util.*;

public class FrogB
{
        // read two integers separated by space and output their product
        public static void main(String[] args)
        {

                Scanner scan = new Scanner(System.in);

                do {

                    // Reading the input
                    int N = scan.nextInt();

                    int[] H = new int[N];
                    int[] S = new int [N];

                    for(int i=0;i<N;++i){
                        H[i] = scan.nextInt();
                    }


                    // Calling the function that solves the problem
                    FrogB F = new FrogB();
                    int e = F.findPath(S, H);

                    // writing the output
                    System.out.println(e);
                    for(int i=0;i<N;++i){
                        if(i>0)
                            System.out.print(" ");
                        System.out.print(S[i]);
                    }
                    System.out.println();


                }while(!scan.hasNext());
                scan.close();

    }


/*
Need to implement your solution in the findPath function as specified in the problem specifications.

In this file you are allowed to:

* change the body of the findPath function (i.e. the implementation) the signature of the function
* change the signature of the findPath function in any way you want
* You are allowed to minimally change the main function - only the line where 
	the function call to findPath is done in order for the program to compile
* You can add any variable to the class of any size, but not new methods.


*/

        public int findPath(int[] S, int[] H) {
            int numRocks = H.length;
            int[] energy = new int[numRocks]; // stores minimum energy used to jump to each rock
            int[] prevJump = new int[numRocks]; // stores index of last rock to jump from

            // initializing
            energy[0] = 0;
            prevJump[0] = -1;

            for (int i = 1; i < numRocks; i++) {
                energy[i] = Integer.MAX_VALUE; 
                int bestJump = -1; // keeps track of optimal jump (1 or 2)
                
                
                for (int j = 1; j <= 2 && i - j >= 0; j++) { // considered both one jump and two jumps
                    int used = energy[i - j] + Math.abs(H[i] - H[i - j]); // calculating the energy used
                    
                    // update arrays when used energy is lower or equal
                    if (used < energy[i] || (used == energy[i] && j == 2)) { 
                        energy[i] = used;
                        prevJump[i] = i - j;
                        bestJump = j;
                    }
                }
                // setting the best jump in S
                if (bestJump == 2) {
                    S[i] = 1;
                    S[i - 1] = 0;
                }
            }

            // getting the optimal path by going backwards
            int currentRock = numRocks - 1;
            while (currentRock >= 0) {
                S[currentRock] = 1;
                int nextRock = prevJump[currentRock];
                if (nextRock >= 0) {
                    S[nextRock] = 0;
                }
                currentRock = nextRock;
            }

            return energy[numRocks - 1];
        }

}
