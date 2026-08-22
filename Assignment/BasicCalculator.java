public class BasicCalculator {
        int total = 0;

        public int getTotal() {
            return  total;
        }

        public int add(int x) {
            total += x;
            return total;
        }

        public int deduct(int x) {
            total -= x;
            return total;
        }

        public int multiply(int x) {
            total *= x;
            return total;
        }

        public int divide(int x) {
            total /= x;
            return total;
        }

        public int modulo(int x) {
            int remainder = total % x;
            return remainder;
        }

        public void reset() {
            total = 0;
        }


        public static void main(String[] args) {
            BasicCalculator calc = new BasicCalculator();

            System.out.println("Initial total:  " + calc.getTotal());

            


        }
    }
    
