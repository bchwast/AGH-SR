// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: primes.proto

package gen;

public interface PrimesResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:calculator.PrimesResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated int64 primes = 1;</code>
   * @return A list containing the primes.
   */
  java.util.List<java.lang.Long> getPrimesList();
  /**
   * <code>repeated int64 primes = 1;</code>
   * @return The count of primes.
   */
  int getPrimesCount();
  /**
   * <code>repeated int64 primes = 1;</code>
   * @param index The index of the element to return.
   * @return The primes at the given index.
   */
  long getPrimes(int index);
}