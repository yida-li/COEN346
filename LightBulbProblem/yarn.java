
public class yarn { // because a Yarn is composed of multiple threads

    private static int threadCounter = 0;

    public void findDefective(int arr[]) throws InterruptedException {

        findDefective(arr, 0, arr.length - 1); // Calls the function findDefective with an addition of the 1st index and
                                               // the last index
        Thread.sleep(2000); // Main thread sleeps for 2000 ms in order for all other thread to finish before
                            // printing out the total amount of thread
        System.out.println("The number of thread is " + threadCounter);
    }

    private void findDefective(int arr[], int l, int r) {

        // *********************************************************************************************************************

        int count = 0; // Checks if the subarray contain a defective lightbulb
        for (int i = l; i <= r; i++) // Increments counter if each find is a 1
        {
            if (arr[i] == 1)
                count++;
        }
        if (count == (r - l) + 1) // If the number of counter matchs the actual size of array
        { // Then it must be that there is no defective light bulb
            threadCounter++; // Increment the counter for numbers of thread
            return;
        }
        // *********************************************************************************************************************

        threadCounter++; // if there is a defective, increase thread count as well

        if (l < r) // if the subarray is more than 1
        {
            int m = (l + r) / 2; // declaring a pivot point m in the middle of array

            Thread t1 = new Thread(new Runnable() { // declare the first thread object
                @Override
                public void run() {
                    findDefective(arr, l, m); // left-side of recursive call
                }
            });

            Thread t2 = new Thread(new Runnable() { // declare a second thread object
                @Override
                public void run() {
                    findDefective(arr, m + 1, r); // right-side of recursive call
                }
            });

            try {
                t1.join(); // join the thread object 1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t2.join(); // join the thread object 2
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            t1.start();
            t2.start();
        }

        if (l == r) { // if the subarray is of size 1
            if (arr[l] == 0) { // if the element of array is '0', then it is declared as defective!
                System.out.println("Defective lightbulb found at position : " + (l + 1));

            }
        }

    }
}