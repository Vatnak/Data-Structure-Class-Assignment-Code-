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
            if (x != 0) {
                total /= x;
            }
            else {
                System.out.println("Invalid Number Try again!");
            }
            return total;
        }

        public int modulo(int x) {
            total %= x;
            return total;
        }

        public void reset() {
            total = 0;
        }
    }
