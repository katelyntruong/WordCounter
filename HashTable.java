import java.util.LinkedList;
import java.util.Iterator;
public class HashTable {
 
  public LinkedList<HashNode>[] table;
 
 public HashTable (int size) {
  this.table = new LinkedList[size];
 }
 
 //return the index slot in the table to put the word
 public int tableIndex (String word) {
  int index = word.hashCode() % table.length;
  if (index < 0) {
   index += table.length;
  }
  return index;
 }
 
 //return the position node of the word in the list
 public int nodeIndex (String word, int index) {
  if (table[index] == null) {
   table[index] = new LinkedList<>();
   return -1;
  }
  else {
   for (int i = 0; i < table[index].size(); i++) {
    if (table[index].get(i).word.equals(word)) {
     return i;
    }
   }
   return -1;
  }
 }
 
 //rehash the current table if load factor bigger than 0.75, add the words again to the new table
 public void rehash() {
  int sumItem = 0;
  HashTable newTable = new HashTable(this.table.length*2);
  HashTable oldTable = new HashTable(this.table.length);
  for (int i = 0; i < table.length; i ++) {
    if (table[i] != null) {
   sumItem += table[i].size();
    }
  }
  if (sumItem/table.length > 0.75) {
    oldTable.table = this.table;
    this.table = newTable.table;
    
   for(int i = 0; i < oldTable.table.length; i++) {
             if(oldTable.table[i] != null) {
                 Iterator<HashNode> iter = oldTable.table[i].iterator();
                 while(iter.hasNext()) {
                     HashNode node = iter.next();
                     newTable.addNode(node.word,node.frequency);
                 }
             }
         }
     }
 }
 
 //add the word to the table or increase its frequency
 public void addNode(String word, int frequency) {
  int index = tableIndex(word);
  int nodeNum = nodeIndex(word, index);
  if (nodeNum != -1) {
   table[index].get(nodeNum).frequency ++;
  }
  else {
   table[index].add(new HashNode(word,frequency));
  }
  this.rehash();
 }
 
 //print out the words and their associated occurences
 public String toString() {
  StringBuilder s = new StringBuilder();
        for (LinkedList<HashNode> llist : this.table) {
            if (llist != null) {
                Iterator<HashNode> iter = llist.iterator();
                while(iter.hasNext()) {
                    s.append(iter.next());
                }
            }
        }
        return s.toString();
 }
 
 //print out additional information
 public String other() {
  int sumItem = 0;
  int nonEmpty = 0;
  int emptySlot = 0;
  for (int i = 0; i < this.table.length; i ++) {
    if (table[i] != null) {
      sumItem += table[i].size();
      nonEmpty ++;
   }
    else {
     emptySlot += 1; 
    }
  }
  double loadFactor = (double) sumItem/this.table.length;
  double aveColl = (double) sumItem/nonEmpty;
  return this.table.length + "\n" + emptySlot + "\n" + loadFactor + "\n" + aveColl;
 }
 
}

