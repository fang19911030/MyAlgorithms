package MyAlgorithms;

/**
 * Created by fang on 5/19/17.
 */
public class TopKAmongNArray {
    public class HeapNode{
        public int value;
        public int arrNum;
        public int index;
        public HeapNode(int value, int arrNum, int index){
            this.value = value;
            this.arrNum = arrNum;
            this.index = index;
        }
    }

    public void printTopK(int[][]matrix, int topK){
        int heapSize = matrix.length;
        HeapNode[] heap = new HeapNode[heapSize];
        for(int i = 0; i!=heapSize;i++){
            int index = matrix[i].length-1;
            heap[i] = new HeapNode(matrix[i][index], i, index);
            heapInsert(heap, i);                                   //insert method build heap
        }
        System.out.println("TOP"+ topK+" : ");
        for(int i=0;i!=topK;i++){
            if(heapSize==0){
                break;
            }
            System.out.print(heap[0].value+" ");
            if(heap[0].index != 0){
                heap[0].value = matrix[heap[0].arrNum][--heap[0].index];
            }else{
                swap(heap,0,--heapSize);
            }
            heapify(heap,0,heapSize);
        }
    }

    public void heapInsert(HeapNode[] heap, int index){
        while(index != 0){
            int parent = (index-1)/2;
            if(heap[parent].value<heap[index].value){
                swap(heap, parent,index);
                index = parent;
            }else{
                break;
            }
        }
    }

    public void swap(HeapNode[] heap, int i, int j){
        HeapNode tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void heapify(HeapNode[] heap, int index, int heapSize){
        int largest = index;
        int left = 2*index +1;
        int right = 2*index+2;
        while(left<heapSize){
            if(heap[left].value>heap[index].value){
                largest = left;
            }
            if(right<heapSize && heap[right].value>heap[index].value){
                largest = right;
            }
            if(largest!=index){
                swap(heap,index,largest);
            }else{
                break;
            }
            index = largest;
            left = 2*index+1;
            right = 2*index+2;
        }

    }

    public static void main(String[] args){
        int[][] matrix={{1,3,5,7,9},{2,4,6,8,10},{11,99}};
        TopKAmongNArray solution = new TopKAmongNArray();
        solution.printTopK(matrix,4);
    }
}
