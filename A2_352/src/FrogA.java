import java.util.*;

public class FrogA
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
                    int e = findPath(S, H, 0);

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

In this file you are only allowed to change the body of the findPath function (i.e. the implementation) and
the signature of the function only by adding new input arguments as long as the space of the input
variables that you add are O(1).

For example you can add an int and a String input variable, but not a array of length n of int

If you change the signature of the findPath function you are allowed to minimally change also the line that calls it
in the main function in order for the program to compile and run

*/

        // findPath takes an array S to track the jumps taken, 
        // an array H of given energies used to jump to each rock, 
        // int currentRock to track the current rock position   
   public static int findPath(int[] S, int[] H, int currentRock) {
            int numRocks = H.length; // initializing the number of rocks
            
            // case where no input (0 rocks) is given
            if (numRocks == 0) {
            	return 0;
            }
            
            S[0] = 1;
            
            // base case: if we are at the last rock, no further jumps are needed.
            if (currentRock == numRocks - 1) {
                S[currentRock] = 1; // visited the last rock since that's the end point
                return 0;
            }
            
            // initializing the energy used for one jump and two jump scenarios
            int oneJumpEnergy = Integer.MAX_VALUE; 
            int twoJumpEnergy = Integer.MAX_VALUE;
            
            if (currentRock + 2 < numRocks) {
                int energy = Math.abs(H[currentRock] - H[currentRock + 2]);
                int twoJumpResult = energy + findPath(S, H, currentRock + 2);
                if (twoJumpResult < oneJumpEnergy) {
                    twoJumpEnergy = twoJumpResult;
                }
            }


            if (currentRock + 1 < numRocks) {
                int energy = Math.abs(H[currentRock] - H[currentRock + 1]);
                int oneJumpResult = energy + findPath(S, H, currentRock + 1);
                if (oneJumpResult < twoJumpEnergy) {
                    oneJumpEnergy = oneJumpResult;
                }
            }

            // marks the current jump as a part of the best/optimal path
            if (oneJumpEnergy <= twoJumpEnergy) {
                S[currentRock + 1] = 1;
                return oneJumpEnergy;
            } else {
                S[currentRock + 2] = 1;
                return twoJumpEnergy;
            }
        }
}