/**
 * HashNode
 *
 * The Node to form HashTable
 *
 * @author Nhan Truong
 *
 * @version March 16, 2020
 *
 */
public class HashNode {
 public String word;
 public int frequency;
 
 public HashNode(String word, int frequency) {
  this.word = word;
  this.frequency = frequency;
 }
 
 public HashNode(String word) {
  this.word = word;
  this.frequency = 0;
 }
 
 public HashNode () {
  this.word = null;
  this.frequency = 0;
 }
 
 public String toString() {
  return "(" + this.word + ", " + this.frequency + ") "; 
 }
}
