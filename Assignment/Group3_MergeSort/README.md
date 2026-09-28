# Group 3: Merge Sort Algorithm

Group project comparing Merge Sort against Bubble Sort, Selection Sort, and Insertion Sort
on datasets of 5, 1,000, and 1,000,000 elements.

## Project Structure

```
Group3_MergeSort/
├── src/
│   ├── Main.java
|   |
|   ├── output/
|   |   ├── sample_5.txt
│   │   ├── sample_1000.txt
│   │   └── sample_1000_000.txt
│   ├── sorts/
│   │   ├── BubbleSort.java
│   │   ├── SelectionSort.java
│   │   ├── InsertionSort.java
│   │   └── MergeSort.java
│   ├── utils/
│   |   ├── SortedResult.java
|   |   └── DataSetGenerator.java
|   |
|   ├── report/
|   |   ├── Report.docx
│   |
|   └── slide/
|       ├── presentation.pptx
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or newer installed
- Any code editor with an integrated terminal (VS Code, IntelliJ, etc.)

## How to Compile and Run

1. Extract the submitted `.zip` file to a folder on your computer.
2. Open the extracted folder in your code editor.
3. Open a terminal inside the code editor.
4. Navigate into the `src` folder:
   ```
   cd src
   ```
5. Compile all source files:
   ```
   javac *.java
   ```
   This compiles `Main.java` and automatically compiles its dependencies in the
   `sorts` and `utils` subfolders as well.
6. Run the program:
   ```
   java Main
   ```

That's all so no additional setup or external libraries are required.

## What the Program Does

The program runs all four sorting algorithms (Bubble, Selection, Insertion, Merge)
on three dataset sizes:

| Label  | Size          |
|--------|---------------|
| small  | 5 elements    |
| medium | 1,000 elements|
| large  | 1,000,000 elements |

For each dataset size, after it finish it writes the to a text file
to the `output` directory:

- `sample_5.txt`
- `sample_1000.txt`
- `sample_1000_000.txt`

Each file contains, all four algorithms: number of comparisons, number of
swaps/movements, runtime in nanoseconds, and the fully sorted array.

## Notes

- The **large (1,000,000-element)** run can take a noticeably long time for
  Bubble Sort, Selection Sort, and Insertion Sort (their O(n²) complexity makes
  them much slower at this scale), while Merge Sort finishes quickly. This is
  expected and is part of the performance comparison so please allow the program
  time to finish rather than interrupting it.
- The same random seed is used to generate each dataset, so all four algorithms
  sort identical data at each size, making the comparison fair. (feel free to change the seed)
- `sample_1000_000.txt` will be a fairly large file (several MB) since it includes
  the full 1,000,000-element sorted array for each algorithm.
