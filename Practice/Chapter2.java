package Practice;
public class Chapter2 {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] newArr = new int[arr1.length + 5];

        int insertPos = 7;
        int value = 20;
        for (int i = 0; i < arr1.length; i++) {
            newArr[i] = arr1[i];
        }
        newArr[insertPos] = value;
        for (int x = 0; x < newArr.length; x++) {
            System.out.print(newArr[x]);
        }
    }

}
