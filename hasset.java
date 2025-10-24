import java.util.ArrayList;
import java.util.List;

class MyHashSet {
    List<Integer> hashTable;
    int N=10^6;
    int init=-1;
    public MyHashSet() {
        hashTable = new ArrayList();
        for(int i=0;i<=N; i++){
            hashTable.add(init);
        }

    }

    public void add(int key) {
        hashTable.add(key, key);
    }

    public void remove(int key) {
        hashTable.remove(key);
        hashTable.add(key, init);
    }

    public boolean contains(int key) {
        if(hashTable.get(key)!=-1){
            return true;
        }else{
            return false;
        }
    }
}