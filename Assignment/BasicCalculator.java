/** Basic Calculator provide simple aritmetic operation:
 * Add, Deduct, Multiply, Devide, Modulo
 * The output will return as Total
 */
public class BasicCalculator {
    /** Initate Total Varieble that will store the output */
        int total = 0;

        /* get total */
        public int getTotal() {
            return  total;
        }

        /* add x to total then return total  */
        public int add(int x) {
            total += x;
            return total;
        }
        
        /* deduct x to total then return total  */
        public int deduct(int x) {
            total -= x;
            return total;
        }

        /* multiple the total with x then return total  */
        public int multiply(int x) {
            total *= x;
            return total;
        }

        /* devide the total with x then return total  */
        public int divide(int x) {
            /* check condition whether the number is not 0 */
            if (x != 0) {
                total /= x;
            }
            /* if the number is 0 it will print out invalid number */
            else {
                System.out.println("Invalid Number Try again!");
            }
            return total;
        }
        
        /**
        * Computes total modulo x and returns the updated total.
        * If x is 0, prints an error message and leaves total unchanged.
         */
        public int modulo(int x) {
            if (x != 0) {
                total %= x;
            }
            else {
                System.out.println("Invalid Number Try again!");
            }
            return total;
        }

        /* Reset total to 0 */
        public void reset() {
            total = 0;
        }
    }
